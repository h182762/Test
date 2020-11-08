package no.hvl.dat108;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "deltakerliste", name = "person")
public class Person {
	private String fornavn;
	private String etternavn;
	@Id
	private String mobilNr;
	
	private String passord;
	private String kjonn;
	
	public Person() {
		
	}
	
	public Person(String fornavn, String etternavn, String mobilNr, String passord, String kjonn) {
		super();
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobilNr = mobilNr;
		this.passord = passord;
		this.kjonn = kjonn;
		
	}
	public String getFornavn() {
		return fornavn;
	}
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	public String getMobilNr() {
		return mobilNr;
	}
	public void setMobilNr(String mobilNr) {
		this.mobilNr = mobilNr;
	}
	public String getPassord() {
		return passord;
	}
	public void setPassord(String passord) {
		this.passord = passord;
	}
	
	@Override
	public String toString() {
		return "Person [fornavn=" + fornavn + ", etternavn=" + etternavn + ", mobilNr=" + mobilNr + ", passord="
				+ passord + "]";
	}

	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}

	
	
	
}
