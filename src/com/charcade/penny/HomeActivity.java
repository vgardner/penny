package com.charcade.penny;

import java.util.ArrayList;

import android.app.ActionBar.OnNavigationListener;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.charcade.penny.db.GoalDbManager;
import com.charcade.penny.db.HabitDbManager;
import com.charcade.penny.entities.Goal;
import com.charcade.penny.entities.Habit;
import com.charcade.penny.goals.AddGoalActivity;
import com.charcade.penny.habits.AddHabitActivity;
import com.slidingmenu.lib.SlidingMenu;

public class HomeActivity extends BaseActivity {
  
  public HomeActivity() {
	super(R.string.app_name);
  }
/** Called when the activity is first created. */
  private ShareActionProvider mShareActionProvider;
  private OnNavigationListener mOnNavigationListener;
  private SlidingMenu slidingMenu;
  private Intent homeIntent;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.front_main);
    
    slidingMenu = enableSlidingMenu(slidingMenu);
    
    //Populate habits list.
    populateHabits();
    
    populateGoals();
    
  }
  
  @Override
  public void onResume() {
	  super.onResume();
	  refreshHabits();
	  populateGoals();
  }
  
  private final class MyTouchListener implements OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
        ClipData data = ClipData.newPlainText("", "");
        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.INVISIBLE);
        
        Context context = getApplicationContext();
        Toast.makeText(context, "Touched!", Toast.LENGTH_SHORT).show();
        return true;
      } else {
        return false;
      }
    }
  }

  class MyDragListener implements OnDragListener {
    Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
    Drawable normalShape = getResources().getDrawable(R.drawable.shape);

    @Override
    public boolean onDrag(View v, DragEvent event) {
      int action = event.getAction();
      switch (event.getAction()) {
      case DragEvent.ACTION_DRAG_STARTED:
        // Do nothing
        break;
      case DragEvent.ACTION_DRAG_ENTERED:
        v.setBackgroundDrawable(enterShape);
        break;
      case DragEvent.ACTION_DRAG_EXITED:
        v.setBackgroundDrawable(normalShape);
        break;
      case DragEvent.ACTION_DROP:
        // Dropped, reassign View to ViewGroup
        View view = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) view.getParent();
        owner.removeView(view);
        RelativeLayout container = (RelativeLayout) v;
        container.addView(view);
        view.setVisibility(View.VISIBLE);
        break;
      case DragEvent.ACTION_DRAG_ENDED:
        v.setBackgroundDrawable(normalShape);
      default:
        break;
      }
      return true;
    }
  }
  @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	slidingMenu.toggle();
	        break;
	        case R.id.menu_add_habit:
	        	homeIntent = new Intent(this, AddHabitActivity.class);
	        	startActivity(homeIntent);
	        break;
	        case R.id.menu_add_goal:
	        	homeIntent = new Intent(this, AddGoalActivity.class);
	        	startActivity(homeIntent);
	        break;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
		return false;
	}
  public void refreshHabits(){
	  populateHabits();
  }
  public void populateHabits(){
    HabitDbManager habitDbManager = new HabitDbManager(this);
    ArrayList<Habit> habitList = habitDbManager.getHabitList();
    
    LinearLayout habitRow = (LinearLayout) findViewById(R.id.layout_row_habits);
    habitRow.removeAllViews();
    
    // Populate list of habits.
    for (Habit currentHabit : habitList) {
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout habitItemLayout = (RelativeLayout) vi.inflate(R.layout.habit_item, null);
        
        // Edit LayoutParams in habitItem.
        RelativeLayout habitItem = (RelativeLayout) habitItemLayout.findViewById(R.id.habit_item);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(400, 420);
        habitItem.setLayoutParams(params);
        habitItem.setOnDragListener(new MyDragListener());
        
        // Adding values to habit image icon.
        ImageView habitItemIcon = (ImageView) habitItemLayout.findViewById(R.id.habit_item_icon);
        habitItemIcon.setImageResource(R.drawable.ic_beer);
        habitItemIcon.setOnTouchListener(new MyTouchListener());
        
        // Adding values to habit image icon.
        TextView habitItemText = (TextView) habitItemLayout.findViewById(R.id.habit_item_text);
        habitItemText.setText(currentHabit.getName());
       
        habitRow.addView(habitItem);
    }
  }
  public void populateGoals() {
  	GoalDbManager goalDbManager = new GoalDbManager(this);
    ArrayList<Goal> goalList = goalDbManager.getGoalList();
    
    LinearLayout goalRow = (LinearLayout) findViewById(R.id.layout_row_goals);
    goalRow.removeAllViews();
    
    // Populate list of habits.
    for (Goal currentGoal : goalList) {
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout goalItemLayout = (RelativeLayout) vi.inflate(R.layout.goal_item, null);
        
        // Edit LayoutParams in habitItem.
        RelativeLayout goalItem = (RelativeLayout) goalItemLayout.findViewById(R.id.goal_item);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(400, 420);
        goalItem.setLayoutParams(params);
        goalItem.setOnDragListener(new MyDragListener());
        
        // Adding values to habit image icon.
        ImageView goalItemIcon = (ImageView) goalItemLayout.findViewById(R.id.goal_item_icon);
        goalItemIcon.setImageResource(R.drawable.ic_beer);
        goalItemIcon.setOnTouchListener(new MyTouchListener());
        
        // Adding values to habit image icon.
        TextView goalItemText = (TextView) goalItemLayout.findViewById(R.id.goal_item_text);
        goalItemText.setText(currentGoal.getName());
       
        goalRow.addView(goalItem);
    }
  }
} 