package meTube;

import java.io.Serializable;

import ourExceptions.*;
import dataStructures.*;

public interface MeTubeInterface extends Serializable {

	
	
	public void addUser(String nick, String mail, String name) 
			throws UserAleradyExsitsException ;
	
	public void addVideo(String idVideo, String nick, String url, int duration, String title) 
			throws VideoAleradyExsitsException,UserDoesNotExistException,InvalidDurationException;
	
	public void deactivateVideo(String idVideo) 
			throws VideoDoesNotExistException, InnactiveVideoException;
	
	public void viewVideo(String idVideo, String nick) 
			throws VideoDoesNotExistException, InnactiveVideoException, UserDoesNotExistException;
	
	public Iterator<Entry<String, VideoSimpleInterface>> listVideos(String nick) 
			throws UserDoesNotExistException, NoUploadedVideosException;
	
	public  Iterator<VideoSimpleInterface> listHistory(String nick) 
			throws UserDoesNotExistException, EmptyHistoryException;
	
	public void removeHistory(String nick) 
			throws UserDoesNotExistException;
	
	public void addFavorites(String idVideo, String nick) 
			throws VideoDoesNotExistException, InnactiveVideoException, UserDoesNotExistException, VideoAlreadyFavoriteException;
	
	public void removeFavorites(String idVideo, String nick) 
			throws VideoDoesNotExistException, UserDoesNotExistException, VideoNotFavoriteException;
	
	public Iterator<Entry<String,VideoSimpleInterface>> listFavorites(String nick) 
			throws UserDoesNotExistException, NoFavoritesException;
	
	public void addTag(String idVideo, String tag)
			throws VideoDoesNotExistException, InnactiveVideoException, TagAlreadyExsitsException;
	
	public Iterator<String> listTags(String idVideo) 
			throws VideoDoesNotExistException, VideoHasNoTagsException;
	//fase 2 e 3 este metodo retornara um Iterator<String>
	
	public Iterator<Entry<String,VideoSimpleInterface>> searchTag(String tag) throws TagDoesNotExistException;
}
