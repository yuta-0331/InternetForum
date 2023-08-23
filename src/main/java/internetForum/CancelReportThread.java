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
import model.CancelReportThreadModel;

/**
 * Servlet implementation class CancelReportResponse
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/thread/cancel_report" })
public class CancelReportThread extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String threadIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // loginセッションを持っていて、有効なthreadIdの場合
        if (new LoginValidation().valid(session) && new NumberValidation().isInteger(threadIdStr)) {
            new CancelReportThreadModel().report(Integer.parseInt(threadIdStr));
            response.sendRedirect("../admin");
            return;
            
        }
        // login sessionを持っていないか、無効なresponseIdの場合はNOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}

}
