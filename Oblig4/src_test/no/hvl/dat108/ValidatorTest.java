package no.hvl.dat108;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidatorTest {

	
	@Test
	public void erGyldigFornavn() {
		//Lengde på fornavn
		assertFalse(Validator.erGyldigFornavn(""));
		assertFalse(Validator.erGyldigFornavn("P"));
		assertTrue(Validator.erGyldigFornavn("Pe"));
		assertTrue(Validator.erGyldigFornavn("Per"));
		
		assertTrue(Validator.erGyldigFornavn("Abcdefghijklmnopqrs"));
		assertTrue(Validator.erGyldigFornavn("Abcdefghijklmnopqrst"));
		assertFalse(Validator.erGyldigFornavn("Abcdefghijklmnopqrstu"));
		assertFalse(Validator.erGyldigFornavn("Abcdefghijklmnopqrstuv"));
		
		//Stor forbokstav
		assertFalse(Validator.erGyldigFornavn("frank"));
		assertTrue(Validator.erGyldigFornavn("Frank"));
		
		//Kan ha 1 bindestrek eller 1 mellomrom
		assertTrue(Validator.erGyldigFornavn("Frank roger"));
		assertTrue(Validator.erGyldigFornavn("Frank-roger"));
		assertFalse(Validator.erGyldigFornavn("Frank--roger"));
		assertFalse(Validator.erGyldigFornavn("Frank  roger"));
	}
	
	
	@Test
	public void gyldigEtternavn() {
		assertTrue(Validator.erGyldigEtternavn("Abcdefghijklmnopqrst"));
		assertFalse(Validator.erGyldigEtternavn("Abcdefghijklmnopqrstu"));
		
		//Ikke bindestrek eller mellomrom
		assertFalse(Validator.erGyldigEtternavn("Frank roger"));
		assertFalse(Validator.erGyldigEtternavn("Frank-roger"));
	
	}

	@Test
	public void gyldigMobilNr() {
		assertFalse(Validator.erGyldigNr("asdfghj"));
		assertFalse(Validator.erGyldigNr("asdfghjk"));
		assertFalse(Validator.erGyldigNr("1234567"));
		assertFalse(Validator.erGyldigNr("123456789"));
		assertTrue(Validator.erGyldigNr("12345678"));
	}
	
}








