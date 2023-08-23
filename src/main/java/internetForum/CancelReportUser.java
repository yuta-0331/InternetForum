package internetForum;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import internetForum.validation.LoginValidation;
import internetForum.validation.NumberValidation;
import model.CancelReportUserModel;

/**
 * Servlet implementation class CancelReportResponse
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/profile/cancel_report" })
public class CancelReportUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String userIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // loginセッションを持っていて、有効なresponseIdの場合
        if (new LoginValidation().valid(session) && new NumberValidation().isInteger(userIdStr)) {
            new CancelReportUserModel().report(Integer.parseInt(userIdStr));
            response.sendRedirect("../admin");
            return;
            
        }
        // login sessionを持っていないか、無効なresponseIdの場合はNOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}

}
