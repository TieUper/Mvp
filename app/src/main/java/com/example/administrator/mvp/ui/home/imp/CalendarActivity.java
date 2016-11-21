package com.example.administrator.mvp.ui.home.imp;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.common.utils.DateUtil;
import com.example.administrator.mvp.ui.home.ICalendarActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tie on 2016/10/11.
 */

public class CalendarActivity extends BaseActivity implements ICalendarActivity {


    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.view_calender)
    MaterialCalendarView mViewCalender;

    CalendarDay mDate;

    @Override
    protected void onBackPressedSupport() {

    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        setToolBar(mToolBar, "选择日期");
        mViewCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mViewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_calender;
    }

    @OnClick(R.id.tv_calender_enter)
    void chooseDate() {
        //RxBus.getDefault().post(mDate);
        //finish();
    }
}
