package com.charcade.penny.goals;

import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.charcade.penny.BaseActivity;
import com.charcade.penny.R;
import com.charcade.penny.db.HabitDbManager;
import com.charcade.penny.entities.Habit;
import com.slidingmenu.lib.SlidingMenu;

public class ViewGoalActivity extends BaseActivity {
  
	public ViewGoalActivity() {
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
    // Get parameters from intent.
    Intent intent = getIntent();
    String habitId = intent.getStringExtra("hid");
    // Get habit db manager.
    HabitDbManager habitDbManager = new HabitDbManager(this);
    Habit habit = habitDbManager.getHabit(Integer.parseInt(habitId));
    // Set view element texts and images.
    this.setTitle(habit.getName());
    
    TextView habitViewPriceText = (TextView) findViewById(R.id.habit_view_price_text);
    habitViewPriceText.setText("$" + habit.getValue());
    
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
