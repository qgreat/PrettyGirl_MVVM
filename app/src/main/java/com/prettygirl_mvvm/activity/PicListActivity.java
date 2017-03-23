package com.prettygirl_mvvm.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bigkoo.mvvmframework.listener.OnViewModelNotifyListener;
import com.prettygirl_mvvm.R;
import com.prettygirl_mvvm.databinding.ActivityPiclistBinding;
import com.prettygirl_mvvm.model.GirlsBean;
import com.prettygirl_mvvm.model.Pic;
import com.prettygirl_mvvm.viewmodel.PicDetailViewModel;
import com.prettygirl_mvvm.viewmodel.PicListViewModel;

/**
 * 通用BaseRecyclerViewModel使用例子
 * Created by Sai on 16/6/3.
 */
public class PicListActivity extends AppCompatActivity implements OnViewModelNotifyListener {
    public static final int CODE_ITEM = 0;
    public static final int CODE_HEADER_FOOTER = 1;
    public PicListViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPiclistBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_piclist);
        viewModel = new PicListViewModel();
        viewModel.setOnViewModelNotifyListener(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

    }

    @Override
    public void onViewModelNotify(Bundle bundle, int code) {
        switch (code){
            case CODE_ITEM:
                GirlsBean pic = (GirlsBean) bundle.getSerializable("model");
//                Toast.makeText(this, pic.getWho(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, PicDetailActivity.class);
                intent.putExtra("model", pic);
                startActivity(intent);
                break;
            case CODE_HEADER_FOOTER:
                Toast.makeText(this,bundle.getString("msg"),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
