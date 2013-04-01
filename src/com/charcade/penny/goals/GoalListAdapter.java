package com.charcade.penny.goals;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.charcade.penny.R;
import com.charcade.penny.entities.Habit;

public class GoalListAdapter extends ArrayAdapter{

      private int resource;
      private LayoutInflater inflater;
      private Context context;
      private Habit currentHabit;
      
      public GoalListAdapter (Context ctx, int resourceId, List objects) {
		super(ctx, resourceId, objects);
		resource = resourceId;
		inflater = LayoutInflater.from(ctx);
		context = ctx;
      }

      @Override
      public View getView (int position, View convertView, ViewGroup parent ) {

            /* create a new view of my layout and inflate it in the row */
            convertView = ( RelativeLayout ) inflater.inflate( resource, null );

            /* Extract the city's object to show */
            Habit habit = (Habit) getItem(position);
            
            /* Take the TextView from layout and set the city's name */
            TextView habitName = (TextView) convertView.findViewById(R.id.habit_list_name);
            habitName.setText(habit.getName());

            /* Take the TextView from layout and set the city's wiki link */
            TextView habitPrice = (TextView) convertView.findViewById(R.id.habit_list_price);
            habitPrice.setText(String.valueOf(habit.getValue()));

            /* Take the ImageView from layout and set the city's image */
            ImageView habitImage = (ImageView) convertView.findViewById(R.id.habit_list_image);
            //String uri = "drawable/" + city.getImage();
            //int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            //Drawable image = context.getResources().getDrawable(imageResource);
            habitImage.setImageResource(R.drawable.ic_tea);
            
            // Assign habit id to view id.
            convertView.setId(habit.getHid());
            
            convertView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	Intent viewHabitIntent = new Intent(context, ViewGoalActivity.class);
                	viewHabitIntent.putExtra("hid", String.valueOf(v.getId()));
                    context.startActivity(viewHabitIntent);
                }
            });

            return convertView;
      }
}