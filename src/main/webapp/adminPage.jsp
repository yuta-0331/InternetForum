<%@page import="model.schema.Thread"%>
<%@page import="model.schema.User"%>
<%@page import="internetForum.AbsolutePass"%>
<%@page import="model.schema.Response"%>
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
               ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
               ArrayList<Thread> threadList = (ArrayList<Thread>) request.getAttribute("threadList");
           %>
           <div class='reported_item_container'>
               <div class='reported_response_container'>
		           <%
		               if (responseList.size() == 0) {
		                   out.println("通報されたresponseはありません");
		               }
			           for (Response res : responseList) {
			               out.println("<div class='reported_res_inner'>"
                                   + "<a rel='削除対象のレスの書かれたスレッドへ移動' href='" + AbsolutePass.PASS + "thread?id=" + res.getThreadId() + "'><p>" + res.getDescription() + "</p></a>"
			                       + "<form class='' method='post' action='" + AbsolutePass.PASS + "response/delete?id=" + res.getResponseId() + "?adminPage=true'>" 
			                       + "<input type='submit' value='削除'>"
			                       + "<input type='submit' value='キャンセル' formaction='" + AbsolutePass.PASS + "response/cancel_report?id=" + res.getResponseId() + "'>"
			                       + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
			                       + "<input type='text' name='responseId' value='" + res.getResponseId() + "' hidden>"
			                       + "<input type='text' name='threadId' value='" + res.getThreadId() + "' hidden>"
			                       + "</form></div>");
			           }
		           %>               
               </div>
               <div class='reported_user_container'>
		           <%
			           if (userList.size() == 0) {
	                       out.println("通報されたuserはありません");
	                   }
			           for (User user : userList) {
			               out.println("<div class='reported_user_inner'>"
                               +"<a rel='通報されたユーザーのプロフィールへ移動' href='" + AbsolutePass.PASS + "profile?id=" + user.getUserId() + "'>"
			                   + user.getUserName()
			                   + "</a>"
			                   + "<form method='post' action='" + AbsolutePass.PASS + "account_info/delete?id=" + user.getUserId() + "'>"
	                           + "<input type='submit' value='削除'>"
                               + "<input type='submit' value='キャンセル' formaction='" + AbsolutePass.PASS + "profile/cancel_report?id=" + user.getUserId() + "'>"
	                           + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
			                   + "</form></div>");
			           }
		           %>
               </div>
               <div class='reported_thread_container'>
                   <%
	                   if (threadList.size() == 0) {
	                       out.println("通報されたthreadはありません");
	                   }
	                   for (Thread thread : threadList) {
	                       out.println("<div class='reported_thread_inner'"
                                   + "<a rel='削除対象のスレッドへ移動' href='" + AbsolutePass.PASS + "thread?id=" + thread.getThreadId() + "'><p>" + thread.getTitle() + "</p></a>"
	                               + "<form class='' method='post' action='" + AbsolutePass.PASS + "thread/delete?id=" + thread.getThreadId() + "'>" 
	                               + "<input type='submit' value='削除'>"
	                               + "<input type='submit' value='キャンセル' formaction='" + AbsolutePass.PASS + "thread/cancel_report?id=" + thread.getThreadId() + "'>"
	                               + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
	                               + "</form></div>");
	                   }
                   %>
               </div>
           </div>
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