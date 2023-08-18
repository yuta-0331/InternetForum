<%@page import="java.util.ArrayList"%>
<%@page import="model.schema.Genre"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="会員制の掲示板サイトです">
        <title>the掲示板</title>
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
	       <div class="main_container">
               <div class="canvas_container">
                   <canvas id="canvas"></canvas>
               </div>
               <br>
               <ul class="color_select">
                   <%
                       String[] colorArray = { "black", "blue", "red", "yellow", "orange", "pink", "green", "purple" };
                       for (String color : colorArray) {
                           out.println("<li id=\"" + color + "\">" + color + "</li>");
                       }
                   %>
               </ul>
               <ul class="font_weight_select">
                   <li id="thick">太</li>
                   <li id="medium">並</li>
                   <li id="thin">細</li>
               </ul>
               <div class="info_genre_list_container">
                   <div class="info_container">
                       <h2>更新情報</h2>
                       <ul>
                           <li>・トップページにお絵描き機能実装</li>
                           <li>・スレッド作成、レスポンス機能実装</li>
                           <li>・通報機能実装</li>
                       </ul>
                   </div>
                   <div class="genre_list_container">
                       <h2>ジャンルメニュー</h2>
                       <ul>
                           <%
                               ArrayList<Genre> genreList = (ArrayList<Genre>) request.getAttribute("genreList");
                               for (Genre genre : genreList) {
                                   out.println(
                                           "<li><a rel='" + genre.getGenreName()
                                           + "のスレッド一覧ページへ移動' href='thread_list?genre=" + genre.getGenreId() + "'>" + genre.getGenreName() + "</a></li>"
                                   );
                               }
                           %>
                       </ul>
                   </div>
               </div>
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
	   <script src="https://cdnjs.cloudflare.com/ajax/libs/fabric.js/4.5.0/fabric.min.js"></script>
	   <script type="text/javascript">
		   const colorArray = ["black", "blue", "red", "yellow", "orange", "pink", "green", "purple"];
		   const lineWeightArray = [{"thin": 1}, {"thick": 6}, {"medium": 3}];
		   // キャンバスとブラウザサイズの自動取得
		   const canvas = new fabric.Canvas("canvas", {
			   width: window.innerWidth - 100,
			   height: window.innerHeight / 2
		   });
		   canvas.freeDrawingBrush = new fabric.PencilBrush(canvas);
		   canvas.isDrawingMode = true;
		   // 配列処理を使用して、イベントリスナを登録
		   colorArray.forEach((color) => {
			   document.getElementById(color).addEventListener("click", () => {
				   canvas.freeDrawingBrush.color = color;
			   });
		   })
		   lineWeightArray.forEach((weight) => {
			   let key = Object.keys(weight)[0];
			   let value = Object.values(weight)[0];
			   document.getElementById(key).addEventListener("click", () => {
				   canvas.freeDrawingBrush.width = value;
			   });
		   });

		   fabric.Image.fromURL('${pageContext.request.contextPath}/image/bbs.png', (oImg) => {
			   oImg.scale(0.3).set({
				   opacity: 0.8,
				   left: window.innerWidth - 300,
			   }).set('selectable', false); // イメージを選択できない設定
			   canvas.add(oImg);
		   });

		   canvas.add(new fabric.Text("Draw freely", {
			   fontStyle: 'italic',
			   fontFamily: 'Delicious',
			   opacity: 0.5
		   }));

	   </script>
    </body>
</html>