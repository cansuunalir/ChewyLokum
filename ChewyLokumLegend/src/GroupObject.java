import java.util.ArrayList;

import Model.BoardObject;


public abstract class GroupObject {
	
	private Integer type;
	private ArrayList<BoardObject> groupObjects;
	
	public GroupObject(){
		groupObjects = null;
		type = null;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ArrayList<BoardObject> getGroupObjects() {
		return groupObjects;
	}

	public void setGroupObjects(ArrayList<BoardObject> groupObjects) {
		this.groupObjects = groupObjects;
	}
	

}
