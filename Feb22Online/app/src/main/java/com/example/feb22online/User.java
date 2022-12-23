package com.example.feb22online;


import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("id")
	private String id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
	public String toString() {
		return "User{" +
				"mobile='" + mobile + '\'' +
				", lastName='" + lastName + '\'' +
				", id='" + id + '\'' +
				", firstName='" + firstName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}