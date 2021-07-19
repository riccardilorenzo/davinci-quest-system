package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Attribute;
import model.Commander;

import java.io.IOException;
import java.io.Serial;
import java.sql.*;
import java.util.TreeMap;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Servlet used for logging in users.", urlPatterns = { "/home" })
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

		// Find user
		Connection conn;
		PreparedStatement ps;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(ServletUtils.URL);
			ps = conn.prepareStatement("SELECT * FROM quest_users WHERE id = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			int rowCount = rs.last() ? rs.getRow() : 0;

			if (rowCount == 1) {
				String name = rs.getString("name"), realName = rs.getString("realname");
				boolean isAdmin = rs.getBoolean("admin");
				int points = rs.getInt("points");
				ps.close();

				ps = conn.prepareStatement("SELECT * FROM quest_attributes WHERE id = ?;");
				ps.setString(1, login);
				rs = ps.executeQuery();
				TreeMap<Attribute, Integer> attrs = new TreeMap<>();
				while (rs.next()) {
					attrs.put(Attribute.valueOf(rs.getString("attribute")), rs.getInt("value"));
				}
				ps.close();

				session.setAttribute("commander", new Commander(login, name, realName, attrs, points, isAdmin));
				response.sendRedirect("home.jsp");
			} else {
				// User not found
				session.setAttribute("error", "Utente non trovato, per favore ritenta (od ottieni il tuo codice da Lisa).");
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException | ClassNotFoundException e) {
			session.setAttribute("error", e.getLocalizedMessage());
			response.sendRedirect("index.jsp");
		}
	}
}

// TODO: create a remote DB (dumping then importing the local one) and edit Lisa (/idquest)