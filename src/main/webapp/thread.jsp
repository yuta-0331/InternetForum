<%@page import="model.schema.Thread"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.schema.Response"%>
<%@page import="model.schema.ThreadWithResponseList"%>
<%@ page import="java.util.Arrays" %>
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
                   <a href="top" rel="TOPページへ移動">the掲示板</a>
               </div>
               <nav class="header_nav">
                   <ul class="header_menu">
                       <% if (isAdmin) {
                           out.println("<li class='header_menu_item'><a href='admin' rel='管理者メニューへ移動'>管理者</a></li>");
                           }
                          if (isLogin) {
                           out.println("<li class='header_menu_item'><a href='account_info' rel='会員管理画面へ移動'>管理画面</a></li>");
                           }
                          if (isLogin) {
                              out.println("<li class='header_menu_item'>ログイン中</li>");
                          } else {
                              out.println("<li class='header_menu_item'><a href='login' rel='ログイン/新規登録画面へ移動'>ログイン/登録</a></li>");
                          }
                       %>
                   </ul>
               </nav>
           </div>
       </header>
       <main>
           <% 
//               ThreadWithResponseList list = (ThreadWithResponseList) request.getAttribute("threadWithResponseList");
//               Thread thread = list.getThread();
//               ArrayList<Response> responseList = list.getResponseList();
               Thread thread = new Thread.Builder(1).with(b -> {
                   b.title = "title";
                   b.userId = 12;
                   b.userName = "yuta";
                   b.desc = "スレッド本文";
                   b.createDay = "12/1";
               }).build();
               ArrayList<Response> responseList = new ArrayList<>(Arrays.asList(new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
               b.userName = "tanaka";
               b.description = "レスの本文";
               b.postedDate = "12/2";
               b.userId = 13;
               b.deleteFlag = true;
           }).build(), new Response.Builder(1).with(b -> {
               b.userName = "tanaka";
               b.description = "レスの本文";
               b.postedDate = "12/2";
               b.userId = 13;
               b.deleteFlag = true;
           }).build(), new Response.Builder(1).with(b -> {
               b.userName = "tanaka";
               b.description = "レスの本文";
               b.postedDate = "12/2";
               b.userId = 13;
               b.deleteFlag = true;
           }).build(), new Response.Builder(1).with(b -> {
               b.userName = "tanaka";
               b.description = "レスの本文";
               b.postedDate = "12/2";
               b.userId = 13;
               b.deleteFlag = true;
           }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build(), new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
               }).build()));
           %>
           <div class="thread_list_response_form_container">
               <div class="thread_container">
                   <div class="thread_outer">
                       <div class="threads_inner">
                           <h1 class="thread_name"><%= thread.getTitle() %></h1>
                           <p class="thread_user_name"><a href="profile?id=<%= thread.getUserId() %>" rel="スレッド主のプロフィールページへ">by <%= thread.getUserName() %></a></p>
                           <p class="thread_desc"><%= thread.getDesc() %></p>
                           <p class="thread_create_day"><%= thread.getCreateDay() %></p>
                       </div>
                   </div>
                   <dl class="response_outer">
                       <%
                           int i = 1;
                           // responseの表示
                           for (Response res : responseList) {
                               // deleteFlagがtrueならresponseを表示
                               if (res.isDeleteFlag()) {
                                   out.println("<p class='res_num'>[" + i++ + "] </p>" +
                                           "<dt class='res_user_name'><a href='profile?id=" + res.getUserId() + "' rel='レス者のプロフィールページへ'>" + res.getUserName() + "</a></dt>"
                                           + "<dd class='res_description'>"
                                           +  res.getDescription());
                                   // 編集された値なら（編集済）表示を付与する
                                   if (res.getUpdate() != null) {
                                       out.println("<span>(編集済)</span>)");
                                   }
                                   // ログイン中なら通報ボタンを表示する
                                   if (isLogin) {
                                       out.println("<a rel='通報ボタン' href='profile/report?id=" + res.getUserId() + "'><span>"
                                               + "通報"
                                               + "</span></a>");
                                   }
                                   // 管理者なら削除ボタンを表示する
                                   if (isAdmin) {
                                       out.println("<a rel='削除ボタン' href='response/delete?id=" + res.getResponseId() + "'><span>"
                                               + "削除"
                                               + "</span></a>");
                                   }
                                   // 作成者なら編集ボタンを表示する
                                   if (isLogin && ((Integer) session.getAttribute("loginSession")) == res.getUserId()) {
                                       out.println("<a rel='編集ボタン' href='response?id=" + res.getResponseId() + "'><span>"
                                               + "編集"
                                               + "</span></a></dd>");
                                   }
                               }

                           }
                       %>
                   </dl>
               </div>
               <div class="response_form_container">
                   <div class="response_form_inner">
                       <%
                           String initValue = (String) request.getAttribute("initValue");
                           Integer responseId = (Integer) request.getAttribute("responseId");

                           // ログイン中なら、response投稿フォームを表示する true -> isLogin
                           if (true) {
                               // 返信の編集中の場合、responseIdに値が渡るので、編集中と表示を出す
                               if (responseId != null) {
                                   out.println("返信の編集中...");
                               }
                               out.println(
                                       "<form class='res_form_container' action='response?id=" + request.getParameter("id") + "' method='post'"
                                               + "<p>" + session.getAttribute("loginUserName") + "でログイン中</p>"
                                               + "<textarea name='desc' id='desc'>");
                               // initValueが空でなければtextareaの初期値に設定する
                               if (initValue != null) {
                                   out.println(initValue);
                               }
                               out.println("</textarea><input class='res_form_submit' type='submit' value='投稿'>"
                                       // csrf tokeの付与
                                       + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
                                       // responseIdを隠しパラメータで送信
                                       + "<input type='text' name='responseId' value='" + responseId + "' hidden>"
                               );
                           }
                       %>
                   </div>
               </div>
           </div>
       </main>
       <footer>
           <div class="footer_container">
               <div class="footer_inner">
                   <p class="copy_right"><small>&copy; the掲示板</small></p>
                   <%
                       if (isLogin) {
                           out.println("<p class='logout_button_wrapper'><a href='logout' rel='ログアウト画面へ移動'>ログアウト</a></p> ");
                       }
                   %>
               </div>
           </div>
       </footer>
    </body>
</html>