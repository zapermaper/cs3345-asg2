import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements QueueInterface<E> {

    // each node holds data and pointers to the next and previous nodes
    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data) {
            this.data = data;
        }
    }

    private Node front; // first element in the queue
    private Node rear;  // last element in the queue
    private int size;   // number of elements

    // initializes an empty queue
    public LinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // returns true if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // returns the number of elements in the queue
    public int size() {
        return size;
    }

    // adds an element to the back of the queue
    public void enqueue(E e) {

        if (e == null)
            throw new NullPointerException();

        Node newNode = new Node(e);

        if (isEmpty()) {
            front = rear = newNode; 
        } else {
            rear.next = newNode;    // link new node after current rear
            newNode.prev = rear;    // link back to old rear
            rear = newNode;         // update rear
        }

        size++;
    }

    // returns the front element without removing it or null if empty
    public E peek() {

        if (isEmpty())
            return null;

        return front.data;
    }

    // removes and returns the front element or null if empty
    public E dequeue() {

        if (isEmpty())
            return null;

        E data = front.data;
        front = front.next;         // move front forward 1

        if (front != null)
            front.prev = null;      // clear back pointer on new front
        else
            rear = null;            // queue is now empty

        size--;
        return data;
    }

    // removes and returns the element at the given spot
    public E dequeue(int index) {

        if (index < 0 || index >= size)
            throw new NoSuchElementException();

        // go to the node at the index
        Node current = front;
        for (int i = 0; i < index; i++)
            current = current.next;

        // unlink from previous node or update the front
        if (current.prev != null)
            current.prev.next = current.next;
        else
            front = current.next;

        // unlink from next node or update the rear
        if (current.next != null)
            current.next.prev = current.prev;
        else
            rear = current.prev;

        size--;
        return current.data;
    }

    // clears the queue by removing all references to it
    public void removeAll() {
        front = null;
        rear = null;
        size = 0;
    }

    // returns an iterator that traverses from front to rear
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    // iterator that goes from the queue front to rear
    private class QueueIterator implements Iterator<E> {

        private Node current = front; // start at the front

        // returns true if there are more places to visit
        public boolean hasNext() {
            return current != null;
        }

        // returns the next element and moves the pointer forward
        public E next() {

            if (!hasNext())
                throw new NoSuchElementException();

            E data = current.data;
            current = current.next;
            return data;
        }
    }
}