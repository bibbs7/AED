package dataStructures;  

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 

	
    static final long serialVersionUID = 0L;


    // The array of dictionaries.
    protected Dictionary<K,V>[] table;


    @SuppressWarnings("unchecked")
    public ChainedHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new OrderedDoubleList<K,V>();
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                


    // Returns the hash value of the specified key.
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    protected void rehash(){
    	
    	//creates new ChainedHashTable with double the maxSize
    	ChainedHashTable<K,V> newTable = new ChainedHashTable<K,V>( this.maxSize*2);
    	
    	Iterator<Entry<K,V>> it = this.iterator();
    	Entry<K,V> entry = null;

    	//copies every entry of the full table to the new table
    	while(it.hasNext()){
    		entry = it.next();
    		newTable.insert(entry.getKey(), entry.getValue());
    	}
    	
    	this.table = newTable.table;
    	
    	
    }
    
    // If there is an entry in the dictionary whose key is the specified key,
    // returns its value; otherwise, returns null.
     public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // replaces its value by the specified value and returns the old value;
    // otherwise, inserts the entry (key, value) and returns null.
    public V insert( K key, V value )
    {
        if ( this.isFull() )
             this.rehash();
        
        V oldvalue = table[this.hash(key)].insert(key, value);
        
        if(oldvalue == null)
        		currentSize++;

       return oldvalue;
        
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // removes it from the dictionary and returns its value;
    // otherwise, returns null.
    public V remove( K key )
    {
    	V oldvalue = table[this.hash(key)].remove(key);
        
        if(oldvalue != null)
        		currentSize--;
    	
    	return oldvalue;
    }


    // Returns an iterator of the entries in the dictionary.
    public Iterator<Entry<K,V>> iterator( )
    {
        return new ChainedHashTableIterator<K,V>(table);
    } 


}


