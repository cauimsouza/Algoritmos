import java.util.*;

class HashTable<K extends Comparable<K>, V>{
	private ArrayList< HashNode<K, V> > hashTable;
	private int size;

	HashTable(int n){
		hashTable = new ArrayList<>(Collections.nCopies(n, null));
		size = 0;
	}

	private int hashTableIndex(K key){
		return Math.abs(key.hashCode()) % hashTable.size();
 	}	

	public V get(K key){
		int id = hashTableIndex(key);
		HashNode<K, V> node = hashTable.get(id);
		while(node != null){
			if(node.getKey().compareTo(key) == 0)	return node.getValue();
			node = node.prox;
		}
		return null;
	}

	public void add(K key, V val){
		int id = hashTableIndex(key);
		HashNode node = hashTable.get(id);
		if(node == null){
			hashTable.set(id, new HashNode<>(key, val));
			size++;
			return;
		}
		while(true){
			if(node.getKey().compareTo(key) == 0){
				node.setValue(val);
				return;
			}
			if(node.prox == null){
				node.prox = new HashNode<>(key, val);
				size++;
				return;
			}
			node = node.prox;
		}
	}

	public void remove(K key){
		int id = hashTableIndex(key);
		HashNode<K, V> node = hashTable.get(id);
		if(node != null && node.getKey().compareTo(key) == 0){
			hashTable.set(id, node.prox);
			size--;
			return;
		}
		while(node.prox != null){
			if(node.prox.getKey().compareTo(key) == 0){
				node.prox = node.prox.prox;
				size--;
				return;
			}
			node = node.prox;
		}
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public int getSize(){
		return size;
	}

	class HashNode<K extends Comparable<K>, V>{
		public HashNode<K, V> prox;
		private K key;
		private V value;
		HashNode(K key, V value){
			this.key = key;
			this.value = value;
			this.prox = null;
		}

		public K getKey(){
			return key;
		}

		public V getValue(){
			return value;
		}

		public void setValue(V newValue){
			this.value = newValue;
		}
	}


	public static void main(String[] args){		
		
		HashTable<String, Integer> ht = new HashTable<>(10);

		ht.add("today", 6);	
		ht.add("tomorrow", 7);
		ht.add("yesterday", 5);

		System.out.println(ht.get("today"));
		System.out.println(ht.get("tomorrow"));

		ht.remove("yesterday");

		System.out.println("hashtable contains 'yesterday'? " + (ht.get("yesterday") != null));
		System.out.println("Size: " + ht.getSize());

	}
}

