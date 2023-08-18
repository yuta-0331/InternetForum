package internetForum;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FetchThreadWithResponseList;
import model.schema.ThreadWithResponseList;

/**
 * Servlet implementation class Thread
 */
@WebServlet("/thread")
public class ThreadPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // クエリパラメータを取得する
	    String threadIdStr = request.getParameter("id");
	    // クエリパラメータが存在しない場合はNOT FOUNDページを表示
	    if (threadIdStr == null) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
	        dispatcher.forward(request, response);
	        return;
	    }
	    // クエリパラメータが存在する場合
	    ThreadWithResponseList list = new FetchThreadWithResponseList().fetch(Integer.parseInt(threadIdStr));
	    if (list == null) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
            dispatcher.forward(request, response);
            return;
	    }
	    request.setAttribute("threadWithResponseList", list);
	    
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/thread.jsp");
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
