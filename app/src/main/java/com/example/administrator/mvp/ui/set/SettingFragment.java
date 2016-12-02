package com.example.administrator.mvp.ui.set;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseFragment;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.common.injector.component.RxBus;
import com.example.administrator.mvp.common.utils.DayNightHelper;
import com.example.administrator.mvp.common.utils.SharedPreferenceUtil;
import com.example.administrator.mvp.model.entity.DayNight;
import com.example.administrator.mvp.model.entity.NightModeEvent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tie on 2016/11/23.
 */

public class SettingFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

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

    @Bind(R.id.ll_setting)
    LinearLayout mLL;

    boolean isNull = true;
    @Bind(R.id.text_normal)
    TextView mTextNormal;
    @Bind(R.id.ll_normal)
    LinearLayout mCard1;
    @Bind(R.id.text_other)
    TextView mTextOther;
    @Bind(R.id.ll_other)
    LinearLayout mCard2;
    @Bind(R.id.icon_cache)
    ImageView mIconCache;
    @Bind(R.id.icon_image)
    ImageView mIconImage;
    @Bind(R.id.icon_night)
    ImageView mIconNight;
    @Bind(R.id.icon_feedback)
    ImageView mIconFeedback;
    @Bind(R.id.icon_clear)
    ImageView mIconClear;
    @Bind(R.id.icon_update)
    ImageView mIconUpdate;
    @Bind(R.id.text_cache)
    TextView mTextCache;
    @Bind(R.id.text_image)
    TextView mTextImage;
    @Bind(R.id.text_night)
    TextView mTextNight;
    @Bind(R.id.text_feedback)
    TextView mTextFeedback;
    @Bind(R.id.text_clear)
    TextView mTextClear;
    @Bind(R.id.text_update)
    TextView mTextUpdate;

    private DayNightHelper mDayNightHelper;


    @Override
    public void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void initUI() {

        initData();

        cbSettingCache.setChecked(SharedPreferenceUtil.getAutoCacheState());
        cbSettingImage.setChecked(SharedPreferenceUtil.getNoImageState());
        cbSettingNight.setChecked(SharedPreferenceUtil.getNightModeState());
        cbSettingCache.setOnCheckedChangeListener(this);
        cbSettingImage.setOnCheckedChangeListener(this);
        cbSettingNight.setOnCheckedChangeListener(this);
    }

    @Override
    public void fetchData() {

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
//                    useNightMode(isChecked);
                }
                changeThemeByZhiHu();
                break;
            case R.id.cb_setting_image:
                SharedPreferenceUtil.setNoImageState(isChecked);

                break;
            case R.id.cb_setting_cache:
                SharedPreferenceUtil.setAutoCacheState(isChecked);
                break;
        }
    }

    private void initData() {
        mDayNightHelper = new DayNightHelper(getActivity());
    }

    private void initTheme() {
        if (mDayNightHelper.isDay()) {
            getActivity().setTheme(R.style.DayTheme);
        } else {
            getActivity().setTheme(R.style.NightTheme);
        }
    }

    /**
     * 使用简书的实现套路来切换夜间主题
     */
    private void changeThemeByJianShu() {
        toggleThemeSetting();
        refreshUI();
    }

    /**
     * 使用知乎的实现套路来切换夜间主题
     */
    private void changeThemeByZhiHu() {
        showAnimation();
        toggleThemeSetting();
        refreshUI();
    }

    /**
     * 刷新UI界面
     */
    private void refreshUI() {
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        TypedValue settingBg = new TypedValue();//settingBgColor
        TypedValue cardBg = new TypedValue();//CardViewBgColor
        TypedValue icon_cache = new TypedValue();//icon_cache
        TypedValue icon_clear = new TypedValue();//icon_clear
        TypedValue icon_feedback = new TypedValue();//icon_feedback
        TypedValue icon_image = new TypedValue();//icon_image
        TypedValue icon_night = new TypedValue();//icon_night
        TypedValue icon_update = new TypedValue();//icon_update
        TypedValue settingtext = new TypedValue();//text

        Resources.Theme theme = getActivity().getTheme();
        theme.resolveAttribute(R.attr.clockBackground, background, true);
        theme.resolveAttribute(R.attr.settingTextColor, settingtext, true);
        theme.resolveAttribute(R.attr.clockTextColor, textColor, true);
        theme.resolveAttribute(R.attr.settingBgColor, settingBg, true);
        theme.resolveAttribute(R.attr.cardBgColor, cardBg, true);
        theme.resolveAttribute(R.attr.icon_cache, icon_cache, true);
        theme.resolveAttribute(R.attr.icon_clear, icon_clear, true);
        theme.resolveAttribute(R.attr.icon_feedback, icon_feedback, true);
        theme.resolveAttribute(R.attr.icon_image, icon_image, true);
        theme.resolveAttribute(R.attr.icon_night, icon_night, true);
        theme.resolveAttribute(R.attr.icon_update, icon_update, true);


        mLL.setBackgroundResource(settingBg.resourceId);

        Resources resources = getResources();
        mTextNormal.setTextColor(resources.getColor(textColor.resourceId));
        mTextOther.setTextColor(resources.getColor(textColor.resourceId));
        mTextCache.setTextColor(resources.getColor(settingtext.resourceId));
        mTextImage.setTextColor(resources.getColor(settingtext.resourceId));
        mTextNight.setTextColor(resources.getColor(settingtext.resourceId));
        mTextFeedback.setTextColor(resources.getColor(settingtext.resourceId));
        mTextClear.setTextColor(resources.getColor(settingtext.resourceId));
        mTextUpdate.setTextColor(resources.getColor(settingtext.resourceId));
        tvSettingUpdate.setTextColor(resources.getColor(settingtext.resourceId));
        tvSettingClear.setTextColor(resources.getColor(settingtext.resourceId));

        mIconCache.setImageResource(icon_cache.resourceId);
        mIconClear.setImageResource(icon_clear.resourceId);
        mIconImage.setImageResource(icon_image.resourceId);
        mIconFeedback.setImageResource(icon_feedback.resourceId);
        mIconNight.setImageResource(icon_night.resourceId);
        mIconUpdate.setImageResource(icon_update.resourceId);

        mCard1.setBackgroundResource(cardBg.resourceId);
        mCard2.setBackgroundResource(cardBg.resourceId);
//        for (RelativeLayout layout : mLayoutList) {
//            layout.setBackgroundResource(background.resourceId);
//        }
//        for (CheckBox checkBox : mCheckBoxList) {
//            checkBox.setBackgroundResource(background.resourceId);
//        }
//        for (TextView textView : mTextViewList) {
//            textView.setBackgroundResource(background.resourceId);
//        }
//
//        Resources resources = getResources();
//        for (TextView textView : mTextViewList) {
//            textView.setTextColor(resources.getColor(textColor.resourceId));
//        }

        //让 RecyclerView 缓存在 Pool 中的 Item 失效
        //那么，如果是ListView，要怎么做呢？这里的思路是通过反射拿到 AbsListView 类中的 RecycleBin 对象，然后同样再用反射去调用 clear 方法
//        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
//        try {
//            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
//            declaredField.setAccessible(true);
//            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
//            declaredMethod.setAccessible(true);
//            declaredMethod.invoke(declaredField.get(mRecyclerView), new Object[0]);
//            RecyclerView.RecycledViewPool recycledViewPool = mRecyclerView.getRecycledViewPool();
//            recycledViewPool.clear();
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        refreshStatusBar();
    }

    /**
     * 刷新 StatusBar
     */
    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getActivity().getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getActivity().getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }

    /**
     * 切换主题设置
     */
    private void toggleThemeSetting() {
        if (mDayNightHelper.isDay()) {
            mDayNightHelper.setMode(DayNight.NIGHT);
            getActivity().setTheme(R.style.NightTheme);
        } else {
            mDayNightHelper.setMode(DayNight.DAY);
            getActivity().setTheme(R.style.DayTheme);
        }
    }

    /**
     * 展示一个切换动画
     */
    private void showAnimation() {
        final View decorView = getActivity().getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(getActivity());
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
