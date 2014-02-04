package com.gocoder.arun.tweetxpress.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class LoggedInUser implements Serializable{

	private static final long serialVersionUID = 5971848094938937963L;
	private String name;
	private String screenName;
	private String profileImageUrl;

	private String tagLine;
	private int followers;
	private int following;
	private long uid;
	private static LoggedInUser instance;
	
	private LoggedInUser(){
		name="";
		screenName="";
	}
	
	public String getName() {
		return name;
	}
	public String getScreenName() {
		return screenName;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public String getFormattedScreenName(){
		return "@"+screenName;
	}
	public String getTagLine() {
		return tagLine;
	}

	public int getFollowers() {
		return followers;
	}

	public int getFollowing() {
		return following;
	}
	
	public String getFollowersString(){
		return String.valueOf(followers)+" Followers";
	}
	public String getFollowingString(){
		return String.valueOf(following)+" Following";
	}

	public long getUid() {
		return uid;
	}

	public static LoggedInUser getInstance(){
		if(instance == null){
			instance  = new LoggedInUser();
		}
		return instance;
	}
	
	public void setUserInfo(JSONObject json){
		try {
			name = json.getString("name");
			screenName = json.getString("screen_name");
			profileImageUrl = json.getString("profile_image_url");
			tagLine = json.getString("description");
        	followers = json.getInt("followers_count");
        	uid = json.getLong("id");
        	following = json.getInt("friends_count");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "LoggedInUser [name=" + name + ", screenName=" + screenName
				+ ", profileImageUrl=" + profileImageUrl + ", tagLine="
				+ tagLine + ", followers=" + followers + ", following="
				+ following + "]";
	}
	
	
	
}
