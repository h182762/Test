package no.hvl.dat108;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logginn")
public class LoggInnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private PersonDAO dao;

	private static PassordUtil passUtil = new PassordUtil();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/logginn.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String brukernavn = request.getParameter("brukernavn");
		String passord = request.getParameter("passord");

		if (passord == null || dao.finnes(brukernavn) == false) {
			Utils.logginnError(request, response);
		}

		if (!passUtil.sjekkPassord(passord, dao.finnPassord(brukernavn))) {
			Utils.logginnError(request, response);
		} else {
			HttpSession sesjon = request.getSession(false);
			Utils.sesjonLoggetInn(sesjon, request);

			response.sendRedirect("deltakerliste");
		}
	}

}// class
