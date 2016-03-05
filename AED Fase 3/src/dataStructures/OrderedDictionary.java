package dataStructures;

public interface OrderedDictionary<K, V> extends Dictionary<K, V> {

	
	Entry<K,V> maxEntry();
	
	Entry<K,V> minEntry();
	
	
}
