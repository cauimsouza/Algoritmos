/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
*/
public class Select{
	public static void sort(Comparable[] a){
		for(int i = 0; i < a.length; i++){
			int min = i;
			for(int j = i + 1; j < a.length; j++){
				if(less(a[j], a[min])) min = j;
			}
			exch(a, i, min);
		}
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