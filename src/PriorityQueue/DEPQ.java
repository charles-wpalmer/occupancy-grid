package PriorityQueue;

/**
* ------------------------------------------------------------------------------------------------------------
* 											 |	Introduction  |
* ------------------------------------------------------------------------------------------------------------
* This class, PriorityQueue.DEPQ provides the functionality for a double ended priority queue, in which both the minimum
* and maximum values are calculated, and should both be accounted for. The class has 7 main methods that are 
* enforced by the interface PriorityQueue.DEPQ, these are add, size, isEmpty, inspectMost, inspectLeast, getMost and finally
* getLeast. This implementation of a double ended priority queue uses the Interval heap data structure, which
* uses nodes, each node can hold up to two values, left and/or right, where the left value is always smaller 
* then or equal to the right, so we can say l <= r. As well as both of the keys in each node being within the 
* range of the two key values of its parent node. The values in any node must be within the range of the two 
* values in the parent node. For example, if parent node has value 2 in the left key and 10 in the right key.
* The child's left and right has to be a value between 2 and 10, left can't be smaller than two, and right
* can't be greater than 10. In an interval heap, each node can have a maximum of two children, where the 
* element being added is always added to the last node,or if a new node needs adding, the node is always
* added to the next possible left node available. In the example below in the time complexity, a new value
* has been added prompting a new node to be created which is inserted as a child as the next node that doesn't
* have two children.
* 
* The interval heap type has been used because is find it a very efficient way of structuring the priority
* queue, accessible at both ends. Very little calculation needs to be done, and the theory of the structure
* is very simple to apply, and code, as well as being interested to learn the theory of the interval heap.

* @author chp38 - Charles Palmer
*/
public class DEPQ implements IDEPQ{
	Comparable<?> max, min;
	int listSize = 0;
	int elements= 0;
	Comparable<?> temp = 0;
	int curr = 0;
	Interval list[];
	boolean newNode = true;
    /**
    * Constructor method, when an instance of the class is called, the constructor is executed and an array list of 
    * 1000 items is initialised. The flat array type is Interval, to match that of the nodes to be stored in the list.
    */
	public DEPQ(){
		list = new Interval[1000000];
	}
    /**
    * This nested class Interval is the class used to created instances of the Interval nodes, four values are in the 
    * class, the left value (minimum element) and the right value (maximum element), the parent of the node if
    * applicable, and the index which is the position in the tree the node is at. 
    */
	class Interval{
		int index;
		private Comparable left = null;
	    private Comparable right = null;
		public Interval parent;
		/**
		 * Constructor for the Interval class, adds the new value (c) to the left key of the new node. Added to the left
		 * element as it is the first value in the node, saves having another variable to store a node with a single
		 * value.
		 * @param c to be added to the left key of the new node.
		 */
		public Interval(Comparable<?> c){
			left = c;
		}
	}
	
	/**
	 * Returns the smallest element in the tree, without removing it.
	 * The complexity of this method will be O(1), because there is only one operation within this method, which is a return.
	 * No matter how many elements are in the array, it will take the same amount of time to operate. The smallest value is
	 * stored in the min variable, that is being returned in the element below. Each time an item is added or removed the min
	 * variable is reset to the value left of the root node, the minimum element, as a quick and easy way to store, and return
	 * the value when the method is called.
	 * @return min, minimum element at any given time of the tree.
	 */
	public Comparable<?> inspectLeast() {
		return min;
	}
	
	/**
	 * This method is identical to the method above, however returns the largest value in the tree, stored in the max variable.
	 * @return max, the maximum element at any given time of the tree.
	 */
	public Comparable<?> inspectMost() {
		return max;
	}
	
