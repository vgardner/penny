package com.charcade.penny.goals;

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
import com.charcade.penny.db.GoalDbHelper;
import com.charcade.penny.db.GoalDbManager;
import com.charcade.penny.db.GoalDbMap;
import com.slidingmenu.lib.SlidingMenu;

public class AddGoalActivity extends BaseActivity {
  
	public AddGoalActivity() {
		super(R.string.app_name);
	}
/** Called when the activity is first created. */
  private ShareActionProvider mShareActionProvider;
  private OnNavigationListener mOnNavigationListener;
  private SlidingMenu slidingMenu;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.goal_add);
    enableSlidingMenu(slidingMenu);
    setTitle("New Goal");
    
    Button buttonOne = (Button) findViewById(R.id.goal_save);
    buttonOne.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
        	Context context = getApplicationContext();
        	CharSequence text = " successfully created!";
        	int duration = Toast.LENGTH_SHORT;
        	
        	EditText GoalName = (EditText) findViewById(R.id.goal_name);
        	String GoalNameText = GoalName.getText().toString();
        	
        	SeekBar GoalPrice = (SeekBar) findViewById(R.id.goal_price);
        	int GoalPriceValue = GoalPrice.getProgress();
        	
        	Long newRowId = createGoal(GoalNameText, String.valueOf(GoalPriceValue));
        	
        	// Check if Goal was successfully saved.
        	if (newRowId instanceof Long) {   	
            	Toast toast = Toast.makeText(context, GoalNameText + text, duration);
            	toast.show();
        		finish();
        	}
        	else {
        		Toast toast = Toast.makeText(context, "There was a problem creating your Goal." + text, duration);
            	toast.show();
        	}
        }
    });
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
	  GoalDbManager GoalDbManager = new GoalDbManager(this);
	  return GoalDbManager.createGoal(title, value);
  }
}
