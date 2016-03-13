package com.joint.turman.app.ui.search;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joint.turman.app.R;

import java.util.Map;

/**
 * Created by dqf on 2016/3/11.
 */
public class SearchBar extends LinearLayout implements View.OnClickListener {
    private EditText mSearchContext;
    private TextView mSearchBtn;
    private Context context;

    //查询条件的名称
    private String searchTag;
    //查询url
    private String url;
    //其他附加的条件
    private Map<String,Object> params;

    public SearchBar(Context context) {
        super(context);
        init(context);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.view_searchbar,null);
        mSearchContext = (EditText) view.findViewById(R.id.frg_list_search_input);
        mSearchBtn = (TextView) view.findViewById(R.id.frg_list_search_btn);
        mSearchBtn.setOnClickListener(this);
        setOrientation(VERTICAL);
        addView(view);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public void onClick(View v) {
        //这里写查询过程
    }

    public String getSearchTag() {
        return searchTag;
    }

    public void setSearchTag(String searchTag) {
        this.searchTag = searchTag;
    }
}























