package com.charcade.penny.entities;

import android.view.Menu;

public class MenuListItem {
	private String title;
	private String icon;
	
	public MenuListItem (String title) {
		setTitle(title);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
