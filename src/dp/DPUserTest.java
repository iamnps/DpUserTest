package dp;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class DPUserTest {
	/**
	 * 主要功能是抓取大众点评页面，获取部分参数
	 */
	private static String name = "";
	public String getHtmlContent(String htmlurl) {  
        URL url;  
        String temp;  
        StringBuffer sb = new StringBuffer();  
        try {  
            url = new URL(htmlurl);  
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
            while ((temp = in.readLine()) != null) {  
                sb.append(temp);  
            }  
            in.close();  
        } catch (final MalformedURLException me) {  
            System.out.println("URL错误!");  
            me.getMessage();  
        } catch (final IOException e) {  
            e.printStackTrace();  
        }  
        return sb.toString();  
    }
	
	public static void main(String[] args) throws InterruptedException {  
		DPUserTest t = new DPUserTest();  
		//路径是大众点评深圳同城活动美食页面
        String aa = t.getHtmlContent("http://s.dianping.com/event/shenzhen/c1");  
        List<String> bba = t.getNews(aa);  
        for (String s : bba) {  
        	String str = s.replaceAll("<.*?>", "").trim();
        	if(getFlag(str)){
        		name = str;
        		System.out.println(name);
        		break;
        	}
        }
		
		
    	boolean flag = false;
    	do{
    		if(flag){
    			break;
    		}
            String content = t.getHtmlContent("http://s.dianping.com/event/shenzhen/c1");  
            List<String> a = t.getNews(content);  
            for (String s : a) {  
            	String str = s.replaceAll("<.*?>", "").trim();
            	if(getFlag(str)){
            		if(!str.equals(name)){
            			EventQueue.invokeLater(new Runnable() {
                			@Override
                			public void run() {
                				JFrame frame = new DPMsgBox();
                				frame.setTitle("TreeViewer");
                				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                				frame.setVisible(true);
                			}
                		});
        				
            			System.out.println(str + new Date());
            			flag = true;
            		}
            		Thread.sleep(5000);
            		System.out.println("nothing!!" + new Date());
            		break;
            	}
            }
    	}while(true);
    }
	
	private static boolean getFlag(String str){
		boolean flag = str.indexOf("【体验券】") != -1 || str.indexOf("同城聚会") != -1 || str.indexOf("专享") != -1 || str.indexOf("【") != -1;
		return flag;
	}

	/**
	 * 最主要的方法，正则筛选出抓取信息中的有用字符串 
	 */
	private List<String> getNews(String s) {
		String regex = "<a.*?</a>"; 
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL); 
        Matcher ma = pa.matcher(s); 
        List<String> list = new ArrayList<String>(); 
        while (ma.find()) { 
            list.add(ma.group()); 
        } 
        return list; 
	}
}
