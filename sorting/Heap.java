/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
*/
public class Heap{
	public static void sort(Comparable[] a){
		buildHeap(a);
		for(int i = a.length - 1; i > 0; i--){
			exch(a, 0, i);
			maxHeapfy(a, i, 1);
		}
	}
	private static void buildHeap(Comparable[] a){
		int n = a.length;
		for(int i = n / 2; i > 0; i--) maxHeapfy(a, n, i);
	}
	private static void maxHeapfy(Comparable[] a, int length, int i){
		int left = 2 * i;
		int right = left + 1;
		int n = length;
		int max = i;
		if(left <= n){
			if(more(a[left - 1], a[max - 1])) max = left;
		}
		if(right <= n){
			if(more(a[right - 1], a[max - 1])) max = right;
		}
		if(max != i){
			exch(a, i - 1, max - 1);
			maxHeapfy(a, length, max);
		}
	}
	private static boolean more(Comparable u, Comparable v){
		return u.compareTo(v) > 0;
	}
	private static void exch(Comparable[] a, int i, int j){
		Comparable aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}
}