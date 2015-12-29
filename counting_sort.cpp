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

	// C[i] agora contém o número de elementos menores ou iguais a i
	for(int i = 1; i <= k; i++)
		C[i] = C[i] + C[i-1];

	//A variavel i começa em n-1 para que a ordenação sejá estável, uma boa característica do algoritmo
	for(int i = n-1; i >= 0; i--){
		B[C[A[i]] - 1] = A[i];
		C[A[i]]--;
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
