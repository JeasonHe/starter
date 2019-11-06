package com.strugglm.core.common.utils;

import com.strugglm.core.common.enity.okHttp.callback.DefaultCallBack;
import com.strugglm.core.common.enity.resp.Resp;
import com.strugglm.core.common.enums.RespEnum;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;


/**
 * @description: Http工具类
 * @author: hejiale
 * @create: 2019/10/30 13:56
 */
public class OkHttpUtils {

    private static OkHttpClient okHttpClient = (OkHttpClient) SpringContextUtils.getBean("okHttpClient");

    /**
     * get方式 同步请求
     *
     * @param url
     * @param queryParam
     * @return
     */
    public static <T> Resp synGet(String url, String queryParam) {
        return synRequest(doGetRequest(url));
    }

    /**
     * Post同步请求，消息体为Map
     *
     * @param url
     * @param params
     * @return
     */
    public static <T> Resp synPost(String url, Map<String, String> params) {
        return synRequest(doPostRequest(url, buildMapRequestBody(params)));
    }

    /**
     * Post同步请求，消息体为Json
     *
     * @param url
     * @param jsonParam
     * @return
     */
    public static <T> Resp synPost(String url, String jsonParam) {
        return synRequest(doPostRequest(url, buildJsonRequestBody(jsonParam)));
    }

    /**
     * 同步上传文件
     *
     * @param url
     * @param fileMap
     * @param <T>
     * @return
     */
    public static <T> Resp synUpload(String url, Map<String, File> fileMap) {
        return synRequest(doPostRequest(url, buildFileMapRequestBody(fileMap)));
    }

    /**
     * 同步上传文件和普通字段
     *
     * @param url
     * @param param
     * @param fileMap
     * @param <T>
     * @return
     */
    public static <T> Resp synUpload(String url, Map<String, String> param, Map<String, File> fileMap) {
        return synRequest(doPostRequest(url, buildFileMapRequestBody(param, fileMap)));
    }


    /**
     * Get的异步请求，默认回调
     *
     * @param url        请求地址
     * @param queryParam 请求参数
     * @return
     */
    public static void asynGet(String url, String queryParam) {
        asynRequest(doGetRequest(url));
    }

    /**
     * Get的异步请求,自定义回调
     *
     * @param url
     * @param queryParam
     * @param callback
     */
    public static void asynGet(String url, String queryParam, Callback callback) {
        if (callback == null) {
            asynRequest(doGetRequest(url));
        } else {
            asynRequest(doGetRequest(url), callback);
        }
    }

    /**
     * 默认回调的异步post请求，消息体为map
     *
     * @param url
     * @param mapParam
     */
    public static void asynPost(String url, Map<String, String> mapParam) {
        asynRequest(doPostRequest(url, buildMapRequestBody(mapParam)));
    }

    /**
     * 自定义回调的异步post请求，消息体为map
     *
     * @param url
     * @param mapParam
     * @param callback
     */
    public static void asynPost(String url, Map<String, String> mapParam, Callback callback) {
        if (callback == null) {
            asynRequest(doPostRequest(url, buildMapRequestBody(mapParam)));
        } else {
            asynRequest(doPostRequest(url, buildMapRequestBody(mapParam)), callback);
        }
    }

    /**
     * 默认回调的异步post请求，消息体为json
     *
     * @param url
     * @param jsonParam
     */
    public static void asynPost(String url, String jsonParam) {
        asynRequest(doPostRequest(url, buildJsonRequestBody(jsonParam)));
    }

    /**
     * 自定义回到的post请求，消息体为json
     *
     * @param url
     * @param jsonParam
     * @param callback
     */
    public static void asynPost(String url, String jsonParam, Callback callback) {
        if (callback == null) {
            asynRequest(doPostRequest(url, buildJsonRequestBody(jsonParam)));
        } else {
            asynRequest(doPostRequest(url, buildJsonRequestBody(jsonParam)), callback);
        }
    }

    /**
     * 异步文件上传
     *
     * @param url
     * @param fileMap
     */
    public static void asynUpload(String url, Map<String, File> fileMap) {
        asynRequest(doPostRequest(url, buildFileMapRequestBody(fileMap)));
    }

