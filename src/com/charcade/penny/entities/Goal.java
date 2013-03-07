package com.charcade.penny.entities;

import java.math.BigDecimal;
import java.security.Timestamp;

public class Goal {
	private int id;
	private String name;
	private BigDecimal TotalSaved;
	private String Description;
	private int weight;
	private Boolean visible;
	private Timestamp created;
	private Timestamp updated;
}
