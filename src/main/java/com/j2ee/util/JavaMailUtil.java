package com.j2ee.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

    // 窟周繁議喨�篋傭楝�
    public static String emailAccount = "936560749@qq.com";
    // 窟周繁喨�簔楝襭�嗤議頁娩幡鷹��
    public static String emailPassword = "mgfactplgtbfbffe";
    // 窟周繁喨�箏�暦仇峽
    public static String emailSMTPHost = "smtp.qq.com";
    //  辺周繁喨��
    public static String receiveMailAccount = "";
    /**
     *  幹秀匯撃喨周(窟周繁、辺周繁、喨周坪否)
     * @param session
     * @param sendMail
     * @param receiveMail
     * @param html
     * @return
     * @throws MessagingException
     * @throws IOException
     * cc:貝僕
     * Bcc:畜僕
     * To:窟僕
     */
    public static  MimeMessage creatMimeMessage(Session session,String sendMail,String receiveMail,String html) throws MessagingException, IOException {
        // 1、幹秀匯撃喨周斤��
        MimeMessage message = new MimeMessage(session);
        // 2、From�嵯⊆�繁
        message.setFrom(new InternetAddress(sendMail, "ふゆからくるる", "UTF-8"));
        // 3、To:辺周繁�┸敏墺�紗謹倖辺周繁�些�僕賜宀畜僕��
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "仟喘薩", "UTF-8"));
        // 4、Subject:喨周麼籾
        message.setSubject("廣過刮屬鷹","UTF-8");
        // 5、Content:喨周屎猟�┸敏塋荒�Html炎禰��
        message.setContent(html,"text/html;charset=UTF-8");
        // 6、譜崔窟僕扮寂
        message.setSentDate(new Date());
        // 7、隠贋譜崔
        message.saveChanges();
        // 8、繍乎喨周隠贋壓云仇
        OutputStream out = new FileOutputStream("D://MyEmail" + UUID.randomUUID().toString() + ".eml");
        message.writeTo(out);
        out.flush();
        out.close();
        return message;
    }
}