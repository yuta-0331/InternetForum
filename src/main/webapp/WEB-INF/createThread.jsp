<%@page import="model.schema.Genre"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.FetchGenreList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="会員制の掲示板サイトです">
        <title>スレッド作成</title>
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
                       <%
	                       if (isAdmin) {
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
           <div class="thread_form_container">
               <form action="create_thread" method="post" class="form_thread">
                   <div class="form_inner">
                       <div class="genre_select">
                           <label for="genre">ジャンル選択</label>
                           <select name='genre' id="genre">
                               <%
                                   // コントローラでfetchしてリクエストパラメータで渡すように変更すること
                                   ArrayList<Genre> genreList = new FetchGenreList().fetch();
                                   for (Genre genre : genreList) {
                                       out.println("<option value='" + genre.getGenreId() + "'>" + genre.getGenreName() + "</option>");
                                   }
                               %>
                           </select>
                       </div>
                       <div>
                           <label for="threadTitle">title</label>
                           <input type="text" id="threadTitle" name="threadTitle" 
                               <% 
                                   // valueに初期値を設定
                                   String title = (String) request.getAttribute("threadTitle"); 
                                   if (title != null) {
                                       out.println("value=\"" + title + "\"");
                                   }
                               %>>
                       </div>
                       <div>
                           <label for="threadDesc">スレッド本文</label>
                           <textarea id="threadDesc" name="threadDesc">
                               <%
                                   // textareaの初期値を設定
                                   String desc = (String) request.getAttribute("threadDesc"); 
                                   if (desc != null) {
                                       out.println(desc);
                                   }
                               %>
                           </textarea>
                       </div>
                       <%
                           // csrf tokenの送信
                           out.println("<input type=\"text\" name=\"csrfToken\" value=\"" +(String) request.getSession(false).getAttribute("csrfToken") + "\" hidden>");
                       %>
                       <div class="thread_form_submit">
                           <p style="color: red"><small></small></p>
                           <input type="submit" value="作成">
                       </div>
                   </div>
               </form>
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