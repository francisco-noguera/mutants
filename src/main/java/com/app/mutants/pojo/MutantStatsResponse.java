package com.app.mutants.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MutantStatsResponse implements Serializable {
	
	private static final long serialVersionUID = -9040407685868621318L;
	private Long countMutantDna;
	private Long countHumanDna;
	private BigDecimal mutantRatio;
	private BigDecimal humanRatio;
	
	public MutantStatsResponse(Long countMutantDna, Long countHumanDna, BigDecimal mutantRatio, BigDecimal humanRatio) {
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.mutantRatio = mutantRatio;
		this.humanRatio = humanRatio;
	}
	
	public Long getCountMutantDna() {
		return countMutantDna;
	}
	public void setCountMutantDna(Long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	public Long getCountHumanDna() {
		return countHumanDna;
	}
	public void setCountHumanDna(Long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}
	public BigDecimal getMutanRatio() {
		return mutantRatio;
	}
	public void setMutanRatio(BigDecimal mutanRatio) {
		this.mutantRatio = mutanRatio;
	}
	public BigDecimal getHumanRatio() {
		return humanRatio;
	}
	public void setHumanRatio(BigDecimal humanRatio) {
		this.humanRatio = humanRatio;
	}

}
