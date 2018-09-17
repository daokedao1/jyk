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
  <link rel="stylesheet" href="../lib/adminlte/plugins/datatables/dataTables.bootstrap.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="box">
  <div class="box-header">
    <h3 class="box-title">用户管理</h3>
  </div>
  <!-- /.box-header -->
  <div class="box-body">
    <table id="example1" class="table table-bordered table-striped">
      <thead>
      <tr>
        <th>编号</th>
        <th>账户</th>
        <th>用户名</th>
        <th>性别</th>
        <th>联系方式</th>
        <th>邮箱</th>
        <th>微信</th>
        <th>部门</th>
        <th>角色</th>
      </tr>
      </thead>
      <tbody>

      <tr>
        <td></td>
        <td></td>
        <td>-</td>
        <td>-</td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <td>-</td>
        <td>-</td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <td>-</td>
        <td>-</td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <td>-</td>
        <td>-</td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <td>-</td>
        <td>-</td>
        <td></td>
      </tr>
      </tbody>

    </table>
  </div>
  <!-- /.box-body -->
</div>
</body>
<!-- jQuery 2.2.3 -->
<script src="../lib/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../lib/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="../lib/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../lib/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        $('#example1').DataTable({
            "paging": true,
            "lengthChange": true,
            "searching": true,
            "ordering": true,
            "aaSorting" : [[0, "asc"]], //默认的排序方式，第1列，升序排列
            "info": false,
            "autoWidth": false,
            "destroy":true,
            "processing":true,
            "scrollX": true,   //水平新增滚动轴
            "serverSide":true,    //true代表后台处理分页，false代表前台处理分页
            "aLengthMenu":[5,10,15,20],
            "PaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页
            //当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
            "deferRender": true,
//            "fnServerData":function (sSource111,aoData111, fnCallback111) {
//                console.log(sSource111);
//                console.log(aoData111);
//                console.log(fnCallback111);
//            },
            "ajax":{
                url:"../user/getAll",
                dataSrc:
                    function(data){
                        data.callbackCount=data.totleNum;
                        data.iTotalRecords = data.totleNum;
                        data.iTotalDisplayRecords =data.totleNum;
                        console.log(data);
                        //抛出异常
                        if(data.sqlException){
                            alert(data.sqlException);
                        }
                        //查询结束取消按钮不可用
                        $("#queryDataByParams").removeAttr("disabled");
                        return data.object;      //自定义数据源，默认为data
                    },
                type:"post"
            },
            columns:[
                { data: 'uid' },
                { data: 'uname'},
                { data: 'realname' },
                { data: 'sex' },
                { data: 'phone'},
                { data: 'email' },
                { data: 'wechat' },
                { data: 'dept' },
                { data: 'roleid' }

            ],
            //"bJQueryUI": true,/*是否开启主题*/
            "oLanguage": {    // 语言设置
                "sLengthMenu": "显示 _MENU_ 条",
                "sZeroRecords": "抱歉， 没有找到",
//                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
//                "sInfoEmpty": "没有数据",
//                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "sZeroRecords": "没有检索到数据",
                "sSearch": "检索:",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                },
                sProcessing:'<img src="../img/loading.gif"/>'
            }

        });
    });
</script>
</html>
