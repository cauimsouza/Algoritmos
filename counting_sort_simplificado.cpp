#include <bits/stdc++.h>
using namespace std;

// Recebe um vetor A[0...n-1] de n inteiros no intervalo [0...k] e o ordenada no vetor B[0...n-1] 
void CountingSort(int *A, int *B, int n, int k){
	int *C = new int[k+1];

	for(int i = 0; i <= k; i++)
		C[i] = 0;

	// C[i] agora contém o número de elementos iguais a i
	for(int i = 0; i < n; i++)
		C[A[i]]++;

	// A grande diferença dessa versão é que ela não é estável
	int j = 0;
	for(int i = 0; i <= k; i++){
		while(C[i]--)
			B[j++] = i;
	}

	delete[]C;
}

int main(){
	
	int n, k;
	int *A, *B;

	printf("Digite o numero de elementos do vetor a ser ordenado: ");
	scanf("%d", &n);
	printf("Digite o maior numero que um elemento do vetor pode assumir: ");
	scanf("%d", &k);

	A = new int[n];
	B = new int[n];

	printf("Digite os %d numero do vetor a ser ordenado:\n", n);
	for(int i = 0; i < n; i++)
		scanf("%d", &A[i]);

	CountingSort(A, B, n, k);

	printf("\nVetor ordenado:\n");
	for (int i = 0; i < n; ++i)
		printf("%d ", B[i]);

	printf("\n");
}
