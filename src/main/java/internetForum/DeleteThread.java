package internetForum;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import internetForum.validation.AdminValidation;
import internetForum.validation.NumberValidation;
import model.DeleteThreadModel;

/**
 * Servlet implementation class DeleteAccount
 */
@WebServlet("/thread/delete")
public class DeleteThread extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // treadIdの取得
        String threadIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // 有効なthreadIdでadminセッションを持っている場合
        if (new AdminValidation().valid(session) && new NumberValidation().isInteger(threadIdStr)) {
            // userを削除するメソッドの実行
            new DeleteThreadModel().delete(Integer.parseInt(threadIdStr));
            response.sendRedirect("../admin");
            return;
        }
        // admin sessionを持っていないか、無効なthreadIdの場合はNOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}

}
