package com.charcade.penny.habits;

import android.app.ActionBar.OnNavigationListener;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.charcade.penny.BaseActivity;
import com.charcade.penny.R;
import com.charcade.penny.db.HabitDbHelper;
import com.charcade.penny.db.HabitDbManager;
import com.charcade.penny.db.HabitDbMap;
import com.slidingmenu.lib.SlidingMenu;

public class ViewHabitActivity extends BaseActivity {
  
	public ViewHabitActivity() {
		super(R.string.app_name);
	}
/** Called when the activity is first created. */
  private ShareActionProvider mShareActionProvider;
  private OnNavigationListener mOnNavigationListener;
  private SlidingMenu slidingMenu;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.habit_view);
    enableSlidingMenu(slidingMenu);
    
    //Button buttonOne = (Button) findViewById(R.id.habit_save);
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
  public long createHabit(String title, String value){
	  HabitDbManager habitDbManager = new HabitDbManager(this);
	  return habitDbManager.createHabit(title, value);
  }
}
