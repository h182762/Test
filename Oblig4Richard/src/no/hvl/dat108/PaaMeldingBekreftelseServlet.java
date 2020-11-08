package no.hvl.dat108;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/paameldingsbekreftelse")
public class PaaMeldingBekreftelseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);
		if (sesjon == null || sesjon.getAttribute("innlogget") == null) {
			response.sendRedirect("paamelding");
		} else {
			request.getRequestDispatcher("WEB-INF/paameldingsbekreftelse.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);
		if (sesjon == null || sesjon.getAttribute("innlogget") == null) {
			response.sendRedirect("paamelding");
		} else {
			response.sendRedirect("deltakerliste");
		}
	}
}