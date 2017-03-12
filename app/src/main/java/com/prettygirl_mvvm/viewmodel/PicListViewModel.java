package com.prettygirl_mvvm.viewmodel;

import android.os.Bundle;
import android.view.View;

import com.bigkoo.mvvmframework.model.HttpResult;
import com.bigkoo.mvvmframework.viewmodel.BaseRefreshRecyclerViewModel;
import com.prettygirl_mvvm.R;
import com.prettygirl_mvvm.activity.PicListActivity;
import com.prettygirl_mvvm.model.GirlsBean;
import com.prettygirl_mvvm.model.Pic;
import com.prettygirl_mvvm.network.HttpServiceGenerator;

import java.util.List;

import me.tatarka.bindingcollectionadapter.LayoutManagers;
import retrofit2.Call;


/**
 * 通用BaseRecyclerViewModel使用例子
 * Created by Sai on 16/6/3.
 */
public class PicListViewModel extends BaseRefreshRecyclerViewModel {

    private final String ssss;

    public PicListViewModel() {
        //正常的item样式
        super(R.layout.item_piclist);
        //设置特殊样式的view
        ssss = new String();
        String ssss = this.ssss;
///*/*/*/*/*/**/*/*/*/*/*/
        setSpecialView(8, R.layout.item_special_piclist);
        setSpecialView(10, R.layout.item_special_piclist);
        //加入header
        addHeader(R.layout.item_header_hot_piclist, "spanner");
        addHeader(R.layout.item_header_image_piclist, 0);

//        setLayoutManager(LayoutManagers.grid(2));  //网格模式
        onListRefresh();
    }

    @Override
    public Call<HttpResult<List<GirlsBean>>> onLoadListHttpRequest() {
        return HttpServiceGenerator.createService().getGirls("福利",getPage(), getPageSize());
    }

    @Override
    public void onItemClick(View v, int position, View itemView, Object item) {
        Bundle bundle = new Bundle();
        int code = PicListActivity.CODE_ITEM;
        if (item instanceof Pic) {
            bundle.putSerializable("model", ((Pic) item));
        } else {
            code = PicListActivity.CODE_HEADER_FOOTER;
            bundle.putString("msg", "点击了header或footer");
        }
        onViewModelNotify(bundle, code);
    }

    /**
     * 可选重写，如果是第一页并且有更多可以加载，则加入footer
     */
    @Override
    public void onLoadListComplete() {

        if (isFirstPage() && getHasMore().get() && getFooterSize() == 0) {
            //加入footer
            addFooter(R.layout.item_footer_loadmore_piclist, "加载更多");
        } else if (!getHasMore().get() && getFooterSize() != 0) {
            removeFooters();
        }
    }
}
