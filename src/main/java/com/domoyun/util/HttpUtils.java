package com.domoyun.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.domoyun.routine.post;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.domoyun.DAO.dataprovider.Configure;

public class HttpUtils {
    private static CloseableHttpClient httpclient;
    private static RequestConfig defaultRequestConfig;
    //example
    public static void main(String[] args) {
      /*  String s = get("http://192.168.109.224:8000/V4/Api/LabelPrintService/PrintLabel?type=json",null);
        String s1 = get("http://localhost:8001/eduservice/teacher/pageList/2/2",null);

        String s2 = post("http://localhost:8001/eduservice/teacher/pageListVo/1/5","{\n" +
                "\n" +
                "  \"name\": \"string\"\n" +
                "}");*/

        String authorization = HttpPostWithJson("http://localhost:8001/eduservice/teacher/pageListVo/1/5","{\n" +
                "\n" +
                "  \"name\": \"string\"\n" +
                "}", "");

        System.out.println(authorization);
    }

    static {
        defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
    }

    public static String get(String url,  Map<String,  String> paramsMap) {
        String  result=null;

            //创建一个容器，保存名值对参数
            List<NameValuePair> parameters = null;

            //有参数设置参数，没参数不要设值参数
            if (paramsMap != null) {
                parameters = new ArrayList<>();
                //遍历map的所有的键值对
                Set<String> keySet = paramsMap.keySet();
                for (String name : keySet) {
                    String value = paramsMap.get(name);
                    parameters.add(new BasicNameValuePair(name, value));
                }

                //编码后的参数列表
                String encodeParams = URLEncodedUtils.format(parameters, "UTF-8");
                url += ("?" + encodeParams);
            }

            HttpGet get = new HttpGet(url);


            //添加cookie请求头
            String jSessionId = ParameterUtils.getGlobalData("JSESSIONID");
            if (jSessionId != null) {
                get.addHeader("Cookie", jSessionId);
            }

            get.setConfig(defaultRequestConfig);

        try {
            //发数据包-->获得相应
            CloseableHttpResponse response = httpclient.execute(get);

            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (ClientProtocolException e) {
            System.out.println("http接口调用异常：url is::" + url+"\t" +e);

            return null;
        } catch (Exception e) {

            System.out.println("http接口调用异常：url is::" + url+"\t" +e);

            return null;
        }

        return result;
    }
    public static String post(String url,String json) {
        String result = "";

            //2:生成post请求
            HttpPost post = new HttpPost(url);

                //创建一个原生form表单的请求体
                //第三步：给httpPost设置JSON格式的参数
                StringEntity requestEntity = new StringEntity(json, "UTF-8");
                //
                post.addHeader("Content-Type", "application/json");
                post.addHeader("Authorization", "Basic Nzc3Nzc6LlFrPilaMnZ+Kg==");

                //设置post请求请求体
                post.setEntity(requestEntity);


            //			post.addHeader("Cookie", "JSESSIONID=59916658BFE68AF0F469C6C5D1BC39C4");
            //			post.addHeader("Cookie", ParameterUtils.getGlobalData("JSESSIONID"));
            //添加cookie请求头
            String jSessionId = ParameterUtils.getGlobalData("JSESSIONID");
            if (jSessionId != null) {
                post.addHeader("Cookie", jSessionId);
            }

            try {
            //创建一个HTTP发包客户端(HTTP发包客户端具备这样的功能：浏览器、postman、jmeter、fiddler、soapui、app)
            //发数据包-->获得相应
            CloseableHttpResponse response = httpclient.execute(post);

            //将会话id添加到全局数据池
            addSeesionIdToGlobalData(response);
            //获得响应体

            result = EntityUtils.toString(response.getEntity(),"UTF-8");

            } catch (ClientProtocolException e) {
                System.out.println("http接口调用异常：url is::" + url+"\t" +e);

                return null;
            } catch (Exception e) {

                System.out.println("http接口调用异常：url is::" + url+"\t" +e);

                return null;
            }
        return result;
    }
    public static String put(String url, String json) {
        String result = "";

        //2:生成post请求
        HttpPut put = new HttpPut(url);

        //给httpPost设置JSON格式的参数
        StringEntity requestEntity = new StringEntity(json, "UTF-8");

        //			post.addHeader("Cookie", "JSESSIONID=59916658BFE68AF0F469C6C5D1BC39C4");
        //			post.addHeader("Cookie", ParameterUtils.getGlobalData("JSESSIONID"));
        put.addHeader("Content-Type", "application/json");
        put.addHeader("Authorization", "Basic Nzc3Nzc6LlFrPilaMnZ+Kg==");

        //设置post请求请求体
        put.setEntity(requestEntity);


        //添加cookie请求头
        String jSessionId = ParameterUtils.getGlobalData("JSESSIONID");
        if (jSessionId != null) {
            put.addHeader("Cookie", jSessionId);
        }

        try {
            //发数据包-->获得相应
            CloseableHttpResponse response = httpclient.execute(put);

            //将会话id添加到全局数据池
            addSeesionIdToGlobalData(response);

            //获得响应体
            result = EntityUtils.toString(response.getEntity(),"UTF-8");

        } catch (ClientProtocolException e) {
            System.out.println("http接口调用异常：url is::" + url+"\t" +e);

            return null;
        } catch (Exception e) {

            System.out.println("http接口调用异常：url is::" + url+"\t" +e);

            return null;
        }
        return result;
    }
    public static String delete(String url, Map<String, String> paramsMap) {
        String  result=null;

        //创建一个容器，保存名值对参数
        List<NameValuePair> parameters = null;

        //有参数设置参数，没参数不要设值参数
        if (paramsMap != null) {
            parameters = new ArrayList<>();
            //遍历map的所有的键值对
            Set<String> keySet = paramsMap.keySet();
            for (String name : keySet) {
                String value = paramsMap.get(name);
                parameters.add(new BasicNameValuePair(name, value));
            }

            //编码后的参数列表
            String encodeParams = URLEncodedUtils.format(parameters, "UTF-8");
            url += ("?" + encodeParams);
        }

        HttpDelete delete = new HttpDelete(url);


        //添加cookie请求头
        String jSessionId = ParameterUtils.getGlobalData("JSESSIONID");
        if (jSessionId != null) {
            delete.addHeader("Cookie", jSessionId);
        }

        delete.setConfig(defaultRequestConfig);

        try {
            //发数据包-->获得相应
            CloseableHttpResponse response = httpclient.execute(delete);

            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (ClientProtocolException e) {
            System.out.println("http接口调用异常：url is::" + url+"\t" +e);

            return null;
        } catch (Exception e) {

            System.out.println("http接口调用异常：url is::" + url+"\t" +e);

            return null;
        }

        return result;
    }

    /**
     * 将登录成功后返回的token保存到全局数据池中间
     *
     * @param response
     */
    public static void addSeesionIdToGlobalData(CloseableHttpResponse response) {
        //		Header[] headers = response.getAllHeaders();
        //根据响应头的名称获得对应响应头
        Header header = response.getFirstHeader("Set-Cookie");
        if (header != null) {
            //获得Set-Cookie响应头的值
            String headerValue = header.getValue();
            //JSESSIONID=F1B8174B3B8DE92ADA325AE8D6DF0D5D; Path=/futureloan; HttpOnly
            if (headerValue != null && headerValue.trim().length() > 0) {
                //如果包含JSESSIONID字符串
                if (headerValue.contains("JSESSIONID")) {
                    int idx = headerValue.indexOf(";");
                    String jsessionId = headerValue.substring(0, idx);
                    //保存到全局数据池
                    ParameterUtils.addGlobalData("JSESSIONID", jsessionId);
                }
            }

        }
    }

    /**
     * 发包（分发各种请求）
     */
    public static String request(String apiId, String url, Map<String, String> paramsMap) {
        String result = "";
        String method = Configure.getRequestMethodByApiId(apiId);
        System.out.println(method);
        if ("get".equalsIgnoreCase(method)) {
            result = get(url, paramsMap);
        } else if ("delete".equalsIgnoreCase(method)) {
            result = delete(url, paramsMap);
        }
        return result;
    }

    /**
     * 发包（分发各种请求）
     */
    public static String request(String apiId, String url, String requestData, String sheetname, String created) {
        String result = "";
        String method = Configure.getRequestMethodByApiId(apiId);
        if ("postJson".equalsIgnoreCase(method)) {
            result = HttpPostWithJson(url, requestData, created);
        } else if ("postXml".equalsIgnoreCase(method)) {
            result = HttpPostWithXml(url, requestData, sheetname);
        }
        return result;
    }

    /**
     * json格式post请求
     * @param url
     * @param json String类型的json格式数据
     * @return String类型json格式数据
     */
    public static String HttpPostWithJson(String url, String json, String authorization) {
        String result = "请求数据格式Json,这是默认返回值，接口调用失败";

        try {
//          创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

//          给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json);

            httpPost.addHeader("Authorization", "Basic " + authorization);
            //指定Content-Encoding头，作为一个字符串。 默认实现调用setContentEncoding(Header) 。
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

//          发送HttpPost请求，获取返回值
            CloseableHttpResponse response = httpclient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(),"utf-8"); //调接口获取返回值时，必须用此方法

        } catch (ClientProtocolException e) {
            System.out.println("http接口调用异常：url is::" + url+"\t" +e);

            return null;
        } catch (Exception e) {

            System.out.println("http接口调用异常：url is::" + url+"\t" +e);

            return null;
        }
        //第五步：返回值
        return result;
    }

    /**
     * xml格式post请求
     *
     * @param url
     * @param xml String类型的xml格式数据
     * @return String类型xml格式数据
     */
    public static String HttpPostWithXml(String url, String xml, String sheetname) {
        String result = "请求数据格式Xml,这是默认返回值，接口调用失败";
        CloseableHttpResponse response = null;

        String xmlparmete =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.example.org/Ec/\">\n" +
                        "\t<SOAP-ENV:Body>\n" +
                        "\t\t<ns1:callService>\n" +
                        ParameterUtils.getFunctionOptStr(ParameterUtils.getCommonStr(xml)) +
                        "\t\t\t<appToken>" + ParameterUtils.getGlobalData("appToken") + "</appToken>\n" +
                        "\t\t\t<appKey>" + ParameterUtils.getGlobalData("appKey") + "</appKey>\n" +
                        "\t\t\t<service>" + sheetname + "</service>\n" +
                        "\t\t</ns1:callService>\n" +
                        "\t</SOAP-ENV:Body>\n" +
                        "</SOAP-ENV:Envelope>";

        try {
            //创建HttpPost对象
            HttpPost httpPost = new HttpPost(url);
            //设置setEntity
            if (xml != null && xml.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(xmlparmete, "UTF-8");
                stringEntity.setContentType("text/xml");
                httpPost.setEntity(stringEntity);
                //发送HttpPost请求，获取返回值
                response = httpclient.execute(httpPost);

                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            } else {
                System.out.println("xml请求正文为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
