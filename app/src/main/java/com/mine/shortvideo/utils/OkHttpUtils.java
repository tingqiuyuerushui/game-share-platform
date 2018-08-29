package com.mine.shortvideo.utils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.MutableByte;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import com.mine.shortvideo.application.MyApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import timber.log.Timber;

/**
 * Created by fan on 2016/11/9.
 */

public class OkHttpUtils {

    /**
     * 静态实例
     */
    private static OkHttpUtils sOkHttpUtilsManager;

    /**
     * okhttpclient实例
     */
    private OkHttpClient mClient;

    /**
     * 因为我们请求数据一般都是子线程中请求，在这里我们使用了handler
     */
    private Handler mHandler;
    public static final MediaType JSONTYPE = MediaType.parse("application/json; charset=utf-8");

    /**
     * 构造方法
     */
    private OkHttpUtils() {
        // 可以通过实现 Logger 接口更改日志保存位置
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        mClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        mClient = new OkHttpClient();
        /**
         * 在这里直接设置连接超时.读取超时，写入超时
         */
        mClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);



       /* OkHttpClient.Builder builder = mClient.newBuilder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);*/
//        builder.addInterceptor(loggingInterceptor);
      //  mClient = builder.build();

        /**
         * 如果是用的3.0之前的版本  使用以下直接设置连接超时.读取超时，写入超时
         */

        //client.setConnectTimeout(10, TimeUnit.SECONDS);
        //client.setWriteTimeout(10, TimeUnit.SECONDS);
        //client.setReadTimeout(30, TimeUnit.SECONDS);

        /**
         * 初始化handler
         */
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 单例模式  获取OkHttp实例
     *
     * @return
     */
    public static OkHttpUtils getInstance() {

        if (sOkHttpUtilsManager == null) {
            sOkHttpUtilsManager = new OkHttpUtils();
        }
        return sOkHttpUtilsManager;
    }

    //-------------------------同步的方式请求数据--------------------------

    /**
     * 对外提供的get方法,同步的方式
     *
     * @param url 传入的地址
     * @return
     */
    public static Response getSync(String url) {

        //通过获取到的实例来调用内部方法
        return sOkHttpUtilsManager.inner_getSync(url);
    }

