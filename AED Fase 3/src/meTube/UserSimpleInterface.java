package meTube;

import dataStructures.Iterator;
import dataStructures.Entry;

public interface UserSimpleInterface {
	
	public String getNick();
	
	public String getMail();
	
	public String getName();
	
	public Iterator<VideoSimpleInterface> listHistory();
	
	public Iterator<Entry<String, VideoSimpleInterface>> listFavorites();
	
	public Iterator<Entry<String, VideoSimpleInterface>> listVideos();
	
	public boolean hasFavorites();

}
