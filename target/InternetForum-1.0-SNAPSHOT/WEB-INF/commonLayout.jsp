<%@page import="model.FetchGenreList"%>
<%@page import="model.schema.Genre"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="internetForum.AbsolutePass" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="会員制の掲示板サイトです">
        <title></title>
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
		   <div class='reported_item_container'>
			   <div class='reported_response_container'>
				   <%=
					   "<a rel='削除対象のレスの書かれたスレッドへ移動' href='" + AbsolutePass.PASS + "thread?id=" + "'><p>" + "description" + "</p></a>"
					   + "<form class='' method='post' action='" + AbsolutePass.PASS + "response/delete?id=" + "?adminPage=true'>"
					   + "<input type='submit' value='削除'>"
					   + "<input type='submit' value='キャンセル' formaction='" + AbsolutePass.PASS + "response/cancel_report?id=" + "'>"
					   + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
					   + "<input type='text' name='responseId' value='" + "' hidden>"
					   + "<input type='text' name='threadId' value='" + "' hidden>"
					   + "</form>"
				   %>
			   </div>
			   <div class='reported_user_container'>
				   <%=
					   "<a rel='通報されたユーザーのプロフィールへ移動' href='" + AbsolutePass.PASS + "profile?id=" + "'>"
					   + "user name"
					   + "</a>"
					   + "<form method='post' action='" + AbsolutePass.PASS + "account_info/delete?id=" + "'>"
					   + "<input type='submit' value='削除'>"
					   + "<input type='submit' value='キャンセル' formaction='" + AbsolutePass.PASS + "profile/cancel_report?id=" + "'>"
					   + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
					   + "</form>"
				   %>
			   </div>
			   <div class='reported_thread_container'>
				   <%=
				       "<a rel='削除対象のスレッドへ移動' href='" + AbsolutePass.PASS + "thread?id=" + "'><p>" + "title" + "</p></a>"
					   + "<form class='' method='post' action='" + AbsolutePass.PASS + "thread/delete?id='>"
					   + "<input type='submit' value='削除'>"
					   + "<input type='submit' value='キャンセル' formaction='" + AbsolutePass.PASS + "thread/cancel_report?id=" + "aa" + "'>"
					   + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
					   + "</form>"
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