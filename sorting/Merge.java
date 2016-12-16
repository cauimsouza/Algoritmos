/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
*/
public class Merge{
	public static void sort(Comparable[] a){
		sortRange(a, 0, a.length - 1);
	}
	private static void sortRange(Comparable[] a, int left, int right){
			if(left < right){
				int mid = (left + right) / 2;
				sortRange(a, left, mid);
				sortRange(a, mid + 1, right);
				merge(a, left, mid, right);
			}
	}
	private static void merge(Comparable[] a, int left, int mid, int right){
		int l1 = mid - left + 1;
		int l2 = right - mid;
		Comparable[] leftArr = new Comparable[l1];
		Comparable[] rightArr = new Comparable[l2];
		for(int i = 0; i < l1; i++) leftArr[i] = a[left + i];
		for(int i = 0; i < l2; i++) rightArr[i] = a[mid + i + 1];
		int i, j, k;
		i = 0;
		j = 0;
		k = left;
		while(i < l1 && j < l2){
			if(less(rightArr[j], leftArr[i])) a[k++] = rightArr[j++];
			else a[k++] = leftArr[i++];
		}
		while(i < l1) a[k++] = leftArr[i++];
		while(j < l2) a[k++] = rightArr[j++];
	}
	private static boolean less(Comparable u, Comparable v){
		return u.compareTo(v) < 0;
	}
}