package no.hvl.dat108;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/paamelding")
public class PaaMeldingSkjemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private PersonDAO dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/paameldingsskjema.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Person person = Utils.behandleInputs(request, response, dao);
		if (person == null || dao.finnes(person.getMobilNr())) {
			Utils.redirectPaamelding(request, response);
		} else {
			HttpSession sesjon = request.getSession(false);
			if (sesjon != null) {
				sesjon.invalidate();
			}
			sesjon = request.getSession(true);
			sesjon.setMaxInactiveInterval(10);
			sesjon.setAttribute("innlogget", true);
			
			dao.leggtilDeltaker(person);
			sesjon.setAttribute("nyDeltaker", person);

			response.sendRedirect("paameldingsbekreftelse");
		}
	}

}
