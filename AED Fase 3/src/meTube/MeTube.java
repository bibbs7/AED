package meTube;

import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.OrderedDictionary;
import dataStructures.BinarySearchTree;
import dataStructures.Iterator;
import dataStructures.Entry;
import ourExceptions.*;


public class MeTube implements MeTubeInterface{

	private static final long serialVersionUID = 0L;
	public static final int N_EXPECTED_USERS = 2000;
	public static final int N_EXPECTED_VIDEOS = 5000;

	private Dictionary<String, User> users;
	private Dictionary<String, Video> videos;
	private Dictionary<String, OrderedDictionary<String, VideoSimpleInterface>> tags;
	
	
	public MeTube() {
		users = new ChainedHashTable<String, User>(N_EXPECTED_USERS);
		videos = new ChainedHashTable<String, Video>(N_EXPECTED_VIDEOS);
		
		tags = new ChainedHashTable<String, OrderedDictionary<String, VideoSimpleInterface> >();
	}

	@Override
	public void addUser(String nick, String mail, String name) throws UserAleradyExsitsException{
		
		User user = users.find(nick.toUpperCase());
		
		if (user!=null){
			throw new UserAleradyExsitsException();
		}else{
			user = new User(nick, mail, name);
			users.insert(nick.toUpperCase(), user);
		}
		
	}

	@Override
	public void addVideo(String idVideo, String uploader, String url, int duration,	String title) 
			throws VideoAleradyExsitsException, UserDoesNotExistException,InvalidDurationException{
		
		//to avoid throwing the wrong exception if multiple aplly
		//we won't change the order in wich they are checked
		//at the cost of this method being less eficient
		
		Video video = videos.find(idVideo.toUpperCase());
		
		
		if(video != null){
			throw new VideoAleradyExsitsException();
		}else{
			
			User user = users.find(uploader.toUpperCase());
			
			if(user == null){
				throw new UserDoesNotExistException();
			}else{
				if(duration < 0){
					throw new InvalidDurationException();
				}else{
					video = new Video(idVideo, uploader, url, duration, title);
					user.uploadVideo(video);
					videos.insert(idVideo.toUpperCase(), video);
					
				}
			}
		}
	}

	@Override
	public void deactivateVideo(String idVideo) throws VideoDoesNotExistException, InnactiveVideoException{
		
		Video video = videos.find(idVideo.toUpperCase());
		
		if(video == null){
			throw new VideoDoesNotExistException();
		}else{
			if(!video.getState()){
				throw new InnactiveVideoException();
			}else{
				video.deactivate();
			}
		}
		
	}

	@Override
	public void viewVideo(String idVideo, String nick) 
			throws VideoDoesNotExistException, InnactiveVideoException, UserDoesNotExistException{
		Video video = videos.find(idVideo.toUpperCase());
		User user = users.find(nick.toUpperCase());
		
		if(video == null){
			throw new VideoDoesNotExistException();
		}else{
			if(!video.getState()){
				throw new InnactiveVideoException();
			}else{
				if(user==null){
					throw new UserDoesNotExistException();
				}else{
					user.viewVideo(video);
				}
			}
		}
		
	}

	@Override
	public Iterator<Entry<String,VideoSimpleInterface>> listVideos(String nick) throws UserDoesNotExistException, NoUploadedVideosException {

		User user=users.find(nick.toUpperCase()); 
		if(user==null){
			throw new UserDoesNotExistException();
		}else{
			Iterator<Entry<String,VideoSimpleInterface>> it = user.listVideos();

			if(!it.hasNext()){
				throw new NoUploadedVideosException();
			}else{
				return it;
			}
		}
	}
	
	@Override
	public Iterator<VideoSimpleInterface> listHistory(String nick) throws UserDoesNotExistException, EmptyHistoryException {
		User user=users.find(nick.toUpperCase()); 
		if(user==null){
			throw new UserDoesNotExistException();
		}else{
			if(user.isHistoryEmpty()){
				throw new EmptyHistoryException();
			}else{
				return user.listHistory();
			}
		}
	}

