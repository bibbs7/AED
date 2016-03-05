package dataStructures;

public class IteratableStackInList<E> extends StackInList<E> implements
		IteratableStack<E> {

	private static final long serialVersionUID = 0L;

	@Override
	public Iterator<E> list() {
		// TODO Auto-generated method stub
		return list.iterator();
	}

}
