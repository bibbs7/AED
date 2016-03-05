package dataStructures;

public class EntryClass<K, V> implements Entry<K, V> {

	private static final long serialVersionUID = 0L;

	private K key;
	private V value;
	
	public EntryClass(K key, V value) {
		this.key = key;
		this.value = value;
	
	}

	@Override
	public K getKey() {
		
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	public K setKey( K newKey){
		
		K oldKey = key;
		
		key = newKey;
		
		return oldKey;
		
	}
	
	@Override
	public V setValue( V newValue) {
		V oldValue = value;
		
		value = newValue;
		
		return oldValue;
	}

}
