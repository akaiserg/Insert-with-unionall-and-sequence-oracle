package org.myproject.hibernate.dto;

import java.math.BigDecimal;
/**
 * Values of the table Parent 
 *
 */
public class ParentDto {

	private BigDecimal parId;
	private String parName;

	public BigDecimal getParId() {
		return parId;
	}

	public void setParId(BigDecimal parId) {
		this.parId = parId;
	}

	public String getParName() {
		return parName;
	}

	public void setParName(String parName) {
		this.parName = parName;
	}

}
