package internetForum;

import javax.servlet.*;
import javax.servlet.http.*;

import internetForum.validation.SignUpValidation;
import model.UserLogin;
import model.UserSignUp;

import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loginSession") != null) {
            response.sendRedirect("top");
            return;
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/authForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // postされたパラメータを取得する
        String authType = request.getParameter("auth");
        String userName = request.getParameter("userName");
        String mailAddress = request.getParameter("mailAddress");
        String password = request.getParameter("password");

        UserLogin userLogin = new UserLogin();
        // ログインにチェックがついている場合の処理
        if (authType.equals("login")) {
            // ログインに失敗した場合、エラーメッセージを返す
            if (!userLogin.login(mailAddress, password)) {
                request.setAttribute("errorMsg", "不正な値です");
                System.out.print(request.getAttribute("csrfToken"));
                System.out.println(request.getSession().getAttribute("csrfToken"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/authForm.jsp");
                dispatcher.forward(request, response);
                return;
            }
            // ログインに成功したらログインセッションにuserId, userNameを付与する
            HttpSession session = request.getSession(true);
            session.setAttribute("loginSession", userLogin.getUserId());
            session.setAttribute("loginUserName", userLogin.getUserName());
            response.sendRedirect("top");
            // ログイン成功後、管理者権限を持っているか確認、持っていればセッション付与
            if (userLogin.getAdminId() != null) {
                session.setAttribute("adminSession", userLogin.getAdminId());
            }
        } 
        // サインアップにチェックがついている場合の処理
        else {
            UserSignUp userSignUp = new UserSignUp();
            if (new SignUpValidation().validation(userName, mailAddress, password)) {
                int row = userSignUp.signup(mailAddress, userName, password);
                if (row == 1) {
                    HttpSession session = request.getSession(true);
                    // 新規作成に成功したらログイン状態にする。
                    userLogin.login(mailAddress, password);
                    session.setAttribute("loginSession", userLogin.getUserId());
                    session.setAttribute("loginUserName", userLogin.getUserName());
                    response.sendRedirect("top");
                    return;
                } else {
                    request.setAttribute("errorMsg", "新規作成に失敗しました");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/authForm.jsp");
                    dispatcher.forward(request, response);
                }
            } 
            // ログインフォームのバリデーションに引っかかった場合の処理
            else {
                request.setAttribute("errorMsg", "不正な値です");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/authForm.jsp");
                dispatcher.forward(request, response);;
            }
        }
    }
}