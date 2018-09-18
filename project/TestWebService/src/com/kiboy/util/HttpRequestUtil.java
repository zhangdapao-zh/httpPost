package com.kiboy.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpRequestUtil {
	private static Log log=LogFactory.getLog(HttpRequestUtil.class);

	/**
	 * 
	 * <p>描述: 使用HttpPost传输 有用户名密码验证的xml格式的WebService调用</p> 
	 * @author WK-kiboy
	 * @date 2018年9月18日  
	 * @param url webservice的URL
	 * @param xmlStr xml格式的数据字符串
	 * @param username 用户名
	 * @param password 密码
	 * @return 返回传输响应的结果字符串
	 */
	public static String postXMLAuthWithHttpPost(String url, String xmlStr, String username,String password){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpEntity entity = null;
		HttpEntity Rentity = null;
		String retStr="";
		CloseableHttpResponse response=null;
		try {
			entity = new StringEntity(xmlStr,"UTF-8");
			HttpPost hp = new HttpPost(url);
			hp.addHeader("Content-Type","application/soap+xml;charset=UTF-8");
			hp.addHeader(new BasicHeader("Authorization","Basic " + Base64.encodeBase64String((username+":"+password).getBytes())));
			hp.setEntity(entity);
			response = httpclient.execute(hp);
			Rentity = response.getEntity();
			if (Rentity != null) {
				retStr=EntityUtils.toString(Rentity, "UTF-8");
			}
		} catch (Exception e) {
			log.error("使用HttpPost传输XML格式字符串失败："+e);
		} finally {
			// 关闭连接,释放资源
			try {
				if(response!=null) response.close();
				if(httpclient!=null) httpclient.close();
			} catch (IOException e) {
			}
		}
		return retStr;
	}
	

	/**
	 * 
	 * <p>描述: 使用HttpURLConnection传输 有用户名密码验证的xml格式的WebService调用</p> 
	 * @author WK-kiboy
	 * @date 2018年9月18日  
	 * @param url webservice的URL
	 * @param xmlStr xml格式的数据字符串
	 * @param username 用户名
	 * @param password 密码
	 * @return 返回传输响应的结果字符串
	 */
	public static String postXMLAuthWithUrlConn(String url,String xmlStr,String username,String password){
		String encoding =new String(Base64.encodeBase64(new String(username+":"+password).getBytes()));
		URL urlObj =null;
		HttpURLConnection conn=null;
		String retStr="";
		DataOutputStream dos=null;
		BufferedReader reader=null;
		try {
			urlObj = new URL(url);
			conn = (HttpURLConnection) urlObj.openConnection();
			conn.setRequestProperty("Content-Type", "application/soap+xml;charset=utf-8");
			conn.setRequestProperty("Authorization","Basic "+encoding);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			dos = new DataOutputStream(conn.getOutputStream());
			dos.write(xmlStr.getBytes("utf-8"));
			dos.flush();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line = null;
			StringBuffer strBuf = new StringBuffer();
			while ((line = reader.readLine()) != null) {
			    strBuf.append(line);
			}
			retStr = strBuf.toString();
		} catch (Exception e) {
			log.error("使用HttpURLConnection POST传输XML数据异常==="+e);
		}finally {
			try {
				if(dos!=null) dos.close();
				if(reader!=null) reader.close();
			} catch (Exception e2) { }
		}
		return retStr;
	}
	
   
}