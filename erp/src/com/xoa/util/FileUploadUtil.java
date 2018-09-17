//package com.xoa.util;
//import com.xoa.model.enclosure.Attachment;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
// /**
// * 创建作者:   张龙飞
// * 创建日期:   2017年5月22日 上午11:08:15
// * 类介绍  :   	 文件上传工具类
// * 构造参数:
// *
// */
//public class FileUploadUtil {
//     public static final List<String> ALLOW_TYPES = Arrays.asList(
//                "image/jpg","image/jpeg","image/png","image/gif"
//        );
//    //文件重命名
//        public static String rename(String fileName){
//            int i = fileName.lastIndexOf(".");
//            String str = fileName.substring(i);
//            //return new DateTest().getTime()+""+ new Random().nextInt(99999999) +str;
//            return Math.abs((int) System.currentTimeMillis()) +str;
//        }
//    //校验文件类型是否是被允许的
//        public static boolean allowUpload(String postfix){
//            return ALLOW_TYPES.contains(postfix);
//        }
//        //返回附件信息
//        public static Object[] reAttachment(List<Attachment> list){
//            StringBuffer id = new StringBuffer();
//            StringBuffer name = new StringBuffer();
//            for (Attachment attachment : list) {
//                int aid = attachment.getAid();
//                int attachId = attachment.getAttachId();
//                String ym = attachment.getYm();
//                String attachName = attachment.getAttachName();
//                String all = aid + "@" + ym + "_" + attachId;
//                id.append(all).append(",");
//                name.append(attachName).append("*");
//            }
//            Object[] o =new Object[2];
//            o[0] = id.toString();
//            o[1] = name.toString();
//            return o;
//        }
//}