    /**
     * 异步文件上传，自定义回调
     *
     * @param url
     * @param fileMap
     * @param callback
     */
    public static void asynUpload(String url, Map<String, File> fileMap, Callback callback) {
        if (callback == null) {
            asynRequest(doPostRequest(url, buildFileMapRequestBody(fileMap)));
        } else {
            asynRequest(doPostRequest(url, buildFileMapRequestBody(fileMap)), callback);
        }
    }

    /**
     * 异步文件和普通字段上传
     *
     * @param url
     * @param param
     * @param fileParam
     */
    public static void asynUpload(String url, Map<String, String> param, Map<String, File> fileParam) {
        asynRequest(doPostRequest(url, buildFileMapRequestBody(param, fileParam)));
    }

    /**
     * 异步文件和普通字段上传，自定义回调
     *
     * @param url
     * @param param
     * @param fileParam
     * @param callback
     */
    public static void asynUpload(String url, Map<String, String> param, Map<String, File> fileParam, Callback callback) {
        if (callback == null) {
            asynRequest(doPostRequest(url, buildFileMapRequestBody(param, fileParam)));
        } else {
            asynRequest(doPostRequest(url, buildFileMapRequestBody(param, fileParam)), callback);
        }
    }

    /**
     * 执行post请求
     *
     * @param url
     * @param requestBody
     * @return
     */
    private static Request doPostRequest(String url, RequestBody requestBody) {
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    /**
     * 执行Get请求
     *
     * @param url
     * @return
     */
    private static Request doGetRequest(String url) {
        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }

    /**
     * 构建map格式的请求体
     *
     * @param params
     * @return
     */
    private static RequestBody buildMapRequestBody(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        return builder.build();
    }

    /**
     * 构造含有文件类型的请求体
     *
     * @param fileMap
     * @return
     */
    private static RequestBody buildFileMapRequestBody(Map<String, String> params, Map<String, File> fileMap) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //字符串参数
        for (Map.Entry<String, String> map : params.entrySet()) {
            builder.addFormDataPart(map.getKey(), map.getValue());
        }
        //文件类型参数
        for (Map.Entry<String, File> map : fileMap.entrySet()) {
            String key = map.getKey();
            File file = map.getValue();
            if (file == null
                    || !file.exists()
                    || file.length() == 0) {
                continue;
            }
            builder.addFormDataPart(key, file.getName(), RequestBody.create(file, MediaType.parse("multipart/form-data")));
        }
        return builder.build();
    }

    /**
     * 构造只含有文件类型的请求体
     *
     * @param fileMap
     * @return
     */
    private static RequestBody buildFileMapRequestBody(Map<String, File> fileMap) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //文件类型参数
        for (Map.Entry<String, File> map : fileMap.entrySet()) {
            String key = map.getKey();
            File file = map.getValue();
            if (file == null
                    || !file.exists()
                    || file.length() == 0) {
                continue;
            }
            builder.addFormDataPart(key, file.getName(), RequestBody.create(file, MediaType.parse("multipart/form-data")));
        }
        return builder.build();
    }

    /**
     * 构造Json格式的请求体
     *
     * @param jsonParam
     * @return
     */
    private static RequestBody buildJsonRequestBody(String jsonParam) {
        return RequestBody.create(jsonParam, MediaType.parse("application/json; charset=utf-8"));
    }

    /**
     * 发送同步请求，获取响应结果
     *
     * @param request 构造okHttp的Request
     * @return
     */
    private static <T> Resp synRequest(Request request) {
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                //TODO 处理返回值
                return Resp.success(response.body().string());
            } else {
                return Resp.fail(RespEnum.FAIL_OKHTTP, "请求失败，错误码:" + response.code() + ",错误信息:" + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Resp.fail(RespEnum.FAIL_OKHTTP, "请求异常:信息:" + e.getMessage());
        }
    }

    /**
     * 异步请求，默认callback
     *
     * @param request
     */
    private static void asynRequest(Request request) {
        okHttpClient.newCall(request).enqueue(new DefaultCallBack());
    }

    /**
     * 异步请求，自定义callback
     *
     * @param request
     * @param callback
     */
    private static void asynRequest(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }

}
