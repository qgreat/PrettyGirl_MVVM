package com.prettygirl_mvvm.network;


import com.bigkoo.mvvmframework.model.HttpResult;
import com.prettygirl_mvvm.model.GirlsBean;
import com.prettygirl_mvvm.model.Pic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Sai on 16/3/5.
 */
public interface HttpService {

    @GET("api/data/{type}/{count}/{page}")
    Call<HttpResult<List<GirlsBean> >>getGirls(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
    @GET("api/data/{type}/{count}/{page}")
    Call<HttpResult<List<GirlsBean> >>getGirl(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
    @GET("api/list/pic")
    Call<HttpResult<List<Pic>>> getTicketList(@Query("page") int page, @Query("pageSize") int pageSize);
    @GET("api/detail/pic")
    Call<HttpResult<Pic>> getTicketDetail(@Query("id") String id);
}
