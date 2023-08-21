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
import model.FetchUserInfo;
import model.schema.User;

/**
 * Servlet implementation class EditAccountInfo
 */
@WebServlet("/account_info/edit")
public class EditAccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // userIdの取得
        String userIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // userIdが有効で、そのuserIdと一致するloginセッションを持っているか
        if (new NumberValidation().isInteger(userIdStr) && session!= null && Integer.parseInt(userIdStr) == (Integer) session.getAttribute("loginSession")) {
            // userIdからuserを取得して、リクエストスコープに保存
            User user = new FetchUserInfo().fetch(Integer.parseInt(userIdStr));
            request.setAttribute("userInfo", user);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editAccountInfo.jsp");
            dispatcher.forward(request, response);
            return;
        }
        // login sessionを持っていないか、無効なuserIdの場合、loginユーザーと異なるuserIdの場合はNOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userIdStr = request.getParameter("id");
		HttpSession session = request.getSession(false);
        // userIdが有効で、そのuserIdと一致するloginセッションを持っているか
		if (new NumberValidation().isInteger(userIdStr) && session!= null && Integer.parseInt(userIdStr) == (Integer) session.getAttribute("loginSession")) {
		    String userName = request.getParameter("userName");
		    String profile = request.getParameter("profile");
		}
	}

}
