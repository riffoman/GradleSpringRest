package com.costs.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cost {
	@Id
	private int costId;
	private String costName;
	private String costDescription;
	private double costAmmount;

	@DBRef
	private Category costCategory;

	@DBRef
	private User costUser;

	public int getCostId() {
		return costId;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public Category getCostCategory() {
		return costCategory;
	}

	public void setCostCategory(Category costCategory) {
		this.costCategory = costCategory;
	}

	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	public void setCostId(int costId) {
		this.costId = costId;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public double getCostAmmount() {
		return costAmmount;
	}

	public void setCostAmmount(double costAmmount) {
		this.costAmmount = costAmmount;
	}
}
