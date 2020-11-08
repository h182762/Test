package no.hvl.dat108;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deltakerliste")
public class DeltakerListeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersonDAO dao;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Person> deltakere = dao.hentDeltakere();

		HttpSession sesjon = request.getSession(false);
		
		if (sesjon == null || sesjon.getAttribute("innlogget") == null) {
			response.sendRedirect("paamelding");
		} else {
		request.setAttribute("nyDeltaker", sesjon.getAttribute("nyDeltaker"));
		request.setAttribute("deltakerliste", deltakere);
		
		request.getRequestDispatcher("WEB-INF/deltakerliste.jsp")
		.forward(request, response);
		
		}
	}
}