    /**
     * GET方式请求的内部逻辑处理方式，同步的方式
     *
     * @param url
     * @return
     */
    private Response inner_getSync(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            //同步请求返回的是response对象
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 对外提供的同步获取String的方法
     *
     * @param url
     * @return
     */
    public static String getSyncString(String url) {
        return sOkHttpUtilsManager.inner_getSyncString(url);
    }


    /**
     * 同步方法
     */
    private String inner_getSyncString(String url) {
        String result = null;
        try {
            /**
             * 把取得到的结果转为字符串，这里最好用string()
             */
            result = inner_getSync(url).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //-------------------------异步的方式请求数据--------------------------
    public static void getAsync(String url,boolean isAuth, DataCallBack callBack) {
        if(isAuth){
            getInstance().inner_getAsync(url, callBack);
        }else{
            getInstance().inner_getAsync_noAuth(url, callBack);
        }
    }

    /**
     * 内部逻辑请求的方法
     *
     * @param url
     * @param callBack
     * @return
     */
    private void inner_getAsync(String url, final DataCallBack callBack) {
        Timber.e("请求链接："+url);
        final Request request = new Request
                .Builder()
                .url(url)
                .addHeader("Authorization","Basic " + getAuthHeader())
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                deliverDataSuccess(result, callBack);
            }
        });
    }
    private void inner_getAsync_noAuth(String url, final DataCallBack callBack) {
        Timber.e("请求链接："+url);
        final Request request = new Request
                .Builder()
                .url(url)
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                deliverDataSuccess(result, callBack);
            }
        });
    }

    /**
     * 分发失败的时候调用
     *
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverDataFailure(final Request request, final IOException e, final DataCallBack callBack) {
        /**
         * 在这里使用异步处理
         */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(request, e);
                }
            }
        });
    }

    /**
     * 分发成功的时候调用
     *
     * @param result
     * @param callBack
     */
        private void deliverDataSuccess(final String result, final DataCallBack callBack) {
        /**
         * 在这里使用异步线程处理
         */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.requestSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 数据回调接口
     */
    public interface DataCallBack {
        void requestFailure(Request request, IOException e);

        void requestSuccess(String result) throws Exception;
    }

    //-------------------------提交表单--------------------------

    public static void postAsync(String url, Map<String, String> params, DataCallBack callBack) {
        getInstance().inner_postAsync(url, params, callBack);
    }

    private void inner_postAsync(String url, Map<String, String> params, final DataCallBack callBack) {
        RequestBody requestBody;
        if (params == null) {
            params = new HashMap<>();
        }
        FormBody.Builder builder = new FormBody.Builder();

        /**
         * 在这对添加的参数进行遍历，map遍历有四种方式，如果想要了解的可以网上查找
         */
        for (Map.Entry<String, String> map : params.entrySet()) {
            String key = map.getKey().toString();
            String value = null;
            if (map.getValue() == null) {
                value = "";
            } else {
                value = map.getValue();
            }
            /**
             * 把key和value添加到formbody中
             */
            builder.add(key, value);
        }
        requestBody = builder.build();
        // 请求对象
        final Request request = new Request.Builder().url(url)
                .post(requestBody)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                deliverDataSuccess(result, callBack);
            }
        });
    }
    //-------------------------提交json--------------------------
    public static void postJsonAsync(String url, String json, DataCallBack callBack) {
        getInstance().inner_postJsonAsync(url, json, callBack);
    }
    private void inner_postJsonAsync(String url, String json, final DataCallBack callBack) {
        Timber.e("postJson url=="+url);
        Timber.e("json parameter=="+json);
        RequestBody body = RequestBody.create(JSONTYPE, json);
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization","Basic " + getAuthHeader())
                .addHeader("X-CSRF-Token",MySharedData.sharedata_ReadString(MyApplication.getAppContext(),"token"))
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                deliverDataSuccess(result, callBack);
            }
        });
    }
    //-------------------------patch修改json--------------------------
    public static void patchJsonAsync(String url, String json, DataCallBack callBack) {
        getInstance().inner_patchJsonAsync(url, json, callBack);
    }
    private void inner_patchJsonAsync(String url, String json, final DataCallBack callBack) {
        Timber.e("postJson url=="+url);
        Timber.e("json parameter=="+json);
        RequestBody body = RequestBody.create(JSONTYPE, json);
        final Request request = new Request.Builder()
                .url(url)
                .patch(body)
                .addHeader("Authorization","Basic " + getAuthHeader())
                .addHeader("X-CSRF-Token",MySharedData.sharedata_ReadString(MyApplication.getAppContext(),"token"))
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                deliverDataSuccess(result, callBack);
            }
        });
    }
    //-------------------------上传文件--------------------------
    /**
     * 不带参数上传文件
     *
     * */
    public static void postFileAsyncNoParameter(String url,String filePath,ProgressListener listener, DataCallBack callBack) {
        getInstance().inner_postFileAsyncNoParameter(url,filePath,listener, callBack);
    }

    /**
     * 带参数上传文件
     *
     * */
    public static void postFileAsync(String url, Map<String,String> map,String filePath, DataCallBack callBack) {
        getInstance().inner_postFileAsync(url, map,filePath, callBack);
    }

    private void inner_postFileAsync(String url, Map<String,String> map,String filePath, final DataCallBack callBack)
    {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (map!=null)
        {
            for (Map.Entry<String,String> entry:map.entrySet())
            {
                builder.addFormDataPart(entry.getKey(),entry.getValue());
            }
        }

        File file = new File(filePath);
        if(file.exists()){
            String TYPE = "application/octet-stream";
            RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE),file);
            final Request request = new Request.Builder()
                    .url(url)
                    .post(fileBody)
                    .addHeader("Authorization","Basic " + getAuthHeader())
                    .addHeader("Content-Disposition","file; filename=" + "\""+file.getName() + "\"")
                    .build();
            mClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    deliverDataSuccess(result, callBack);
                }
            });
        }else {
            Timber.e("post3: 文件不存在");
            RequestBody requestBody = builder
                    .setType(MultipartBody.FORM)
                    .build();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader("Authorization","Bearer" + getAuthHeader())
                    .build();
            mClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    deliverDataSuccess(result, callBack);
                }
            });
        }

    }
    private void inner_postFileAsyncNoParameter(String url,String filePath,final ProgressListener listener, final DataCallBack callBack)
    {
        File file = new File(filePath);
        if(file.exists()){
            String TYPE = "application/octet-stream";
            ProgressRequestBody fileBody = new ProgressRequestBody(0,MediaType.parse(TYPE),file,listener);
            final Request request = new Request.Builder()
                    .url(url)
                    .post(fileBody)
                    .addHeader("Authorization","Basic " + getAuthHeader())
                    .addHeader("Content-Disposition","file; filename=" + "\""+file.getName() + "\"")
                    .build();
            mClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    deliverDataSuccess(result, callBack);
                }
            });
        }else {
            Timber.e("post3: 文件不存在");
        }

    }
    private static String getAuthHeader(){
        String auth;
        String userName = MySharedData.sharedata_ReadString(MyApplication.getAppContext(),"userId");
        String password = MySharedData.sharedata_ReadString(MyApplication.getAppContext(),"password");
        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){
            auth = userName + password;
        }else{
            auth = "admin" + ":" + "admin";
        }
