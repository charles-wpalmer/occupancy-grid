package PriorityQueue;

/**
 * A simple Double Ended Priority Queue Interface
 * @author bpt
 */
public interface IDEPQ {
  /**
   * Returns the smallest element in the PriorityQueue.DEPQ but does not remove it from the PriorityQueue.DEPQ
   * @return returns the smallest element in the PriorityQueue.DEPQ
   */
  public Comparable inspectLeast();
  
  /**
   * Returns the largest element in the PriorityQueue.DEPQ but does not remove it from the PriorityQueue.DEPQ
   * @return returns the largest element in the PriorityQueue.DEPQ
   */
  public Comparable inspectMost();

 /**
  * Adds an element to the PriorityQueue.DEPQ
  * @param c the element to insert into the PriorityQueue.DEPQ
  */
  public void add(Comparable c);
 
  /**
   * Removes the smallest element from the PriorityQueue.DEPQ and returns it
   * @return returns the smallest element in the PriorityQueue.DEPQ
   */
  public Comparable getLeast();  
    
  
  /**
   * Removes the largest element from the PriorityQueue.DEPQ and returns it
   * @return returns the largest element in the PriorityQueue.DEPQ
   */
  public Comparable getMost();
 
  /**
   * Checks if the PriorityQueue.DEPQ is empty
   * @return returns true if the queue is empty
   */
  public boolean isEmpty();
  
  /**
   * Returns the size of the PriorityQueue.DEPQ
   * @return returns the number of elements currently in the PriorityQueue.DEPQ
   */
  public int size();
}