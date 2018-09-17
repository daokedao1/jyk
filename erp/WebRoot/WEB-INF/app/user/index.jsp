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
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title></title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="../lib/adminlte/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="../lib/adminlte/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="../lib/layui/css/layui.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="">
  <div class="box-header">
    <h3 class="box-title">产品管理</h3>
  </div>
  <!-- /.box-header -->
  <div class="box-body">
    <div class="box-tools pull-left">
      <div class="has-feedback">
        <input type="text" class="form-control input-sm" placeholder="Search...">
        <span class="glyphicon glyphicon-search form-control-feedback text-muted"></span>
      </div>
    </div>
    <div class="box-tools pull-right"> <button class="layui-btn layui-btn-normal"><i class="layui-icon"></i>新建</button></div>
    <table id="demo" class="layui-table" width="100%" lay-filter="test"></table>
  </div>
  <!-- /.box-body -->
</div>

</body >

<!-- jQuery 2.2.3 -->
<script src="../lib/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../lib/adminlte/bootstrap/js/bootstrap.min.js"></script>

<script src="../lib/layui/layui.js"></script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#demo'
            ,skin:'row'
            ,even:true
            //,height: "full-100"
            ,limit:10
            ,url: '../user/getAll' //数据接口
            ,method: 'post'
            ,request: {
                pageName: 'page' //页码的参数名称，默认：page
                ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
            }
            ,cellMinWidth:150
            ,response:{
                msgName: 'msg' //状态信息的字段名称，默认：msg
                ,countName: 'count' //数据总数的字段名称，默认：count
                ,dataName: 'object' //数据列表的字段名称，默认：data
            }
            ,page: true //开启分页
            ,cols: [[
                {field: 'uid', title: 'ID', width:'80', sort: true}
                ,{field: 'uname', title: '用户名' }
                ,{field: 'sex',title: '性别', sort: true }
                ,{field: 'address', title: '地址', sort: true}
                ,{field: 'phone', title: '手机号', sort: true}
                ,{field: 'wechat', title: '微信', sort: true}
                ,{field: 'tel' ,title: '座机', sort: true}
                ,{field: 'email', title: '邮箱', sort: true}
                ,{field: 'bak', title: '备注',sort: true }

            ]]
            ,done:function(res, curr, count){
                console.log(res)
            }
        });

    });
</script>
</html>
