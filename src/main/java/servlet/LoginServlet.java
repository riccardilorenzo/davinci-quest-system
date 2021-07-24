package servlet;

import controller.CommanderController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.QuestException;
import persistence.DatabaseReader;
import persistence.DatabaseWriter;

import java.io.IOException;
import java.io.Serial;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Servlet used for logging in users.", urlPatterns = { "/LoginUser" })
public class LoginServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		if (login == null) response.sendRedirect("index.jsp");

		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
		session = request.getSession(true);

		try {
			CommanderController cmdrController = new CommanderController(new DatabaseReader(ServletUtils.LOCAL_DB_URL), new DatabaseWriter(ServletUtils.LOCAL_DB_URL));
			session.setAttribute("commander", cmdrController.getCommander(login));
			response.sendRedirect("home/home.jsp");
		} catch (QuestException | IllegalArgumentException e) {
			session.setAttribute("error", e.getLocalizedMessage());
			response.sendRedirect("index.jsp");
		}
	}
}

// TODO: create a remote DB in Vitruvio (dumping then importing the local one) and edit Lisa (/idquest) in order to properly create a new user