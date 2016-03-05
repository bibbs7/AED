package meTube;

import dataStructures.Iterator;
import dataStructures.IteratableQueue;
import dataStructures.IteratableQueueInList;

public class Video implements VideoInterface {

	private static final long serialVersionUID = 0L;
	
	private String id;
	private String uploader;
	private String url;
	private int duration;
	private String title;
	
	//true se activo, false se inactivo
	private boolean state;
	
	private IteratableQueue<String> tags;
	
	public Video(String idVideo, String uploader, String url, int duration, String title) {
		this.id = idVideo;
		this.uploader = uploader;
		this.url = url;
		this.duration = duration;
		this.title = title;
		this.state = true;
		this.tags = new IteratableQueueInList<String>();
	}

	@Override
	public void deactivate() {
		state = false;
		
	}

	@Override
	public boolean getState() {
		return state;
	}

	@Override
	public void addTag(String tag)
	{
		tags.enqueue(tag);
	}

	@Override
	public Iterator<String> listTags()
	{
		return tags.list();
	}

	public String getId() {
		return id;
	}

	public int getDuration() {
		return duration;
	}

	public String getTitle() {
		return title;
	}

	public String toString()
	{
		if(this.state)
		{
			return this.id+" "+this.title+" "+this.duration+" "+"ativo";
		}
		else return this.id+" "+this.title+" "+this.duration+" "+"inativo";
	}

	public String getUploader(){
		return uploader;
	}
	
	public String getUrl(){
		return url;
	}

}
