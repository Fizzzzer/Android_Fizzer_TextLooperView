package com.fizzer.doraemon.textlooper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fizzer on 2016/12/20.
 * Email: doraemonmqq@sina.com
 */

public class TextLoopView extends LinearLayout {

    private Context mContext;
    private ViewFlipper mViewFlipper;
    private onItemClickListener mListener;

    private List<String> datas = new ArrayList<>();

    public TextLoopView(Context context) {
        super(context);

        init(context);
    }

    public TextLoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextLoopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        mViewFlipper = new ViewFlipper(mContext);
        mViewFlipper.setFlipInterval(2000);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        mViewFlipper.setLayoutParams(lp);
        this.addView(mViewFlipper);


    }

    /**
     * 设置数据源
     *
     * @param data data
     */
    public void setDatas(List<String> data) {
        this.datas = data;
        initData();
    }

    public void setItemOnClickListener(onItemClickListener listener){
        this.mListener = listener;
    }

    private void initData() {
        if (datas == null || datas.size() == 0) {
            return;
        }

        for (int index = 0; index < datas.size(); index++) {
            TextView textView = new TextView(mContext);
            textView.setText(datas.get(index));
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(lp);
            textView.setGravity(Gravity.CENTER);
            mViewFlipper.addView(textView);

            final int finalIndex = index;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener == null){
                        return;
                    }
                    mListener.onItemClick(finalIndex);
                }
            });
        }
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext,R.anim.text_loop_in));
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.text_loop_out));
        mViewFlipper.startFlipping();

    }

    interface onItemClickListener{
        void onItemClick(int position);
    }
}
