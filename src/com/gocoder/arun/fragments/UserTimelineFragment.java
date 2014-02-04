package com.gocoder.arun.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;

import com.gocoder.arun.tweetxpress.TwitterClientApp;
import com.gocoder.arun.tweetxpress.models.Tweet;
import com.gocoder.arun.tweetxpress.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	onUserTimeLineListener callback;
	public interface onUserTimeLineListener{
		public void onUserTimeLineMessage(User obj);
	}
	@Override
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Long userid = (Long)getActivity().getIntent().getExtras().get("userid");
		TwitterClientApp.getRestClient().getUserTimeLine(userid, new JsonHttpResponseHandler(){
			public void onSuccess(JSONArray jsonTweets){
				//Log.d("DEBUG",jsonTweets.toString());
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				getAdapter().addAll(tweets);
				callback.onUserTimeLineMessage(tweets.get(0).getUser());
			}
		});
	};
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
			try {
				callback = (onUserTimeLineListener) activity;
			} catch (ClassCastException e) {
				 throw new ClassCastException(activity.toString()
		                    + " must implement onUserTimeLineListener");

			}
	}

}
