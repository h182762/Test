package no.hvl.dat108;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {
	
	static PassordUtil passUtil = new PassordUtil();
	
	public static Person behandleInputs(HttpServletRequest request, HttpServletResponse response, PersonDAO dao) {
		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");
		String mobilNr = request.getParameter("mobil");
		String passord = request.getParameter("passord");
		String passordRepetert = request.getParameter("passordRepetert");
		String kjonn = request.getParameter("kjonn");
		
		boolean errors = Validator.gyldigeInputs(fornavn, etternavn, mobilNr, passord, passordRepetert, kjonn, request);
		
		if (dao.finnes(mobilNr)) {
			request.setAttribute("mobilError", "Mobilnummer finnes allerede");
		}

		if (errors == true) {
			return null;
		} else {
			return new Person(fornavn, etternavn, mobilNr, passUtil.krypterPassord(passord), kjonn);
		}
	}
}
