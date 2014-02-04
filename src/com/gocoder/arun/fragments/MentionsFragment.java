package com.gocoder.arun.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.util.Log;

import com.gocoder.arun.tweetxpress.TwitterClientApp;
import com.gocoder.arun.tweetxpress.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsFragment extends TweetsListFragment {
	@Override
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterClientApp.getRestClient().getMentions(new JsonHttpResponseHandler(){
			public void onSuccess(JSONArray jsonTweets){
				Log.d("DEBUG",jsonTweets.toString());
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				getAdapter().addAll(tweets);
			}
		});
		
	};
}
