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
import internetForum.validation.PostResponseValidation;
import model.EditResponseModel;
import model.FetchResponse;
import model.PostResponseModel;
import model.schema.Response;

/**
 * Servlet implementation class PostResponse
 */
@WebServlet("/response")
public class PostOrEditResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 編集ボタンをクリックした際の処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String responseIdStr = request.getParameter("id");
        // 有効なresponseIdの場合
        if (new NumberValidation().isInteger(responseIdStr)) {
            Integer responseId = Integer.parseInt(responseIdStr);
            // responseIdからresponseを取得
            Response res = new FetchResponse().fetch(responseId);
            if (res.getUserId() == (Integer) session.getAttribute("loginSession")) {
                request.setAttribute("initValue", res.getDescription());
                request.setAttribute("responseId", responseId);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/thread?id=" + res.getThreadId());
                dispatcher.forward(request, response);
                return;
            }
        }
        // 無効なresponseIdの場合、NOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションからユーザーidを取得する
	    HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("loginSession");
		// クエリパラメータからthreadIdを取得する
		int threadId = Integer.parseInt(request.getParameter("id"));
		// 入力内容を取得する
		String desc = request.getParameter("desc");
		// hiddenで送信されたresponseIdパラメータを取得
		String responseIdStr = request.getParameter("responseId"); 
		
		// 返信フォームの入力チェック
		if (new PostResponseValidation().validation(desc)) {
		    // resonseIdStrがnullじゃなく、数値変換可能であれば、レス編集のメソッドを実行
		    if (new NumberValidation().isInteger(responseIdStr)) {
		        new EditResponseModel().editResponse(threadId, Integer.parseInt(responseIdStr), desc);
		    } else {
		        // レス投稿のメソッド実行
		        int row = new PostResponseModel().postResponse(userId, threadId, desc);
		        // 投稿に成功したらリダイレクト
		        if (row != 0) {
		            response.sendRedirect("thread?id=" + threadId);
		            return;
		        }
		    }
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/thread?id=" + threadId);
		dispatcher.forward(request, response);
	}

}
