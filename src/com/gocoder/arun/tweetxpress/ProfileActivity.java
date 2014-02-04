package com.gocoder.arun.tweetxpress;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.gocoder.arun.fragments.UserTimelineFragment.onUserTimeLineListener;
import com.gocoder.arun.tweetxpress.models.LoggedInUser;
import com.gocoder.arun.tweetxpress.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity implements onUserTimeLineListener {
	LoggedInUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("DEBUG","acreated");
		setContentView(R.layout.activity_profile);
	}

	private void loadProfileInfo(User user) {
		getActionBar().setTitle(user.getFormattedScreenName());
		populateProfileHeader(user);
	}

	private void populateProfileHeader(User user) {
		TextView tvName = (TextView) findViewById(R.id.tvName);
		TextView tvTagLine = (TextView) findViewById(R.id.tvTagLine);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		
		tvName.setText(user.getName());
		tvTagLine.setText(Html.fromHtml("<i>"+user.getTagLine().toString()+"</i>"));
		tvFollowers.setText(String.valueOf(user.getFollowersCount())+" Followers");
		tvFollowing.setText(String.valueOf(user.getFriendsCount())+" Following");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public void onUserTimeLineMessage(User obj) {
		loadProfileInfo(obj);
		
	}

}
