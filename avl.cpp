/**
 * Implementacao de arvore AVL em C/C++
 * Algoritmos baseados no livro "Introduction to algorithms" - third edition - (CLRS)
 * e no site http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * 
 * autor: Cauim de Souza Lima
 * email: cauimsouza@gmail.com
 * data: 28/12/2015
 */
#include <bits/stdc++.h>
using namespace std;

/* ============================= */

/* Definição de tipos e estruturas */
struct no{
	no *esquerda, // ponteiro para o filho esquerdo
		*direita,	// ponteiro para o filho direito
		*p;	// ponteiro para o pai
	int chave;	// valor armazenado no nó, pode ser alterado para qualquer tipo (char, double, string, etc.)
	int altura; // altura do nó
};

struct AVL{
	no *raiz;	// raiz da arvore AVL
};

/* ============================= */

/* Declaração das rotinas utilizadas */
// Definição está abaixo da 'main'

int Altura(no *u);
int BalanceFactor(no *n);
void RightRotation(AVL *T, no *u); 
void LeftRotation(AVL *T, no *u);
void AVL_Insertion(AVL *T, int k);
void AVL_Insertion_Fixup(AVL *T, int k, no *z);
no* CreateNode(int k);
no* Tree_Minimum(no *u);
void Transplant(AVL *T, no *u, no *v);
void DeleteNode(AVL *T, int x);
void AVL_Deletion_Fixup(AVL *T, no *z);
void InOrder(no *u);
void PreOrder(no *u);
void PostOrder(no *u);
no *TreeSearch(no *u, int k);

/* ============================= */

/* Main */
int main(int argc, char const *argv[])
{
	AVL tree;
	tree.raiz = NULL;
	AVL_Insertion(&tree, 9);
	AVL_Insertion(&tree, 5);
	AVL_Insertion(&tree, 10);
	AVL_Insertion(&tree, 0);
	AVL_Insertion(&tree, 6);
	AVL_Insertion(&tree, 11);
	AVL_Insertion(&tree, -1);
	AVL_Insertion(&tree, 1);
	AVL_Insertion(&tree, 2);

	printf("O percurso em pre-ordem da arvore AVL construida eh\n");
	PreOrder(tree.raiz);
	printf("\n\n");

	printf("O percurso em in-ordem da arvore AVL construida eh\n");
	InOrder(tree.raiz);
	printf("\n\n");

	printf("O percurso em pos-ordem da arvore AVL construida eh\n");
	PostOrder(tree.raiz);
	printf("\n\n");

	printf("A altura da arvore AVL construida eh %d\n\n", Altura(tree.raiz));

	DeleteNode(&tree, 9);
	printf("O percurso em pre-ordem da arvore AVL construida eh\n");
	PreOrder(tree.raiz);
	printf("\n\n");

	return 0;
}

/* ============================= */

/* Definição das rotinas utilizadas */

/**
 * Retorna a altura do nó, caso não seja nulo. Retorna 0 caso contrário.
 * Vamos usar a convenção de que árvores triviais têm altura 1.
 * @param  u ponteiro para o nó que se deseja conhecer a altura
 * @return   altura do nó, 0 caso caso ele não exista(NULL)
 */
int Altura(no *u){
	if(u == NULL)	return 0;
	return u->altura;
}

/**
 * Retorna o fator de balanceamento(altura da subarvore esquerda - altura da suabrvore direita) de um nó.
 * Retorn 0 caso o nó seja nulo(NULL)
 * @param  n ponteiro para o nó
 * @return   fator de balanceamento do nó apontado por n
 */
int BalanceFactor(no *n){
	if(n == NULL)
		return 0;
	return Altura(n->esquerda) - Altura(n->direita);
}

/**
 * Executa uma rotação à direita no nó apontado por u, seu filho esquerdo v assume seu lugar.
 * O filho direito de v passa a ser filho esquerdo de u.
 * u passa a ser filho direito de v
 * A função também ajusta as alturas resultantes de cada nó envolvido
 * @param u ponteiro para o nó que sofrerá a rotação
 * @param T ponteiro para árvore AVL
 */
void RightRotation(AVL *T, no *u){
	no *v = u->esquerda;
	Transplant(T, u, v);
	u->esquerda = v->direita;
	if(v->direita != NULL)
		v->direita->p = u;
	u->p = v;
	v->direita = u;

	// ajuste das alturas
	u->altura = max(Altura(u->esquerda), Altura(u->direita)) + 1;
	v->altura = max(Altura(u), Altura(v->esquerda)) + 1;
}

