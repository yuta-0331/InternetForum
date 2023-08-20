<%@page import="internetForum.AbsolutePass"%>
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

               Response sampleRes = new Response.Builder(1).with(b -> {
                   b.userName = "tanaka";
                   b.description = "レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文レスの本文";
                   b.postedDate = "12/2";
                   b.userId = 13;
                   b.deleteFlag = true;
                   b.update = "update";
                   b.report = true;
               }).build();
               ArrayList<Response> responseList = new ArrayList<>(Arrays.asList(sampleRes, sampleRes, sampleRes));
           %>
           <div class="thread_list_response_form_container">
               <div class="thread_container">
                   <div class="thread_outer">
                       <div class="threads_inner">
                           <h1 class="thread_name"><%= "#" + thread.getThreadId() + ": " + thread.getTitle() %></h1>
                           <p class="thread_user_name"><a href="profile?id=<%= thread.getUserId() %>" rel="スレッド主のプロフィールページへ">by <%= thread.getUserName() %></a></p>
                           <p class="thread_desc"><%= thread.getDesc() %></p>
                           <p class="thread_create_day"><%= thread.getCreateDay() %></p>
                       </div>
                   </div>
                   <div class="response_outer">
                       <%
                           int i = 1;
                           // responseの表示
                           for (Response res : responseList) {
                               // deleteFlagがtrueならresponseを表示
                               if (res.isDeleteFlag()) {
                                   out.println("<div class='res_inner'><p class='res_name'>[" + i++ + "] "
                                           + "<a href='profile?id=" + res.getUserId() + "' rel='レス者のプロフィールページへ'>" + res.getUserName() + "</a></p>"
                                           + "<p class='res_description'>"
                                           +  res.getDescription());
                                   // 編集された値なら（編集済）表示を付与する
                                   if (res.getUpdate() != null) {
                                       out.println("<span><small style='color: red'>(編集済)</small></span>");
                                   }

                                   out.println("<span class='res_posted_date'>" + res.getPostedDate() + "</span></p>");
                                   // ログイン中かつ自分以外の投稿に通報ボタンを表示する
                                   if (isLogin && loginSession != res.getUserId()) {
                                       out.println("<div class='res_report_container'><a rel='通報ボタン' href='" + AbsolutePass.PASS + "response/report?id=" + res.getResponseId() + "'><span class='res_report_button'>"
                                               + "通報"
                                               + "</span></a>");
                                       // リクエストスコープから通報フラグを取得してtrueならポップアップを表示する
                                       Boolean isReport = (Boolean) request.getAttribute("reportFlag" + res.getResponseId());
                                       if (isReport != null && isReport ) {
                                           out.println("<form class='res_report_popup' method='post' action='" + AbsolutePass.PASS + "response/report?id=" + res.getResponseId() + "'>" 
                                           + "<p>通報しますか？</p>"
                                           + "<input type='submit' value='通報'>"
                                           + "<a href='" + AbsolutePass.PASS + "thread?id=" + res.getThreadId() + "'>キャンセル</a>"
                                           + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
                                           + "<input type='text' name='responseId' value='" + res.getResponseId() + "' hidden>"
                                           + "<input type='text' name='threadId' value='" + res.getThreadId() + "' hidden>"
                                           + "</form></div>");
                                       }
                                   }
                                   // 管理者なら削除ボタンを表示する
                                   if (isAdmin) {
                                       out.println("<a rel='削除ボタン' href='" + AbsolutePass.PASS + "response/delete?id=" + res.getResponseId() + "'><span class='res_delete_button'>"
                                               + "削除"
                                               + "</span></a>");
                                   }
                                   // 作成者なら編集ボタンを表示する
                                   if (isLogin && ((Integer) session.getAttribute("loginSession")) == res.getUserId()) {
                                       out.println("<a rel='編集ボタン' href='" + AbsolutePass.PASS + "response?id=" + res.getResponseId() + "'><span class='res_edit_button'>"
                                               + "編集"
                                               + "</span></a>");
                                   }
                                   out.println("</div>");
                               }

                           }
                       %>
                   </div>
               </div>
               <div class="response_form_container">
                   <div class="response_form_inner">
                       <%
                           String initValue = (String) request.getAttribute("initValue");
                           Integer responseId = (Integer) request.getAttribute("responseId");

                           // ログイン中なら、response投稿フォームを表示す
                           if (isLogin) {
                               // 返信の編集中の場合、responseIdに値が渡るので、編集中と表示を出す
                               if (responseId != null) {
                                   out.println("返信の編集中...");
                               }
                               out.println(
                                       "<form class='res_form_container' action='" + AbsolutePass.PASS + "response?id=" + request.getParameter("id") + "' method='post'>"
                                               + "<p>" + session.getAttribute("loginUserName") + "でログイン中</p>"
                                               + "<textarea name='desc' id='desc'>");
                               // initValueが空でなければtextareaの初期値に設定する
                               if (initValue != null) {
                                   out.println(initValue);
                               }
                               out.println("</textarea><div class='res_form_submit_container'><input class='res_form_submit' type='submit' value='投稿'></div>"
                                       // csrf tokeの付与
                                       + "<input type='text' name='csrfToken' value='" +(String) request.getSession(false).getAttribute("csrfToken") + "' hidden>"
                                       // responseIdを隠しパラメータで送信
                                       + "<input type='text' name='responseId' value='" + responseId + "' hidden></form>"
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
                           out.println("<p class='logout_button_wrapper'><a href='" + AbsolutePass.PASS + "logout' rel='ログアウト画面へ移動'>ログアウト</a></p> ");
                       }
                   %>
               </div>
           </div>
       </footer>
    </body>
</html>