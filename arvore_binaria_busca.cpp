/* 
	Implementação dos algoritmos de BST do livro "Introduction to algorithms" (CLRS)
	BST: arvore binaria de busca
	Propriedade: qualquer no y na subarvore esquerda de x é tal que y.chave < x.chave
				 qualquer no y na subarvore direita de x é tal que y.chave >= x.chave

	Autor: Cauim de Souza Lima
	Email: cauimsouza@gmail.com
	Data: 27/12/2015
*/

#include <bits/stdc++.h>
using namespace std;

// Tipos utilizados
struct no{
	no *esquerda,	// ponteiro para o filho a esquerda
		*direita,	// ponteiro para o filho a direita	
		*p;			// ponteiro para o pai
	int chave;		// valor armazenado pelo no
};
typedef no *BST;
BST Raiz; // raiz da BST declarada como variavel global


/* ================================ */

// Rotinas utilizadas
void Inorder_Tree_Walk(BST T);
no* Tree_Search(BST T, int k);
no* Tree_Minimum(BST T);
no *Tree_Maximum(BST T);
no *Tree_Successor(no *x);
no *Tree_Predecessor(no *x);
void Tree_Insert(int k);
void Transplant(no *u, no *v);
void Tree_Delete(no *z);
no* CriaNo(int x);

/* ================================ */ 

int main(){	

	no *x;

	for (int i = 0; i < 10; ++i)
	{
		Tree_Insert(i);
	}

	/* ================================  Menor e maior elemento ================================ */
	x = Tree_Minimum(Raiz); 
	printf("Menor elemento: %d\n", x->chave); // deve imprimir 0
	x = Tree_Maximum(Raiz);
	printf("Maior elemento: %d\n", x->chave); // deve imprimir 9


	/* ================================ Checando se elemento pertence a BST  ================================ */ 
	x = Tree_Search(Raiz, 8); 
	if(x != NULL) // 8 existe na BST
		printf("8 esta na BST:)\n");
	else
		printf("8 nao esta na BST:(\n");
	
	/* ================================ Deletando elemento pertencente a BST  ================================ */
	x = Tree_Search(Raiz, 8);  
	Tree_Delete(x);	// deletando o nó com chave igual a 8

	x = Tree_Search(Raiz, 8);
	if(x != NULL) // 8 existe na BST
		printf("8 esta na BST:)\n");
	else
		printf("8 nao esta na BST:(\n");

	/* ================================ Inserindo elemento a BST ================================ */ 
	Tree_Insert(42); // numero do universo

	/* ================================ Encontrando o sucessor  ================================ */
	x = Tree_Search(Raiz, 9);  
	x = Tree_Successor(x); // sucessor de 9 = 42
	printf("Sucessor de 9 eh %d\n", x->chave);

	return 0;
}

/*
	Imprime os nos da BST enraizada em T tem ordem crescente (percurso de arvore em in-ordem)
*/
void Inorder_Tree_Walk(BST T){
	if(T != NULL){
		Inorder_Tree_Walk(T->esquerda);
		printf("%d ", T->chave);
		Inorder_Tree_Walk(T->direita);
	}
}

/*
	Retorna um ponteiro para o nó da BST enraizada em T que tem chave k, se existir. Retorna NULL caso contrário
*/
no* Tree_Search(BST T, int k){// bem direto, a partir da propriedade de BST
	while(T != NULL && T->chave != k){
		if(T->chave > k)	T = T->esquerda;
		else	T = T->direita;
	}
	return T;
}

/*
	Retorna um ponteiro para o no com menor chave na BST enraizada em T
*/
no* Tree_Minimum(BST T){ // o nó com menor chave é o nó na extremidade esquerda da árvore
	while(T->esquerda != NULL)
		T = T->esquerda;
	return T;
}

/*
	Retorna um ponteiro para o no com maior chave na BST enraizada em T
*/
no *Tree_Maximum(BST T){ // o nó com maior chave é o nó na extremidade direita da árvore
	while(T->direita != NULL)
		T = T->direita;
	return T;
}

/*
	Retorna um ponteiro para o no sucessor do no apontado por x
*/
no *Tree_Successor(no *x){ // o sucessor de um nó x é o nó y cuja chave sucede a chave de x em um percurso in-ordem
	if(x->direita != NULL)
		return Tree_Minimum(x->direita);
	no *y = x->p;
	while(y != NULL && y->direita == x){
		x = y;
		y = y->p;
	}
	return y;
}

/*
	Retorna um ponteiro para o no predecessor do no apontado por x
*/
no *Tree_Predecessor(no *x){// o sucessor de um nó x é o nó y cuja chave precede a chave de x em um percurso in-ordem
	if(x->esquerda != NULL)
		return Tree_Maximum(x->esquerda);
	no *y = x->p;
	while(y != NULL && y->esquerda == x){
		x = y;
		y = y->p;
	}
	return y;
}

/*
	Insere um no chave igual a k na BST enraizada em Raiz
*/
void Tree_Insert(int k){
	no *y = NULL;
	no *x = Raiz;
	no *z = CriaNo(k);
	while(x != NULL){
		y = x;
		if(z->chave < x->chave)
			x = x->esquerda;
		else
			x = x->direita;
	}
	z->p = y;
	if(y == NULL)
		Raiz = z;
	else if(z->chave < y->chave)
		y->esquerda = z;
	else
		y->direita = z;
}

/*
	Rotina auxiliar para a eliminação na BST
	Substitui a subarvore enraizada em u pela subarvore enraizada em v
*/
void Transplant(no *u, no *v){
	if(u->p == NULL) // u e a raiz da árvore
		Raiz = v;
	else if(u->p->esquerda == u)
		u->p->esquerda = v;
	else
		u->p->direita = v;
	if(v != NULL)
		v->p = u->p;
}

/**
 * Deleta o nó apontado por z da BST
 */
void Tree_Delete(no *z){
	if(z->esquerda == NULL)
		Transplant(z, z->direita);
	else if(z->direita == NULL)
		Transplant(z, z->esquerda);
	else{
		no *y = Tree_Minimum(z->direita);
		if(y->p != z){
			Transplant(y, y->direita);
			y->direita = z->direita;
			y->direita->p = y;
		}
		Transplant(z, y);
		y->esquerda = z->esquerda;
		y->esquerda->p = y;
	}
}

/**
 * Aloca um novo nó com chave x e com ponteiros (pai, esquerdo e direito) para NULL
 * @param	x	chave do novo nó
 * @return	um ponteiro para o nó
 */
no* CriaNo(int x){
	no *z = new no;
	z->p = z->esquerda = z->direita = NULL;
	z->chave = x;
}


