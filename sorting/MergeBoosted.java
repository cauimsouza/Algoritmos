/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
*/
public class MergeBoosted{
	private static final int CUTOFF = 7;
	public static void sort(Comparable[] a){
		Comparable[] aux = new Comparable[a.length];
		sortRange(a, aux, 0, a.length - 1);
	}
	private static void sortRange(Comparable[] a, Comparable[] aux, int left, int right){
			if(right <= left) return;
			// first improvement: use insertion sort if subarray is small
			if(right < left + CUTOFF){
				Insertion.sort(a, left, right);
				return;
			}
			
			int mid = left + (right - left) / 2;
			sortRange(a, aux, left, mid);
			sortRange(a, aux, mid + 1, right);
			// second improvement: subarray already sorted
			if(!less(a[mid + 1], a[mid])) return; 
			merge(a, aux, left, mid, right);
	}
	private static void merge(Comparable[] a, Comparable[] aux, int left, int mid, int right){
		assert isSorted(a, left, mid);
		assert isSorted(a, mid + 1, right);

		for(int k = left; k <= right; k++)
			aux[k] = a[k];

		int i = left;
		int j = mid + 1;
		for(int k = left; k <= right; k++){
			if(i > mid) a[k] = aux[j++];
			else if(j > right) a[k] = aux[i++];
			else if(less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}

		assert isSorted(a, left, right);
	}
	private static boolean less(Comparable u, Comparable v){
		return u.compareTo(v) < 0;
	}
	private static boolean isSorted(Comparable[] a, int left, int right){
		for(int i = left; i < right; i++)
			if(less(a[i+1], a[i])) return false;
		return true;
	}

	private static class Insertion{
		public static void sort(Comparable[] a, int left, int right){
			for(int i = left + 1; i <= right; i++){
				int j = i;
				while(j > left && less(a[j], a[j - 1])){
					exch(a, j, j - 1);
					j--;
				}
			}
		}
		public static void exch(Comparable[] a, int i, int j){
			Comparable aux = a[i];
			a[i] = a[j];
			a[j] = aux;
		}
	}
}