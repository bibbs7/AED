package dataStructures;

import ourExceptions.NoSuchElementException;


public class ChainedHashTableIterator<K extends Comparable<K>, V> implements Iterator<Entry<K,V>>{

	private static final long serialVersionUID = 0L;

	//dictionary array to iterate
	private Dictionary<K,V>[] table;
	
	//variable to iterate current entrance of the array 
	private Iterator<Entry<K,V>> currentIterator;
	
	//current array position being iterated
	private int currentTableEntry;
	
	// one assumes no item will be inserted in the middle of iteration
	private Entry<K,V> nextToReturn;
	
	
	public ChainedHashTableIterator(Dictionary<K,V>[] table) {
		this.table = table;
		rewind();
	}

	@Override
	public boolean hasNext() {
		
		return nextToReturn != null;
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		
		if(!hasNext())
			throw new NoSuchElementException();
		
		
		if(currentIterator.hasNext()){
			Entry<K, V> returnNow;
			
			returnNow = nextToReturn;
			nextToReturn = currentIterator.next();
			
			return returnNow;
		//current iterator !hasNext()	
		}else{
			//iterating the last entry of the table
			if(currentTableEntry==table.length-1){
				Entry<K, V> lastEntry = nextToReturn;
				if(currentIterator.hasNext()){
					nextToReturn = currentIterator.next();
				}else{
					nextToReturn = null;
				}
				
				return lastEntry;
			}
			//iterating any other table entry
			else{
				currentTableEntry++;
				currentIterator = table[currentTableEntry].iterator();
				return next();
			}
		}
	}

	@Override
	public void rewind() {
		
		//probs quando size = 0
		currentTableEntry = 0;
		currentIterator = table[currentTableEntry].iterator();
		nextToReturn = null;
		
		while(nextToReturn == null){
			if(currentIterator.hasNext()){
				nextToReturn = currentIterator.next();
			}else{
				//list is empty
				if(currentTableEntry==table.length-1){
						break;
				}else{
					currentTableEntry++;
					currentIterator = table[currentTableEntry].iterator();
				}
				
			}
		}
	}

}
