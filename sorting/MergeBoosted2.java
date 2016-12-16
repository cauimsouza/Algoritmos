/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
*/
public class MergeBoosted2{
	public static void sort(Comparable[] a){
		Comparable[] aux = new Comparable[a.length];
		for(int i = 0; i < a.length; i++) aux[i] = a[i];
		sortRange(a, aux, 0, a.length - 1);
	}
	private static void sortRange(Comparable[] a, Comparable[] aux, int left, int right){
			if(right <= left) return;
			int mid = left + (right - left) / 2;
			sortRange(aux, a, left, mid);
			sortRange(aux, a, mid + 1, right);
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
			if(i > mid) aux[k] = a[j++];
			else if(j > right) aux[k] = a[i++];
			else if(less(a[j], a[i])) aux[k] = a[j++];
			else aux[k] = a[i++];
		}

		assert isSorted(aux, left, right);
	}
	private static boolean less(Comparable u, Comparable v){
		return u.compareTo(v) < 0;
	}
	private static boolean isSorted(Comparable[] a, int left, int right){
		for(int i = left; i < right; i++)
			if(less(a[i+1], a[i])) return false;
		return true;
	}
}