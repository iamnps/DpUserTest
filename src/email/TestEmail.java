package email;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class TestEmail {
	static int stmpServerSSLPort = 465;
    static int stmpServerPort = 25;
    static String stmpServerDomain = "smtpdm.aliyun.com";
    static String emailName = "xxxx";
    static String password = "xxx";
    static Properties properties = null;

    static String nickname = null;
    static String toEmail = "niepeishen@sina.com";

    public static void main(String[] args) throws AddressException, MessagingException {
    	try {
            //昵称
//            encodeText有三个参数，第一个是字符串，这个字符串的格式，需要和第二个utf-8想匹配，否者不能正确解析。B我觉得是大小字节的意思
//            而且当前文件必须是UTF-8格式
//            nickname = MimeUtility.encodeText("丰唐物联","UTF-8","");
            nickname = MimeUtility.encodeText("FANTEM", "UTF-8", "B");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtps");
    	Session session = Session.getInstance(properties);
        session.setDebug(true);

        Message EmailMsg = new MimeMessage(session);
        EmailMsg.setRecipient(Message.RecipientType.TO,
                new InternetAddress(toEmail));
        EmailMsg.setSubject("This is the Subject Line!");
        
        StringBuffer str = new StringBuffer();
        String center = "center ";
        String tableStyle = "width:650px";
        str.append("<table align=" + center + "style=" + tableStyle + ">");
        String trStyle = "height:100px;";
        str.append("<tr style=" + trStyle + ">");
        String thStyle = "background:#E8EAEA;text-align:left";
        str.append("<th style=" + thStyle + ">");
        str.append("<div style=");
        str.append("background-image:url(http://testadmin.fantem-gateway.com/images/oomilogo.png);height:32px;width:93px;margin-left:30px;background-repeat:no-repeat");
        str.append("></div>");
        str.append("</th>");
        str.append("</tr>");
        String tr1Style = "height:80px;";
        str.append("<tr style=" + tr1Style + ">");
        String th1Style = "text-align:center;background:#ffffff;color:#000000;font-size:x-large";
        str.append("<th style="+th1Style+">Oops! This activation link has expired. Please use your Oomi Touch to register again and confirm your account within 24 hours.</th>");
        str.append("</tr>");
        String tr2Style = "height:25px;";
        str.append("<tr style="+tr2Style+">");
        String th2Style= "text-align:center;background:#ffffff;color:#C9C9CA";
        str.append("<th style="+th2Style+">Click the link below to activate your account</th>");
        str.append("</tr>");
        String tr3Style = "height:40px;";
        str.append("<tr style="+tr3Style+">");
        String http = "https://www.baidu.com";
        str.append("<th style="+th2Style+"><a href="+http+">Please click this link.</a></th>");
        str.append("</tr>");
        str.append("<tr style="+tr1Style+">");
        str.append("<th style="+th2Style+">For security reasons, this link will only be active for 24 hours.</th>");
        str.append("</tr>");
        String tr4Style = "height:50px;";
        str.append("<tr style="+tr4Style+">");
        String th3Style = "text-align:center;background:#E8EAEA;";
        str.append("<th style="+th3Style+"></th>");
        str.append("</tr>");
        String tr5Style = "height:80px;";
        str.append("<tr style="+tr5Style+">");
        String th4Style = "text-align:center;background:#ffffff;font-size:x-large";
        str.append("<th style="+th4Style+">Have Questions? </th>");
        str.append("</tr>");
        String tr8Style = "height:60px;";
        str.append("<tr style="+tr8Style+">");
        String th6Style = "text-align:center;background:#ffffff;";
        str.append("<th style="+th6Style+">Please contact our customer support at support@oomi.com</th>");
        str.append("</tr>");
        String tr6Style = "height:40px;";
        str.append("<tr style="+tr6Style+">");
        String th7Style = "text-align:center;background:#E8EAEA;font-size:xx-small";
        str.append("<th style="+th7Style+">Copyright © 2016 Fantem All Rights Reserved.</th>");
        str.append("</tr>");
        str.append("</table>");
        
        
        EmailMsg.setContent(str.toString(), "text/html;charset=utf-8");
        
        EmailMsg.setFrom(new InternetAddress(nickname + "<" + emailName + ">"));
        Transport transport = session.getTransport();
//		如果是163邮箱，可以只要@前面，因为163的域名和邮箱后缀是一致的，javaMail会自动补全，但公司的那个不同，所以需要区分。
//		transport.connect(stmpServerDomain, stmpServerSSLPort,emailName.split("@")[0], password);
        transport.connect(stmpServerDomain, stmpServerSSLPort, emailName, password);
        transport.sendMessage(EmailMsg,
                new Address[]{new InternetAddress(toEmail)});
        transport.close();
	}
    
    
//    MimeMultipart msgMultipart = new MimeMultipart("mixed");
//    EmailMsg.setContent(msgMultipart);
//    
//    MimeBodyPart content = new MimeBodyPart();
//    msgMultipart.addBodyPart(content);
//    
//    MimeMultipart bodymp = new MimeMultipart("related");
//    content.setContent(bodymp);
//    
//    MimeBodyPart bp = new MimeBodyPart();
//    MimeBodyPart bodypart1 = new MimeBodyPart();
//    FileDataSource fileds = new FileDataSource(new File("F:/email.jpg"));
//    bp.setDataHandler(new DataHandler(fileds));
//    bp.setFileName("email.jpg");
//    bp.setContentID("mei");
//    
//    bodymp.addBodyPart(bp);
//    
//    StringBuffer str = new StringBuffer();
//    str.append("<body background=\"cid:mei\" style=" + "background-repeat:no-repeat; text-align: center" + ">");
//    str.append("<H1>Hello</H1>");
//    str.append("</body>");
//    
//    bodypart1.setContent(str.toString(),"text/html;charset=utf-8");
//    
//    EmailMsg.saveChanges();
      
    
    
//  String svgVersion = "1.1 ";
//  String svgStyle = "height:100px;width:100px;padding-left:15px;";
//  String logoSvg = "logoSVG";
//  String xmlns = "http://www.w3.org/2000/svg";
//  String xlink = "http://www.w3.org/1999/xlink";
//  str.append("<svg version=" + svgVersion + " style=" + svgStyle + " id=" + logoSvg + " xmlns=" + xmlns + " xmlns:xlink=" + xlink + " x=" + "0px " + " y="+"0px"+" viewBox="+"0 0 93.4 31.9"+" enable-background="+"new 0 0 93.4 31.9"+" xml:space="+"preserve"+">");
//  str.append("<g>");
//  str.append("<path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M90.2,0.1h3.1v31.7h-3.1V0.1z");
//  str.append("></path><path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M58.9,0.1H62l-4.4,31.7h-3.1L58.9,0.1z");
//  str.append("></path><path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M58.9,0.1H62l10.4,31.7h-3.1L58.9,0.1z");
//  str.append("></path><path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M87.2,31.8h-3.1L79.8,0.1h3.1L87.2,31.8z");
//  str.append("></path><path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M72.4,31.8h-3.1L79.7,0.1h3.1L72.4,31.8z");
//  str.append("></path><path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M16.9,28.6c-6.9,0.8-13-4.3-13.8-11.4c-0.7-7,4.2-13.3,11.1-14.1c4.7-0.5,9.1,1.7,11.6,5.4 c0.5-0.9,1.1-1.8,1.7-2.7c-3.2-4-8.2-6.4-13.6-5.8C5.4,1.1-0.8,8.9,0.2,17.6c0.9,8.7,8.5,15,17,14c4.2-0.5,7.8-2.6,10.3-5.7 c-0.7-0.8-1.2-1.7-1.7-2.7C23.8,26.1,20.6,28.2,16.9,28.6z");
//  str.append("></path><path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M36.8,0.2C32.6,0.7,29,2.8,26.5,5.9c0.7,0.8,1.2,1.7,1.7,2.7c2-2.9,5.2-5,8.9-5.4 c6.9-0.8,13,4.3,13.8,11.4c0.7,7-4.2,13.3-11.1,14.1c-4.7,0.5-9.1-1.7-11.6-5.4c-0.5,0.9-1.1,1.8-1.7,2.7c3.2,4,8.2,6.4,13.7,5.8 c8.5-0.9,14.6-8.7,13.7-17.4C52.9,5.5,45.3-0.7,36.8,0.2z");
//  str.append("></path><path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M29.1,8.5c-0.5-1-1.1-1.9-1.7-2.7c-0.7,0.8-1.2,1.7-1.7,2.7c0.7,1.1,1.3,2.3,1.7,3.6 c0.2,0.5,0.3,1.1,0.4,1.7c0,0.2,0.1,0.4,0.1,0.6c0.1,1.1,0.1,2.2-0.1,3.3c0.3,2.4,1.2,4.4,1.7,4.6c1-2.3,1.5-4.9,1.3-7.6 c0-0.2,0-0.4,0-0.6C30.7,12.2,30.1,10.3,29.1,8.5z");
//  str.append("></path><path fill=");
//  str.append("#E66C25");
//  str.append(" d=");
//  str.append("M26.6,19.9c-0.2-0.5-0.3-1-0.4-1.6c0-0.2-0.1-0.5-0.1-0.7c-0.1-1.2-0.1-2.3,0.1-3.4 c-0.3-2.4-1.4-4.4-1.7-4.5c-1,2.3-1.5,4.8-1.4,7.4c0,0.3,0,0.6,0.1,0.8c0.2,2,0.8,3.9,1.6,5.6c0.5,1,1.1,1.9,1.7,2.7 c0.7-0.8,1.2-1.7,1.7-2.7C27.5,22.1,26.9,20.9,26.6,19.9z");
//  str.append("></path>");
//  str.append("</g>");
//  str.append("</svg>");

}


