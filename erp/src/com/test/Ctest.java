//package com.test;
//
//import java.util.*;
//
//import javax.annotation.Resource;
//import javax.swing.*;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONWriter;
//import com.sun.corba.se.impl.oa.toa.TOA;
//import com.xoa.dao.users.UsersMapper;
//import com.xoa.model.email.EmailBoxModel;
//import com.xoa.model.email.Webmail;
//import com.xoa.model.flowplugin.FlowPluginModel;
//import com.xoa.model.unitmanagement.UnitManage;
//import com.xoa.model.users.Users;
//import com.xoa.model.workflow.FlowProcess;
//import com.xoa.model.workflow.FlowProcessList;
//import com.xoa.model.workflow.FlowRunPrcs;
//import com.xoa.model.workflow.FlowTypeModel;
//import com.xoa.service.flowplugin.FlowPluginService;
//import com.xoa.service.syspara.SysParaService;
//import com.xoa.service.unitmanagement.UnitManageService;
//import com.xoa.service.users.UsersService;
//import com.xoa.service.work.WorkFlowServiceContext;
//import com.xoa.service.work.WorkService;
//import com.xoa.service.workflow.flowtype.FlowProcessService;
//import com.xoa.service.workflow.flowtype.FlowRunPrcsService;
//import com.xoa.service.workflow.flowtype.FlowRunService;
//import com.xoa.service.workflow.flowtype.FlowTypeService;
//import com.xoa.service.workflow.flowtype.impl.FlowRunPrcsServiceImpl;
//import com.xoa.util.dataSource.ContextHolder;
//import org.apache.poi.util.SystemOutLogger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.quartz.SchedulerContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.ServletRequestUtils;
//
//import com.alibaba.fastjson.JSON;
//import com.xoa.model.email.EmailModel;
//import com.xoa.model.email.EmailBodyModel;
//import com.xoa.model.worldnews.News;
//import com.xoa.service.email.EmailService;
//import com.xoa.service.worldnews.NewService;
//import com.xoa.util.DateFormat;
//import com.xoa.util.ToJson;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:config/spring/springs-beans.xml"})
//public class Ctest {
//
//	@Resource
//	private EmailService emailService;
//	@Resource
//	private UsersMapper UsersMapper;
//
//	@Resource
//	WorkFlowServiceContext workFlowServiceContext;
//
//	@Resource
//	WorkService workService;
//
//	@Resource
//	FlowRunPrcsService flowRunPrcsService;
//
//	@Resource
//	SysParaService sysParaService;
//
//	@Test
//	public void test()throws  Exception{
//
//		Map<String, Object> maps = new HashMap<String, Object>();
//		maps.put("userId","admin");
//		ToJson<FlowRunPrcs> toJson = flowRunPrcsService.selectObject(maps,null,
//				null, true);
//		List list = toJson.getObj();
//		Map map = new HashMap();
//		map =(Map) list.get(0);
//		System.out.println(map.toString());
//
//
//        /**/
//		/*JSONArray json = new JSONArray();
//		String formdata="[{\"key\":\"DATA_47\",\"value\":\"\"},{\"value\":\"-1\"},{\"value\":\"\"},{\"value\":\"\"}]";
//		List<Map<String, Object>> l = json.parseObject(formdata, List.class);
//		for (int i=0;i<l.size();i++){
//			if (l.get(i).size()!=2){
//				l.remove(l.get(i));
//			}
//		}*/
//
//
//
//		/*Map<String, Object> map = new HashMap<String, Object>();
//		map.put("tableName", "flow_data_" + 163);
//		map.put("runId", "510");
//		Map<String, Object> maps = workFlowServiceContext.getWorkMapper().select(map);
//		maps.get("DATA_2");
//		System.out.println(maps.get("DATA_2").toString());*/
//
//	}
//
//
//
//
//
//
////    @Resource
////    private UsersService usersService;
////
////    @Resource
////	private UnitManageService unitManageService;
//
////	@Test
////	public void test() throws Exception {
////		FlowProcess flowProcess = new FlowProcess();
//////		==>  Preparing: insert into flow_process ( FLOW_ID , PRCS_ID , PRCS_NAME , SET_LEFT , SET_TOP ) values ( ?, ?, ?, ?, ? )
//////==> Parameters: 2(Integer), 9(Integer), node_1(String), 719(Integer), 280(Integer)
////		flowProcess.setFlowId(2);
////		flowProcess.setPrcsId(9);
////		flowProcess.setPrcsName("node_1");
////		flowProcess.setSetLeft(719);
////		flowProcess.setSetTop(280);
//////		flowProcess.setPluginSave("4546546");
////		flowProcess.setRemindFlag(0);
////		flowProcessService.insertSelective(flowProcess);
//////		System.out.println(JSON.toJSONStringWithDateFormat(toJson, "yyyy-MM-dd HH:mm:ss"));
//////		System.out.println(i);
////
////	}
//
////		Integer page = 1;
////		Integer pageSize = 5;
////		boolean useFlag = true;
////		Map<String, Object> maps = new HashMap<String, Object>();
////		maps.put("fromId","zhanglan");
////		maps.put("sendFlag","1");
////		maps.put("firstFlag","");
////		maps.put("secondFlag","");
//////			maps.put("startTime", DateFormat.getTime("2015-10-10 10:10:10"));
//////			maps.put("endTime", DateFormat.getTime("2017-04-12 10:10:10"));
//////		maps.put("readFlag","");
//////		maps.put("boxId", "");
//////		maps.put("sign","");
//////		maps.put("keyword","");
////		List<EmailBody> list = emailUtilService.selectEmail(maps, page, pageSize,
////				useFlag);
////		int listLength = list.size();
////		if (listLength > 0) {
////			ToJson<EmailBody> tojson = new ToJson<EmailBody>(0, "查询成功");
////			tojson.setObj(list);
////			System.out.println(JSON.toJSONStringWithDateFormat(tojson,
////					"yyyy-MM-dd HH:mm:ss"));
////		} else {
////			ToJson<EmailBody> tojson = new ToJson<EmailBody>(1, "查询失败");
////			System.out.println(JSON.toJSONStringWithDateFormat(tojson,
////					"yyyy-MM-dd HH:mm:ss"));
////		}
////
//	}
//
//
////	邮箱测试
////	@Test
////	public void test() throws Exception {
////
////		Map<String,Object> maps = new HashMap<String,Object>();
////		maps.put("fromId","admin");
////		maps.put("boxId",4);
////		ToJson<EmailBodyModel> toJson =
////		emailUtilService.deleteBoxEmail(maps,  1, 5, false);
////		System.out.println(toJson);
//
//
////		System.out.println(DateFormat.getStrTime(1494929129));
////		EmailBoxModel emailBoxModel = new EmailBoxModel();
////		emailBoxModel.setBoxName("测试盒子");
////		emailBoxModel.setUserId("admin");
////		emailBoxModel.setBoxNo(1);
////		Map<String,Object> maps = new HashMap<String,Object>();
////		maps.put("userId","admin");
////		EmailModel emailModel = new EmailModel();
////		emailModel.setBoxId(1);
////		emailModel.setEmailId(1110);
////		emailModel.setToId("admin");
////		Map<String,Object> maps = new HashMap<String,Object>();
////		maps.put("fromId","admin");
////		maps.put("boxId","1");
////		ToJson<EmailBodyModel> toJson =emailUtilService.selectBoxEmail( maps,1, 5, true,"xoa1001");
////		System.out.println(toJson.getMsg());
//
////		ContextHolder.setConsumerType("xoa1001");
////		UnitManage um = unitManageService.showUnitManage();
//////		loger.info("结果信息：" + JSON.toJSONStringWithDateFormat(um, "yyyy-MM-dd HH:mm:ss"));
////		Map<String, String> map = new HashMap<String, String>();
////		map.put("showNews", JSON.toJSONStringWithDateFormat(um, "yyyy-MM-dd HH:mm:ss"));
////		System.out.println(JSON.toJSONStringWithDateFormat(um, "yyyy-MM-dd HH:mm:ss"));
////		return ;
//
//
////		String names = "<p dir=\"ltr\"><br>\n&#27979;&#35797;<br></p>\n<p dir=\"ltr\">&#27979;&#35797;<br></p>\n" +
////				"<p dir=\"ltr\">&#27979;&#35797;<br>\n--&#21457;&#33258;&#25105;&#30340;&#24515;&#36890;&#36798;OA&#29256;--</p>\n";
////		System.out.println(JEditorPane.getKeymap(names));
////		Html.get
//
////		ResourceBundle rb =  ResourceBundle.getBundle("jdbc-sql");
////		String name = rb.getString("mysql.driverClassName");
////		System.out.println("jieguo:"+name);
//
////        ContextHolder.setConsumerType("xoa");
////        System.out.println("类型："+ContextHolder.getConsumerType());
////                Users user=usersService.findUserByName("zhangyong");
////        ToJson<Users> json=new ToJson<Users>(0, null);
////        try {
////            if (user.getBpNo()=="") {
////                System.out.println("登录失败");
////            }else {
////                System.out.println("登录成功");
////            }
////
////        } catch (Exception e) {
////            json.setMsg(e.getMessage());
////
////        }
//
//
//
//
//
////		Map<String,Object> maps = new HashMap<String, Object>();
////		maps.put("bodyId", Integer.valueOf("11"));
////		maps.put("fromId", "zhanglan");
////		EmailBodyModel emailBody = emailUtilService.queryById(maps, 1, 5, false);
////		EmailBodyModel emailbo = new EmailBodyModel();
////
////		System.out.println("emailBody:"+!StringUtils.isEmpty(emailBody));
////		System.out.println("\n emailbo:"+emailBody.getBodyId());
////
////		ToJson<EmailBodyModel> tojson = new ToJson<EmailBodyModel>(0, "查询成功");
////		tojson.setObject(emailBody);
////
////		System.out.println(JSON.toJSONStringWithDateFormat(tojson,
////					"yyyy-MM-dd HH:mm:ss"));
//
//
////
////		 Map<String,Object> maps = new HashMap<String, Object>();
////	     maps.put("typeId", null);
////	     maps.put("newsTime", null);
////	    List<News> page = newService.selectNews(maps, 1, 5, true);
////		System.out.println(JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss"));
//
//
////		EmailBodyModel emailBody = new EmailBodyModel();
////		emailBody.setFromId("admin");
////		emailBody.setToId2(
////				"liumingcai,liuyingwu,wangyun,changbai,chenchangliu,chenqiang,lichang,liuyongkang,liuna,wangde,litian,wuhai,lihai,zhangshan,");
////		emailBody.setCopyToId("wangde,zhaomin,");
////		emailBody.setSecretToId("liuna,");
////		emailBody.setSubject("植一株小草于心间");
////		emailBody.setContent("下班回家的时候，我无意间看到了墙头的草。我就惊叹于它的自强和坚韧。于草来说，这应该是最恶劣的环境了吧。砖头墙的中间，上不着天，下不着地，没有及时的雨水，更没有丰厚的土壤。唯一赖以生存的，就是砖头缝里的那一丁点泥土。而它，没有萎蔫，也没有抱怨，鲜嫩的叶子正昭示着它蓬勃的生命力。后来，观察那株小草成为我生活中必不可少的事儿。有一次，因为一个小小的差错，我被主管狠狠地批评了一顿。那天，心情极为黑暗，眼泪随时都有掉下来的冲动。下班回家的时候，我照例观察起了墙角的那棵小草。它一如既往地展示着动人的生命力，与平日并无两样。有那么一瞬间，我被小草感动了。这是一种多么坦然的心境啊，不以物喜，不以己悲，我们都没法做到，可是它做到了。（励志文章）毒辣的阳光、猛烈的暴雨、强劲的阵风……当它们次第在小草身上经过的时候，小草没有妥协，更没有自暴自弃，只是默默地绽放着自己的色彩。这么想着的时候，我的心里顿时舒坦了许多。相对于小草，我的处境显然优越了许多，我又有什么理由不比它更坚强呢？秋天的时候，小草终究枯萎了。而我，并没有悲伤，因为我已经把它植于我的心间。现在的社会，工作忙碌，生活节奏快，人际关系复杂。而我们，正需要这样一棵草，来提醒我们要自立，要坚强，要拼搏！");
////		emailBody.setSendTime(DateFormat.getTime(DateFormat.getStrDate(new Date())));
////		emailBody.setAttachmentId("");
////		emailBody.setAttachmentName("");
////		emailBody.setSendFlag("1");
////		emailBody.setSmsRemind("0");
////		// 重要程度(空-一般邮件,1-重要,2-非常重要)
////		emailBody.setImportant("");
////		emailBody.setSize((long) 0);
////		emailBody.setFromWebmailId(0);
////		emailBody.setFromWebmail("");
////		emailBody.setToWebmail("");
////		emailBody.setCompressContent(new byte[0]);
////		emailBody.setWebmailContent(new byte[0]);
////		emailBody.setWebmailFlag("0");
////		emailBody.setRecvFromName("");
////		emailBody.setRecvFrom("");
////		emailBody.setRecvToId(0);
////		emailBody.setRecvTo("");
////		emailBody.setIsWebmail("0");
////		emailBody.setIsWf("0");
////		emailBody.setKeyword("项目,项目,训练营,精英,魔鬼");
////		// 标识符 未定义
////		emailBody.setSecretLevel(0);
////		// 审核人 需要明确关系
////		emailBody.setAuditMan("");
////		// 审核不通过备注 需明确
////		emailBody.setAuditRemark("");
////		// 抄送外部邮箱串 需明确
////		emailBody.setCopyToWebmail("");
////		// 密送外部邮箱串 需明确
////		emailBody.setSecretToWebmail("");
////		// 点赞人 需明确
////		emailBody.setPraise("");
////		EmailModel email = new EmailModel();
////		email.setEmailId(241);
////		email.setReadFlag("");
////		// 邮件删除状态
////		email.setDeleteFlag("");
////		// 邮件箱分类ID 需明确
////		email.setBoxId(0);
////		// 邮件体ID 需明确
////		email.setBodyId(0);
////		email.setReceipt("");
////		// 需明确关系 显示不能为空
////		email.setIsF("");
////		// 需明确关系 显示不能为空
////		email.setIsR("");
////		// 显示不能为空
////		email.setSign("");
////		emailUtilService.sendEmail(emailBody, email);
////}
