package internetForum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FetchGenreList;
import model.FetchThreadListByGenre;
import model.schema.Genre;
import model.schema.Thread;

/**
 * Servlet implementation class ThreadList
 */
@WebServlet("/thread_list")
public class ThreadList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// クエリパラメータの取得
	    String genreId = request.getParameter("genre");
	    ArrayList<Genre> genreList = new FetchGenreList().fetch();
	    // genreIdがあるかどうかチェック
	    if (genreId != null && !genreId.equals("")) {
    	    // クエリパラメータの値がジャンルリストに存在するかチェックする
    	    for (Genre genre : genreList) {
    	        if (genre.getGenreId() == Integer.parseInt(genreId)) {
    	            // 存在すればthreadList取得のロジックを実行
    	            ArrayList<Thread> list = new FetchThreadListByGenre().fetch(Integer.parseInt(genreId));
    	            // ジャンルリストをリクエストスコープに設定
    	            request.setAttribute("threadList", list);
    	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/threadList.jsp");
    	            dispatcher.forward(request, response);
    	            return;
    	        }
    	    }
	    }
	    // genreIdがジャンルリストに存在しない場合、NOT FOUNDページに遷移する
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
	    
	}

}
