package meTube;

import ourExceptions.EmptyStackException;
import dataStructures.BinarySearchTree;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.IteratableStackInList;
import dataStructures.IteratableStack;
import dataStructures.OrderedDictionary;

public class User implements UserInterface{
	
	private static final long serialVersionUID = 0L;
	
	private String nick;
	private String mail;
	private String name;
	
	private IteratableStack<VideoSimpleInterface> history;
	private OrderedDictionary<String, VideoSimpleInterface> favorites;
	private OrderedDictionary<String, VideoSimpleInterface> addedVideos;
	
	

	public User(String nick, String mail, String name) {
		this.nick = nick;
		this.mail = mail;
		this.name = name;
	
		this.history    = new IteratableStackInList<VideoSimpleInterface>();
		this.favorites  = new BinarySearchTree<String, VideoSimpleInterface>();
		this.addedVideos= new BinarySearchTree<String, VideoSimpleInterface>();
	}

	@Override
	public void viewVideo(Video video) {
		history.push(video);
		
	}

	@Override
	public Iterator<VideoSimpleInterface> listHistory() {
		return history.list();
	}

	@Override
	public void removeHistory() {
		try{
			while(true){
				history.pop();
			}
		}catch(EmptyStackException e){}
		
	}

	@Override
	public void addFavorites(Video video) {
		favorites.insert(video.getId().toUpperCase(), video);
	}

	@Override
	public void removeFavorites(Video video) {
		favorites.remove(video.getId().toUpperCase());
		
	}

	@Override
	public Iterator<Entry<String, VideoSimpleInterface>> listFavorites() {
		
		return favorites.iterator();
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getName() {
		return name;
	}

	public boolean isHistoryEmpty(){
		return history.isEmpty();
	}

	@Override
	public boolean isFavorite(Video video) {
		return favorites.find(video.getId().toUpperCase()) != null;
	}

	@Override
	public boolean hasFavorites() {
		return !favorites.isEmpty();
	}

	
	@Override
	public void uploadVideo(Video video)
	{
		addedVideos.insert(video.getId().toUpperCase(), video);
	}

	@Override
	public Iterator<Entry<String, VideoSimpleInterface>> listVideos()
	{
		return addedVideos.iterator();
	}
	
	
}
