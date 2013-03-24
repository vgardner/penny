package com.charcade.penny.habits;

import android.app.ActionBar.OnNavigationListener;
import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.charcade.penny.BaseActivity;
import com.charcade.penny.R;
import com.slidingmenu.lib.SlidingMenu;

public class AddHabitActivity extends BaseActivity {
  
	public AddHabitActivity() {
		super(R.string.app_name);
	}
/** Called when the activity is first created. */
  private ShareActionProvider mShareActionProvider;
  private OnNavigationListener mOnNavigationListener;
  private SlidingMenu slidingMenu;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.habit_add);
    enableSlidingMenu(slidingMenu);  
  }
  @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	slidingMenu.toggle();
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
} 