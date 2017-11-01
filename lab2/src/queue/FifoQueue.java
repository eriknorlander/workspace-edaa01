package queue;
import java.util.*;
import java.util.Iterator;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		
	}

	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		private int count;
		
		private QueueIterator() {
			pos = last;
			count = 0;
		}
		
		public boolean hasNext() {
			if(pos == null){
				return false;
			} else if (count < size) {
				return true;
			}
			return false;
		}
		
		public E next() {
			if(hasNext()){
				pos = pos.next;
				count++;
				return pos.element;
			} else { throw new NoSuchElementException(); }
		}
	}
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	x the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E x) {
		if(size != 0){
			QueueNode<E> newLast = new QueueNode<E>(x);
			QueueNode<E> head = last.next;
			last.next = newLast;
			newLast.next = head;
			last = newLast;
		} else {
			last = new QueueNode<E>(x);
			last.next = last;
		}
		size++;
		return true;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(size == 1){
			QueueNode<E> head = last.next;
			last = null;
			size = 0;
			return head.element;
		} else if(size != 0) {
			QueueNode<E> head = last.next;
			last.next = head.next;
			size--;
			return head.element;
		} else {
			return null;
		}
	}

	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size != 0){
			return last.next.element;
		}
		return null;
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* 	    to this queue. This specified queue (q) is empty
	* @param q the queue to append
	* @throws IllegalArgumentEscpetion if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if(size==0 && q.size==0){
			last = null;
		} else if(size==0 && q.size!=0){
			last = q.last;
			size = q.size();
		} else if(size!=0 && q.size!=0 && this != q){
			QueueNode<E> head = last.next;
			last.next = q.last.next;
			q.last.next = head;
			last = q.last;
		} else if(this == q) {
			throw new IllegalArgumentException();
		}	
		q.last = null;
		q.size = 0;
	}


	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
