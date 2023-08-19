package internetForum;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import internetForum.validation.NumberValidation;
import model.FetchResponse;
import model.ReportResponseModel;
import model.schema.Response;

/**
 * Servlet implementation class ReportResponse
 */
@WebServlet("/response/report")
public class ReportResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // responseIdの取得
        String responseIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // login sessionを持っていないか、無効なresponseIdの場合はNOT FOUNDを表示する
        if (session.getAttribute("loginSession") != null && responseIdStr != null && NumberValidation.isInteger(responseIdStr)) {
            // responseIdからresponseを取得して、リクエストスコープに保存
            Response res = new FetchResponse().fetch(Integer.parseInt(responseIdStr));
            request.setAttribute("response", res);
            // reportFlagをtrueにして、レスに通報確認のポップアップを表示させる
            request.setAttribute("reportFlag" + res.getResponseId(), true);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("../thread?id=" + res.getThreadId());
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
	    String responseIdStr = request.getParameter("id");
	    String threadIdStr = request.getParameter("threadId");
        HttpSession session = request.getSession(false);
	    // login sessionを持っていないか、無効なresponseIdの場合はNOT FOUNDを表示する
        if (session.getAttribute("loginSession") != null && responseIdStr != null && NumberValidation.isInteger(responseIdStr)) {
            int row = new ReportResponseModel().report(Integer.parseInt(responseIdStr));
            RequestDispatcher dispatcher = request.getRequestDispatcher("../thread?id=" + threadIdStr);
            dispatcher.forward(request, response);
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	}

}
