package com.fullerton.skimmdit.representation;

public class Link {
	
	private int id;
	private String title;
	private String description;
	private String url;
	private int votes;
	private String username;

	public Link(){

	}
	public Link(int id, String title,String description,String url,int votes, String username){
		this.title = title;
		this.description = description;
		this.url = url;
		this.votes = votes;
		this.username = username;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}