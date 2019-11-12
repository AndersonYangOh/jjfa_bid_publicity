package com.longl.weixinbid.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class Util {
	 /** 
     * httpClient的get请求方式 
     *  
     * @param url 
     * @param charset 
     * @return 
     * @throws Exception 
     */  
    public static String doGet(String url, String charset) throws Exception {  
        HttpClient client = new HttpClient();  
        GetMethod method = new GetMethod(url);  
        if (null == url || !url.startsWith("http")) {  
            throw new Exception("请求地址格式不对");  
        }  
        // 设置请求的编码方式  
//        if (null != charset) {  
//            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + charset);  
//        } else {  
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + "utf-8");  
//        }  
        method.addRequestHeader("Access-Control-Allow-Origin", "*");
        method.addRequestHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
        method.addRequestHeader("Access-Control-Allow-Headers", "x-requested-with");
        method.addRequestHeader("Access-Control-Max-Age", "1");
        method.addRequestHeader("Referer",url);
        method.addRequestHeader("Host","www.zfcg.sh.gov.cn");
        int statusCode = client.executeMethod(method);  
  
        if (statusCode != 200) {// 打印服务器返回的状态  
            System.out.println("Method failed: " + method.getStatusLine());  
        }  
        // 返回响应消息  
        byte[] responseBody = method.getResponseBodyAsString().getBytes(method.getResponseCharSet());  
        // 在返回响应消息使用编码(utf-8或gb2312)  
        String response = new String(responseBody, "GBK");  
        System.out.println("------------------response:" + response);  
        // 释放连接  
        method.releaseConnection();  
        return response;  
    }  
	/**
	 * 检查年月日是否合法
	 * @param ymd
	 * @return
	 */
	public static boolean checkTime(String dtime) {
	    if (dtime == null || dtime.length() != 14) {
	        return false;
	    }
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	    try {
	        Date date = format.parse(dtime);
	        if (!format.format(date).equals(dtime)) {
	            return false;
	        }
	    } catch (ParseException e) {
	        return false;
	    }
	    return true;
	}
	
	public static int CompareDay(String startDate,String endDate) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
	    try  
	    {  
	      Date d1 = df.parse(startDate);  
	      Date d2 = df.parse(endDate);  
	      long diff = d2.getTime() - d1.getTime();//这样得到的差值是毫秒级别  
	      long days = diff / (1000 * 60 * 60 * 24);  
	      return (int) days;
	    }catch (Exception e)  
	    {  
	    }  
	    return -1;
	}
	
	/**
	 * 检查省份
	 * @param ll
	 * @return
	 */
	public static boolean checkProvince(String province) {
	    if (province.equals("北京")) {
	        return true;
	    }else if (province.equals("兵团")) {
	        return true;
	    }else if (province.equals("黑龙江")) {
	        return true;
	    }else if (province.equals("辽宁")) {
	        return true;
	    }else if (province.equals("吉林")) {
	        return true;
	    }else if (province.equals("天津")) {
	        return true;
	    }else if (province.equals("重庆")) {
	        return true;
	    }else if (province.equals("上海")) {
	        return true;
	    }else if (province.equals("四川")) {
	        return true;
	    }else if (province.equals("陕西")) {
	        return true;
	    }else if (province.equals("山西")) {
	        return true;
	    }else if (province.equals("山东")) {
	        return true;
	    }else if (province.equals("河北")) {
	        return true;
	    }else if (province.equals("河南")) {
	        return true;
	    }else if (province.equals("湖北")) {
	        return true;
	    }else if (province.equals("湖南")) {
	        return true;
	    }else if (province.equals("安徽")) {
	        return true;
	    }else if (province.equals("浙江")) {
	        return true;
	    }else if (province.equals("江苏")) {
	        return true;
	    }else if (province.equals("江西")) {
	        return true;
	    }else if (province.equals("福建")) {
	        return true;
	    }else if (province.equals("广东")) {
	        return true;
	    }else if (province.equals("广西")) {
	        return true;
	    }else if (province.equals("贵州")) {
	        return true;
	    }else if (province.equals("云南")) {
	        return true;
	    }else if (province.equals("内蒙古")) {
	        return true;
	    }else if (province.equals("宁夏")) {
	        return true;
	    }else if (province.equals("兰州")) {
	        return true;
	    }else if (province.equals("甘肃")) {
	        return true;
	    }else if (province.equals("青海")) {
	        return true;
	    }else if (province.equals("新疆")) {
	        return true;
	    }else if (province.equals("西藏")) {
	        return true;
	    }else if (province.equals("海南")) {
	        return true;
	    }else if (province.equals("中央")) {
	        return true;
	    }
	    
	    return false;
	}
	
	public static String replaceBlank(String str){
	       String dest = null;
	       if(str == null){
	           return dest;
	       }else{
	           Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	           Matcher m = p.matcher(str);
	           dest = m.replaceAll("");
	           return dest;
	       }
	   }
}
