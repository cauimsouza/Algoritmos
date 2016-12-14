public class Queue<Item>{
	private Node first;
	private Node last;

	private class Node{
		Node next;
		Item item;
	}

	public boolean isEmpty(){
		return first == null;
	}

	public void enqueue(Item item){
		Node oldlast = last;
		last = new Node();
		last.next = null;
		last.item = item;
		if(isEmpty()) first = last;
		else oldlast.next = last;
	}

	public Item dequeue(){
		if(isEmpty()) return null;
		Item item = first.item;
		first = first.next;
		if(isEmpty()) last = null;
		return item;
	}
}