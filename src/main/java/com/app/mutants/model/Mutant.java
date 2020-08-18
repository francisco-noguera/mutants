package com.app.mutants.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mutant")
public class Mutant implements Serializable {
	
	private static final long serialVersionUID = 3912545783507865141L;
	
	@Id
	@Column(name="mutant_id")
	private String mutantId;
	private String dna;
	@Column(name="is_mutant")
	private boolean mutant;
	
	public Mutant() {}
	
	public Mutant(String mutantId, String dna, boolean mutant) {
		this.mutantId = mutantId;
		this.dna = dna;
		this.mutant = mutant;
	}
	
	public String getMutantId() {
		return mutantId;
	}
	public void setMutantId(String mutantId) {
		this.mutantId = mutantId;
	}
	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public boolean isMutant() {
		return mutant;
	}
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
	
	@Override  
    public int hashCode() {  
        int hash = 0;  
        hash += (this.getMutantId() != null ? this.getMutantId().hashCode() : 0);  
  
        return hash;  
    }  
  
    @Override  
    public boolean equals(Object object) {  
    if (this == object)  
            return true;  
        if (object == null)  
            return false;  
        if (getClass() != object.getClass())  
            return false;  
  
        Mutant other = (Mutant) object;  
        if (this.getMutantId() != other.getMutantId() && (this.getMutantId() == null || !this.mutantId.equals(other.mutantId))) {  
            return false;  
        }  
        return true;  
    }  
  
    @Override  
    public String toString() {  
        return this.getClass().getName() + " [ID=" + mutantId + "]";  
    } 

}
