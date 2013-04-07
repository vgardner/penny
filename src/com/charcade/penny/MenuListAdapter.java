package com.charcade.penny;

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

import com.charcade.penny.entities.MenuListItem;
import com.charcade.penny.goals.ListGoalsActivity;
import com.charcade.penny.habits.ListHabitsActivity;

public class MenuListAdapter extends ArrayAdapter{

      private int resource;
      private LayoutInflater inflater;
      private Context context;

      public MenuListAdapter (Context ctx, int resourceId, List objects) {
		super(ctx, resourceId, objects);
		resource = resourceId;
		inflater = LayoutInflater.from(ctx);
		context = ctx;
      }

      @Override
      public View getView (int position, View convertView, ViewGroup parent ) {

            /* create a new view of my layout and inflate it in the row */
            convertView = (RelativeLayout) inflater.inflate( resource, null );

            /* Extract the city's object to show */
            MenuListItem menuItem = (MenuListItem) getItem(position);
            
            /* Take the TextView from layout and set the city's name */
            TextView txtName = (TextView) convertView.findViewById(R.id.menu_list_name);
            txtName.setText(menuItem.getTitle());

            /* Take the ImageView from layout and set the city's image */
            ImageView menuIconImage = (ImageView) convertView.findViewById(R.id.menu_list_image);
            //String uri = "drawable/" + city.getImage();
            //int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            //Drawable image = context.getResources().getDrawable(imageResource);
            menuIconImage.setImageResource(R.drawable.ic_coffee);
            RelativeLayout.LayoutParams imageParams =
                    new RelativeLayout.LayoutParams(100, 100);
            menuIconImage.setLayoutParams(imageParams);
            
            convertView.setId(position);
            convertView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	if (v.getId() == 3) {
                		Intent intent = new Intent(context, ListHabitsActivity.class);
                		context.startActivity(intent);
                	}
                	if (v.getId() == 4) {
                		Intent intent = new Intent(context, ListGoalsActivity.class);
                		context.startActivity(intent);
                	}
                }
            });
            return convertView;
      }
}