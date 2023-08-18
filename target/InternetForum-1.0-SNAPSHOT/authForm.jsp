<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="会員制の掲示板サイトです">
        <title>ログイン/新規登録</title>
        <style><%@include file="/WEB-INF/css/style.css" %></style>
    </head>
	<body>
	   <%
	   String errorMsg = (String) request.getAttribute("errorMsg");
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
	               <a href="top" rel="TOPページへ移動">the 掲示板</a>
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
	       <div class="auth_form_container">
               <form action="login" method="post" class="form_login">
                   <div class="radio_container">
                       <label for="signup">
                       <input type="radio" name="auth" value="signup" id="signup" checked>サインアップ</label>
                       <label for="login">
                       <input type="radio" name="auth" value="login" id="login">ログイン</label>
                   </div>
                   <div class="auth_form_inner">
                       <div id="username_label">
                           <label for="userName">user name</label>
                           <input type="text" id="userName" name="userName">
                       </div>
                       <div>
                           <label for="mailAddress">mail</label>
                           <input type="email" id="mailAddress" name="mailAddress">
                       </div>
                       <div>
                           <label for="password">password</label>
                           <input type="password" id="password" name="password">
                       </div>
                       <%
                           // sessionからcsrf tokenを取得して、隠しパラメータとしてリクエストに付与する
                           out.println("<input type=\"text\" name=\"csrfToken\" value=\"" +(String) request.getSession(false).getAttribute("csrfToken") + "\" hidden>");
                       %>
                       <div class="auth_form_submit">
                           <p style="color: red">
                               <small><%= errorMsg != null ? errorMsg : ""%></small>
                           </p>
                           <input type="submit" value="送信">
                       </div>
                   </div>
               </form>
           </div>
	   </main>
	   <footer>
	       <div class="footer_container">
	           <div class="footer_inner">
	               <p class="copy_right"><small>&copy; the 掲示板</small></p>
				   <%
					   if (isLogin) {
						   out.println("<p class=\"logout_button_wrapper\"><a href=\"logout\" rel=\"ログアウト画面へ移動\">ログアウト</a></p> ");
					   }
				   %>
	           </div>
	       </div>
	   </footer>
	   <script type="text/javascript">
	       const $login = document.getElementById("login");
	       const $signup = document.getElementById("signup");
	       const $username_label = document.getElementById("username_label");
	       // radioボタンでクラスに.hidden付与の有無を出し分け
	       $login.addEventListener("change", () => {
	    	   $username_label.classList.add("hidden");
	       })
	       $signup.addEventListener("change", () => {
               $username_label.classList.remove("hidden");
           })
	   </script>
    </body>
</html>