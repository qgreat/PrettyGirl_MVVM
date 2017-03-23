package com.prettygirl_mvvm.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.mvvmframework.listener.OnViewModelNotifyListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.prettygirl_mvvm.R;
import com.prettygirl_mvvm.databinding.ActivityPicdetailBinding;
import com.prettygirl_mvvm.model.GirlsBean;
import com.prettygirl_mvvm.utils.BitmapUtil;
import com.prettygirl_mvvm.utils.ColorUtil;
import com.prettygirl_mvvm.viewmodel.PicDetailViewModel;
import com.prettygirl_mvvm.widget.PinchImageView;

/**
 * Created by Sai on 16/6/10.
 */
public class PicDetailActivity extends AppCompatActivity implements OnViewModelNotifyListener {
    public static final int CODE_ITEM = 0;
    public static final int CODE_HEADER_FOOTER = 1;
    public PicDetailViewModel viewModel;
    ActivityPicdetailBinding binding;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_picdetail);
        viewModel = new PicDetailViewModel();
        GirlsBean girlsBean = (GirlsBean) getIntent().getSerializableExtra("model");

        int color = getIntent().getIntExtra("color",0);
        change(color);

        viewModel.setOnViewModelNotifyListener(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        viewModel.setDetail(girlsBean);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getColorAndSet();
//
//            }
//        },2000);

    }

    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onViewModelNotify(Bundle bundle, int code) {
        switch (code) {
            case CODE_ITEM:
                GirlsBean pic = (GirlsBean) bundle.getSerializable("model");
//                Toast.makeText(this, pic.getSpotName(),Toast.LENGTH_SHORT).show();

                break;
            case CODE_HEADER_FOOTER:
                Toast.makeText(this, bundle.getString("msg"), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getColorAndSet() {

        PinchImageView imageView = binding.ivDetail;
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
//                Palette.Swatch vir = palette.getLightMutedSwatch();
                Palette.Swatch vir = palette.getVibrantSwatch();
                if (vir == null)
                    return;
                change(vir.getRgb());
            }
        });
    }

    public void change(int color) {
//        mToolbar.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.setStatusBarColor(ColorUtil.colorBurn(color));
            window.setNavigationBarColor(ColorUtil.colorBurn(color));
        }
        binding.llDetail.setBackgroundColor(color);


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PicDetail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
