package meTube;

import java.io.Serializable;

public interface UserInterface extends UserSimpleInterface, Serializable{
	
	public void viewVideo(Video video);
	
	public void removeHistory();
	
	public void addFavorites(Video video);
	
	public void removeFavorites(Video video);
	
	public boolean isHistoryEmpty();
	
	public boolean isFavorite(Video video);
	
	public void uploadVideo(Video video);
	
	
	

}
