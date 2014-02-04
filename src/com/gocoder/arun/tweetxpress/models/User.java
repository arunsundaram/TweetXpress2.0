package com.gocoder.arun.tweetxpress.models;


import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String name;
	private long uid;
	private String screenName;
	private String profileBgImageUrl;
	private String tagLine;
	private String profileImageUrl;

	private int numTweets;
	private int followersCount;
	private int friendsCount;
	
    public String getName() {
        return name;
    }

    public long getId() {
        return uid;
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


    public String getProfileBackgroundImageUrl() {
        return profileBgImageUrl;
    }

    public int getNumTweets() {
        return numTweets;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public static User fromJson(JSONObject json) {
        User u = new User();
        try {
        	u.name = json.getString("name");
        	u.uid = json.getLong("id");
        	u.screenName = json.getString("screen_name");
        	u.profileBgImageUrl = json.getString("profile_background_image_url");
        	u.numTweets = json.getInt("statuses_count");
        	u.followersCount = json.getInt("followers_count");
        	u.friendsCount = json.getInt("friends_count");
        	u.profileImageUrl = json.getString("profile_image_url");
        	u.tagLine = json.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }

	@Override
	public String toString() {
		return "User [name=" + name + ", uid=" + uid + ", screenName="
				+ screenName + ", profileBgImageUrl=" + profileBgImageUrl
				+ ", tagLine=" + tagLine + ", profileImageUrl="
				+ profileImageUrl + ", numTweets=" + numTweets
				+ ", followersCount=" + followersCount + ", friendsCount="
				+ friendsCount + "]";
	}

	public CharSequence getTagLine() {
		// TODO Auto-generated method stub
		return tagLine;
	}


}
