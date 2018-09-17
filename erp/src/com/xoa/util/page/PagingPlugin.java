package com.xoa.util.page;

import java.beans.PropertyDescriptor;  
import java.lang.reflect.Field;  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.*;

import javax.transaction.NotSupportedException;

import com.xoa.util.common.session.SessionUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;  
import org.apache.ibatis.mapping.BoundSql;  
import org.apache.ibatis.mapping.MappedStatement;  
import org.apache.ibatis.plugin.Interceptor;  
import org.apache.ibatis.plugin.Intercepts;  
import org.apache.ibatis.plugin.Invocation;  
import org.apache.ibatis.plugin.Plugin;  
import org.apache.ibatis.plugin.Signature;  
import org.apache.ibatis.reflection.MetaObject;  
import org.apache.ibatis.reflection.SystemMetaObject;  
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;  
import org.apache.ibatis.session.Configuration;  
import com.alibaba.fastjson.JSONArray;
import com.xoa.util.common.L;


  
/**
 * 
 * 创建作者:   张勇
 * 创建日期:   2017-4-20 上午11:08:23
 * 类介绍  :   分页插件拦截器
 * 构造参数:   
 *
 */

public class PagingPlugin implements Interceptor {

    public static final String[] TABLE_NAME_STR = {"ADDRESS",
            "ADDRESS_GROUP",
            "AFFAIR",
            "ATTACHMENT",
            "ATTACHMENT_EDIT",
            "ATTACHMENT_MODULE",
            "ATTACHMENT_POSITION",
            "ATTEND_ASK_DUTY",
            "ATTEND_CONFIG",
            "ATTEND_DUTY",
            "ATTEND_DUTY_SHIFT",
            "ATTEND_EVECTION",
            "ATTEND_HOLIDAY",
            "ATTEND_LEAVE",
            "ATTEND_LEAVE_MANAGER",
            "ATTEND_MACHINE",
            "ATTEND_MANAGER",
            "ATTEND_MOBILE",
            "ATTEND_OUT",
            "ATTENDANCE_OVERTIME",
            "CALENDAR",
            "DATA_SRC",
            "DEPARTMENT",
            "DEPT_MAP",
            "DIARY",
            "DIARY_COMMENT",
            "DIARY_COMMENT_REPLY",
            "DIARY_SHARE",
            "DIARY_TOP",
            "EMAIL",
            "EMAIL_BODY",
            "EMAIL_BOX",
            "EMAIL_BOXGROUP",
            "EMAIL_KEYWORD",
            "EMAIL_NAME",
            "EMAIL_TAG",
            "EXT_DEPT",
            "EXT_USER",
            "FIELD_DATE",
            "FIELDSETTING",
            "FILE_COMMENT",
            "FILE_CONTENT",
            "FILE_SCORE",
            "FILE_SORT",
            "FLOW_CONTROLS",
            "FLOW_DATA_1",
            "FLOW_DATA_27",
            "FLOW_FEEDBACK_COMMON",
            "FLOW_FORM_TYPE",
            "FLOW_FORM_VERSION",
            "FLOW_HOOK",
            "FLOW_MANAGE_LOG",
            "FLOW_PRINT_TPL",
            "FLOW_PRIV",
            "FLOW_PROCESS",
            "FLOW_QUERY_TPL",
            "FLOW_REPORT",
            "FLOW_REPORT_PRIV",
            "FLOW_RULE",
            "FLOW_RUN",
            "FLOW_RUN_ATTACH",
            "FLOW_RUN_DATA",
            "FLOW_RUN_FEEDBACK",
            "FLOW_RUN_HOOK",
            "FLOW_RUN_LOG",
            "FLOW_RUN_PRCS",
            "FLOW_SORT",
            "FLOW_TIMER",
            "FLOW_TRIGGER",
            "FLOW_TYPE",
            "FLOW_VERSION",
            "FORM_SORT",
            "GBT_CONF",
            "HELP_QUESTION",
            "HELP_SORT",
            "IM_CHAT_LIST",
            "IM_MESSAGE",
            "IM_ROOM",
            "INTERFACE",
            "IP_RULE",
            "LOGIN_APP",
            "MEETING",
            "MEETING_COMMENT",
            "MEETING_EQUIPMENT",
            "MEETING_ROOM",
            "MEETING_RULE",
            "MOBILE_APP",
            "MODULE_MANAGE",
            "MODULE_PRIV",
            "MYTABLE",
            "NETCHAT",
            "NETDISK",
            "NEWS",
            "NEWS_COMMENT",
            "NOTES",
            "NOTIFY",
            "OA_CYCLESOURCE_USED",
            "OA_SOURCE",
            "OA_SOURCE_USED",
            "OFFICE_DEPOSITORY",
            "OFFICE_LOG",
            "OFFICE_PRODUCTS",
            "OFFICE_TASK",
            "OFFICE_TRANSHISTORY",
            "OFFICE_TYPE",
            "ORG_MANAGE",
            "PICTURE",
            "PLAN_TYPE",
            "PORTAL_TEMPLATES",
            "PORTALS",
            "REMINDERS",
            "SAL_DATA",
            "SAL_FLOW",
            "SAL_ITEM",
            "SD_FILE",
            "SD_SORT",
            "SEAL",
            "SEAL_LOG",
            "SESSION",
            "SMS",
            "SMS2",
            "SMS2_PRIV",
            "SMS_BODY",
            "SYS_CODE",
            "SYS_FUNCTION",
            "SYS_LOG",
            "SYS_MENU",
            "SYS_PARA",
            "TASK",
            "TASKCENTER",
            "TERP_SERVER",
            "TRAFFIC_RESTRICTION",
            "UNIT",
            "URL",
            "USER_",
            "USER_EXT",
            "USER_FUNCTION",
            "USER_GROUP",
            "USER_JPUSH",
            "USER_MAP",
            "USER_MOBILE_DEVICES",
            "USER_ONLINE",
            "USER_PRIV",
            "USER_WEIXINQY",
            "VEHICLE",
            "VEHICLE_MAINTENANCE",
            "VEHICLE_OIL_CARD",
            "VEHICLE_OIL_USE",
            "VEHICLE_OPERATOR",
            "VEHICLE_USAGE",
            "VERSION",
            "VI_FLOW_RUN",
            "VOTE_DATA",
            "VOTE_ITEM",
            "VOTE_TITLE",
            "WEBMAIL",
            "WEBMAIL_BODY",
            "WEIXUN_SHARE",
            "WEIXUN_SHARE_FOLLOW",
            "WEIXUN_SHARE_TOPIC",
            "WINEXE",
            "WORK_DETAIL",
            "WORK_PERSON",
            "WORK_PLAN",
            "document"}; //全部字段

