package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		// TODO: IMPORTANT -> MAKE A PROPER AUTH SYSTEM, INSTANTIATING A COMMANDER (IN CASE OF FAILURE, SEND BACK TO INDEX)
		/*Connection conn;
		PreparedStatement stmt;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(ServletUtils.URL);
			stmt = conn.prepareStatement("SELECT * FROM quest_users WHERE id = ?;");
			stmt.setString(1, request.getParameter("login"));
			ResultSet rs = stmt.executeQuery();
			int rowCount = 0;
			while (rs.next()) rowCount++;
			if (rowCount == 1) {
				// TODO: SELECT also on the Attributes table to create the HashSet object needed to instantiate Commander.
				// TODO: SELECT also on the Missions table to create the Map object needed to instantiate Commander.
				String id = rs.getString(1), name = rs.getString(2);
				int points = rs.getInt(4);
				boolean isAdmin = rs.getBoolean(5);
				
				rs.close(); stmt.close();
				
				HashSet<Attribute> attributes = new HashSet<>();
				PreparedStatement newStmt = conn.prepareStatement("SELECT * FROM quest_attributes WHERE id = ?;");
				newStmt.setString(1, id);
				ResultSet newRs = newStmt.executeQuery();
				while (newRs.next()) attributes.add(Attribute.of(newRs.getString(2), newRs.getInt(3)));
				
				request.setAttribute("commander", new Commander(id,
						name, attributes, points, null isAdmin));
				
				newRs.close(); newStmt.close();
				
				// Da eliminare (vedi catch e fine metodo)
				request.setAttribute("error", false);
				//
				
				//Substitute with authentication
				RequestDispatcher view = request.getRequestDispatcher("home.jsp");
				view.forward(request, response);
			} else
				throw new QuestException("Non esiste un comandante con tale ID, per favore registrati da Lisa, nel Discord della DaVinci.");
		} catch (SQLException | ClassNotFoundException | QuestException e) {
			// In caso di errore rimandare alla index con messaggio di errore
			request.setAttribute("error", true);
			request.setAttribute("errorMessage", e.getLocalizedMessage());
			
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
		}*/
	}

}
