package no.hvl.dat108;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	public static void logginnError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("logginnError", "Ugyldig brukernavn og/eller passord");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/logginn.jsp");
		rd.forward(request, response);
	}
	
	public static void redirectPaamelding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/paameldingsskjema.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sesjonLoggetInn(HttpSession sesjon, HttpServletRequest request) {
		if (sesjon != null) {
			sesjon.invalidate();
		}
		sesjon = request.getSession(true);
		sesjon.setMaxInactiveInterval(10);
		sesjon.setAttribute("innlogget", true);
		
	}
}
