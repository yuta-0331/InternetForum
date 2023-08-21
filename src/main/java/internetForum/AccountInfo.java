package internetForum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import internetForum.validation.LoginValidation;
import model.FetchThreadListByUserId;
import model.FetchUserInfo;
import model.schema.User;

/**
 * Servlet implementation class AccountInfo
 */
@WebServlet("/account_info")
public class AccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// login sessionを持っていない場合、TOPページへリダイレクトする。
        if (!new LoginValidation().valid(session)) {
            response.sendRedirect("top");
            return;
        }
        
        int userId = (Integer) session.getAttribute("loginSession");
        // user infoとthread listを取得するロジックを実行する
        User user = new FetchUserInfo().fetch(userId);
        ArrayList<model.schema.Thread> threadList = new FetchThreadListByUserId().fetch(userId);
        // 取得したデータをそれぞれリクエストスコープに渡す
        request.setAttribute("user", user);
        request.setAttribute("threadList", threadList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accountInfo.jsp");
        dispatcher.forward(request, response);
	}

}
