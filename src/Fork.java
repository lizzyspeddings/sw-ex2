
/**
 * This class represents a binary search tree that is NOT empty, it implements the interface
 * Bst.java
 * 
 * @author LizzySpeddings
 * 
 */

import java.util.Optional;

public class Fork<Key extends Comparable<Key>,Value> implements Bst<Key,Value> {

	private final Key root;
	private final Value value;
	private final Bst<Key,Value> left, right;

	public Fork(Key root, Value value, Bst<Key,Value> left, Bst<Key,Value> right) {
		assert(left != null);      
		assert(right != null);

		assert(left.smaller(root)); 
		assert(right.bigger(root));

		this.root = root;   
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/**
	 * This method tells you whether or not the BST is empty.
	 * @return boolean This returns whether or not the BST is empty.
	 */
	@Override
	public boolean isEmpty() { 
		return false; 
	}

	/**
	 * This method checks whether all nodes in the tree are smaller than the element with Key k.
	 * @param k This is the key of the element which we want to compare with all other elements.
	 * @return boolean This returns whether or not all elements are smaller than the element with Key k.
	 */
	@Override
	public boolean smaller(Key k) {
		if(largest().isPresent()) {
			return (largest().get().getKey().compareTo(k) < 0);
		}
		else{
			throw new RuntimeException("Attempted to check whether all nodes were smaller than the element with Key k.");
		}
		
	}

	/**
	 * This method checks whether all nodes in the tree are bigger than the element with Key k.
	 * @param k This is the key of the element which we want to compare with all other elements.
	 * @return boolean This returns whether or not all elements are bigger than the element with Key k.
	 */
	@Override
	public boolean bigger(Key k) {
		if(smallest().isPresent()) {
			return (smallest().get().getKey().compareTo(k) > 0);
		}
		else{
			throw new RuntimeException("Attempted to check whether all nodes were bigger than the element with Key k.");
		}
	}
	
	/**
	 * This method checks whether the element with a Key k, appears in the BST.
	 * @param k This is the key of the element that we will use to check if the element occurs in the BST.
	 * @return boolean This returns whether or not an element of Key k exists in the tree.
	 */
	@Override
	public boolean has(Key k) {
		if (k.compareTo(root) == 0)
			return true;
		else
			if (k.compareTo(root) < 0) 
				return left.has(k);
			else
				return right.has(k);
	}

	/**
	 * This method finds the value of an element with Key k if it exists.
	 * @param k This is the key of the element that we want to find the value of.
	 * @return Optional<Value> This returns the Value of the element with key k if it is in the tree else it returns null.
	 */
	@Override
	public Optional<Value> find(Key k) {
		if(find(k).isPresent()) {
			if (k.compareTo(root) == 0) {
				return Optional.of(largest().get().getValue());
			}      
			else if (k.compareTo(root) < 0) {
				return left.find(k);
			}	
			else {
				return right.find(k);
			}	
		}
		else {
			return Optional.empty();
		}	
	}

	/**
	 * This method inserts an element into the existing BST tree.
	 * @param k This is the key of the element that we're inserting.
	 * @param v This is the value of the element that we're inserting.
	 * @return Bst<Key, Value> This returns a new BST tree with the new element inserted.
	 */
	@Override
	public Bst<Key, Value> put(Key k, Value v) {  
		if (k.compareTo(root) == 0)		
			return this;           
		else                     
			if (k.compareTo(root) < 0) {
				return new Fork<Key,Value>(root, value, left.put(k,v), right); 
			}	
			else {
				return new Fork<Key,Value>(root, value, left, right.put(k,v));
			}	
	}

	/**
	 * This method deletes an element with Key k from the BST.
	 * @param k This is the key of the element that we're deleting.
	 * @return  Optional<Bst<Key, Value>> This returns a BST with element of Key k deleted. If there is no such element it returns null.
	 */
	@Override
	public Optional<Bst<Key, Value>> delete(Key k) {
		if(largest().isPresent()) {
			if (k.compareTo(root) == 0) {
				if (left.isEmpty()) {
					return Optional.of(right);
				}
				else {
					if (right.isEmpty()) {
						return Optional.of(left);
					}
					else {
						Fork<Key,Value> tree = new Fork<Key,Value> (left.largest().get().getKey(), left.largest().get().getValue(), left.deleteLargest().get(), right);
						return Optional.of(tree);
					}
				}
			}
			else {            
				if (k.compareTo(root) < 0) {
					Fork <Key,Value> tree1 = new Fork<Key,Value>(root, value, left.delete(k).get(), right); 
					return Optional.of(tree1);
				}
				else {
					Fork <Key,Value> tree2 = new Fork<Key,Value>(root, value, left, right.delete(k).get()); 
					return Optional.of(tree2);
				}
			}
		}
		else {
			return Optional.empty();
		}
		
	}

	/**
	 * This method finds the smallest element in the BST.
	 * @return Optional<Entry<Key,Value>> This returns the smallest element in the BST. If there are no elements it returns null.
	 */
	@Override
	public Optional<Entry<Key, Value>> smallest() {
		if (left.isEmpty()) {
			Entry<Key,Value> smallest = new Entry<Key,Value> (root, value);
			return Optional.of(smallest);
		}
		else {
			return left.smallest();
		}
			
	}

	/**
	 * This method deletes the smallest element in the tree.
	 * @return Optional<Bst<Key,Value>> This returns a new BST with the smallest element deleted. If there is originally only 
	 * one element in the BST then this will return null.
	 */
	@Override
	public Optional<Bst<Key, Value>> deleteSmallest() {
		if (left.isEmpty()) {
			return Optional.of(right);
		}
		else {
			Fork<Key,Value> delSmall = new Fork<Key,Value> (root, value, left.deleteSmallest().get(), right);
			return Optional.of(delSmall);
		}
	}

	/**
	 * This method finds the largest element in the BST.
	 * @return Optional<Entry<Key,Value>> This returns the largest element in the BST. If there are no elements it returns null.
	 */
	@Override
	public Optional<Entry<Key, Value>> largest() {
		if (right.isEmpty()) {
			Entry<Key,Value> largest = new Entry<Key,Value> (root, value);
			return Optional.of(largest);
		}
		else
			return right.largest();
	}

	/**
	 * This method deletes the largest element in the tree.
	 * @return Optional<Bst<Key,Value>> This returns a new BST with the largest element deleted. If there is originally only 
	 * one element in the BST then this will return null.
	 */
	@Override
	public Optional<Bst<Key, Value>> deleteLargest() {
		if (right.isEmpty()) 
			return Optional.of(left);
		else {
			Fork<Key,Value> delLarg = new Fork<Key,Value>(root, value, left, right.deleteLargest().get());
			return Optional.of(delLarg);
		}
			
	}

	/**
	 * This method prints out the BST as as string. It tends to be for debugging purposes.
	 * @return String A BST is returned in the form of a string.
	 */
	@Override
	public String fancyToString() {
		return "\n\n" + fancyToString(0) + "\n\n";
	}

	/**
	 * 
	 */
	@Override
	public String fancyToString(int depth) { 
		int step = 4;  // depth step
		String l = left.fancyToString(depth+step);
		String r = right.fancyToString(depth+step);
		return r + spaces(depth) + root + "\n" + l;
	}
	
	/**
	 * 
	 * @param n
	 * @return 
	 */
	private String spaces(int n) { // Helper method for the above:
		String s = "";
		for (int i = 0; i < n; i++)
			s = s + " ";
		return s;
	}

	/**
	 * This method returns the number of nodes in the binary search tree.
	 * @return int This is the number of nodes in the binary search tree.
	 */
	@Override
	public int size() {
		if(root != null) {
			return 1 + left.size() + right.size();
		}
		else{
			return 0;
		}
	}

	/**
	 * This method returns the height of the binary search tree.
	 * @return The height of the binary search tree.
	 */
	@Override
	public int height() {
		if(root == null) {
			return 0;
			
		}
		else {
			return 1 + Math.max(left.height(), right.height());
		}
	}

	/**
	 * 
	 */
	@Override
	public void printInOrder() {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 */
	@Override
	public void saveInOrder(Entry<Key, Value>[] a) {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 */
	@Override
	public int saveInOrder(Entry<Key, Value>[] a, int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public Bst<Key, Value> balanced() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Key> getKey() {
		return Optional.of(root);
	}

	@Override
	public Optional<Value> getValue() {
		return Optional.of(value);
	}

	@Override
	public Optional<Bst<Key, Value>> getLeft() {
		return Optional.of(left);
	}

	@Override
	public Optional<Bst<Key, Value>> getRight() {
		return Optional.of(right);
	}
}
