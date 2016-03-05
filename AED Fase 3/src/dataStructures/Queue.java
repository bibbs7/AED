package dataStructures;

import java.io.Serializable;
import ourExceptions.EmptyQueueException;

public interface Queue<E> extends Serializable
{

    // Returns true iff the queue contains no elements.
    boolean isEmpty( );

    // Returns the number of elements in the queue.
    int size( );

    // Inserts the specified element at the rear of the queue.
    void enqueue( E element );

    // Removes and returns the element at the front of the queue.             
    E dequeue( ) throws EmptyQueueException;

}                                                                       
