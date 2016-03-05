package dataStructures;

import java.io.Serializable;

public interface Entry<K,V> extends Serializable
{

    // Returns the key in the entry.
    K getKey( );

    // Returns the value in the entry.
    V getValue( );
    
    //updates the key
    K setKey( K newKey);
    
    // updates the value
    V setValue( V newValue );
    
    
}
