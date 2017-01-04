/**
 *
 * @author      LIMA, Cauim
 * @email       cauimsouza@gmail.com
 * @description Implementation of a maximum priority queue using binary heap as data
 *              structure.
 * @date        03/01/2017
 */
public class MaxPQ<Key extends Comparable<Key> >{
    private static final int INITIAL_CAPACITY = 10;
    private Key[] array;
    private int capacity;
    private int size;
    
    public MaxPQ(){
       this(INITIAL_CAPACITY);
    }
    
    public MaxPQ(int capacity){
        size = 0;
        this.capacity = capacity;
        array = (Key[]) new Comparable[capacity + 1]; 
    }
    
    // PQ operations
    
    /**
     * 
     * @return True if the priority queue is empty, false otherwise.
     */
    public boolean isEmpty(){
        return size == 0;
    }
    
    /**
     * 
     * @return The number of elements in the priority queue
     */
    public int size(){
        return size;
    }
    
    /**
     * 
     * @param K Key to be inserted in the priority queue 
     */
    public void insert(Key K){
        array[++size] = K;
        swim(size);
        if(size == capacity)    resize(capacity * 2);
    }
    
    /**
     * 
     * @return The largest element in the priority queue and deletes it
     *         from the priority queue
     */
    public Key remove(){
        Key largest = array[1];
        exch(1, size--);
        sink(1);
        array[size + 1] = null;
        if(capacity > INITIAL_CAPACITY && size <= capacity / 4)    resize(capacity / 2);    
        return largest;
    }
    
    /**
     * 
     * @return The largest element in the priority queue 
     */
    public Key max(){
        return array[1];
    }
    
    // heap helper functions
    
    /**
     * 
     * @param i Index of the binary heap where the heap order is first violated
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
     * @param i Index of the binary heap where the heap order is first violated
     *          The heap order is verified from leaf to root
     */
    private void swim(int i){
        while(i > 1 && less(i / 2, i)){
            exch(i / 2, i);
            i /= 2;
        }
    }
    
    // array helper functions
    
    /**
     * 
     * @param i Index in the array of the first element to be exchanged 
     * @param j Index in the array of the second element to be exchanged
     */
    private void exch(int i, int j){
        Key aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }
    
    /**
     * 
     * @param newCapacity New maximum capacity of the heap
     */
    private void resize(int newCapacity){
        capacity = newCapacity;
        Key[] aux = (Key[]) new Comparable[capacity + 1];
        for(int i = 1; i <= size; i++)  aux[i] = array[i];
        array = aux;
    }
    
    /**
     * 
     * @param i First element
     * @param j Second element
     * @return  Returns true if the first element is considered less than
     *          the second element.
     */
    private boolean less(int i, int j){
        return array[i].compareTo(array[j]) < 0;
    }
    
    // testing code
    
    public static void main(String[] args){
        MaxPQ<Integer> pq = new MaxPQ<>();
        pq.insert(4);
        pq.insert(47);
        pq.insert(20);
        pq.insert(89);
        pq.insert(-5);
        for(int i = -100; i < 9; i++)
            pq.insert(i);
        
        System.out.println(pq.remove());
    }
}