/**
 * Executa uma rotação à esquerda no nó apontado por u, seu filho direito v assume seu lugar.
 * O filho esquerdo de v passa a ser filho direito de u.
 * u passa a ser filho esquerdo de v
 * A função também ajusta as alturas resultantes de cada nó envolvido
 * @param u ponteiro para o nó que sofrerá a rotação
 * @param T ponteiro para árvore AVL
 */
void LeftRotation(AVL *T, no *u){
	no *v = u->direita;
	Transplant(T, u, v);
	u->direita = v->esquerda;
	if(v->esquerda)
		v->esquerda->p = u;
	v->esquerda = u;
	u->p = v;

	// ajuste das alturas
	u->altura = max(Altura(u->esquerda), Altura(u->direita)) + 1;
	v->altura = max(Altura(u), Altura(v->direita)) + 1;
}

/**
 * Insere um novo nó com chave k na árvore AVL T, se nenhum nó nessa árvore tiver chave igual a k.
 * Após a inserção, há a chamada a AVL_Insertion_Fixup para corrigir o balanceamento da árvore AVL
 * @param T ponteiro para a árvore AVL
 * @param k valor da chave do novo nó a ser inserido na árvore
 */
void AVL_Insertion(AVL *T, int k){
	if(TreeSearch(T->raiz, k) == NULL){ // verifica se algum nó já contém tal chave
		no *z = CreateNode(k);
		no *y = NULL;
		no *x = T->raiz;

		while(x != NULL){
			y = x;
			if(k < x->chave)
				x = x->esquerda;
			else
				x = x->direita;
		}
		z->p = y;
		if(y == NULL) // a árvore era inicialmente vazia
			T->raiz = z;
		else if(k < y->chave)
			y->esquerda = z;
		else
			y->direita = z;
		AVL_Insertion_Fixup(T, k, z->p);
	}
}

/**
 * A rotina percorre o caminho simples do nó inserido à raíz da árvore atualizando as alturas e, para cada ancestral,
 * verifica se esse ancestral está balanceado. Se não estiver, realiza as rotação adequadas.
 * Note que o rebalanceamento de um único nó desbalanceado já garante o balanceamento de toda a árvore.
 * No entanto, o processo se repete até a raíz da árvore para atualizar as alturas de cada nó.
 * @param T ponteiro para a árvore AVL
 * @param k valor da chave do novo nó inserido
 * @param z ponteiro para o nó ancestral
 */
void AVL_Insertion_Fixup(AVL *T, int k, no *z){
	if(z == NULL)
		return;
	/* Atualizando a altura do ancentral do nó inserido */
	z->altura = max(Altura(z->esquerda), Altura(z->direita))+1;

	/* Obtendo o novo fator de balanceamento para verificar se o ancestral está desbalanceado */
	int fator_balanceamento = BalanceFactor(z);

	/* Caso haja desbalanceamento, existem 4 casos possíveis */
	
	//Rotação simples à direita
	if(fator_balanceamento > 1 && k < z->esquerda->chave)
		RightRotation(T, z);
	//Rotação dupla esquerda/direita
	else if(fator_balanceamento > 1 && k > z->esquerda->chave){
		LeftRotation(T, z->esquerda);
		RightRotation(T, z);
	}
	//Rotação simples à esquerda
	else if(fator_balanceamento < -1 && k > z->direita->chave)
		LeftRotation(T, z);
	//Rotação dupla direita/esquerda
	else if(fator_balanceamento< -1 && k < z->direita->chave){
		RightRotation(T, z->direita);
		LeftRotation(T, z);
	}

	/* Avança para o próximo ancestral */
	AVL_Insertion_Fixup(T, k, z->p);
}

/**
 * Cria um novo nó com chave igual a k e retorna um ponteiro para o nó
 * @param  k valor da chave do novo nó
 * @return   ponteiro para o nó criado
 */
no* CreateNode(int k){
	no *A = new no;
	A->p = A->esquerda = A->direita = NULL;
	A->chave = k;
	A->altura = 1;
	return A;
}

/**
 * Retorna um ponteiro para o nó com menor chave na árvore
 * @param  u ponteiro para a raiz da aŕvore
 * @return   ponteiro para o nó com menor chave
 */
no* Tree_Minimum(no *u){
	while(u->esquerda != NULL)
		u = u->esquerda;
	return u;
}

/**
 * Função auxiliar para a deleção. Troca a subárvore enraizada em u pela subárvore enraizada em v 
 * @param T ponteiro para a árvore AVL onde ocorrerá a substituição
 * @param u raiz da subárvore que será substituída
 * @param v raiz da subárvore que substituirá
 */
