import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author      LIMA, Cauim
 * @param <Key> Class that inherits from Comparable
 * @email       cauimsouza@gmail.com
 * @description Implementation of a maximum priority queue using binary heap as data
 *              structure. Code based on R. Sedgewick and K. Wayne code available at
 *              http://algs4.cs.princeton.edu/24pq/MaxPQ.java.html
 * @date        03/01/2017
 */
public class MaxPQ<Key extends Comparable<Key> > implements Iterable<Key> {
    private static final int INITIAL_CAPACITY = 10;
    private Key[] array;    // array where elements of the pq are stored
    private int capacity;   // capacity of array
    private int size;   // number of elements in the priority queue
    private Comparator<Key> comparator; // optional comparator
    
    /**
     * Initializes an empty priority queue with an initial capacity equals
     * INITIAl_CAPACITY
     */
    public MaxPQ(){
       this(INITIAL_CAPACITY);
    }
    
    /**
     * Initializes an empty priority queue with the given initial capacity 
     * 
     * @param capacity maximum initial capacity of the priority queue
     */
    public MaxPQ(int capacity){
        size = 0;
        this.capacity = Math.max(capacity, INITIAL_CAPACITY);
        array = (Key[]) new Comparable[capacity + 1]; 
    }
    
    /**
     * Initializes an empty priority queue with the given initial capacity
     * and uses the given comparator to sort keys
     * 
     * @param capacity maximum initial capacity of the priority queue
     * @param comparator optional comparator to sort keys
     */
    public MaxPQ(int capacity, Comparator<Key> comparator){
        this(capacity);
        this.comparator = comparator;
    }
    
    /**
     * Initializes an empty priority queue with the given comparator
     * to sort keys
     * 
     * @param comparator optional comparator to sort keys
     */
    public MaxPQ(Comparator<Key> comparator){
        this(INITIAL_CAPACITY);
        this.comparator = comparator;
    }
    
    /**
     * PQ operations
     */
     
    /**
     * Returns true if the priority queue is empty, false otherwise.
     * 
     * @return true if the priority queue is empty, false otherwise.
     */
    public boolean isEmpty(){
        return size == 0;
    }
    
    /**
     * Returns the number of elements in the priority queue
     * 
     * @return the number of elements in the priority queue
     */
    public int size(){
        return size;
    }
    
    /**
     * Inserts a new element in the priority queue
     * 
     * @param K key to be inserted in the priority queue 
     */
    public void insert(Key K){
        array[++size] = K;
        swim(size);
        if(size == capacity)    resize(capacity * 2);
    }
    
    /**
     * Returns and deletes a largest element in the priority queue
     * 
     * @return a largest element in the priority queue and deletes it
     *         from the priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key remove(){
        if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key largest = array[1];
        exch(1, size--);
        sink(1);
        array[size + 1] = null;
        if(capacity > INITIAL_CAPACITY && size <= capacity / 4)    resize(capacity / 2);    
        return largest;
    }
    
    /**
     * Returns a largest element in the priority queue
     * 
     * @return a largest element in the priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key max(){
        if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return array[1];
    }
    
    /**
     * heap helper functions
     */
    
    /**
     * 
     * @param i index of the binary heap where the heap order is first violated
     *          The heap order is verified from root to leaf
     */
    private void sink(int i){
        while(2 * i <= size){
            int j = 2 * i;
            if(j < size && less(j, j + 1))  j++;
            if(!less(i, j)) break;
            exch(i, j);
            i = j;
        }
    }
    
    /**
     * 
     * @param i index of the binary heap where the heap order is first violated
     *          The heap order is verified from leaf to root
     */
    private void swim(int i){
        while(i > 1 && less(i / 2, i)){
            exch(i / 2, i);
            i /= 2;
        }
    }
    
    /**
     * array helper functions
     */
    
    /**
     * Exchanges elements in the positions i and j
     * 
     * @param i index in the array of the first element to be exchanged 
     * @param j index in the array of the second element to be exchanged
     */
    private void exch(int i, int j){
        Key aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }
    
    /**
     * Increases or shrinks the size of the array where the elements
     * of the priority queue are stored
     * 
     * @param newCapacity new maximum capacity of the heap
     */
    private void resize(int newCapacity){
        capacity = newCapacity;
        Key[] aux = (Key[]) new Comparable[capacity + 1];
        for(int i = 1; i <= size; i++)  aux[i] = array[i];
        array = aux;
    }
    
    /**
     * 
     * @param i first element
     * @param j second element
     * @return  true if the first element is considered less than
     *          the second element.
     */
    private boolean less(int i, int j){
        if(comparator == null)
            return array[i].compareTo(array[j]) < 0;
        else
            return comparator.compare(array[i], array[j]) < 0;
    }
    
    /**
     * Iterator 
     */
    
    /**
     * Returns an iterator that iterates over the keys on the priority
     * key in descending order.
     * @return an iterator that iterates over the keys in descending order
     */
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }
    
    private class HeapIterator implements Iterator<Key>{
        private MaxPQ<Key> copy;
        
        public HeapIterator(){
            if(comparator == null) copy = new MaxPQ<>(size);
            else copy = new MaxPQ<>(size, comparator);
            for(int i = 1; i <= size; i++)
                copy.insert(array[i]);
        }
        
        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if(!hasNext()) throw new NoSuchElementException();
            return copy.remove();
        }
        
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * testing code
     */
    
    public static void main(String[] args){
        MaxPQ<String> pq = new MaxPQ<>();
        pq.insert("cauim");
        pq.insert("mae");
        pq.insert("inaie");
        pq.insert("mauricio");
        pq.insert("semimm");
        
        for(String s : pq)
            System.out.println(s);
    }

    
}
