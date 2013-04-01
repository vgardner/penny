package com.charcade.penny;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.charcade.penny.entities.MenuListItem;

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
            ImageView imageCity = (ImageView) convertView.findViewById(R.id.menu_list_image);
            //String uri = "drawable/" + city.getImage();
            //int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            //Drawable image = context.getResources().getDrawable(imageResource);
            imageCity.setImageResource(R.drawable.ic_coffee);
            return convertView;
      }
}