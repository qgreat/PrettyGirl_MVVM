package com.prettygirl_mvvm.viewmodel;

import com.bigkoo.mvvmframework.model.HttpResult;
import com.bigkoo.mvvmframework.viewmodel.BaseDetailViewModel;
import com.prettygirl_mvvm.model.Pic;
import com.prettygirl_mvvm.network.HttpServiceGenerator;

import retrofit2.Call;


/**
 * Created by Sai on 16/6/10.
 */
public class PicDetailViewModel extends BaseDetailViewModel {
    public PicDetailViewModel(){
        onLoad();
    }

    @Override
    public Call<HttpResult<Pic>> onLoadDatailHttpRequest() {
        return HttpServiceGenerator.createService().getTicketDetail("10002");
    }
}
