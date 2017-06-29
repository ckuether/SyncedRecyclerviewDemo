package com.example.corey.syncedrecyclerviewdemo;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class SyncedRVContainer extends LinearLayout{

    float downX, downY, upX, upY;

    ViewConfiguration vc;
    Context mContext;

    View topRV, bottomRV;

    public SyncedRVContainer(Context context) {
        super(context);
        mContext = context;
    }

    public SyncedRVContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public SyncedRVContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void init(){
        vc = ViewConfiguration.get(mContext);

        topRV = getChildAt(0);
        bottomRV = getChildAt(1);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        /*
         * This method JUST determines whether we want to intercept the motion.
         * If we return true, onTouchEvent will be called and we do the actual
         * scrolling there.
         */

        if(e.getAction() == MotionEvent.ACTION_DOWN){
            return true;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int touchSlop = vc.getScaledTouchSlop();

        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            downX = ev.getX();
            downY = ev.getY();

            topRV.dispatchTouchEvent(ev);
            bottomRV.dispatchTouchEvent(ev);

            return true;
        }else if(ev.getAction() == MotionEvent.ACTION_MOVE){
            topRV.dispatchTouchEvent(ev);
            bottomRV.dispatchTouchEvent(ev);
            return true;
        }else if(ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL){
            upX = ev.getX();
            upY = ev.getY();


            float deltaX = downX - upX;
            float deltaY = downY - upY;
            if(Math.abs(deltaX) < touchSlop && Math.abs(deltaY) < touchSlop){

                topRV.dispatchTouchEvent(ev);
                bottomRV.dispatchTouchEvent(ev);
//                if(downY > topRV.getTop() && downY < topRV.getBottom()){
//                    topRV.dispatchTouchEvent(ev);
//                }else if(downY > bottomRV.getTop() && downY < bottomRV.getBottom()){
//                    bottomRV.dispatchTouchEvent(ev);
//                }
            }else {
                topRV.dispatchTouchEvent(ev);
                bottomRV.dispatchTouchEvent(ev);

            }
            return true;
        }

        return false;
    }
}
