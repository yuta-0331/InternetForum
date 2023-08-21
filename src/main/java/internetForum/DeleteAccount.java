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
import model.DeleteAccountModel;
import model.FetchUserInfo;
import model.schema.User;

/**
 * Servlet implementation class DeleteAccount
 */
@WebServlet("/account_info/delete")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // userIdの取得
        String userIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // 有効なuserIdで、そのuserIdと一致するloginセッションを持っているか
        if (new NumberValidation().isInteger(userIdStr) && session!= null && Integer.parseInt(userIdStr) == (Integer) session.getAttribute("loginSession")
                // 有効なuserIdでadminセッションを持っている場合
                || new AdminValidation().valid(session) && new NumberValidation().isInteger(userIdStr)) {
            // userIdからuserを取得して、リクエストスコープに保存
            User user = new FetchUserInfo().fetch(Integer.parseInt(userIdStr));
            request.setAttribute("userInfo", user);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/deleteAccount.jsp");
            dispatcher.forward(request, response);
            return;
        }
        // admin sessionを持っていないか、無効なuserIdの場合、loginユーザーと異なるuserIdの場合はNOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // userIdの取得
        String userIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // 有効なuserIdで、そのuserIdと一致するloginセッションを持っているか
        if (new NumberValidation().isInteger(userIdStr) && session!= null && Integer.parseInt(userIdStr) == (Integer) session.getAttribute("loginSession")
                // 有効なuserIdでadminセッションを持っている場合
                || new AdminValidation().valid(session) && new NumberValidation().isInteger(userIdStr)) {
            // userを削除するメソッドの実行
            new DeleteAccountModel().delete(Integer.parseInt(userIdStr));
            
            // adminならtopへリダイレクト、ユーザー本人ならログアウト処理
            if (new AdminValidation().valid(session)) {
                response.sendRedirect(AbsolutePass.PASS + "top");
                return;
            } else {
                response.sendRedirect(AbsolutePass.PASS + "logout");
                return;
            }
            
        }
        // admin sessionを持っていないか、無効なuserIdの場合、loginユーザーと異なるuserIdの場合はNOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}

}
