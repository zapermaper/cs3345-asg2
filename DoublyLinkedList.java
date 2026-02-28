public class DoublyLinkedList<T> implements List<T>,Iterable<T> {
	private Node head, tail;
	private int numberOfElements;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		numberOfElements = 0;
	}

	@Override
	public Iterator<T> iterator() {
    	return new DoublyLinkedListIterator();
	}
	
	@Override
	public void addLast(T item) {
		// TODO 
			Node temp = new Node(item);
			if(isEmpty()){head=temp;tail=temp;}
			else{
				tail.next = temp;
				temp.previous=tail;
				tail=temp;
			}
			numberOfElements++;

	}

	@Override
	public void addFirst(T item) {
		// TODO 
		Node temp = new Node(item);
		if(isEmpty()){head=temp;tail=temp;}
		else{
			temp.next=head;
			head.previous=temp;
			head=temp;
		}
			numberOfElements++;
	}

	@Override
	public T get(int position) {
		// TODO 
		if(position<0||numberOfElements<=position)return null;
		Node curPos=head;
		int i=0;
		while(i<position){
			curPos=curPos.next;
			i++;
		}
		
		return curPos.data; 
	}

	@Override
	public void print() {
		// TODO
		Node curPos=head;
		while(curPos!=null){
			System.out.print(curPos.data+" ");
			curPos=curPos.next;
		}
		System.out.println();
				
	}

	@Override
	public void printBackwards() {
		// TODO 
			Node curPos=tail;
		while(curPos!=null){
			System.out.print(curPos.data+" ");
			curPos=curPos.previous;
		}
		System.out.println();
	}

	@Override
	public boolean remove(T item) {
		// TODO 
	if(isEmpty())
		return false;
	Node curPos=head;
	while(curPos!=null){
		if(curPos.data.equals(item)){
			if(head==tail)head=tail=null;
			else if(curPos==head){
				head=head.next;
				head.previous=null;
			}
			else if(curPos==tail){
				tail=tail.previous;
				tail.next=null;
			}
			else{
				curPos.previous.next=curPos.next;
				curPos.next.previous=curPos.previous;
			}
			numberOfElements--;return true;
		}
		curPos=curPos.next;
	}
	return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return numberOfElements==0; 
	}

	@Override
	public int getLength() {
		// TODO 
	return numberOfElements;
	}
	
	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private T data;
		private Node next, previous;

		private Node(T data) {
			this(data,null,null);
		}

		private Node (T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.previous = prev;
		}
	}

private Node curr=head;
@Overide 
public boolean hasNext(){
	return current !=null;
}
	@Override
    public T next() {
        T data = current.data;
        current = current.next;
        return data;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

