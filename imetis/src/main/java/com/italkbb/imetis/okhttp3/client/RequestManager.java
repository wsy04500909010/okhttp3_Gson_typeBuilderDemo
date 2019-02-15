package com.italkbb.imetis.okhttp3.client;

import com.italkbb.imetis.okhttp3.listener.DataProcessor;
import com.italkbb.imetis.okhttp3.listener.ProcessorListener;
import com.italkbb.imetis.okhttp3.request.BuildRequest;
import com.italkbb.imetis.okhttp3.request.RequestParams;
import com.italkbb.imetis.okhttp3.response.FileDownloadCallback;
import com.italkbb.imetis.okhttp3.response.FileUploadCallback;
import com.italkbb.imetis.okhttp3.response.JsonCallback;

import java.util.Map;

/***********
 * @Author rape flower
 * @Date 2017-03-15 17:00
 * @Describe 处理网络请求的业务
 */
public class RequestManager {

    private static boolean isInit = false;//表示是否执行过初始化方法（init），true: 执行过，false: 没执行过

    private RequestManager() {
        throw new AssertionError();
    }

    /**
     * 初始化
     *
     * @param headers
     */
    public static void init(Map<String, String> headers, boolean isDebug) {
        BuildConfig.setDebug(isDebug);
        XOkHttpClient.initConfig(headers);
        isInit = true;
    }

    /**
     * 是否初始化
     */
    private static void checkInit() {
        if (!isInit) {
            throw new RuntimeException("Please call method: init() in your application start");
        }
    }

    /**
     * Get请求
     *
     * @param url
     * @param params
     * @param dataListener
     * @param clazz
     */
    public static void get(String url, RequestParams params, ProcessorListener dataListener, Class<?> clazz) {
        checkInit();
        XOkHttpClient.sendRequest(BuildRequest.createGetRequest(url, params),
                new JsonCallback(new DataProcessor(dataListener, clazz)));
    }

    /**
     * Post请求
     *
     * @param url
     * @param params
     * @param dataListener
     */
    public static void post(String url, RequestParams params, ProcessorListener dataListener, Class<?> clazz) {
        checkInit();
        XOkHttpClient.sendRequest(BuildRequest.createPostRequest(url, params),
                new JsonCallback(new DataProcessor(dataListener, clazz)));
    }

    /**
     * 文件上传
     *
     * @param url
     * @param params
     * @param dataListener
     */
    public static void uploadFile(String url, RequestParams params, ProcessorListener dataListener) {
        checkInit();
        XOkHttpClient.sendRequest(BuildRequest.createMultiPostRequest(url, params),
                new FileUploadCallback(new DataProcessor(dataListener)));
    }

    /**
     * 文件下载
     *
     * @param url
     * @param params
     * @param dataListener
     * @param savePath 文件下载保存路径
     */
    public static void downloadFile(String url, RequestParams params, ProcessorListener dataListener, String savePath) {
        checkInit();
        //文件下载用GET请求
        XOkHttpClient.sendRequest(BuildRequest.createGetRequest(url, params),
                new FileDownloadCallback(new DataProcessor(dataListener, savePath)));
    }

}
