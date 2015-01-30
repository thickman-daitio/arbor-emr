package com.daitio.arboremr;

import org.bson.types.ObjectId;

public class Encounter {
	
	
	private ObjectId id;
	private String comments;
	private String commentshtml;
	
//	public String getCommentsHtml(){
//		return commentshtml;
//	}
//	
//	public void setCommentsHtml(String comments){
//		 commentshtml ="<li>" + comments + "</li>";
//	}
//		
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
		
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
}
