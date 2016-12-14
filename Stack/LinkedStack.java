public class LinkedStack<Item>{
	private Node first = null;

	private class Node{
		Node next;
		Item item;
	}

	public boolean isEmpty(){
		return first == null;
	}

	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public Item pop(){
		if(isEmpty()) return null;
		Item item = first.item;
		first = first.next;
		return item;
	}
}