	/**
	 * isEmpty method is for returning if the priority queue is empty or not, this is done using a simple if statement that
	 * checks the value of the integer listSize, if this is equal to zero, then the list is empty and the method returns
	 * true. Else return true because the queue isn't empty. In this implementation of a double ended priority queue, the
	 * listSize variable corresponds to the amount of nodes in the tree, not the amount of values added, which is stored by
	 * the integer elements. Therefore elements is always double, or double -1 of the listSize because each node can
	 * contain one or two values. This has been done so that i could monitor the amount of nodes, as well as the amount
	 * of elements in the queue easily, without having to do a calculation when I required one of the values. 
	 * @return true or false. If the tree has at least one element, false, if the tree is empty, returns false.
	 */
	public boolean isEmpty() {
		if(listSize == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Simple method that returns the amount of elements that are in the tree, stored in the integer elements. Value is
	 * changed in the add, and getMost and getLeast methods to match the new number of values in the tree. Whenever a 
	 * new value is 
	 * @return the number of elements in the tree.
	 */
	public int size() {
		return elements;
	}
	
	/**
	 * The add method is used to add elements into the priority queue. It first determines if the list is empty by checking
	 * the value of the listSize variable. If it is empty, equal to zero, then a new node is created, added to the list.
	 * If it is not empty, the program needs to check if a new node is needed, by checking the variable newNode, if it is
	 * true, a new node is created with the new value and added to the list. However, if a newNode is not needed then the
	 * value is added to the right value, and compared with the left, if it is smaller the values are swapped. Finally, 
	 * the new value needs to be compared to the corresponding parent values, bubbling up occurs until the new value
	 * is in the correct place. If current is greater than 2, meaning there are more layers to the tree, the minUp or
	 * maxUp methods are called to bubble up the values (explained in the javaDoc for the methods). I have used separate
	 * methods for this code as it is needed more than once, making the code more readable id a method is created that 
	 * can be called when needed. 
	 * 
	 * The boolean variable newNode used to check if a new node is needed or not is, I feel a more efficient way to 
	 * keep track of if a new node is needed than other examples i saw which calculate it by the number of elements
	 * in the array, I felt a variable would work better as it saves calculations of the elements variables when 
	 * checking if a new node is needed.
     * public
	 * @param c the element being added to queue.
	 */
	public void add(Comparable c) {
		Interval node, parent;
		//If the queue is empty, create the first node, and add in the c variable to the left hand node (done in Interval
		//constructor), increment both listSize and elements as there is a new element and a new node.
		if(listSize == 0){
			newNode = false;
			listSize++;
			elements++;
			//Generate the new node, the value (c) is inserted into the left value of the new node.
			node = new Interval(c);
			node.index = listSize;
			//Put the node into the correct place in the array.
			list[curr] = node;
		}
		//If the listSize is greater than zero.
		else if(listSize != 0){
			//newNode is false, we don't need to add a new node.
			if(newNode == false){
				elements++;
				curr = listSize-1;
				node = list[curr];
				//newNode set to true, a new node is now needed if another element is added.
				newNode = true;
				//Checks if the value being added is less then the current node's right value. If it is, the values need to
				//be swapped. Using the compare to method.
				if(node.left.compareTo(c) > 0){
					temp = node.left;
					node.left = c;
					node.right = temp;
					parent = node.parent;
					//Can only be one parent, check the value to the parent value.
					if (curr >= 1){
						if(node.left.compareTo(parent.left) < 0 ){
							temp = parent.left;
							parent.left = node.left;
							node.left = temp;
						}
					}
					//More levels in the tree, need to bubble up.
					if(curr > 2){
						node = node.parent;
						parent = node.parent;
						minUp(node, parent);
					}
				}
				//else if the value is greater than or equal to the left value, it is added to the right value in the last node.
				else{
					node.right = c;
					parent = node.parent;
					//Can only be one parent, check the value to the parent value.
					if (curr >= 1){
						if(node.right.compareTo(parent.right)>0){
							temp = parent.right;
							parent.right = node.right;
							node.right = temp;
						}
					}
					//More levels in the tree, need to bubble up.
					if(curr > 2){
						node = node.parent;
						parent = node.parent;
						maxUp(node, parent);
					}
				}
			}
			//New node is needed, create a new node, insert value and add to list. Finally increment values to match new tree size.
			else if(newNode == true){
				listSize++;
				elements++;
				curr = listSize-1;
				node = new Interval(c);
				node.index = listSize;
				//Calculates the parent node for the new node, and stores it in the nodes parent variable for future use.
				node.parent = list[(curr-1)/2];
				list[curr]=node;
				newNode = false;
				parent = node.parent;
				if(node.left.compareTo(parent.right) > 0){
					temp = parent.right;
					parent.right = node.left;
					node.left = temp;
					if(curr > 2){
						node = node.parent;
						parent = node.parent;
						maxUp(node, parent);
					}
				}
				else if(node.left.compareTo(parent.left) < 0){
					temp = parent.left;
					parent.left = node.left;
					node.left = temp;
					if(curr > 2){
						node = node.parent;
						parent = node.parent;
						minUp(node, parent);
					}
				}
			}
		}
		//Updates the min and max element, as a new value could be in the root left or right.
		if(newNode == false && listSize==1){
			max = list[0].left;
			min = list[0].left;
		}
		else{
			max = list[0].right;
			min = list[0].left;
		}
	}
	
	/**
	 * This method provides the bubbling up for the minimum values (left key of the nodes). The current node left is
	 * compared to its parent node. The while loops until either the current node has no parent, or the parents left
	 * value is smaller than the current nodes value. The while loop works using a simple 'break' statement, breaking
	 * the while loop when all conditions of the interval loop are met. 
	 * @param node, current node.
	 * @param parent, parent node, current node has to be compared to. 
	 */
	public void minUp(Interval node, Interval parent){
		while(true){
			//Current node left is smaller than parent node left, swap the values.
			if(node.left.compareTo(parent.left)<0){
				temp = parent.left;
				parent.left = node.left;
				node.left = temp;
				//Current node becomes the new parent node to be compared.
				node = node.parent;
				//Parent of the new node becomes the new parent, if is is null, current node must be root node, break.
				parent = node.parent;
				if(parent == null){
					break;
				}
			}
			else{
				break;
			}
		}
	}
	
	/**
	 * The maxUp method does exactly the same as the minUp method, however with the right value of each node, as the 
	 * program is now dealing with the maximum values. Again, the while loop loops until the current node right is 
	 * smaller than the parents right value, or until the parent is null, in which case we have hit the root node
	 * and can't go any further. 
	 * @param node, current node.
	 * @param parent, parent of the current node.
	 */
	public void maxUp(Interval node, Interval parent){
		while(true){
			if(node.right.compareTo(parent.right) > 0){
				temp = parent.right;
				parent.right = node.right;
				node.right = temp;
				node = node.parent;
				parent = node.parent;
				if(parent == null){
					break;
				}
			}
			else{
				break;
			}
		}
	}
	
	/**
	 * The most complex method, along with getMost, this method returns the smallest value, and then removes it. Once the
	 * smallest value has been removed the last element added is placed in the root nodes left key. Once this has happened
	 * the value has to be bubbled down to its correct position. Similar to the bubbling up, a while loop is used which
	 * swaps the current nodes left with its child's left, until either the current node hasn't got any children, or the
	 * current nodes left value is lower than its child's left value. This is done by figuring out the current nodes two
	 * children by a simple calculation, using the current nodes index position. If the node has two children, then the 
	 * current nodes left has to be compared to both of the child's left, the smaller one is replaced with current nodes
	 * left. If only one child, that child's left is replaced if it is smaller. Depending on if a new node is needed, 
	 * depends from which key the last element is in, for example if a new node is needed, then the last element will be
	 * in the right key of the last node, if a new node is not needed, then the last element is in the last nodes left key.
	 * The while loop has been used like this as there is no set value the loop needs to get to, the amount of loops is 
	 * not known, therefore I have used if statements to check the conditions of the tree, and break the loop if needed. This
	 * can also save time as opposed to looping through the whole tree even though the place for the value has been found,
	 * the loop simply ends when needed. 
	 * @return returns the smallest value of the tree.
	 */
	public Comparable<?> getLeast() {
		if(listSize == 0) {
			return null;
		}
		Comparable oldMin = min;
		Interval rootNode, currNode, childA, childB;
		curr = listSize - 1;
		rootNode = list[0];
		currNode = list[curr];
		//Remove last element from last node at place it in root nodes left key.
		if(newNode == true){
			rootNode.left = currNode.right;
			currNode.right = null;
			newNode = false;
			currNode = rootNode;
		}
		//Remove last element from last node at place it in root nodes left key, delete last node.
		else{
			listSize--;
			rootNode.left = currNode.left;
			list[curr] = null;
			newNode = true;
			currNode = rootNode;
		}
		while(true && listSize>1){
			//Figure out the children using a simple arithmetic calculation from the current node.
			childA = list[currNode.index*2-1];
			childB = list[currNode.index*2];
			//No children, can't compare current node with anything so break loop.
			if(childA == null && childB == null){
				break;
			}
			//Only one child, must be end of the tree, compare with child, swap if needed and then break the loop.
			else if(childA != null && childB == null){
				if(currNode.left.compareTo(childA.left) > 0){
					temp = currNode.left;
					currNode.left = childA.left;
					childA.left = temp;
				}
				break;
			}
			//More than one child, compare the left keys with the child's left keys, and swap if needed.
			else if(childA != null && childB !=null){
				//If the two children nodes are even and greater than current node left key, break, nothing needs swapping.
				if(childA.left.compareTo(childB.left) == 0 && childA.left.compareTo(currNode.left) > 0){
					break;
				}
				//One of the child's left keys is smaller than the current nodes left, need to swap values.
				if(currNode.left.compareTo(childA.left)>0 || currNode.left.compareTo(childB.left)>0){
					//Left child's left is smaller, swap values.
					if(childA.left.compareTo(childB.left) <= 0){
						temp = currNode.left;
						currNode.left = childA.left;
						childA.left = temp;
						//Check if child's left is greater than child's new right, if so swap the values.
						if(childA.right != null && childA.left.compareTo(childA.right) > 0){
							temp = childA.left;
							childA.left = childA.right;
							childA.right = temp;
						}
						currNode = childA;
					}
					else if(childA.left.compareTo(childB.left) > 0){
						temp = currNode.left;
						currNode.left = childB.left;
						childB.left = temp;
						//Check if child's left is greater than child's new right, if so swap the values.
						if(childB.right != null && childB.left.compareTo(childB.right) > 0){
							temp = childB.left;
							childB.left = childB.right;
							childB.right = temp;
						}
						currNode = childB;
					}
				}
				//Current nodes left is smaller or equal to child's left, nothing needs doing, break.
				else if(currNode.left.compareTo(childA.left)<=0){
					break;
				}
			}
		}
		//Update the min variable.
		if(listSize>=1){
			min = list[0].left;
		}
		elements--;
		return oldMin;
	}

	/**
	 * The getMost method works in exactly the same way as the getLeast method, by removing the last value, adding it into
	 * the root nodes right, then bubble down, using the same while loop until all conditions satisfy that of an interval
	 * heap. The only difference is now the program is working with the right key, this value may not be full i.e a new 
	 * node is not required. In this case, I have use some additional if statements to check the newNode variable, if a 
	 * new node isn't needed, then the program has to work with the left element of the child. The reason for storing the
	 * single value of a node in the left key is to simple avoid there being another variable in the Interval class
	 * specifically for the of a node having a single value, also it would make the code more complex as more swapping
	 * and checking would need to be carried out. 
	 * @return returns the largest value of the tree.
	 */
	public Comparable<?> getMost() {
		Comparable oldMax = max;
		Interval rootNode, currNode, childA, childB;
		curr = listSize - 1;
		rootNode = list[0];
		currNode = list[curr];
		//New node is needed, get the right value from the last night, and place it in root nodes right.
		if(newNode == true){
			rootNode.right = currNode.right;
			currNode.right = null;
			newNode = false;
			currNode = rootNode;
		}
		//New node not needed, remove last value from last node, remove node and place value in root nodes right. 
		else{
			listSize--;
			rootNode.right = currNode.left;
			list[curr] = null;
			newNode = true;
			currNode = rootNode;
		}
		while(true && listSize>1){
			childA = list[currNode.index*2-1];
			childB = list[currNode.index*2];
			//Only one child, must be end of tree, compare the values of current node and child node right, then break.
			if(childA != null && childB == null){
				if(newNode == true){
					if(currNode.right.compareTo(childA.right) < 0){
						temp = currNode.right;
						currNode.right = childA.right;
						childA.right = temp;
					}
					if(childA.left.compareTo(childA.right) > 0){
						temp = childA.left;
						childA.left = childA.right;
						childA.right = temp;
					}
				}
				else if(newNode == false){
					if(currNode.right.compareTo(childA.left) < 0){
						temp = currNode.right;
						currNode.right = childA.left;
						childA.left = temp;
					}
				}
				break;
			}
			//No children, reached the end of tree, break.
			else if(childA == null && childB == null){
				break;
			}
			//Current node has two children, compare right values of both children.
			else if(childA != null && childB !=null){
				//Second child's (childb) right isn't null, right value can be used.
				if(childB.right != null){
					//Current nodes right is greater than or equal to both children's right, break, don't need to do anything.
					if(childA.right.compareTo(childB.right) == 0 && childA.right.compareTo(currNode.right) < 0){
						break;
					}
					//Check if either child's right is greater than the current nodes right.
					if(currNode.right.compareTo(childA.right) < 0 || currNode.right.compareTo(childB.right)<0){
						//Childa's right is greater then childb's, swap the current nodes right with childa's right.
						if(childA.right.compareTo(childB.right) >= 0){
							temp = currNode.right;
							currNode.right = childA.right;
							childA.right = temp;
							//If the childs'a left is greater than child's right, swap the values.
							if(childA.right != null && childA.left.compareTo(childA.right) > 0){
								temp = childA.left;
								childA.left = childA.right;
								childA.right = temp;
							}
							//Current node becomes childa, to continue down the tree.
							currNode = childA;
						}
						//Childb's right is greater then childa's, swap the current nodes right with childb's right.
						else if(childA.right.compareTo(childB.right) < 0){
							temp = currNode.right;
							currNode.right = childB.right;
							childB.right = temp;
							if(childB.right != null && childB.left.compareTo(childB.right) > 0){
								temp = childB.left;
								childB.left = childB.right;
								childB.right = temp;
							}
							currNode = childB;
						}
					}
					//Current nodes right is greater, break the loop.
					else if(currNode.right.compareTo(childA.right)>=0){
						break;
					}
				}
				//Second child's (childb) right is null, left value has to be used.
				else{
					//Check if either childa's right or childb's left is greater than the current nodes right.
					if(childA.right.compareTo(childB.left) == 0 && childA.right.compareTo(currNode.right) < 0){
						break;
					}
					//Childa's right is greater then childb's left, swap the current nodes right with childa's right.
					if(currNode.right.compareTo(childA.right) < 0 || currNode.right.compareTo(childB.left)<0){
						if(childA.right.compareTo(childB.left) >= 0){
							temp = currNode.right;
							currNode.right = childA.right;
							childA.right = temp;
							if(childA.right != null && childA.left.compareTo(childA.right) > 0){
								temp = childA.left;
								childA.left = childA.right;
								childA.right = temp;
							}
							currNode = childA;
						}
						//Childb's left is greater then childa's right, swap the current nodes right with childb's left.
						else if(childA.right.compareTo(childB.left) < 0){
							temp = currNode.right;
							currNode.right = childB.left;
							childB.left = temp;
							currNode = childB;
						}
					}
					else if(currNode.right.compareTo(childA.right)>=0){
						break;
					}
				}
			}
		}
		//Decrements number of elements as one has.
		elements--;
		//Reset max variable, as the max number has changed.
		if(newNode == false && listSize==1){
			max = list[0].left;
		}
		else if(listSize>=1){
			max = list[0].right;
		}
		return oldMax;
	}
	
	/**
	 * The comparable type has been used so that the value held in the nodes left and right keys can change, as long as
	 * they are the same. Therefore either Int's or strings can be used in the PriorityQueue.DEPQ. Two parameters are passed through
	 * when the method is called, the two values that are to be compared. Then the two values are compared, if a is
	 * greater than b, the return will be positive, if 0 the values are equal if negative, b is greater than a.
	 * @param a to be compared to b.
	 * @param b to be compared to a.
	 * @return 0 if values are equal, -1 if b is greater than a, +1 if a is greater than b.
	 */
	public Comparable<?> CompareTo(Comparable a, Comparable b){
		return a.compareTo(b);
		
	}
}
