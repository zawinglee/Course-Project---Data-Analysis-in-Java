package cellularData;

import java.util.Iterator;

/**
 * Creates a generic LinkedList class to implement the generic Iterable interface
 * 
 * @author Zerong Li (Jerry), Sally Li
 */
public class LinkedList<T> implements Iterable<T> {
	private Node head;
	private Node tail;
	private int size;

	/**
	 * Constructor that crates an empty LinkedList object
	 * 
	 * @param head	[the first node on the LinkedList]
	 * @param tail	[the last node on the LinkedList]
	 * @param size	[the size of the LinkedList]
	 * @author Zerong Li
	 */
	public LinkedList() {
		// if the list is empty, both head and tail are null
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * Adds a generic object to the Node list
	 * 
	 * @param newObj	[the generic object that needs to add to the linked list]
	 * @author Zerong Li
	 */
	public void add(T newObj) {
		this.size++;
		Node<T> temp = new Node<T> (newObj);
		if (this.head == null) {
			this.head = temp;
			this.tail = temp;
		} else {
			tail.setNext(temp);
			tail = tail.getNext(); // defines the new tail object
		}
	}

	/**
	 * Finds whether that generic object exists on the linked list
	 * 
	 * @param newObj	[the generic object that needs to add to the linked list]
	 * @return the specific generic object or null
	 * @author Zerong Li
	 */
	public T contains(T newObj) {
		Node<T> walker;
		for (walker = head; walker != null; walker = walker.getNext()) {
			if (newObj.equals(walker.getObject())) {
				return walker.getObject();
			}
		}
		return null;
	}

	/**
	 * Gets the generic object on the linked list and return the generic object
	 * 
	 * @param index	[the index that user want to look for]
	 * @throws IndexOutOfBoundsException	[throws an Exception error when index is out of bound]
	 * @return the generic object on that specific index
	 * @author Zerong Li
	 */
	public T getIndex(int index) throws IndexOutOfBoundsException { // use node number to locate and return that generic object
		int indexCount = 0;
		Node<T> walker = head;
		while (walker != null && indexCount <= index) {
			if (indexCount == index) {
				return walker.getObject();
			}
			indexCount++;
			walker = walker.getNext();
		}
		String errorMessage = "Illegal requests of index " + index;
		throw new IndexOutOfBoundsException(errorMessage);
	}

	/**
	 * 
	 * @return size [the size of the linked List]
	 * @author Zerong Li
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Inserts a generic object to the linked list by giving a specific index
	 * 
	 * @param newObj	[the generic object that needs to insert to the linked list]
	 * @param index		[the index that user would like to insert to]
	 * @throws IndexOutOfBoundsException	[throws an Exception error when index is out of bound]
	 * @throws NumberFormatException		[throws an Exception error when index is not an integer]
	 * @author Zerong Li
	 */
	public void insertAtIndex(T newObj, int index) throws IndexOutOfBoundsException, NumberFormatException {
		Node<T> ObjectToAdd = new Node<T>(newObj);
		Node<T> walker;

		if (index >= this.size) { // Adding at the End of a List
			this.add(newObj);
		}

		else if (index >= 0 && index < this.size) { // excepts the starting object
			int indexCount = 0;
			walker = head;
			while (walker != null && indexCount < this.size) {
				if (indexCount == index) {
					Node<T> ObjectNeedToInsert;
					Node<T> ObjectTakenOut = walker.getNext(); // takes out the object which in origin index
					walker.setNext(ObjectToAdd); // replaces the index and links
					ObjectNeedToInsert = walker.getNext(); // defines the new node member
					ObjectNeedToInsert.setNext(ObjectTakenOut); // links the name one ahead of the origin one
					this.size++; // modify the size of the list
				}
				indexCount++;
				walker = walker.getNext();
			}
		}

		else {
			String errorMessage = "Illegal requests of index " + index;
			throw new IndexOutOfBoundsException(errorMessage);
		}
	}

	/**
	 * 
	 * @return a String that contains all generic object on the linked list
	 * @author Zerong Li
	 */
	public String toString() {
		String result = "";
		ListIterator<T> itr = new ListIterator<T>();
		while (itr.hasNext()) {
			result += itr.next().toString() + "\n";
		}
		return result;
	}

	/**
	 * create an iterator object that starts at beginning of the list
	 * 
	 * @return ListIterator [a iterator object that travel the linked list]
	 * @author Zerong Li
	 */
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	/**
	 * an inner class of LinkedList that is used to traverse the collection of
	 * objects in the list
	 * 
	 * @author Zerong Li
	 */
	public class ListIterator<T> implements Iterator<T> {
		private Node<T> current;

		/**
		 * Constructor that initializes Node to the beginning of the list
		 * 
		 * @author Zerong Li
		 */
		public ListIterator() {
			current = head;
		}

		/**
		 * a method that tests whether the Node is the last node in the linked list
		 * 
		 * @author Zerong Li
		 */
		public boolean hasNext() {
			if (current == null) {
				return false;
			}
			return true;
		}

		/**
		 * a method that returns the next object in the linked list
		 * 
		 * @return data [the next object in the linked list]
		 * @throws NoSuchElementException	[Thrown to indicate that there are no more elements in the enumeration]
		 * @author Zerong Li
		 */
		public T next() {
			if (current == null) {
				throw new java.util.NoSuchElementException();
			}
			T data = current.getObject();
			current = current.getNext();
			return data;
		}
	}

}
