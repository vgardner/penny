package com.charcade.penny.goals;

import java.util.ArrayList;

import android.app.ActionBar.OnNavigationListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.charcade.penny.BaseActivity;
import com.charcade.penny.HomeActivity;
import com.charcade.penny.R;
import com.charcade.penny.db.GoalDbManager;
import com.charcade.penny.entities.Goal;
import com.slidingmenu.lib.SlidingMenu;

public class ListGoalsActivity extends BaseActivity {
  
	public ListGoalsActivity() {
		super(R.string.app_name);
	}
/** Called when the activity is first created. */
  private ShareActionProvider mShareActionProvider;
  private OnNavigationListener mOnNavigationListener;
  private SlidingMenu slidingMenu;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.goal_list);
    setTitle("My Goals");
    
    slidingMenu = enableSlidingMenu(slidingMenu);
    populateGoalList();
  }
  
  @Override
  public void onResume() {
	super.onResume();
	populateGoalList();
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
        case android.R.id.home:
    		this.startActivity(new Intent(this, HomeActivity.class));
    	break;
        case R.id.menu_add:
    		this.startActivity(new Intent(this, AddGoalActivity.class));
        break;
        default:
            return super.onOptionsItemSelected(item);
    }
	return false;
  }
  public void populateGoalList(){
    GoalDbManager GoalDbManager = new GoalDbManager(this);
    ArrayList<Goal> GoalList = GoalDbManager.getGoalList();
    
    // Populate ListView
    ListView listView = (ListView) findViewById(R.id.goal_list_view);
    
    listView.setAdapter(new GoalListAdapter(this, R.layout.goal_list_row, GoalList));
  }
}