//        auth = "admin" + ":" + "admin";
//        String auth = "d8admin@163.com" + ":" + "uaes,1234";
//        byte[] encodedAuth = Base64.encode(auth.getBytes(StandardCharsets.UTF_8),);
        String authHeader = Base64.encodeToString(auth.getBytes(StandardCharsets.UTF_8),Base64.NO_WRAP);
        return  authHeader;
    }

    //-------------------------文件下载--------------------------
    public static void downloadAsync(String url, String desDir, DataCallBack callBack) {
        getInstance().inner_downloadAsync(url, desDir, callBack);
    }

    /**
     * 下载文件的内部逻辑处理类
     *
     * @param url      下载地址
     * @param desDir   目标地址
     * @param callBack
     */
    private void inner_downloadAsync(final String url, final String desDir, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                /**
                 * 在这里进行文件的下载处理
                 */
                InputStream inputStream = null;
                FileOutputStream fileOutputStream = null;
                try {
                    //文件名和目标地址
                    File file = new File(desDir, getFileName(url));
                    //把请求回来的response对象装换为字节流
                    inputStream = response.body().byteStream();
                    fileOutputStream = new FileOutputStream(file);
                    int len = 0;
                    byte[] bytes = new byte[2048];
                    //循环读取数据
                    while ((len = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, len);
                    }
                    //关闭文件输出流
                    fileOutputStream.flush();
                    //调用分发数据成功的方法
                    deliverDataSuccess(file.getAbsolutePath(), callBack);
                } catch (IOException e) {
                    //如果失败，调用此方法
                    deliverDataFailure(request, e, callBack);
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                }
            }

        });
    }
    /**
     * 继承RequestBody,实现上传的进度监听
     */
    private class ProgressRequestBody extends RequestBody {
        MediaType contentType;
        File file;
        ProgressListener listener;
        int id;

        /**
         * 构造函数
         *
         * @param id          一次可以上传多个文件,id表示本文件在这一批文件当中的编号
         * @param contentType MIME类型
         * @param file        要上传的文件
         * @param listener    传输进度监听器
         */
        public ProgressRequestBody(int id, MediaType contentType, File file, ProgressListener listener) {
            this.id = id;
            this.contentType = contentType;
            this.file = file;
            this.listener = listener;
        }


        @Override
        public MediaType contentType() {
            return contentType;
        }

        @Override
        public long contentLength() throws IOException {
            return file.length();
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            Source source;
            long len;//记录本次读了多少数据
            long currSize = 0;//记录目前一共读了多少数据
            long totalSize = contentLength();//一共有多少数据

            try {
                source = Okio.source(file);
                Buffer buffer = new Buffer();

                while ((len = source.read(buffer, 2048)) != -1) {
                    sink.write(buffer, len);
                    sink.flush();
                    currSize += len;

                    //回调,进度监听
                    listener.onProgress(totalSize, currSize, totalSize == currSize, id);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据文件url获取文件的路径名字
     *
     * @param url
     * @return
     */
    private String getFileName(String url) {
        int separatorIndex = url.lastIndexOf("/");
        String path = (separatorIndex < 0) ? url : url.substring(separatorIndex + 1, url.length());
        return path;
    }
    /**
     * 传输进度监听器
     */
    public interface ProgressListener {
        void onProgress(long totalSize, long currSize, boolean done, int id);
    }


}
