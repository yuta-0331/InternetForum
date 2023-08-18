package internetForum;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import internetForum.validation.CreateThreadValidation;

/**
 * Servlet implementation class CreateThread
 */
@WebServlet("/create_thread")
public class CreateThread extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// sessionを持っていない場合、TOPページへリダイレクトする。
		if (session != null && ((Integer) session.getAttribute("loginSession") != null)) {
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createThread.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		response.sendRedirect("top");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // パラメータの取得
	    String title = request.getParameter("threadTitle");
		String desc = request.getParameter("threadDesc");
		int genreId = Integer.parseInt(request.getParameter("genre"));
		int userId = (Integer) request.getSession(false).getAttribute("loginSession");
		
		// 入力チェック
		if (new CreateThreadValidation().validation(title, desc)) {
		    // スレッドの作成ロジックを実行する
		    model.CreateThreadModel createThread = new model.CreateThreadModel();
		    int row = createThread.create(title, desc, genreId, userId);
		    if (row == 1) {
		        response.sendRedirect("thread?id=" + createThread.getThreadId());
		        return;
		    }
		}
	    // 書き込み失敗した際に、入力内容を保持するためにリクエストスコープに入力内容を保管する。
        request.setAttribute("threadTitle", title);
        request.setAttribute("threadDesc", desc);
        // バリデーションで弾かれた場合、スレッド作成画面から移動しない
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createThread.jsp");
		dispatcher.forward(request, response);
		
	}

}
