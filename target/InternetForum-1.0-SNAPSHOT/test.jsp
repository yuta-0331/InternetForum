<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <meta name="description" content="お絵描きコンポーネント">
        <style><%@include file="/WEB-INF/css/style.css" %></style>
    </head>
<body>
    <canvas id="canvas" height="500" width="1024" style="border-style: solid; border-color: black;"></canvas>
    <img alt="猫の画像" src="image/bbs.png" id="my_image" hidden>
    <br>
    <div>
        <label for="black">
        <input type="radio" id="black" name="color">黒</label>
        <label for="blue">
        <input type="radio" id="blue" name="color">青</label>
        <label for="red">
        <input type="radio" id="red" name="color">赤</label>
        <label for="yellow">
        <input type="radio" id="yellow" name="color">黄</label>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fabric.js/4.5.0/fabric.min.js"></script>
    <script type="text/javascript">
        const colorArray = ["black", "blue", "red", "yellow"]
        const canvas = new fabric.Canvas("canvas");
        //配列処理を使用して、イベントリスナを登録
        colorArray.forEach((color) => {
        	document.getElementById(color).addEventListener("click", () => {
                canvas.freeDrawingBrush = new fabric.PencilBrush(canvas);
                canvas.freeDrawingBrush.width = 3;
                canvas.freeDrawingBrush.color = color;
                canvas.isDrawingMode = true;
            });
        })
      
        canvas.add(new fabric.Image(document.getElementById('my_image'), {
        	left: 400,
        	opacity: 0.85
        }));
    </script>
</body>
</html>