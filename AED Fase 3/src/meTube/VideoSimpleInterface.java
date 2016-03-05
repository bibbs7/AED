package meTube;

import dataStructures.Iterator;

public interface VideoSimpleInterface {

	
	public String getId();
	
	public int getDuration();
	
	public String getTitle();
	
	public String toString();
	
	public boolean getState();
	
	public Iterator<String> listTags();
}
