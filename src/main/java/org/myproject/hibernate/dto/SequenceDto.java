package org.myproject.hibernate.dto;

import java.math.BigDecimal;
/**
 * DTO for the sequence values
 *
 */
public class SequenceDto {

	private BigDecimal sequence;
	private BigDecimal counter;
	
	public BigDecimal getSequence() {
		return sequence;
	}
	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}
	public BigDecimal getCounter() {
		return counter;
	}
	public void setCounter(BigDecimal counter) {
		this.counter = counter;
	}

	

}
