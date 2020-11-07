package no.hvl.dat108;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidatorTest {

	// NAVN TESTER
	
	@Test
	public void ikkeMindreEnn3() {
		assertFalse(Validator.erGyldigFornavn(""));
		assertFalse(Validator.erGyldigFornavn("P"));
		assertTrue(Validator.erGyldigFornavn("Pe"));
		assertTrue(Validator.erGyldigFornavn("Per"));
	}
	
	@Test
	public void ikkeStørreEnn20() {
		assertTrue(Validator.erGyldigFornavn("Abcdefghijklmnopqrs"));
		assertTrue(Validator.erGyldigFornavn("Abcdefghijklmnopqrst"));
		assertFalse(Validator.erGyldigFornavn("Abcdefghijklmnopqrstu"));
		assertFalse(Validator.erGyldigFornavn("Abcdefghijklmnopqrstuv"));
	}
	
	@Test
	public void harStorForbokstav() {
		assertFalse(Validator.erGyldigFornavn("frank"));
		assertFalse(Validator.erGyldigFornavn("ømer"));
		assertTrue(Validator.erGyldigFornavn("Ømer"));
		assertTrue(Validator.erGyldigFornavn("Frank"));
	}
	
	@Test
	public void fornavnKanInneholdeMellomromOgBindestrek() {
		assertTrue(Validator.erGyldigFornavn("Frank roger"));
		assertTrue(Validator.erGyldigFornavn("Frank-roger"));
		assertFalse(Validator.erGyldigFornavn("Frank--roger"));
		assertFalse(Validator.erGyldigFornavn("Frank  roger"));
	}
	
	@Test
	public void etternavnIkkeStørreEnn20() {
		assertTrue(Validator.erGyldigEtternavn("Abcdefghijklmnopqrs"));
		assertTrue(Validator.erGyldigEtternavn("Abcdefghijklmnopqrst"));
		assertFalse(Validator.erGyldigEtternavn("Abcdefghijklmnopqrstu"));
		assertFalse(Validator.erGyldigEtternavn("Abcdefghijklmnopqrstuv"));
	}
	
	@Test
	public void etternavnIkkeMellomromEllerBindeStrek() {
		assertFalse(Validator.erGyldigEtternavn("Frank roger"));
		assertFalse(Validator.erGyldigEtternavn("Frank-roger"));
	}
	

}








