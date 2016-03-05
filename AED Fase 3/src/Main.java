import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;





import dataStructures.Iterator;
import dataStructures.Entry;
import ourExceptions.EmptyHistoryException;
import ourExceptions.InnactiveVideoException;
import ourExceptions.InvalidDurationException;
import ourExceptions.NoFavoritesException;
import ourExceptions.NoSuchElementException;
import ourExceptions.NoUploadedVideosException;
import ourExceptions.TagAlreadyExsitsException;
import ourExceptions.TagDoesNotExistException;
import ourExceptions.UserAleradyExsitsException;
import ourExceptions.UserDoesNotExistException;
import ourExceptions.VideoAleradyExsitsException;
import ourExceptions.VideoAlreadyFavoriteException;
import ourExceptions.VideoDoesNotExistException;
import ourExceptions.VideoHasNoTagsException;
import ourExceptions.VideoNotFavoriteException;
import meTube.*;


public class Main {

	private static final String EXIT = "XS";
	private static final String filename="Slavery is Awesome.";
	
	private enum Messages
	{
		//extra
		ponto("."),
		//add user
		sucessToAddUser("Insercao de utilizador com sucesso."),
		failToAddUser("Utilizador existente."),
		//add video
		sucessToAddVideo("Video adicionado com sucesso."),
		videoExistente("Video existente."),
		nickInexistente("Nick inexistente."),
		dudracaoInvalida("Duracao invalida."),
		//deactivate video
		sucessToDeactivae("Video desativado com sucesso."),
		videoInexistente("Video inexistente."),
		videoInativo("Video inativo."),
		//visualizar video..
		sucessToViewVideo("Video visualizado com sucesso."),
		//listar historico
		historicoVazio("Historico vazio."),
		//Remover Historico
		successToCleanHistory("Limpeza de historico efetuada com sucesso."),
		//add to favs
		successToAdToFavs("Video adicionado a favoritos com sucesso."),
		alreadyFav("Video ja e favorito de nick."),
		//remove of favs
		sucessToRemoveOfFavs("Video removido de favoritos com sucesso."),
		notFav("Video nao e favorito de nick."),
		//listar fav
		hasNoFavs("Utilizador nao tem favoritos."),
		//add tag
		sucessToAddTag("Tag adicionada a video com sucesso."),
		hasTagAlrdy("Video ja tem tag."),
		//Listar tags
		hasNoTags("Video nao tem tags."),
		//pesquisar por tags
		tagInexistente("Tag inexistente."),
		//listar videos de dado nick
		noVideosAdded("Nick nao inseriu videos."),
	
		sair("Gravando e terminando...");
		
	
		
	
		private String content;
		private Messages(String content)
		{
			this.content=content;
		}
		public String toString()
		{
			return this.content;
		}
	}
	
	public static void main(String[] args) {

		//interpretador de comandos
		 Scanner in = new Scanner(System.in);
		 MeTubeInterface meTube = load();
		 
		 String[] comm = getCommand(in);
	  		 
		 while (!comm[0].equals(EXIT)){
			 

//			 for(int i = 0 ; i < comm.length; i++){
//				 System.out.println(comm[i]);
//			 }
			 
			 
			 switch (comm[0].toUpperCase()) {
			 //inserir utilizador
			 //IU nick email nome
			 case "IU":
				 addUser(meTube, comm[1], comm[2], comm[3]);
				 break;
				 
			 // inserir video
			 // IV idVideo nick URL duracao titulo
			 case "IV":
				 //correcting long titles
				 for(int i = 6 ; i < comm.length; i++){
					 comm[5] = comm[5] + " " + comm[i];
				 }
				 addVideo(meTube, comm[1], comm[2], comm[3], comm[4], comm[5]);
				 break;
				 
			 //desactivar video
			 //DV idVideo
			 case "DV":
				 deactivateVideo(meTube, comm[1]);
				 break;
			
			 //visualizar video
			 //VV idVideo nick
			 case "VV":
				 viewVideo(meTube, comm[1], comm[2]);
				 break;
				 
			 //listar historico
			 //LH nick
			 case "LH":
				 listHistory(meTube, comm[1]);
				 break;
				 
			 //remover historico
			 //RH nick
			 case "RH":
				 removeHistory(meTube, comm[1]);
				 break;
				 
			 //adicionar video aos favoritos
			 //FV idVideo nick
			 case "FV":
				 favoriteVideo(meTube, comm[1], comm[2]);
				 break;
			 
			 //remover video dos favoritos
			 //RV idVideo nick
			 case "RV":
				 removeFavorite(meTube, comm[1], comm[2]);
				 break;
				 
			 //listar favoritos
			 //LF nick
			 case "LF":
				 listFavorites(meTube, comm[1]);
				 break;
			
			 //adicionar tag ao video
			 //TV idVideo tag
			 case "TV":
				 tagVideo(meTube, comm[1], comm[2]);
				 break;
				 
			 //listar tags do video
			 //LT idVideo
			 case "LT":
				 listTags(meTube, comm[1]);
				 break;
				 
			 //pesquisar tag
			 //PV tag
			 case "PV":
				 searchTag(meTube, comm[1]);
				 break;
			 case "LV":
				 	listVideos(meTube, comm[1]);
				 	break;
				 
			 default:
				 System.out.println("ERRO : BAD COMAND");
			 }
			 System.out.println();
			 comm = getCommand(in);
		 }
		 
		 try
		 {
			 save(meTube);
		 }
		 catch(IOException e)
		 {
			System.out.println("error saving"); 
		 }
		 System.out.println(Messages.sair.toString());
		 System.out.println();
		 in.close();
	}


