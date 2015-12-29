/*
 * Recebe um inteiro n seguido por n inteiros e devolve os n inteiros em ordem não-decrescente
 */
#include <stdio.h>
#include <stdlib.h>

#define INF 1000000000

void Merge(int *vetor, int p, int q, int r){
	int n1 = q - p + 1;
	int n2 = r - q;
	int *l1 = (int*)malloc((n1+2)*sizeof(int));
	int *l2 = (int*)malloc((n2+2)*sizeof(int));
	int i, j, k;

	for(i = 1; i <= n1; i++)
		l1[i] = vetor[p+i-1];
	l1[n1+1] = INF;
	for(i = 1; i <= n2; i++)
		l2[i] = vetor[q+i];
	l2[n2+1] = INF;

	i = j = 1;
	k = p;
	while(k <= r){
		if(l1[i] <= l2[j]){
			vetor[k++] = l1[i++];
		}
		else
			vetor[k++] = l2[j++];
	}
	free(l1);
	free(l2);
}

void Mergesort(int *vetor, int p, int r){
	if(p < r){
		int q = (p+r)/2;
		Mergesort(vetor, p, q);
		Mergesort(vetor, q+1, r);
		Merge(vetor, p, q, r);
	}
}

int main(){

	int n;	/* numero de elementos */
	int i;	/* variavel de iteracao */
	printf("MERGESORT\n\n");
	printf("Digite o numero de inteiros a serem ordenados em ordem crescente(0 para finalizar): ");
	while(scanf("%d", &n) != EOF){
		if(n == 0)
			break;
			
		int *vetor = (int *) malloc((n+1)*sizeof(int));
		for(i = 1; i <= n; i++)
			scanf("%d", &vetor[i]);

		Mergesort(vetor, 1 , n);

		for(i = 1; i <= n; i++)
			printf("%d ", vetor[i]);
		printf("\n\n");
		printf("Digite o numero de inteiros a serem ordenados em ordem crescente(0 para finalizar): ");
		free(vetor);
	}
	return 0;
}
