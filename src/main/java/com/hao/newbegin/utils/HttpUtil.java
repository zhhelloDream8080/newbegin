package com.hao.newbegin.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtil {
    private static CloseableHttpClient httpClient = null;
    private final static Object syncLock = new Object();
    //创建httpClient类

    /**
     * 1.HttpClients.createDefault()创建的httpclient默认使用连接池机制
     * 2.使用连接池机制,httpclient要声明成全局变量，只能reponse.close,并且httpclient不能关闭，
     * httpclient.close关闭会导致连接池释放
     * 3.原有的局部声明httpclient，并且httpclient.close方式，相当于未使用连接池机制，浪费
     * 4.另外一种httpclient= new
     * DefaultHttpClient()方式，未使用连接池，结束时要回收资源，使用httpclient.close()or
     * httpclient.getConnectionManager.shutdown()
     */
    private static CloseableHttpClient getHttpClient() {
        synchronized (syncLock) {
            if (httpClient == null) {

                PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
                connManager.setMaxTotal(1000);// 设置最大连接数100
                connManager.setDefaultMaxPerRoute(1000);// 设置每个路由默认连接数
                // connManager.setMaxPerRoute(new HttpRoute(host),
                // 5);//设置路由器对服务器允许最大5个并发访问
                // httpClient =
                // HttpClients.custom().setConnectionManager(connManager).setHostnameVerifier(new
                // AllowAllHostnameVerifier()).build();
                httpClient = HttpClients.custom().setConnectionManager(connManager).build();
            }
        }
        return httpClient;
    }

    //post调用
    public static String httpPost(String url, String param, Map<String, Object> header) throws Exception {
        CloseableHttpResponse httpResponse = null;
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            //处理头部信息
            if (header != null && header.size() > 0) {
                for (Map.Entry entry : header.entrySet()) {
                    httpPost.setHeader((String) entry.getKey(), (String) entry.getValue());
                }
            }
            //处理入参
            if (param != null && param.length() != 0) {
                HttpEntity httpEntity = new StringEntity(param, "UTF-8");
                httpPost.setEntity(httpEntity);
            }
            //开始发送请求信息
            log.info("请求入参："+httpPost.toString());
            httpResponse = getHttpClient().execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            log.info("获得出参："+result.toString());
            EntityUtils.consume(httpEntity);
        } catch (Exception e) {
            log.info("调用http请求失败");
        } finally {
            if (httpResponse != null) {
                //释放连接
                httpResponse.close();
            }
        }
        return result;
    }

    //get调用
    public static String httpGet(String url, Map<String, Object> param, Map<String, Object> header) throws Exception{
        String result = null;
        CloseableHttpResponse httpResponse = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (param != null) {
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    String name = entry.getKey();
                    String value = (String) entry.getValue();
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?")  //linshi
                    + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
            log.info("请求入参："+httpGet.toString());
            httpResponse=getHttpClient().execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            log.info("获得出参："+result.toString());
            EntityUtils.consume(httpEntity);
        } catch (Exception e) {
            log.info("调用http请求失败");
        }finally {
            if (httpResponse!=null){
                httpResponse.close();
            }
        }
        return result;
    }
}
