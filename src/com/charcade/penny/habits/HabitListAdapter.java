package com.charcade.penny.habits;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.charcade.penny.R;
import com.charcade.penny.entities.Habit;

public class HabitListAdapter extends ArrayAdapter{

      private int resource;
      private LayoutInflater inflater;
      private Context context;

      public HabitListAdapter (Context ctx, int resourceId, List objects) {
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
            TextView txtName = (TextView) convertView.findViewById(R.id.habit_list_name);
            txtName.setText(habit.getName());

            /* Take the TextView from layout and set the city's wiki link */
            TextView txtWiki = (TextView) convertView.findViewById(R.id.habit_list_price);
            txtWiki.setText(String.valueOf(habit.getValue()));

            /* Take the ImageView from layout and set the city's image */
            ImageView imageCity = (ImageView) convertView.findViewById(R.id.habit_list_image);
            //String uri = "drawable/" + city.getImage();
            //int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            //Drawable image = context.getResources().getDrawable(imageResource);
            imageCity.setImageResource(R.drawable.ic_tea);
            return convertView;
      }
}