package com.wll.myproject.aggregatedata.util;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wll.myproject.aggregatedata.address.BaseAddress;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装okhttp
 * Created by sby on 2017/3/28.
 */


public class HttpApi {
    //固定版本号+机型+sdk版本号
    private static String userAgent = "(Android " + Build.VERSION.RELEASE + ";" + Build.MODEL + ","
            + Build.VERSION.SDK_INT + ""
            + ")";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=UTF-8");//mdiatype 这个需要和服务端保持一致
    private static final MediaType MEDIA_TYPE_POST = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");//mdiatype 这个需要和服务端保持一致
    public static final String WEBREQUSET_ERR = "网络请求异常";
    public static String needtoken;

    //执行相应的请求。构建Http请求。
    private static String generalRequestGet(Uri.Builder builder,
                                            ArrayList<String> paramsName, ArrayList<String> paramsValue) throws IOException {
        for (int i = 0; i < paramsName.size(); i++) {
            //将应用名，值，应用版本拼接在url后面。
            builder.appendQueryParameter((String) paramsName.get(i),
                    (String) paramsValue.get(i));
        }

        //设置请求的头部信息。头部信息的作用是方便在浏览器f12查看对应接口所需要的头部信息。
        Request.Builder builder_req = new Request.Builder()
                .addHeader("User-Agent", String.format(userAgent, "", "", "","", ""))
                .addHeader("x-changfu-app","android");
        //判断令牌是否有值。。此处是否定的，不走。
        if(!needtoken.equals("")){
            //如果有令牌，则加入请求对象的头部。。。？？
            builder_req.addHeader("Authorization",needtoken);
        }
        //创建一个请求
        Request request = null;
        try {
            //创建一个request对象。
            //利用添加的头信息的Request.Builder对象的url方法的build方法获得request对象。
            request = builder_req.url(builder.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建网络连接对象？？？。。。
        OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //创建一个Call
        //利用连接对象获得call拨打对象。。。
        final Call call = mOkHttpClient.newCall(request);
        //执行请求
        //执行请求，获得一个响应对象。。。
        final Response response = call.execute();
        //将相应对象变为ResponseBody对象然后字符串化。。。

        return response.body().string();
    }
    private static String generalRequestPutjson(Uri.Builder builder, List<String> paramArrayList1, List<String> paramArrayList2) throws IOException {
        JSONObject json = new JSONObject();
        for(int i = 0; i<paramArrayList1.size(); i++){
            try {
                json.put(paramArrayList1.get(i), paramArrayList2.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //创建一个请求实体对象 RequestBody
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json.toString());
        Request.Builder builder_req = new Request.Builder()
                .addHeader("User-Agent", String.format(userAgent, "", "", "","", ""))
                .addHeader("x-changfu-app","android");
        if(!needtoken.equals("")){
            builder_req.addHeader("Authorization",needtoken);
        }
        OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //创建一个请求
        final Request request = builder_req.url(builder.toString()).put(body).build();
        //创建一个Call
        final Call call = mOkHttpClient.newCall(request);
        //执行请求
        Response response = call.execute();
        return response.body().string();
    }
    private static String generalRequestDeletejson(Uri.Builder builder, List<String> paramArrayList1, List<String> paramArrayList2) throws IOException {
        JSONObject json = new JSONObject();
        for(int i = 0; i<paramArrayList1.size(); i++){
            try {
                json.put(paramArrayList1.get(i), paramArrayList2.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //创建一个请求实体对象 RequestBody
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json.toString());
        Request.Builder builder_req = new Request.Builder()
                .addHeader("User-Agent", String.format(userAgent, "", "", "","", ""))
                .addHeader("x-changfu-app","android");
        //如果token不是空字符串则加入头部。
        if(!needtoken.equals("")){
            builder_req.addHeader("Authorization",needtoken);
        }
        OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //创建一个请求
        final Request request = builder_req.url(builder.toString()).delete(body).build();
        //创建一个Call
        final Call call = mOkHttpClient.newCall(request);
        //执行请求
        Response response = call.execute();
        return response.body().string();
    }
    private static String generalRequestPostjson(Uri.Builder builder, List<String> paramArrayList1, List<String> paramArrayList2) throws IOException, JSONException {
        JSONObject json = new JSONObject();

        for(int i = 0; i<paramArrayList1.size(); i++){
            json.put(paramArrayList1.get(i), paramArrayList2.get(i));
        }
        //创建一个请求实体对象 RequestBody
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json.toString());
        Request.Builder builder_req = new Request.Builder()
                .addHeader("User-Agent", String.format(userAgent, "", "", "","", ""))
                .addHeader("x-changfu-app","android");
        if(!needtoken.equals("")){
            builder_req.addHeader("Authorization",needtoken);
        }
        OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //创建一个请求
        final Request request = builder_req.url(builder.toString()).post(body).build();
        //创建一个Call
        final Call call = mOkHttpClient.newCall(request);
        //执行请求
        Response response = call.execute();
        return response.body().string();
    }
    private static String generalRequestPost(Uri.Builder builder, List<String> paramArrayList1, List<String> paramArrayList2) throws IOException {
        //创建requestBody对象。
        FormBody.Builder formbuilder = new FormBody.Builder();
        for(int i = 0; i<paramArrayList1.size(); i++){
            formbuilder.add(paramArrayList1.get(i), paramArrayList2.get(i));
        }
        RequestBody formBody = formbuilder.build();
        //创建一个请求实体对象 RequestBody
        Request.Builder builder_req = new Request.Builder()
                .addHeader("User-Agent", String.format(userAgent, "", "", "","", ""))
                .addHeader("x-changfu-app","android");
        if(!needtoken.equals("")){
            builder_req.addHeader("Authorization",needtoken);
        }
        OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写入超时时间
                .build();

        //创建一个请求
        Log.i( "wll1", "路径："+builder.toString() );
        final Request request = builder_req.url(builder.toString()).post(formBody).build();
        //创建一个Call
        final Call call = mOkHttpClient.newCall(request);
        //执行请求
        Response response = call.execute();
        return response.body().string();
    }

    //generalRequest：是一般请求的意思。。通用请求。。
    public static void generalRequest(String url, HttpRequestListener<String> httpRequsetListener,
                                      ArrayList<String> paramsName, ArrayList<String> paramsValue, Boolean isJoint, String isGet, String token){

        //用来操作Uri的帮助器类。
        Uri.Builder builder = new Uri.Builder();

        //刚登录时候isJoint为false
        //第一次登陆不会走这里、
        if(isJoint){
            //将请求地址变成一个builder对象。
            //https://x.51-jiedao.com
            //设置先前编码的路径。
            builder.encodedPath( BaseAddress.HOST);
            //url值为：https://x.51-jiedao.com/getVersion/getVersion.dos?packageType=0&deviceType=android
            //将给定的url片段，分割追加到路径。
            builder.appendEncodedPath(url);
        }else{
            //设置先前编码的片段。将我们要访问的路径放入帮助器类中。。。
            builder.encodedPath(url);
        }

        needtoken=token;
        //handler是Android给我们提供用来更新UI的一套机制，也是一套消息处理机制，我们可以发消息，也可以通过它处理消息
        //此处创建了一个handler对象，将接口对象传入了构造器中。
        HttpRequestHandler handler = new HttpRequestHandler(httpRequsetListener);
        //新建一个子线程，
        new Thread(new HttpRequestThread(builder, paramsName, paramsValue, isGet,needtoken, handler)).start();
    }
    public interface Listen{
        void listen(String e);

    }
    //泛型接口。
    public interface HttpRequestListener<T>{
        public void requestListener(T result);
    }

    /**
     * 线程接口类的实现类。定义成员变量，重写run方法。
     */
    public static class HttpRequestThread implements Runnable {
        Uri.Builder builder;
        ArrayList<String> paramsName;
        ArrayList<String> paramsValue;
        String isGet;
        Handler handler;
        String needtoken;

        //通过构造方法将路径，应用名和版本的集合，请求方式，token，handler消息处理更新UI对象，
        // 并赋值给这个线程接口类的成员变量。此处的handler是向上造型，父类引用指向子类HttpRequestHandler。。
        public HttpRequestThread(Uri.Builder builder, ArrayList<String> paramsName, ArrayList<String> paramsValue,
                                 String isGet, String needtoken, Handler handler){
            this.builder = builder;
            this.paramsName = paramsName;
            this.paramsValue = paramsValue;
            this.isGet = isGet;
            this.needtoken = needtoken;
            this.handler = handler;
        }
        @Override
        public void run() {
            //发送请求，服务器的相应结果以字符串的形式保存下来。
            Log.d( "wll","debug走了RUN方法" );
            String result = "";
            try{
                if(isGet.equals("get")){
                    result = generalRequestGet(builder, paramsName, paramsValue);
                }else if(isGet.equals("post")){
                    result = generalRequestPost(builder, paramsName, paramsValue);
                }else if(isGet.equals("json")){
                    result = generalRequestPostjson(builder, paramsName, paramsValue);
                }else if(isGet.equals("delete")){
                     result = generalRequestDeletejson(builder, paramsName, paramsValue);
                }else  if(isGet.equals("put")){
                    result = generalRequestPutjson(builder, paramsName, paramsValue);
                }

            }catch(IOException | JSONException e){
                e.printStackTrace();
                result = WEBREQUSET_ERR;
            }
            //将数据放入message中，通过sendMessage发送出去，通过bundle对象将相应数据保存下来
            // 以便于
            Message message = new Message();
            //Bundle对象用来传递数据，他保存的值是以key和value的形式。
            Bundle bundle = new Bundle();
            bundle.putString("resMsg", result);
            //利用message对象将Bundle保存起来。
            message.setData(bundle);
            //发送消息。
            handler.sendMessage(message);
        }
    }

    /******************************
     * 自定义一个线程间的信息传递类继承Handler，重写handleMessage方法，然后创建Bundle用于视图之间的数据传递。
     * handler是Android给我们提供用来更新UI的一套机制，也是一套消息处理机制，我们可以发消息，也可以通过它处理消息。
     */
    static class HttpRequestHandler extends Handler {
        HttpRequestListener<String> httpRequsetListener;
        public HttpRequestHandler(
                HttpRequestListener<String> httpRequsetListener) {
            this.httpRequsetListener = httpRequsetListener;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            //在这里赋值给result。

            String result = bundle.getString("resMsg");
            //再回去调用接口类对象重写的方法。
            httpRequsetListener.requestListener(result);
        }
    }
    public static String setWebUrl(String url, ArrayList<String> param, ArrayList<String> value){
        Uri.Builder builder = new Uri.Builder();
        builder.encodedPath(BaseAddress.HOST);
        builder.appendEncodedPath(url);
        for (int i = 0; i < param.size(); i++) {
            builder.appendQueryParameter((String) param.get(i), (String) value.get(i));
        }
        return builder.build().toString();
    }


}
