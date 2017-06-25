package cellularData;

/**
 * Create a java generic class Node and generate a linked list of that type of object
 * 
 * @author Zerong Li (Jerry), Qianli Li
 */
public class Node<T> {
	private T data;
	private Node<T> next;

	/**
	 * Constructor that takes in a generic object as a parameter
	 * 
	 * @param newObj		[a generic type of object that need to add to the node list]
	 * @author Zerong Li
	 */
	public Node(T newObj) {
		this.data = newObj;
		this.next = null;
	}

	/**
	 * Constructor that takes in a generic object and linked to another object
	 * 
	 * @param newObj		[a generic type of object that need to add to the node list]
	 * @param anotherObj	[another generic type of object that next to the exist node]
	 * @author Zerong Li
	 */
	public Node(T newObj, Node<T> anotherObj) {
		this.data = newObj;
		this.next = anotherObj;
	}

	/**
	 * Mutator method returns the data portion of the node
	 * 
	 * @return data [the generic object]
	 * @author Zerong Li
	 */
	public T getObject() {
		return this.data;
	}

	/**
	 * Mutator method get the next node
	 * 
	 * @return next [another generic type of object that next to the exist node]
	 * @author Zerong Li
	 */
	public Node<T> getNext() {
		return this.next;
	}

	/**
	 * Mutator method sets the next node
	 * 
	 * @param anotherObj	[another generic type of object that next to the exist node]
	 * @author Zerong Li
	 */
	public void setNext(Node<T> anotherObj) {
		this.next = anotherObj;
	}

	/**
	 * returns a String when object is called
	 * 
	 * @return result [a String that contains countries and data that have been added]
	 * @author Zerong Li
	 */
	public String toString() {
		return this.data.toString();
	}
}