	/**
	 * 默认页码  
	 */
    private Integer defaultPage; 
    /**
     * 默认每页条数 
     */
    private Integer defaultPageSize; 
    /**
     * 默认是否启用插件  
     */
    private Boolean defaultUseFlag; 
    /**
     * 默认是否检测页码参数  
     */
    private Boolean defaultCheckFlag; 
    /**
     * 默认是否清除最后一个order by 后的语句 
     */
    private Boolean defaultCleanOrderBy;  
    
//    private DynDatasources dyTypeDate = new DynDatasources();
//    private static final String DB_TYPE_MYSQL = "mysqlDataSources";
//    private static final String DB_TYPE_ORACLE = "oracleDataSources";
    private static final String DB_TYPE_MYSQL = "mysql";
    private static final String DB_TYPE_ORACLE = "oralce";
    private static final String DB_TYPE_SQLSERVER = "sqlserver";
    private static final String DB_TYPE_DMSQL="dmsql";

    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:08:23
     * 类介绍  :   插件实现方法
     * 构造参数:   
     *
     */
    @Override  
    public Object intercept(Invocation invocation) throws Throwable {  
        StatementHandler stmtHandler = (StatementHandler) getUnProxyObject(invocation.getTarget());  
  
        MetaObject metaStatementHandler = SystemMetaObject.forObject(stmtHandler);  
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");  
         
        MappedStatement mappedStatement =  (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        //获取数据源连接类型

//        String dbType = dyTypeDate.determineCurrentLookupKey().toString();  
        String dbType = this.returnSqlType();
        System.out.println("dbType:"+dbType);

//        String dbType = dyTypeDate.determineCurrentLookupKey().toString();  
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");  
        L.w("sqlcheck you sql is:",sql,"\r\n Params is",JSONArray.toJSONString(boundSql));




        //不是select语句.
        if (!this.checkSelect(sql)) {
            L.w("you sql is not select ,pleasecheck");
            if(DB_TYPE_DMSQL.equals(dbType)){
                return   this.preDmSQL(invocation, metaStatementHandler, boundSql);
            }
            return invocation.proceed();
        }
        
        Object parameterObject = boundSql.getParameterObject();  
        PageParams pageParams = getPageParamsForParamObj(parameterObject);  
        if (pageParams == null) { //无法获取分页参数，不进行分页。  
        	L.w("pageParams is null");
            if(DB_TYPE_DMSQL.equals(dbType)){
                return   this.preDmSQL(invocation, metaStatementHandler, boundSql);
            }
        	return invocation.proceed();  
        }  
  
        //获取配置中是否启用分页功能.  
        Boolean useFlag = pageParams.getUseFlag() == null? this.defaultUseFlag : pageParams.getUseFlag();  
        if (!useFlag) {  //不使用分页插件. 
        	L.w("useFlag"+useFlag);
            if(DB_TYPE_DMSQL.equals(dbType)){
                return   this.preDmSQL(invocation, metaStatementHandler, boundSql);
            }
            return invocation.proceed();  
        }  
        //获取相关配置的参数.  
        Integer pageNum = pageParams.getPage() == null? defaultPage : pageParams.getPage();  
        Integer pageSize = pageParams.getPageSize() == null? defaultPageSize : pageParams.getPageSize();  
        Boolean checkFlag = pageParams.getCheckFlag() == null? defaultCheckFlag : pageParams.getCheckFlag();  
        Boolean cleanOrderBy = pageParams.getCleanOrderBy() == null? defaultCleanOrderBy : pageParams.getCleanOrderBy();  
        //计算总条数  
        int total = this.getTotal(invocation, metaStatementHandler, boundSql, cleanOrderBy, dbType);  
        //回填总条数到分页参数  
        pageParams.setTotal(total);  
        //计算总页数.  
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;  
        //回填总页数到分页参数.  
        pageParams.setTotalPage(totalPage);  
        //检查当前页码的有效性.  
        this.checkPage(checkFlag, pageNum, totalPage);


        //修改sql
        return this.preparedSQL(invocation, metaStatementHandler, boundSql, pageNum, pageSize, dbType);  
    }  
  
  
    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:09:54
     * 方法介绍:   分离出分页参数
     * 参数说明:   @param parameterObject
     * 参数说明:   @return
     * 参数说明:   @throws Exception
     * @return     PageParams
     */
    public PageParams getPageParamsForParamObj(Object parameterObject) throws Exception {  
        PageParams pageParams = null;  
        if (parameterObject == null) {  
            return null;  
        }  
        //处理map参数和@Param注解参数，都是MAP  
        if (parameterObject instanceof Map) {  
            @SuppressWarnings("unchecked")  
            Map<String, Object> paramMap = (Map<String, Object>) parameterObject;  
            Set<String> keySet = paramMap.keySet();  
            Iterator<String> iterator = keySet.iterator();  
            while(iterator.hasNext()) {  
                String key = iterator.next();
                Object value = paramMap.get(key);
                if (value instanceof PageParams) {  
                    return (PageParams)value;  
                }  
            }  
        } else if (parameterObject instanceof PageParams) { //参数POJO继承了PageParams  
            return (PageParams) parameterObject;  
        } else { //从POJO尝试读取分页参数.  
            Field[] fields = parameterObject.getClass().getDeclaredFields();  
            //尝试从POJO中获得类型为PageParams的属性  
            for (Field field : fields) {  
                if (field.getType() == PageParams.class) {  
                    PropertyDescriptor pd = new PropertyDescriptor (field.getName(), parameterObject.getClass());  
                    Method method = pd.getReadMethod();  
                    return (PageParams) method.invoke(parameterObject);  
                }  
            }  
        }  
        return pageParams;  
    }  
  
  
    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:10:05
     * 方法介绍:   判断是否select语句.
     * 参数说明:   @param sql
     * 参数说明:   @return
     * @return     boolean
     */
    private boolean checkSelect(String sql) {  
        String trimSql = sql.trim();  
        int idx = trimSql.toLowerCase().indexOf("select");  
        return idx == 0;  
    }  
  
    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:10:27
     * 方法介绍:    检查当前页码的有效性.
     * 参数说明:   @param checkFlag
     * 参数说明:   @param pageNum
     * 参数说明:   @param pageTotal
     * 参数说明:   @throws Throwable
     * @return     void
     */
    private void checkPage(Boolean checkFlag, Integer pageNum, Integer pageTotal) throws Throwable  {  
        if (checkFlag) {  
            //检查页码page是否合法.  
            if (pageNum > pageTotal) {  
                throw new Exception("查询失败，查询页码【" + pageNum + "】大于总页数【" + pageTotal + "】！！");  
            }  
        }  
    }  
  
  
    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:10:37
     * 方法介绍:   预编译改写后的SQL，并设置分页参数 
     * 参数说明:   @param invocation
     * 参数说明:   @param metaStatementHandler
     * 参数说明:   @param boundSql
     * 参数说明:   @param pageNum
     * 参数说明:   @param pageSize
     * 参数说明:   @param dbType
     * 参数说明:   @return
     * 参数说明:   @throws Exception
     * @return     Object
     */
    private Object preparedSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, int pageNum, int pageSize, String dbType) throws Exception {  
        //获取当前需要执行的SQL  
       System.out.println("preparedSQL:"+dbType);
    	String sql = boundSql.getSql();  
        String newSql = this.getPageDataSQL(sql, dbType);  
        //修改当前需要执行的SQL  
        metaStatementHandler.setValue("delegate.boundSql.sql", newSql);  
        //执行编译，这里相当于StatementHandler执行了prepared()方法，这个时候，就剩下2个分页参数没有设置。  
        Object statementObj = invocation.proceed();  
        //设置两个分页参数。  
        this.preparePageDataParams((PreparedStatement)statementObj, pageNum, pageSize, dbType);  
        return statementObj;  
    }  

