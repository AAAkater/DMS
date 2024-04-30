package com.j2ee.util;

public class htmlText {

        //  返回页面Html携带的6位随机码
        public static String html(String code) {

            String html =
                    "这封邮件是由LYF文件管理系统发送的。<br/>"+
                    "你收到这封邮件的目的是进行新用户注册！<br/>"+
                    "验证码（不区分大小写哦）：<h3 style='color:red;'>" + code + "</h3><br/>";
            return html;
        }

}
