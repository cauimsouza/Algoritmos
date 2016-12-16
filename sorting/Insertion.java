/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
*/
public class Insertion{
	public static void sort(Comparable[] a){
		for(int i = 1; i < a.length; i++){
			int j = i;
			while(j > 0 && less(a[j], a[j - 1])){
				exch(a, j, j - 1);
				j--;
			}
		}
	}
	public static boolean less(Comparable u, Comparable v){
		return u.compareTo(v) < 0;
	}
	public static void exch(Comparable[] a, int i, int j){
		Comparable aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}
}