
import java.util.*;

public class Empty<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

	public Empty() {
	}
	
	@Override
	public boolean isEmpty() { 
		return true; 
	}

	@Override
	public boolean smaller(Key k) {
		return true;
	}

	@Override
	public boolean bigger(Key k) {
		return true;
	}

	@Override
	public boolean has(Key k) {
		return false;
	}

	@Override
	public Optional<Value> find(Key k) {
		return Optional.empty();
	}

	@Override
	public Bst<Key, Value> put(Key k, Value v) {
		Fork<Key,Value> insert = new Fork<Key, Value>(k, v , new Empty<Key,Value>(), new Empty<Key,Value>()); 
		return insert;
	}

	@Override
	public Optional<Bst<Key, Value>> delete(Key k) {
		return Optional.empty();
	}

	@Override
	public Optional<Entry<Key, Value>> smallest() {
		return Optional.empty();
	}

	@Override
	public Optional<Bst<Key, Value>> deleteSmallest() {
		return Optional.empty();
	}

	@Override
	public Optional<Entry<Key, Value>> largest() {
		return Optional.empty();
	}

	@Override
	public Optional<Bst<Key, Value>> deleteLargest() {
		return Optional.empty();
	}
	
	@Override
	public String fancyToString() { 
		return ""; 
	}

	@Override
	public String fancyToString(int d) { 
		return fancyToString(); 
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printInOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveInOrder(Entry<Key, Value> a[]) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int saveInOrder(Entry<Key, Value> a[], int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bst<Key, Value> balanced() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Key> getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Value> getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Bst<Key, Value>> getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Bst<Key, Value>> getRight() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
