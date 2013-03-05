package com.charcade.penny;

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
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.slidingmenu.lib.SlidingMenu;

public class FrontActivity extends BaseActivity {
  
	public FrontActivity() {
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
    
    // configure the SlidingMenu
    slidingMenu = new SlidingMenu(this);
    slidingMenu.setMode(SlidingMenu.LEFT);
    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
    slidingMenu.setShadowDrawable(R.drawable.shadow);
    //slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
    slidingMenu.setBehindWidthRes(R.dimen.slidingmenu_width);
    slidingMenu.setFadeDegree(0.35f);
    slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    slidingMenu.setMenu(R.layout.left_menu);
    
    // Populate ListView
    ListView listView = (ListView) findViewById(R.id.menu_list);
    String[] values = new String[] { "Username", "Savings", "Newsfeed",
      "My habits", "My goals", "Settings" };
    
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
      android.R.layout.simple_list_item_2, android.R.id.text1, values);
    // Assign adapter to ListView
    listView.setAdapter(adapter);
        
    // First row.
    findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
    findViewById(R.id.topright).setOnDragListener(new MyDragListener());
    
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
        LinearLayout container = (LinearLayout) v;
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
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
} 