    private String preDmStrSql(String sql){
        String currSql = sql;
        currSql  =  currSql.toUpperCase();
        currSql =currSql.replaceAll("`","");
        currSql =currSql.replaceAll("USER ","USER_ ");
        for(String a:TABLE_NAME_STR){
            currSql=currSql.replaceAll(" "+a+" " ," XOA1004."+a+" ");
            currSql=currSql.replaceAll(","+a+" " ,",XOA1004."+a+" ");
            currSql=currSql.replaceAll(" "+a+"." ," XOA1004."+a+".");
        }
        L.s("renderSql:",currSql);
        return currSql;
    }
    private Object preDmSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql)throws Exception {
        String sql = boundSql.getSql();
//        String currSql = sql;
//        currSql  =  currSql.toUpperCase();
//        currSql =currSql.replaceAll("`","");
//        currSql =currSql.replaceAll("USER ","USER_ ");
//        for(String a:TABLE_NAME_STR){
//            currSql=currSql.replaceAll(" "+a ," XOA1004."+a);
//        }
//        for(String a:TABLE_NAME_STR){
//
//            currSql=currSql.replaceAll(","+a+" " ,",XOA1004."+a+" ");
//        }
        //修改当前需要执行的SQL
        metaStatementHandler.setValue("delegate.boundSql.sql", preDmStrSql(sql));

        Object statementObj = invocation.proceed();
        return statementObj;
    }


    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:10:50
     * 方法介绍:   获取总条数. 
     * 参数说明:   @param ivt
     * 参数说明:   @param metaStatementHandler
     * 参数说明:   @param boundSql
     * 参数说明:   @param cleanOrderBy
     * 参数说明:   @param dbType
     * 参数说明:   @return
     * 参数说明:   @throws Throwable
     * @return     int
     */
    private int getTotal(Invocation ivt, MetaObject metaStatementHandler, BoundSql boundSql, Boolean cleanOrderBy, String dbType) throws Throwable {



        //获取当前的mappedStatement  
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");  
        //配置对象  
        Configuration cfg = mappedStatement.getConfiguration();  
        //当前需要执行的SQL  
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");  
        //去掉最后的order by语句  
        if (cleanOrderBy) {  
            sql = this.cleanOrderByForSql(sql);  
        }  
        String countSql = this.getTotalSQL(sql, dbType);  
        //获取拦截方法参数，根据插件签名，知道是Connection对象.  
        Connection connection = (Connection) ivt.getArgs()[0];  
        PreparedStatement ps = null;  
        int total = 0;  
        try {  
            //预编译统计总数SQL  
            ps = connection.prepareStatement(countSql);  
            //构建统计总数SQL  
            BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());  
            //构建MyBatis的ParameterHandler用来设置总数Sql的参数。  
            ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);  
            //设置总数SQL参数  
            handler.setParameters(ps);  
            //执行查询.  
            ResultSet rs = ps.executeQuery();  
            while (rs.next()) {  
                total = rs.getInt("total");  
            }  
        } finally {  
            //这里不能关闭Connection否则后续的SQL就没法继续了。  
            if (ps != null) {  
                ps.close();  
            }  
        }  
        return total;  
    }  
  
    private String cleanOrderByForSql(String sql) {  
        StringBuilder sb = new StringBuilder(sql);  
        String newSql = sql.toLowerCase();  
        //如果没有order语句,直接返回  
        if (newSql.indexOf("order") == -1) {  
            return sql;  
        }  
        int idx = newSql.lastIndexOf("order");  
        return sb.substring(0, idx).toString();  
    }  
  
    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:11:01
     * 方法介绍:   从代理对象中分离出真实对象.
     * 参数说明:   @param target
     * 参数说明:   @return
     * @return     Object
     */
    private Object getUnProxyObject(Object target) {  
        MetaObject metaStatementHandler = SystemMetaObject.forObject(target);  
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过循环可以分离出最原始的的目标类)  
        Object object = null;  
        while (metaStatementHandler.hasGetter("h")) {  
            object = metaStatementHandler.getValue("h");  
        }  
        if (object == null) {  
            return target;  
        }  
        return object;  
    }  
  
    /** 
     * 生成代理对象 
     * 参数说明 :  @param statementHandler 原始对象 
     * @return 代理对象 
     */  
    @Override  
    public Object plugin(Object statementHandler) {  
        return Plugin.wrap(statementHandler, this);  
    }  
  
    /** 
     * 设置插件配置参数。 
     */  
    @Override  
    public void setProperties(Properties props) {  
    	
        String strDefaultPage = props.getProperty("default.page", "1");  
        String strDefaultPageSize = props.getProperty("default.pageSize", "10");  
        String strDefaultUseFlag = props.getProperty("default.useFlag", "false");  
        String strDefaultCheckFlag = props.getProperty("default.checkFlag", "false");  
        String StringDefaultCleanOrderBy = props.getProperty("default.cleanOrderBy", "false");  
  
        this.defaultPage = Integer.parseInt(strDefaultPage);  
        this.defaultPageSize = Integer.parseInt(strDefaultPageSize);  
        this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);  
        this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);  
        this.defaultCleanOrderBy = Boolean.parseBoolean(StringDefaultCleanOrderBy);  
    }  
  
    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:11:54
     * 方法介绍:   计算总数的SQL,这里需要根据数据库的类型改写SQL，目前支持MySQL和Oracle 
     * 参数说明:   @param currSql 当前执行的SQL 
     * 参数说明:   @param dbType
     * 参数说明:   @return 改写后的SQL 
     * 参数说明:   @throws NotSupportedException
     * @return     String
     */
    private String getTotalSQL(String currSql, String dbType) throws NotSupportedException {

    	System.out.println("getTotalSQL:"+dbType);
        if (DB_TYPE_MYSQL.equals(dbType)) {  
            return  "select count(*) as total from (" + currSql + ") $_paging";  
        } else if (DB_TYPE_ORACLE.equals(dbType)) {  
            return "select count(*) as total from (" + currSql +")";  
        } else if(DB_TYPE_DMSQL.equals(dbType)){
            currSql= this.preDmStrSql(currSql);
            return ("select count(*) as total from (" + currSql +")").toUpperCase();
        } else {
            throw new NotSupportedException("当前插件未支持此类型数据库");  
        }  
    }  
  
    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:12:19
     * 方法介绍:    需要使用其他数据库需要改写 ,分页获取参数的SQL,这里需要根据数据库的类型改写SQL，目前支持MySQL和Oracle 
     * 参数说明:   @param currSql 当前执行的SQL
     * 参数说明:   @param dbType
     * 参数说明:   @return 改写后的SQL
     * 参数说明:   @throws NotSupportedException
     * @return     String
     */
    private String getPageDataSQL(String currSql, String dbType) throws NotSupportedException {
    	System.out.println("getPageDataSQL:"+dbType);
        if (DB_TYPE_MYSQL.equals(dbType)) {
          /*  currSql = currSql.toUpperCase();
            if(currSql.indexOf("ORDER")!=-1){
                String order = currSql.substring(currSql.indexOf("ORDER"),currSql.length());
                currSql = currSql.substring(0,currSql.indexOf("ORDER"));
                return "select * from  ("+currSql+") $_paging_table "+ order +" limit ?, ? ";
            }*/
            return "select * from (" + currSql + ") $_paging_table limit ?, ?";  
        } else if (DB_TYPE_ORACLE.equals(dbType)) {  
            return " select * from (select cur_sql_result.*, rownum rn from (" + currSql + ") cur_sql_result  where rownum <= ?) where rn > ?";  
        }else if(DB_TYPE_DMSQL.equals(dbType)) {
            currSql=    preDmStrSql(currSql);
            return (" select * from (select cur_sql_result.*, rownum rn from (" + currSql + ") cur_sql_result  where rownum <= ?) where rn > ?").toUpperCase();
        }else {
            throw new NotSupportedException("当前插件未支持此类型数据库");  
        }  
    }  
  
    /**
     * 
     * 创建作者:   张勇
     * 创建日期:   2017-4-20 上午11:12:55
     * 方法介绍:   需要使用其他数据库需要改写 ,使用PreparedStatement预编译两个分页参数，如果数据库的规则不一样，需要改写设置的参数规则。目前支持MySQL和Oracle 
     * 参数说明:   @param ps
     * 参数说明:   @param pageNum
     * 参数说明:   @param pageSize
     * 参数说明:   @param dbType
     * 参数说明:   @throws Exception
     * @return     void
     */
    private void preparePageDataParams(PreparedStatement ps, int pageNum, int pageSize, String dbType) throws Exception {  
        //prepared()方法编译SQL，由于MyBatis上下文没有我们分页参数的信息，所以这里需要设置这两个参数.  
            //获取需要设置的参数个数，由于我们的参数是最后的两个，所以很容易得到其位置  
    	System.out.println("preparePageDataParams:"+dbType);
            int idx = ps.getParameterMetaData().getParameterCount();  
            if (DB_TYPE_MYSQL.equals(dbType)) {  
                //最后两个是我们的分页参数.  
                ps.setInt(idx -1, (pageNum - 1) * pageSize);//开始行  
                ps.setInt(idx, pageSize); //限制条数  
            } else if (DB_TYPE_ORACLE.equals(dbType)) {  
                ps.setInt(idx -1, pageNum * pageSize);//结束行  
                ps.setInt(idx, (pageNum - 1) * pageSize); //开始行  
            } else if(DB_TYPE_DMSQL.equals(dbType)){
                ps.setInt(idx -1, pageNum * pageSize);//结束行
                ps.setInt(idx, (pageNum - 1) * pageSize); //开始行
            }  else{
                throw new NotSupportedException("当前插件未支持此类型数据库");
            }
  
    }
  
