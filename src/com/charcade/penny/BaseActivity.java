package com.charcade.penny;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.slidingmenu.lib.SlidingMenu;

public class BaseActivity extends FragmentActivity {

	private int mTitleRes;
	protected ListFragment mFrag;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(mTitleRes);
		setContentView(R.layout.front_main);

	    // Set up the action bar.
	    final ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_main, menu);	
	    return true;
	}
	
	public void enableSlidingMenu(SlidingMenu slidingMenu){
		// configure the SlidingMenu
	    slidingMenu = new SlidingMenu(this);
	    slidingMenu.setMode(SlidingMenu.LEFT);
	    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	    slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
	    slidingMenu.setShadowDrawable(R.drawable.shadow);
	    //slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
	    slidingMenu.setBehindWidthRes(R.dimen.slidingmenu_width);
	    slidingMenu.setFadeDegree(0.35f);
	    slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
	    slidingMenu.setMenu(R.layout.left_menu);
	}
}
