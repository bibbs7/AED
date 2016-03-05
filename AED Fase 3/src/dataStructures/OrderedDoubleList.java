package dataStructures;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V>{

	
	private static final long serialVersionUID = 0L;

	 // Node at the head of the list.
    protected DListNode<Entry<K,V>> head;

    // Node at the tail of the list.
    protected DListNode<Entry<K,V>> tail;

    // Number of elements in the list.
    protected int currentSize;
	
	
	
	public OrderedDoubleList() {
		head = null;
        tail = null;
        currentSize = 0;
	}

	 // Returns true iff the list contains no elements.
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

    // Returns the number of elements in the list.
	@Override
	public int size() {
		return currentSize;
	}

	
	// If there is an entry in the dictionary whose key is the specified key,
    // returns its value; otherwise, returns null.
	@Override
	public V find(K key) {
		
		//creates "pointer" to node whose key will be compared with the argument
		DListNode<Entry<K,V>> node = head;
		
		
		//advances node until the end of the list or until it finds a bigger key
		while( node != null && node.getElement().getKey().compareTo(key) < 0)
			node = node.getNext();
		
		
		if(node == null || node.getElement().getKey().compareTo(key) != 0)
			//reached the end of the list
			return null;
		else
			//found the correct key-value pair
			return node.getElement().getValue();
	}

	
	// If there is an entry in the dictionary whose key is the specified key,
    // replaces its value by the specified value and returns the old value;
    // otherwise, inserts the entry (key, value) and returns null.
	@Override
	public V insert(K key, V value) {
		
		// list is empty
		if(currentSize == 0){
			Entry<K,V> entry = new EntryClass<K,V>(key, value);
			head = tail = new DListNode<Entry<K,V>>(entry);
			currentSize++;
			return null;
		}
		
		
		
		//creates "pointer" to node whose key will be compared with the argument
		DListNode<Entry<K,V>> node = head;
		
		
		//advances node until the end of the list or until it finds a bigger key
		while( node != null && node.getElement().getKey().compareTo(key) < 0){
			node = node.getNext();
		}
		
		//the key already exists
		if(node!=null && node.getElement().getKey().compareTo(key) == 0){
			return node.getElement().setValue(value);
		}
		
		// a new node will be inserted
		else{
			
			Entry<K,V> entry = new EntryClass<K,V>(key, value);
			
			
			//new node is bigger than all the others
			if (node == null){
				DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(entry, tail, null);
				tail.setNext(newNode);
				tail = newNode;
			
			//new node is smaller than all the others
			}else if(node == head){
				DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(entry, null, head);
				head.setPrevious(newNode);
				head = newNode;
				
			//new node is in the middle
			}else {
				
				DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(entry, node.getPrevious(), node);
				
				node.getPrevious().setNext(newNode);
				node.setPrevious(newNode);
			
			}

			currentSize++;
			return null;
		}
		
	}
		
	// If there is an entry in the dictionary whose key is the specified key,
    // removes it from the dictionary and returns its value;
    // otherwise, returns null.
	@Override
	public V remove(K key) {
		
		DListNode<Entry<K,V>> node = head;
		while( node!= null && node.getElement().getKey().compareTo(key) != 0 )
			node = node.getNext();
		
		//no such key
		if(node==null){
			return null;
		}else{
			//list has one element
			if(currentSize==1){
				head = null;
		        tail = null;
			}else{
				//removes head
				if(node== head){
					head.getNext().setPrevious(null);
					head = head.getNext();
				}else{
					//removes tail
					if(node == tail){
						tail.getPrevious().setNext(null);
						tail = tail.getPrevious();
					}else{
						//removes other
						node.getPrevious().setNext(node.getNext());
						node.getNext().setPrevious(node.getPrevious());
					}
				}
			}

			currentSize--;
			return node.getElement().getValue();
		}
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		
		//returns a normal double linked list iterator
		
		return new DoublyLLIterator<Entry<K,V>>(head, tail);
	}

	@Override
	public Entry<K, V> maxEntry() {
		return tail.getElement();
	}

	@Override
	public Entry<K, V> minEntry() {
		return head.getElement();
	}



}
