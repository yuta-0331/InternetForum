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
import model.ReportThreadModel;

/**
 * Servlet implementation class ReportResponse
 */
@WebServlet("/thread/report")
public class ReportThread extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // threadIdの取得
        String threadIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // ログインセッションを持っていて、有効なthreadIdの場合
        if (new LoginValidation().valid(session) && new NumberValidation().isInteger(threadIdStr)) {
            int row = new ReportThreadModel().report(Integer.parseInt(threadIdStr));
            if (row != 0) {
                response.sendRedirect("../thread?id=" + threadIdStr);
                return;
            }
        }
        // login sessionを持っていないか、無効なthreadIdの場合はNOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}
}
