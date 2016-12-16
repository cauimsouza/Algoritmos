import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item>{
	private Node first = null;
	private int n = 0;

	private class Node{
		Node next;
		Item item;
	}

	public boolean isEmpty(){
		return first == null;
	}

	public int size(){
		return n;
	}

	public Item peek(){
		if(isEmpty()) return null;
		return first.item;
	}

	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		n++;
	}

	public Item pop(){
		if(isEmpty()) return null;
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}

	public Iterator<Item> iterator(){
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>{
		private Node current = first;

		public boolean hasNext(){
			return current != null;
		}
		public void remove(){}
		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}