	private static void addUser(MeTubeInterface meTube ,String nick, String mail, String name)
	{
			try
			{
				meTube.addUser(nick, mail, name);
				System.out.println(Messages.sucessToAddUser.toString());
			}
			catch(UserAleradyExsitsException e)
			{
				System.out.println(Messages.failToAddUser.toString());
			}
			
	}


	private static void addVideo(MeTubeInterface meTube, String idVideo, String nick, String url,String duration, String title) 
	{
		int n=Integer.parseInt(duration);
			try
			{
				meTube.addVideo(idVideo, nick, url, n, title);
				System.out.println(Messages.sucessToAddVideo.toString());
			}
			catch(VideoAleradyExsitsException e)
			{
				System.out.println(Messages.videoExistente.toString());
			}
			catch(UserDoesNotExistException e)
			{
				System.out.println(Messages.nickInexistente.toString());
			}
			catch(InvalidDurationException e)
			{
				System.out.println(Messages.dudracaoInvalida.toString());
			}
	}


	private static void deactivateVideo(MeTubeInterface meTube,String idVideo)
	{
			try
			{
				meTube.deactivateVideo(idVideo);
				System.out.println(Messages.sucessToDeactivae.toString());
			}
			catch(VideoDoesNotExistException e)
			{
				System.out.println(Messages.videoInexistente.toString());
			}
			catch(InnactiveVideoException e)
			{
				System.out.println(Messages.videoInativo.toString());
			}
			
	}

	
	private static void viewVideo(MeTubeInterface meTube,String idVideo, String nick)
	{
			try
			{
				meTube.viewVideo(idVideo, nick);
				System.out.println(Messages.sucessToViewVideo.toString());
			}
			catch(VideoDoesNotExistException e)
			{
				System.out.println(Messages.videoInexistente.toString());
			}
			catch(UserDoesNotExistException e)
			{
				System.out.println(Messages.nickInexistente.toString());
			}
			catch(InnactiveVideoException e)
			{
				System.out.println(Messages.videoInativo.toString());
			}
	}

	
	private static void listHistory(MeTubeInterface meTube,String nick)
	{
		Iterator<VideoSimpleInterface> it;
		try
		{
			it=meTube.listHistory(nick);
			while(it.hasNext())
			{
				System.out.println(it.next().toString());
			}
		}
		catch(UserDoesNotExistException e)
		{
			System.out.println(Messages.nickInexistente.toString());
		}
		catch(EmptyHistoryException e)
		{
			System.out.println(Messages.historicoVazio.toString());
		}
		catch(NoSuchElementException e){}
	}
	
	
	private static void removeHistory(MeTubeInterface meTube,String nick) 
	{
		try
		{
			meTube.removeHistory(nick);
			System.out.println(Messages.successToCleanHistory.toString());
		}
		catch(UserDoesNotExistException e)
		{
			System.out.println(Messages.nickInexistente.toString());
		}
	}


	private static void favoriteVideo(MeTubeInterface meTube,String idVideo, String nick) 
	{
		try
		{
			meTube.addFavorites(idVideo, nick);
			System.out.println(Messages.successToAdToFavs.toString());
		}
		catch(VideoDoesNotExistException e)
		{
			System.out.println(Messages.videoInexistente.toString());
		}
		catch(InnactiveVideoException e)
		{
			System.out.println(Messages.videoInativo.toString());
		}
		catch(UserDoesNotExistException e)
		{
			System.out.println(Messages.nickInexistente.toString());
		}
		catch(VideoAlreadyFavoriteException e)
		{
			System.out.println(Messages.alreadyFav.toString());
		}
	}


