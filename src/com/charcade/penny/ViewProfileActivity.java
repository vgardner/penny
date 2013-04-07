package com.charcade.penny;

import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.charcade.penny.BaseActivity;
import com.charcade.penny.R;
import com.charcade.penny.db.GoalDbManager;
import com.charcade.penny.entities.Goal;
import com.slidingmenu.lib.SlidingMenu;

public class ViewProfileActivity extends BaseActivity {
  
	public ViewProfileActivity() {
		super(R.string.app_name);
	}
/** Called when the activity is first created. */
  private ShareActionProvider mShareActionProvider;
  private OnNavigationListener mOnNavigationListener;
  private SlidingMenu slidingMenu;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.profile_view);
    enableSlidingMenu(slidingMenu);

    // Set view element texts and images.
    this.setTitle("Profile");
    
    TextView profileViewName = (TextView) findViewById(R.id.profile_view_email);
    profileViewName.setText("username@penny.com");
    
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
        case android.R.id.home:
        	this.finish();
        default:
            return super.onOptionsItemSelected(item);
    }
  }
}
