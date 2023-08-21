package internetForum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FetchThreadListByUserId;
import model.FetchThreadWithResponseList;
import model.FetchUserInfo;
import model.schema.Thread;
import model.schema.ThreadWithResponseList;
import model.schema.User;

/**
 * Servlet implementation class ProfilePage
 */
@WebServlet("/profile")
public class ProfilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // クエリパラメータを取得する
        String userIdStr = request.getParameter("id");
        // クエリパラメータが存在しない場合はNOT FOUNDページを表示
        if (userIdStr == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
            dispatcher.forward(request, response);
            return;
        }
        // クエリパラメータが存在する場合user情報を取得する
        User user = new FetchUserInfo().fetch(Integer.parseInt(userIdStr));
        // user 情報が見つからないか、削除フラグが立っている場合、NOT FOUNDを表示
        if (user == null || !user.isDeleteFlag()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
            dispatcher.forward(request, response);
            return;
        }
        // userIdから、スレッドリストを取得する
        ArrayList<Thread> threadList = new FetchThreadListByUserId().fetch(user.getUserId());
        // user情報,スレッドリストをリクエストスコープにセット
        request.setAttribute("userInfo", user);
        request.setAttribute("threadList", threadList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/profile.jsp");
        dispatcher.forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
