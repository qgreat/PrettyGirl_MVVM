package com.prettygirl_mvvm.network;

import android.content.Context;


import com.prettygirl_mvvm.app.Constants;
import com.prettygirl_mvvm.app.MyApplication;
import com.shanbay.mock.MockApiInterceptor;
import com.shanbay.mock.MockApiSuite;
import com.shanbay.mock.api.StandardMockApi;
import com.shanbay.mock.constant.MockHttpMethod;

import java.util.Random;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sai on 16/3/4.
 */
public class HttpServiceGenerator {
    public static Context context;


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constants.GANHUO_API)
                    .addConverterFactory(GsonConverterFactory.create())
            ;

    private static HttpService httpService;

    /**
     * 返回默认的HttpService
     * @return
     */
    public static HttpService createService() {
        if(httpService==null)
            httpService = createService(builder);
        return httpService;
    }

    public static HttpService createService(Retrofit.Builder builder) {
        //这里mock数据用于测试
        MockApiSuite suite = new MockApiSuite("trip"); // mockdata.trip 表示 suite name
        StandardMockApi standardMockApiList = new StandardMockApi(MockHttpMethod.GET, "api/list/pic");
        standardMockApiList.setSuccessDataFile("ticketlist.json");
        standardMockApiList.setRequestTime(1000);
        suite.addMockApi(standardMockApiList);


        suite.addMockApi(new StandardMockApi(MockHttpMethod.GET, "api/detail/pic").setSuccessDataFile("ticketdetail.json"));

        MockApiInterceptor mockApiInterceptor = new MockApiInterceptor(context);
        mockApiInterceptor.addMockApiSuite(suite);
//        httpClient.addInterceptor(mockApiInterceptor);   // 注入mock api interceptor

        Retrofit retrofit = builder.client(MyApplication.defaultOkHttpClient()).build();
        return retrofit.create(HttpService.class);
    }
}
