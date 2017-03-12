package com.prettygirl_mvvm.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bigkoo.mvvmframework.listener.OnViewModelNotifyListener;
import com.prettygirl_mvvm.R;
import com.prettygirl_mvvm.databinding.ActivityPicdetailBinding;
import com.prettygirl_mvvm.model.Pic;
import com.prettygirl_mvvm.viewmodel.PicDetailViewModel;

/**
 * Created by Sai on 16/6/10.
 */
public class PicDetailActivity extends AppCompatActivity implements OnViewModelNotifyListener {
    public static final int CODE_ITEM = 0;
    public static final int CODE_HEADER_FOOTER = 1;
    public PicDetailViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPicdetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_picdetail);
        viewModel = new PicDetailViewModel();
        viewModel.setOnViewModelNotifyListener(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onViewModelNotify(Bundle bundle, int code) {
        switch (code){
            case CODE_ITEM:
                Pic pic = (Pic) bundle.getSerializable("model");
                Toast.makeText(this, pic.getSpotName(),Toast.LENGTH_SHORT).show();
                break;
            case CODE_HEADER_FOOTER:
                Toast.makeText(this,bundle.getString("msg"),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
