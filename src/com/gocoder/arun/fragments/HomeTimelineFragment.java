package com.gocoder.arun.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import com.gocoder.arun.tweetxpress.TwitterClientApp;
import com.gocoder.arun.tweetxpress.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;

public class HomeTimelineFragment extends TweetsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterClientApp.getRestClient().getHomeTimeLine(new JsonHttpResponseHandler(){
			public void onSuccess(JSONArray jsonTweets){
				Log.d("DEBUG",jsonTweets.toString());
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				getAdapter().addAll(tweets);
			}
		});
	}
}
