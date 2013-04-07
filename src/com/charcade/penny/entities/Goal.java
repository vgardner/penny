package com.charcade.penny.entities;

import java.math.BigDecimal;
import java.security.Timestamp;

public class Goal {
	private int gid;
	private String name;
	private BigDecimal value;
	private BigDecimal TotalSaved;
	private String Description;
	private int weight;
	private Boolean visible;
	private Timestamp created;
	private Timestamp updated;
	private Timestamp dealine;
	private String icon;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getTotalSaved() {
		return TotalSaved;
	}
	public void setTotalSaved(BigDecimal totalSaved) {
		TotalSaved = totalSaved;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public Timestamp getDealine() {
		return dealine;
	}
	public void setDealine(Timestamp dealine) {
		this.dealine = dealine;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
