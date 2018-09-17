<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>LotusAdmin</title>
    <link rel="stylesheet" type="text/css" href="css//main/style.css" tppabs="css/style.css" />
    <meta name="description" content="lotusadmin">

    <style>
        body{
            height:100%;
            background:#16a085;
            overflow:hidden;
        }
        canvas{
            z-index:-1;
            position:absolute;
        }
    </style>
    <script src="js/jquery.min.js"></script>
    <script src="lib/layer/layer.js"></script>
    <%--<script src="js/verificationNumbers.js" tppabs="js/verificationNumbers.js"></script>--%>
    <script src="js/Particleground.js" tppabs="js/Particleground.js"></script>

</head>
<body>
<dl class="admin_login">
    <dt>
        <strong>Lotus_Admin极速开发框架</strong>

    </dt>
    <dd class="user_icon">
        <input type="text" name="username" id='username' value="admin" placeholder="账号" class="login_txtbx"/>
    </dd>
    <dd class="pwd_icon">
        <input type="password" name="password" value="123456"  id="password" placeholder="密码" class="login_txtbx"/>
    </dd>

     <%--<dd class="val_icon">--%>
      <%--<div class="checkcode">--%>
        <%--<input type="text" name="username"  id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">--%>
        <%--<canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>--%>
      <%--</div>--%>
      <%--<input type="button" value="验证码核验" class="ver_btn" onClick="validate();">--%>
     <%--</dd>--%>
    <dd>
        <button id="bs" onclick="login()" type="button"  value="立即登陆" class="submit_btn"/>登录</button>
    </dd>
    <dd>
        <p>© 2015-2016 wenhainan 版权所有</p>
        <p>沪B2-8998988-1</p>
    </dd>
</dl>
</body>
<script>
    $(document).ready(function() {
        //粒子背景特效
        $('body').particleground({
            dotColor: '#5cbdaa',
            lineColor: '#5cbdaa'
        });
        //验证码
        createCode();
        //测试提交，对接程序删除即可
        $(".submit_btn").click(function(){
            // location.href="javascrpt:"tpa=http://***index.html;\

        });
    });


    function login(){
        var username  = $('#username').val();
        var password  = $('#password').val();
        $.ajax({
            url: 'login',
            type: 'post',
            dataType: 'json',
            data:{
                username: username,
                password:password
            },
        }).done(function(data){
                if(data.flag){
                    location.href = "/app/main/index.html";
                }else{
                    layer.msg('用户名或密码错误，请重新输入！',{icon:0},function(){
                    });

                }
            })
    }
    document.onkeydown = function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            login();
        }
    }

    layer.msg('账号:admin  密码:123456');

</script>
</html>
