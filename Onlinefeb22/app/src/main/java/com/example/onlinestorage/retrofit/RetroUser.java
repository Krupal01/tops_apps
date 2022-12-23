package com.example.onlinestorage.retrofit;

import com.google.gson.annotations.SerializedName;

public class RetroUser{

	@SerializedName("createdby")
	private String createdby;

	@SerializedName("firstappearance")
	private String firstappearance;

	@SerializedName("imageurl")
	private String imageurl;

	@SerializedName("name")
	private String name;

	@SerializedName("publisher")
	private String publisher;

	@SerializedName("bio")
	private String bio;

	@SerializedName("team")
	private String team;

	@SerializedName("realname")
	private String realname;

	public String getCreatedby(){
		return createdby;
	}

	public String getFirstappearance(){
		return firstappearance;
	}

	public String getImageurl(){
		return imageurl;
	}

	public String getName(){
		return name;
	}

	public String getPublisher(){
		return publisher;
	}

	public String getBio(){
		return bio;
	}

	public String getTeam(){
		return team;
	}

	public String getRealname(){
		return realname;
	}

	@Override
	public String toString() {
		return "RetroUser{" +
				"createdby='" + createdby + '\'' +
				", firstappearance='" + firstappearance + '\'' +
				", imageurl='" + imageurl + '\'' +
				", name='" + name + '\'' +
				", publisher='" + publisher + '\'' +
				", bio='" + bio + '\'' +
				", team='" + team + '\'' +
				", realname='" + realname + '\'' +
				'}';
	}
}