	private static void removeFavorite(MeTubeInterface meTube,String idVideo, String nick)
	{
		try
		{
			meTube.removeFavorites(idVideo, nick);
			System.out.println(Messages.sucessToRemoveOfFavs.toString());
		}
		catch(VideoDoesNotExistException e)
		{
			System.out.println(Messages.videoInexistente.toString());
		}
		catch(UserDoesNotExistException e)
		{
			System.out.println(Messages.nickInexistente.toString());
		}
		catch(VideoNotFavoriteException e)
		{
			System.out.println(Messages.notFav.toString());
		}
	}


	private static void listFavorites( MeTubeInterface meTube, String nick) 
	{
		Iterator<Entry<String, VideoSimpleInterface>> it;
		try
		{
			it=meTube.listFavorites(nick);
			while(it.hasNext())
			{
				System.out.println(it.next().getValue().toString());
			}
		}
		catch(UserDoesNotExistException e)
		{
			System.out.println(Messages.nickInexistente.toString());
		}
		catch(NoFavoritesException e)
		{
			System.out.println(Messages.hasNoFavs.toString());
		}
		catch(NoSuchElementException e){};
	}


	private static void tagVideo(MeTubeInterface meTube, String idVideo, String tag)
	{
		try
		{
			meTube.addTag(idVideo, tag);
			System.out.println(Messages.sucessToAddTag.toString());
		}
		catch(VideoDoesNotExistException e)
		{
			System.out.println(Messages.videoInexistente.toString());
		}
		catch(InnactiveVideoException e)
		{
			System.out.println(Messages.videoInativo.toString());
		}
		catch(TagAlreadyExsitsException e)
		{
			System.out.println(Messages.hasTagAlrdy.toString());
		}
		
	}


	private static void listTags(MeTubeInterface meTube, String idVideo) 
	{
		Iterator<String> tag;
		try
		{
			tag=meTube.listTags(idVideo);
			while(tag.hasNext())
			{
				System.out.println(tag.next());
			}
		}
		catch(VideoDoesNotExistException e)
		{
			System.out.println(Messages.videoInexistente.toString());
		}
		catch(VideoHasNoTagsException e)
		{
			System.out.println(Messages.hasNoTags.toString());
		}
	}


	private static void searchTag(MeTubeInterface meTube,String tag)
	{
		try
		{
			Iterator<Entry< String, VideoSimpleInterface>> it = meTube.searchTag(tag);
			
			while(it.hasNext()){
				
				System.out.println(it.next().getValue().toString());
			}
			
		}
		catch(TagDoesNotExistException e)
		{
			System.out.println(Messages.tagInexistente.toString());
		}
	}


	private static String[] getCommand(Scanner in) {
		String[] input;
		input = in.nextLine().split(" ");
		in.nextLine();
		return input;
	}
	
	private static void listVideos(MeTubeInterface meTube ,String nick)
	{
		try
		{
			Iterator<Entry<String, VideoSimpleInterface>> it = meTube.listVideos(nick);
			while (it.hasNext()){
				System.out.println(it.next().getValue().toString());
			}
		}
		catch(UserDoesNotExistException e)
		{
			System.out.println(Messages.nickInexistente.toString());
		}
		catch(NoUploadedVideosException e)
		{
				System.out.println(Messages.noVideosAdded.toString());
		}
	}
	
	private static MeTubeInterface load()
	{
		
		MeTubeInterface c = null;
		ObjectInputStream inStream =null;
		try
		{
			inStream= new ObjectInputStream(new FileInputStream(filename));
			c = (MeTubeInterface) inStream.readObject();
		}
		catch(IOException e){
			c = new MeTube();
		}catch(ClassNotFoundException e){

			System.out.println("Algo de muito estranho aconteceu no load...");
		}
		finally
		{
			if(inStream!=null)
			{	
				try{
					inStream.close();
				}catch(IOException e){
					System.out.println("Algo de muito estranho aconteceu no load... versao 2");
				}
			}
			
		}
		return c;
	}
	
	private static void save(MeTubeInterface control)throws IOException
	{
		
		ObjectOutputStream outStream = null;
		try {
			outStream =new ObjectOutputStream(new FileOutputStream(filename));
			outStream.writeObject(control);
		}
		finally{
			outStream.flush();
			outStream.close();
		}
	
	}

}