void Transplant(AVL *T, no *u, no *v){
	if(u->p == NULL) // u é a raiz da árvore
		T->raiz = v;
	else if(u == u->p->esquerda) // u é filho esquerdo
		u->p->esquerda = v;
	else	// u é filho direito
		u->p->direita = v;
	if(v != NULL)	
		v->p = u->p;
}

/**
 * Deleta da arvore AVL apontada por T o nó com chave x, se houver.
 * @param T ponteiro para á árvore AVL
 * @param x valor da chave do nó que se deseja eliminar
 */
void DeleteNode(AVL *T, int x){
	no *z = TreeSearch(T->raiz, x); // encontra o nó cuja chave é x
	if(z == NULL)	// caso não exista tal nó, não há nada a fazer
		return; 

	no *y; // nó que substituirá z

	// trata do caso em que z em apenas o filho direito ou não tem nenhum nó
	if(z->esquerda == NULL){
		y = z->direita;
		Transplant(T, z, y);
		AVL_Deletion_Fixup(T, z->p);
		delete[]z;
	}
	// trata do caso em que z tem apenas o filho esquerdo
	else if(z->direita == NULL){
		y = z->esquerda;
		Transplant(T, z, y);
		AVL_Deletion_Fixup(T, z->p);
		delete[]z;
	}
	// trata do caso em que z tem dois filhos
	else{
		y = Tree_Minimum(z->direita); // encontra o sucessor de z
		int k = y->chave;
		if(y != z->direita){
			DeleteNode(T, k);
			z->chave = k;
		}
		else{
			z->chave = k;
			Transplant(T, y, y->direita);
			delete[]y;
			AVL_Deletion_Fixup(T, z);
		}
	}
}

/**
 * Corrige o balanceamento da árvore após a deleção de um nó.
 * Ao contrário da inserção, o rebalanceamento de um único nó não garante que toda a árvore esteja balanceada,
 * logo é necessário subir desde o nó onde ocorreu a deleção até a raiz, percorrendo todos os ancestrais.
 * @param T ponteiro para a árvore AVL onde ocorreu a deleção
 * @param z ponteiro para o ancestral que está sendo corrigido
 */
void AVL_Deletion_Fixup(AVL *T, no *z){
	if(z == NULL)
		return;

	// atualiza a altura do nó z
	z->altura = max(Altura(z->esquerda), Altura(z->direita))+1;

	// se o fator de balanceamento for maior que 1 ou menor que -1, deverá ser feita as rotações adequadas
	int balanceamento = BalanceFactor(z);

	//Rotação simples à direita
	if(balanceamento > 1 && BalanceFactor(z->esquerda) >= 0)
		RightRotation(T, z);

	//Rotação dupla esquerda/direita
	else if(balanceamento > 1){
		LeftRotation(T, z->esquerda);
		RightRotation(T, z);
	}

	// Rotação simples à esquerda
	else if(balanceamento < -1 && BalanceFactor(z->direita) <= 0)
		LeftRotation(T, z);

	// Rotação dupla direita/esquerda
	else if(balanceamento < -1){
		RightRotation(T, z->direita);
		LeftRotation(T, z);
	}

	// Corrige o próximo ancestral
	AVL_Deletion_Fixup(T, z->p);
}

/**
 * Percorre uma árvore binária balanceada (BST) em in-ordem, imprimindo suas chaves
 * @param u ponteiro para a raiz
 */		
void InOrder(no *u){
	if(u != NULL){
		InOrder(u->esquerda);
		printf("%d ", u->chave);
		InOrder(u->direita);
	}
}

/**
 * Percorre uma árvore binária balanceada (BST) em pré-ordem, imprimindo suas chaves
 * @param u ponteiro para a raiz
 */	
void PreOrder(no *u){
	if(u != NULL){
		printf("%d ", u->chave);
		PreOrder(u->esquerda);
		PreOrder(u->direita);
	}
}

/**
 * Percorre uma árvore binária balanceada (BST) em pós-ordem, imprimindo suas chaves
 * @param u ponteiro para a raiz
 */	
void PostOrder(no *u){
	if(u != NULL){
		PostOrder(u->esquerda);
		PostOrder(u->direita);
		printf("%d ", u->chave);
	}
}

/**
 * Retorna um ponteiro para o nó da árvore binária de busca cuja chave é igual a k (se houver).
 * Se não houver tal nó, a função retorna NULL.
 * @param  u ponteiro para a raiz da BST
 * @param  k valor da chave
 * @return   ponteiro para o nó cuja chave vale k
 */
no *TreeSearch(no *u, int k){
	while(u != NULL && u->chave != k){
		if(k < u->chave)
			u = u->esquerda;
		else
			u = u->direita;
	}
	return u;
}