//    /** 
//     * 
//     * TODO 需要使用其他数据库需要改写 
//     * 目前支持MySQL和Oracle 
//     * @param mappedStatement 
//     * @return 
//     * @throws Exception 
//     */  
//    private String getDataSourceType(MappedStatement mappedStatement) throws Exception {  
//        Connection conn = null;  
//        String dbConnectionStr = null;  
//        try {  
//            conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();  
//            dbConnectionStr  = conn.toString();  
//        	System.out.println("getDataSourceType:"+dbConnectionStr);
//        } finally {  
//            if (conn != null) {  
//                conn.close();  
//            }  
//        }  
//        if (null == dbConnectionStr || dbConnectionStr.trim().equals(""))  {  
//            throw new NotSupportedException("当前插件未能获得数据库连接信息。");  
//        }  
//        dbConnectionStr = dbConnectionStr.toLowerCase();  
//        if (dbConnectionStr.contains(DB_TYPE_MYSQL)) {  
//            return DB_TYPE_MYSQL;  
//        } else if (dbConnectionStr.contains(DB_TYPE_ORACLE)) {  
//            return DB_TYPE_ORACLE;  
//        } else {  
//            throw new NotSupportedException("当前插件未支持此类型数据库");  
//        }  
//    }  

    /**
     *
     * 创建作者:   张勇
     * 创建日期:   2017-5-2 下午14:10:10
     * 方法介绍:   数据库连接类型
     * @return     String 数据库类型
     */
    public String returnSqlType(){
        String retrunSql = "";
//	   com.microsoft.sqlserver.jdbc.SQLServerDriver
        String name=  ResourceBundle.getBundle("jdbc-sql").getString("driverClassName");
        if("com.mysql.jdbc.Driver".equals(name.trim())){
            retrunSql = "mysql";
        }else if("oracle.jdbc.driver.OracleDriver".equals(name.trim())){
            retrunSql = "oralce";
        }else if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(name.trim())){
            retrunSql = "sqlserver";
        }else if("dm.jdbc.driver.DmDriver".equals(name.trim())){
            retrunSql ="dmsql";
        }
        return retrunSql;
    }

    
}
