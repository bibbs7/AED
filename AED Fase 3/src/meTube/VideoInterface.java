package meTube;

import java.io.Serializable;

public interface VideoInterface extends VideoSimpleInterface, Serializable{
	
	public void deactivate();
	
	public void addTag(String tag);
	
}
