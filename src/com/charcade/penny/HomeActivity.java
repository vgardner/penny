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

import com.charcade.penny.db.HabitDbManager;
import com.charcade.penny.entities.Habit;
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
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.front_main);
    
    slidingMenu = enableSlidingMenu(slidingMenu);
    
    //Populate habits list.
    populateHabits();
    
    // Second row.
    findViewById(R.id.box1obj).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.box1).setOnDragListener(new MyDragListener());
    findViewById(R.id.box5).setOnDragListener(new MyDragListener());
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
	        case R.id.menu_add:
	        	Intent intent = new Intent(this, AddHabitActivity.class);
	        	startActivity(intent);
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
        
        LinearLayout habitRow = (LinearLayout) findViewById(R.id.layout_row_habits);
        habitRow.addView(habitItem);
    }
  }
} 