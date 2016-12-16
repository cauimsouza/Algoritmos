/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
*/
public class Shell{
	public static void sort(Comparable[] a){
		int N = a.length;
		int h = 1;

		while(h < N/3) h = 3*h + 1; //3x+1 sequence: 1, 4, 13, 40, ...

		// h-sort the array
		while(h > 0){
			// insertion sort
			for(int i = h; i < N; i++){
				for(int j = i; j >= h; j -= h){
					if(less(a[j], a[j - h]))
						exch(a, j, j - h);
					else break;
				}
			}
			h /= 3;
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