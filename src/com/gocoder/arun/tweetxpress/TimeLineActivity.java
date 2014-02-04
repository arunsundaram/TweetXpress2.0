package com.gocoder.arun.tweetxpress;

import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gocoder.arun.fragments.HomeTimelineFragment;
import com.gocoder.arun.fragments.MentionsFragment;
import com.gocoder.arun.fragments.TweetsListFragment;
import com.gocoder.arun.tweetxpress.models.LoggedInUser;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimeLineActivity extends FragmentActivity implements TabListener{
	TweetsListFragment fragmentTweets;
	private final int REQUESTCODE_COMPOSE = 20;
	private final int REQUESTCODE_PROFILE = 21;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
		setupNavigationTabs();
		
		
		if(isOnNetwork()){
			_getUserInfo();
			_getHomeTimeLine();
		}
		else{
			Toast.makeText(this, "no network", Toast.LENGTH_SHORT).show();
		}
	}

	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tabHome = actionBar.newTab().setText("home").setTag("HomeTimelineFragment").setIcon(R.drawable.ic_home).setTabListener(this);
		Tab tabMentions = actionBar.newTab().setText("Mentions").setTag("MentionsFragment").setIcon(R.drawable.ic_profile).setTabListener(this);
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);

		
	}

	private boolean isOnNetwork() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	private void _getHomeTimeLine(){

	}

	
	private void _getUserInfo(){
		TwitterClientApp.getRestClient().getUserDetails(new JsonHttpResponseHandler(){
			public void onSuccess(JSONObject jsonResponse){
				LoggedInUser userinfo = LoggedInUser.getInstance();
				userinfo.setUserInfo(jsonResponse);
				setTitle(userinfo.getFormattedScreenName());
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}
	
	public void onRefreshTweets(MenuItem mi){
		Toast.makeText(this, "refreshing", Toast.LENGTH_SHORT).show();
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		fts.replace(R.id.frame_container, new HomeTimelineFragment());
		fts.commit();
		
	}
	
	public void onComposeTweet(MenuItem mi){
		Intent i = new Intent(TimeLineActivity.this, ComposeTweetActivity.class);
		i.putExtra("user", LoggedInUser.getInstance());
		startActivityForResult(i,REQUESTCODE_COMPOSE);
	}
	
	public void onProfileView(MenuItem mi){
		Intent i = new Intent(TimeLineActivity.this, ProfileActivity.class);
		i.putExtra("userid", LoggedInUser.getInstance().getUid());
		startActivityForResult(i,REQUESTCODE_PROFILE);
	}
	

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	  if (resultCode == RESULT_OK && requestCode == REQUESTCODE_COMPOSE) {
    		  if(data.getExtras().getString("status").equals("POSTED")){
    			  this._getHomeTimeLine();
    		  }
    	  }
   }


	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		if(tab.getTag()=="HomeTimelineFragment"){
			//set to home timeline
			fts.replace(R.id.frame_container, new HomeTimelineFragment());
		}
		else{
			//set to mentions timeline
			fts.replace(R.id.frame_container, new MentionsFragment());
		}
		fts.commit();		
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}



}
