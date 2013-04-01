package com.charcade.penny.habits;

import java.util.ArrayList;

import android.app.ActionBar.OnNavigationListener;
import android.content.Context;
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
import com.charcade.penny.R;
import com.charcade.penny.db.HabitDbManager;
import com.charcade.penny.entities.Habit;
import com.slidingmenu.lib.SlidingMenu;

public class ListHabitsActivity extends BaseActivity {
  
	public ListHabitsActivity() {
		super(R.string.app_name);
	}
/** Called when the activity is first created. */
  private ShareActionProvider mShareActionProvider;
  private OnNavigationListener mOnNavigationListener;
  private SlidingMenu slidingMenu;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.habit_list);
    slidingMenu = enableSlidingMenu(slidingMenu);
    populateHabitList();
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
  public void populateHabitList(){
    HabitDbManager habitDbManager = new HabitDbManager(this);
    ArrayList<Habit> habitList = habitDbManager.getHabitList();
    
    // Populate ListView
    ListView listView = (ListView) findViewById(R.id.habit_list_view);
    
    listView.setAdapter(new HabitListAdapter(this, R.layout.habit_list_row, habitList));
  }
}
