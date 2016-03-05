package dataStructures;

public class IteratableQueueInList<E> extends QueueInList<E> implements IteratableQueue<E>{

	private static final long serialVersionUID = 0L;

	public IteratableQueueInList() {
		super();
	}
	
	@Override
	public Iterator<E> list() {
		return list.iterator();
	}

}
