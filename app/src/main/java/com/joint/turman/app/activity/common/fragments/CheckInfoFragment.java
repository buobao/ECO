package com.joint.turman.app.activity.common.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joint.turman.app.R;
import com.joint.turman.app.ui.listview.SimpleListView;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

/**
 * Created by dqf on 2016/3/17.
 */
public class CheckInfoFragment extends Fragment {

    private MaterialCalendarView mCalendar;
    private SimpleListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_checkinfo,container,false);
        //init view
        mCalendar = (MaterialCalendarView) view.findViewById(R.id.frg_checkinfo_calendar);
        mListView = (SimpleListView) view.findViewById(R.id.frg_checkinfo_list);



        return view;
    }

}
