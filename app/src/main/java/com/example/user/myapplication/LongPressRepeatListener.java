package com.example.user.myapplication;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class LongPressRepeatListener implements View.OnTouchListener {

    private Handler handler = new Handler();

    private int initialInterval;
    private final long normalInterval;
    private final View.OnClickListener clickListener;

    private Runnable handlerRunnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, normalInterval);
            clickListener.onClick(downView);
        }
    };

    private View downView;

    public LongPressRepeatListener(int initialInterval, long normalInterval, View.OnClickListener clickListener) {
        if (clickListener == null)
            throw new IllegalArgumentException("listener null");
        if (initialInterval < 0 || normalInterval < 0)
            throw new IllegalArgumentException("interval error");

        this.initialInterval = initialInterval;
        this.normalInterval = normalInterval;
        this.clickListener = clickListener;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.removeCallbacks(handlerRunnable);
                handler.postDelayed(handlerRunnable, initialInterval);
                downView = view;
                clickListener.onClick(view);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                handler.removeCallbacks(handlerRunnable);
                downView = null;
                return true;
        }

        return false;
    }
}