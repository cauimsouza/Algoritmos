/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
*/
public class Quick{
	public static void sort(Comparable[] a){
		sortRange(a, 0, a.length - 1);
	}
	private static void sortRange(Comparable[] a, int left, int right){
		if(left < right){
			int pivotIndex = partition(a, left, right);
			sortRange(a, left, pivotIndex - 1);
			sortRange(a, pivotIndex + 1, right);
		}
	}
	private static int partition(Comparable[] a, int left, int right){
		Comparable pivot = a[right];
		int k = left - 1;
		for(int i = left; i < right; i++)
			if(less(a[i], pivot)) exch(a, i, ++k);
		exch(a, ++k, right);
		return k;
	}
	private static boolean less(Comparable u, Comparable v){
		return u.compareTo(v) < 0;
	}
	private static void exch(Comparable[] a, int i, int j){
		Comparable aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}
}