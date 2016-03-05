package dataStructures;

import ourExceptions.NoSuchElementException;

public class BSTKeyOrderIterator<K extends Comparable<K>, V> implements Iterator<Entry<K,V>> {

	private static final long serialVersionUID = 1L;

	
	private Stack<BSTNode<K,V>> stack;
	private BSTNode<K,V> root;
	
	public BSTKeyOrderIterator(BSTNode<K,V> root) {
		this.root = root;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public Entry<K,V> next() throws NoSuchElementException {
		
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		
		BSTNode<K,V> node  = stack.pop();
		
		BSTNode<K,V> nodeToReturn = node;
		
		
		if(node.getRight()!=null){
			node = node.getRight();
			stack.push(node);
		
			while(node.getLeft()!=null){
				node = node.getLeft();
				stack.push(node);
			}
		
		}

		return nodeToReturn.getEntry();
	}

	@Override
	public void rewind() {
		stack = new StackInList<BSTNode<K,V>>();
		
		if(root!=null){
		
			stack.push(root);
			
			BSTNode<K,V> node = root;
			
			while(node.getLeft()!=null){
				node = node.getLeft();
				stack.push(node);
			}
		}
		
	}

}
