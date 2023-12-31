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
import model.DeleteResponseModel;
import model.FetchResponse;
import model.schema.Response;

/**
 * Servlet implementation class DeleteResponse
 */
@WebServlet("/response/delete")
public class DeleteResponse extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // responseIdの取得
        String responseIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        // adminセッションを持ち、有効なresponseIdの場合
        if (new AdminValidation().valid(session) && new NumberValidation().isInteger(responseIdStr)) {
            // responseIdからresponseを取得して、リクエストスコープに保存
            Response res = new FetchResponse().fetch(Integer.parseInt(responseIdStr));
            request.setAttribute("response", res);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/deleteResponse.jsp");
            dispatcher.forward(request, response);
            return;
        }
        // admin sessionを持っていないか、無効なresponseIdの場合はNOT FOUNDを表示する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/notFound.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isAdminPage = request.getParameter("adminPage");
        // admin sessionを持っているポストのみ実行する
        HttpSession session = request.getSession(false);
        if (session != null && (Integer) session.getAttribute("adminSession") != 0) {
            // パラメータからresponseId, threadIdを取得する
            String responseIdStr = request.getParameter("responseId");
            String threadIdStr = request.getParameter("threadId");
            int row = new DeleteResponseModel().delete(Integer.parseInt(responseIdStr));
            if (row != 0) {
                // 管理ページからの削除postは管理ページにリダイレクトする
                if (isAdminPage == null || isAdminPage != null && isAdminPage.equals("true")) {
                    response.sendRedirect("../admin");
                    return;
                } else {
                    response.sendRedirect("../thread?id=" + threadIdStr);
                    return;
                }
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("../notFound.jsp");
        dispatcher.forward(request, response);
    }
}
