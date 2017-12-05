package com.costs.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cost {
	@Id
	private int costId;
	private String costDescription;
	private double costAmmount;

	public int getCostId() {
		return costId;
	}

	public void setCostId(int costId) {
		this.costId = costId;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public void setCostsDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	public double getCostAmmount() {
		return costAmmount;
	}

	public void setCostAmmount(double costAmmount) {
		this.costAmmount = costAmmount;
	}
}
