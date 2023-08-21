<%@page import="model.schema.User"%>
<%@page import="internetForum.AbsolutePass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="会員制の掲示板サイトです">
        <title>スレッド</title>
        <style><%@include file="/WEB-INF/css/style.css" %></style>
    </head>
    <body>
       <%
           Integer adminSession = (Integer) session.getAttribute("adminSession");
           Integer loginSession = (Integer) session.getAttribute("loginSession");
           
           boolean isAdmin = false, isLogin = false;
           if (adminSession != null && adminSession != 0) {
               isAdmin = true;
           }
           if (loginSession != null) {
               isLogin = true;
           }
       %>
       <header>
           <div class="header_outer">
               <div class="header_logo">
                   <a href='<%= AbsolutePass.PASS + "top" %>' rel="TOPページへ移動">the掲示板</a>
               </div>
               <nav class="header_nav">
                   <ul class="header_menu">
                       <% if (isAdmin) {
                           out.println("<li class='header_menu_item'><a href='" + AbsolutePass.PASS + "admin' rel='管理者メニューへ移動'>管理者</a></li>");
                           }
                          if (isLogin) {
                           out.println("<li class='header_menu_item'><a href='" + AbsolutePass.PASS + "account_info' rel='会員管理画面へ移動'>管理画面</a></li>");
                           }
                          if (isLogin) {
                              out.println("<li class='header_menu_item'>ログイン中</li>");
                          } else {
                              out.println("<li class='header_menu_item'><a href='" + AbsolutePass.PASS + "login' rel='ログイン/新規登録画面へ移動'>ログイン/登録</a></li>");
                          }
                       %>
                   </ul>
               </nav>
           </div>
       </header>
       <main>
           <%
               User user = (User) request.getAttribute("userInfo");
           %>
           <div class='edit_user_form_container'>
               <div>
                   <form action='<%= AbsolutePass.PASS + "account_info/edit?id=" + user.getUserId() %>' method='post'>
                       <div id="username_label">
                           <label for="userName">user name</label>
                           <input type="text" id="userName" name="userName">
                       </div>
                       <label for='profile'>profile</label>
                       <textarea id='profile' name='profile'></textarea>
                       <%=
                           // sessionからcsrf tokenを取得して、隠しパラメータとしてリクエストに付与する
                           "<input type=\"text\" name=\"csrfToken\" value=\"" +(String) request.getSession(false).getAttribute("csrfToken") + "\" hidden>"
                       %>
                       <div class="edit_user_form_submit">
                           <input type="submit" value="送信">
                       </div>
                   </form>
               </div>
           </div>
       </main>
       <footer>
           <div class="footer_container">
               <div class="footer_inner">
                   <p class="copy_right"><small>&copy; the掲示板</small></p>
                   <%
                       if (isLogin) {
                           out.println("<p class='logout_button_wrapper'><a href='" + AbsolutePass.PASS + "logout' rel='ログアウト画面へ移動'>ログアウト</a></p> ");
                       }
                   %>
               </div>
           </div>
       </footer>
       <script type="text/javascript">
           // 通報ボタンが押された際の画面再読み込み時に、当該返信の位置までスクロールさせる
           window.onload = () => {
               const $elem = document.getElementById("report");
               if (!$elem) return;
               const rect = $elem.getBoundingClientRect();
               const elemTop = rect.top + window.pageYOffset - 400;
               document.documentElement.scrollTop = elemTop;
           }
       </script>
    </body>
</html>