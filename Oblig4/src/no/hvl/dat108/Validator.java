package no.hvl.dat108;

import javax.servlet.http.HttpServletRequest;

public class Validator {

	private final static String ANY_LETTER = "[A-ZØÆÅa-zøæå]+?";
	private final static String ANY_LETTER_WITH_SPACE_AND_HYPHEN = "[A-ZØÆÅa-zøæå]+(?:(\\s|-)[A-ZØÆÅa-zøæå]+)?";// "[a-zA-ZæøåÆØÅ]([\\w
	private final static String FIRST_CASE_UPPER_LETTER = "[A-ZØÆÅ]";
	private final static String TWO_TO_TWENTY_LETTERS = "(?=.{1,19}$)";
	private final static String GYLDIG_NR = "^[0-9]{8}$";

	public static boolean erGyldigFornavn(String fornavn) {

		return fornavn != null && fornavn.matches(
				"^" + FIRST_CASE_UPPER_LETTER + TWO_TO_TWENTY_LETTERS + ANY_LETTER_WITH_SPACE_AND_HYPHEN + "$");
	}

	public static boolean erGyldigEtternavn(String etternavn) {

		return etternavn != null
				&& etternavn.matches("^" + FIRST_CASE_UPPER_LETTER + TWO_TO_TWENTY_LETTERS + ANY_LETTER + "$");
	}

	public static boolean erGyldigNr(String mobilnr) {

		return mobilnr != null && mobilnr.matches(GYLDIG_NR);
	}

	public static int passordStyrke(String passord) {

		int passordScore = 0;

		if (passord.length() < 8)
			return 0;
		else if (passord.length() >= 10)
			passordScore += 2;
		else
			passordScore += 1;

		// if it contains one digit, add 2 to total score
		if (passord.matches("(?=.*[0-9]).*"))
			passordScore += 2;

		// if it contains one lower case letter, add 2 to total score
		if (passord.matches("(?=.*[a-z]).*"))
			passordScore += 2;

		// if it contains one upper case letter, add 2 to total score
		if (passord.matches("(?=.*[A-Z]).*"))
			passordScore += 2;

		// if it contains one special character, add 2 to total score
		if (passord.matches("(?=.*[~!@#$%^&*()_-]).*"))
			passordScore += 2;

		return passordScore;

	}

	public static boolean gyldigeInputs(String fornavn, String etternavn, String mobilnr, String passord,
			String passordRepetert, String kjonn, HttpServletRequest request) {
		boolean errors = false;
		if (!erGyldigFornavn(fornavn)) {
			request.setAttribute("fornavnError", "Ugyldig fornavn");
			errors = true;
		} else {
			request.setAttribute("fornavn", fornavn);
		}

		if (!erGyldigEtternavn(etternavn)) {
			request.setAttribute("etternavnError", "Ugyldig etternavn");
			errors = true;
		} else {
			request.setAttribute("etternavn", etternavn);
		}

		if (!erGyldigNr(mobilnr)) {
			request.setAttribute("mobilError", "Ugyldig mobilnr");
			errors = true;
		} else {
			request.setAttribute("mobilnr", mobilnr);
		}

		if (passordStyrke(passord) == 0) {
			request.setAttribute("passordError", "Ugyldig passord");
			errors = true;
		}

		if (passordStyrke(passord) != 0) {
			setPassordStyrke(passord, request);
		}
		if (!passord.equals(passordRepetert)) {
			request.setAttribute("passordRepetertError", "Passordene må være like");
			errors = true;
		}

		if (kjonn == null) {
			request.setAttribute("kjonnError", "Du må oppgi kjønn");
			errors = true;
		} else {
			request.setAttribute("kjonn", kjonn);
		}

		return errors;
	}

	private static void setPassordStyrke(String passord, HttpServletRequest request) {
		if (passordStyrke(passord) > 8) {
			request.setAttribute("passordStyrke", "Sterkt passord");
		}

		if (passordStyrke(passord) > 4) {
			request.setAttribute("passordStyrke", "Middels passord");
		}

		if (passordStyrke(passord) > 0) {
			request.setAttribute("passordStyrke", "Svakt passord");
		}
	}
}
