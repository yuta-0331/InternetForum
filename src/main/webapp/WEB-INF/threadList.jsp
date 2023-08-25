<%@page import="model.schema.Thread"%>
<%@page import="model.FetchGenreList"%>
<%@page import="model.schema.Genre"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="会員制の掲示板サイトです">
        <title>スレッド一覧</title>
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
           <div class="thread_list_container">
               <div class='breadcrumbs'>
                   <a href="top" rel="TOPページへ移動">>TOP</a>
               </div>
               <div class='thread_list_inner'>
                   <%
                       ArrayList<Thread> threadList = (ArrayList<Thread>)request.getAttribute("threadList");

                       if (threadList.size() == 0) {
                           out.println("<li>このジャンルはまだスレッドがありません</li>");
                       } else {
                           out.println("<table width='600px'>"
                                   + "<tr>"
                                   + "<th>スレッドタイトル</th>"
                                   + "<th>スレ主</th>"
                                   + "<th>最終書込み時間</th>"
                                   + "</tr>");
                           for (Thread th : threadList) {
                               if (!th.isDeleteFlag()) {
                                   continue;
                               }
                               out.println("<tr align='center'>"
                                       + "<td height='30px'><a href='thread?id=" + th.getThreadId() + "'>" + th.getTitle() + "</a></td>"
                                       + "<td><a href='profile?id=" + th.getUserId() + "'>" + th.getUserName() + "</a></td>"
                                       + "<td>" + th.getLastWrittenDate() + "</td>"
                                       + "</tr>");
                           }
                           out.println("</table>");
                       }
                   %>
               </div>
               <div class="create_thread_wrapper">
                   <a href="create_thread" rel="スレッド作成画面へ移動"><span>スレッド作成</span></a>
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