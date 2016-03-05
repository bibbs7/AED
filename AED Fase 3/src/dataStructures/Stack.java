package dataStructures;

import java.io.Serializable;
import ourExceptions.EmptyStackException;

public interface Stack<E> extends Serializable
{

    // Returns true iff the stack contains no elements.
    boolean isEmpty( );

    // Returns the number of elements in the stack.
    int size( );

    // Returns the element at the top of the stack.
    E top( ) throws EmptyStackException;

    // Inserts the specified element onto the top of the stack.
    void push( E element );

    // Removes and returns the element at the top of the stack.
    E pop( ) throws EmptyStackException;

}

