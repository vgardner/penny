package com.charcade.penny.goals;

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

    setContentView(R.layout.goal_view);
    enableSlidingMenu(slidingMenu);
    // Get parameters from intent.
    Intent intent = getIntent();
    String goalId = intent.getStringExtra("hid");
    // Get goal db manager.
    GoalDbManager goalDbManager = new GoalDbManager(this);
    Goal goal = goalDbManager.getGoal(Integer.parseInt(goalId));
    // Set view element texts and images.
    this.setTitle(goal.getName());
    
    TextView goalViewPriceText = (TextView) findViewById(R.id.goal_view_price_text);
    goalViewPriceText.setText("$" + goal.getValue());
    
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
  public long createGoal(String title, String value){
	  GoalDbManager goalDbManager = new GoalDbManager(this);
	  return goalDbManager.createGoal(title, value);
  }
}
