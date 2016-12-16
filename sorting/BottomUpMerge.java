/*
	Created in: December 16, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
	Description: BottomUpMergesort implementation
*/

public class BottomUpMerge{
	public static void sort(Comparable[] a){
		int N = a.length;
		Comparable[] aux = new Comparable[N];
		for(int sz = 1; sz < N; sz += sz)
			for(int lo = 0; lo < N-sz; lo += sz+sz)
				merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
	}
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		for(int k = lo; k <= hi; k++) aux[k] = a[k];

		int i = lo;
		int j = mid + 1;
		for(int k = lo; k <= hi; k++){
			if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else if(less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}

		assert isSorted(a, lo, hi);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi){
		for(int i = lo; i < hi; i++)
			if(less(a[i+1], a[i])) return false;
		return true;
	}

	private static boolean less(Comparable u, Comparable v){
		return u.compareTo(v) < 0;
	}
}