	@Override
	public void removeHistory(String nick) throws UserDoesNotExistException{

		User userf=users.find(nick.toUpperCase());
		if(userf == null){
			throw new UserDoesNotExistException();
		}else{
			userf.removeHistory();
		}
		
	}

	@Override
	public void addFavorites(String idVideo, String nick) 
			throws VideoDoesNotExistException, InnactiveVideoException, UserDoesNotExistException, VideoAlreadyFavoriteException{
		User user=users.find(nick.toUpperCase()); 
		Video video = videos.find(idVideo.toUpperCase());
		
		if(video == null ){
			throw new VideoDoesNotExistException();
		}else{
			if(!video.getState()){
				throw new InnactiveVideoException();
			}else{
				if(user==null ){
					throw new UserDoesNotExistException();
				}else{
					if(user.isFavorite(video)){
						throw new VideoAlreadyFavoriteException();
					}else{
						user.addFavorites(video);
						
					}
				}
			}
		}
		
	}

	@Override
	public void removeFavorites(String idVideo, String nick) 
			throws VideoDoesNotExistException, UserDoesNotExistException, VideoNotFavoriteException{
		User user=users.find(nick.toUpperCase()); 
		Video video = videos.find(idVideo.toUpperCase());
		
		if(video == null){
			throw new VideoDoesNotExistException();
		}else{
			if(user==null ){
				throw new UserDoesNotExistException();
			}else{
				if(!user.isFavorite(video)){
					throw new VideoNotFavoriteException();
				}else{
					user.removeFavorites(video);
					
				}
			}
		}
		
	}

	@Override
	public Iterator<Entry<String,VideoSimpleInterface>> listFavorites(String nick) throws UserDoesNotExistException, NoFavoritesException {
		User user=users.find(nick.toUpperCase());
		if(user == null ){
			throw new UserDoesNotExistException();
		}else{
			if(!user.hasFavorites()){
				throw new NoFavoritesException();
			}else{
				
		 		return user.listFavorites();
			
			}
		}
	}

	@Override
	public void addTag(String idVideo, String tag) throws VideoDoesNotExistException, InnactiveVideoException, TagAlreadyExsitsException{
		
		Video video = videos.find(idVideo.toUpperCase());
		
		if(video == null ){
			throw new VideoDoesNotExistException();
		}else{
			if(!video.getState()){
				throw new InnactiveVideoException();
			}else{
				
				//tag
				
				//tag does  not exist
				if(tags.find(tag.toUpperCase()) == null ){
					OrderedDictionary<String, VideoSimpleInterface> taggedVideos = new BinarySearchTree<String, VideoSimpleInterface>();
					
					taggedVideos.insert(idVideo.toUpperCase(), video);
					tags.insert(tag.toUpperCase(), taggedVideos);
					
				}else{
					
					//the tag is already associated with the video
					if(tags.find(tag.toUpperCase()).find(idVideo.toUpperCase()) != null)
						throw new TagAlreadyExsitsException();
					
					//tag already exists
					tags.find(tag.toUpperCase()).insert(idVideo.toUpperCase(), video);
				}
				
				//video
				video.addTag(tag);
				
				
			}
		}
	}

	@Override
	public Iterator<String> listTags(String idVideo) throws VideoDoesNotExistException, VideoHasNoTagsException {

		Video video = videos.find(idVideo.toUpperCase());
		
		if(video == null ){
			throw new VideoDoesNotExistException();
		}else{
			Iterator<String> it = video.listTags();
			
			if(it.hasNext()){
				return it;
			}else{
				throw new VideoHasNoTagsException();
			}
		}
	}

	@Override
	public Iterator<Entry<String,VideoSimpleInterface>> searchTag(String tag) throws TagDoesNotExistException{

		

			if(tags.find(tag.toUpperCase()) == null){
			throw new TagDoesNotExistException();
		}else{
			return tags.find(tag.toUpperCase()).iterator();
		}
		
	}

	

}
