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
    
    Button buttonOne = (Button) findViewById(R.id.habit_save);
    buttonOne.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
        	Context context = getApplicationContext();
        	CharSequence text = "Hello toast!";
        	int duration = Toast.LENGTH_SHORT;
        	
        	EditText habitName = (EditText) findViewById(R.id.habit_name);
        	String habitNameText = habitName.getText().toString();
        	
        	SeekBar habitPrice = (SeekBar) findViewById(R.id.habit_price);
        	int habitPriceValue = habitPrice.getProgress();
           	
        	Toast toast = Toast.makeText(context, String.valueOf(habitPriceValue), duration);
        	toast.show();
        	
        	long newRowId = createHabit(habitNameText, String.valueOf(habitPriceValue));
        	finish();
        }
    });
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
  public long createHabit(String title, String value){
	  HabitDbManager habitDbManager = new HabitDbManager(this);
	  return habitDbManager.createHabit(title, value);
  }
}
