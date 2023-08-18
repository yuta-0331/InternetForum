package internetForum;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = ((HttpServletRequest) request).getSession();
      Enumeration<String> attributes = session.getAttributeNames();

      // logout時にセッションを削除する
      while (attributes.hasMoreElements()) {
          String attribute = (String) attributes.nextElement();
          session.removeAttribute(attribute);
      }
      response.sendRedirect("top");
	}

}
