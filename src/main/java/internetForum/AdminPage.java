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

import internetForum.validation.NumberValidation;
import model.FetchReportedResponseList;
import model.FetchResponse;
import model.FetchThreadListByUserId;
import model.FetchUserInfo;
import model.schema.Response;
import model.schema.User;

/**
 * Servlet implementation class AdminPage
 */
@WebServlet("/admin")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // responseIdの取得
        String responseIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // admin sessionを持っていないか、無効なresponseIdの場合はNOT FOUNDを表示する
        if (session.getAttribute("adminSession") != null && (Integer) session.getAttribute("adminSession") != 0) {
         // 通報されたresponse listを取得するメソッドを実行する
            ArrayList<Response> responseList = new FetchReportedResponseList().fetch();
            // 取得したデータをそれぞれリクエストスコープに渡す
            request.setAttribute("responseList", responseList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/adminPage.jsp");
            dispatcher.forward(request, response);
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
