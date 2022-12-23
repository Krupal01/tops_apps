package com.example.onlinestorage.cricketgson;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private int userId;

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getBody(){
		return body;
	}

	public int getUserId(){
		return userId;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", title='" + title + '\'' +
				", body='" + body + '\'' +
				", userId=" + userId +
				'}';
	}
}