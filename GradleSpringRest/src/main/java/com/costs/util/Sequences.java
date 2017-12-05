package com.costs.util;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Sequences {
	@Id
	private int seqId;

	@Field("nextval")
	private int seqNextval;

	public int getSeqId() {
		return seqId;
	}

	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}

	public int getSeqNextval() {
		return seqNextval;
	}

	public void setSeqNextval(int seqNextval) {
		this.seqNextval = seqNextval;
	}

}
