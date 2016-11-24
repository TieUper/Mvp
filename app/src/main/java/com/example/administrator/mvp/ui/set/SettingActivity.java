package com.example.administrator.mvp.ui.set;

import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.common.injector.component.RxBus;
import com.example.administrator.mvp.common.utils.SharedPreferenceUtil;
import com.example.administrator.mvp.model.entity.NightModeEvent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tie on 2016/11/23.
 */

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.cb_setting_cache)
    AppCompatCheckBox cbSettingCache;
    @Bind(R.id.cb_setting_image)
    AppCompatCheckBox cbSettingImage;
    @Bind(R.id.cb_setting_night)
    AppCompatCheckBox cbSettingNight;
    @Bind(R.id.ll_setting_feedback)
    LinearLayout llSettingFeedback;
    @Bind(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @Bind(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @Bind(R.id.tv_setting_update)
    TextView tvSettingUpdate;
    @Bind(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;

    boolean isNull = true;

    @Override
    protected void onBackPressedSupport() {
        finish();
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initUI() {
        ButterKnife.bind(this);
        cbSettingCache.setChecked(SharedPreferenceUtil.getAutoCacheState());
        cbSettingImage.setChecked(SharedPreferenceUtil.getNoImageState());
        cbSettingNight.setChecked(SharedPreferenceUtil.getNightModeState());
        cbSettingCache.setOnCheckedChangeListener(this);
        cbSettingImage.setOnCheckedChangeListener(this);
        cbSettingNight.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_setting;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_night:
                if (isNull) {   //防止夜间模式MainActivity执行reCreate后重复调用
                    SharedPreferenceUtil.setNightModeState(isChecked);
                    NightModeEvent event = new NightModeEvent();
                    event.setNightMode(isChecked);
                    RxBus.getDefault().post(event);
                    useNightMode(isChecked);
                }
                break;
            case R.id.cb_setting_image:
                SharedPreferenceUtil.setNoImageState(isChecked);

                break;
            case R.id.cb_setting_cache:
                SharedPreferenceUtil.setAutoCacheState(isChecked);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
