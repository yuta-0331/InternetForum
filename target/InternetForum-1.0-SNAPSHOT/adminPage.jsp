<%@page import="internetForum.AbsolutePass"%>
<%@page import="model.schema.Response"%>
<%@page import="model.FetchGenreList"%>
<%@page import="model.schema.Genre"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="会員制の掲示板サイトです">
        <title>管理者ページ</title>
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
                   <a href="top" rel="TOPページへ移動">the掲示板</a>
               </div>
               <nav class="header_nav">
                   <ul class="header_menu">
                       <% if (isAdmin) {
                           out.println("<li class=\"header_menu_item\"><a href=\"admin\" rel=\"管理者メニューへ移動\">管理者</a></li>");
                           }
                          if (isLogin) {
                           out.println("<li class=\"header_menu_item\"><a href=\"account_info\" rel=\"会員管理画面へ移動\">管理画面</a></li>");
                           }
                          if (isLogin) {
                              out.println("<li class=\"header_menu_item\">ログイン中</li>");
                          } else {
                              out.println("<li class=\"header_menu_item\"><a href=\"login\" rel=\"ログイン/新規登録画面へ移動\">ログイン/登録</a></li>");
                          }
                       %>
                   </ul>
               </nav>
           </div>
       </header>
       <main>
           <%
               ArrayList<Response> responseList = (ArrayList<Response>) request.getAttribute("responseList");
           for (Response res : responseList) {
               out.println("<a rel='削除対象のレスの書かれたスレッドへ移動' href='" + AbsolutePass.PASS + "thread?id=" + res.getThreadId() + "'><p>" + res.getDescription() + "</p></a>"
                       + "<form class='res_report_popup' method='post' action='" + AbsolutePass.PASS + "response/delete?id=" + res.getResponseId() + "'>" 
                       + "<input type='submit' value='削除'>"
                       + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
                       + "<input type='text' name='responseId' value='" + res.getResponseId() + "' hidden>"
                       + "<input type='text' name='threadId' value='" + res.getThreadId() + "' hidden>"
                       + "</form>");
           }
           %>
       </main>
       <footer>
           <div class="footer_container">
               <div class="footer_inner">
                   <p class="copy_right"><small>&copy; the掲示板</small></p>
                   <%
                       if (isLogin) {
                           out.println("<p class=\"logout_button_wrapper\"><a href=\"logout\" rel=\"ログアウト画面へ移動\">ログアウト</a></p> ");
                       }
                   %>
               </div>
           </div>
       </footer>
    </body>
</html>