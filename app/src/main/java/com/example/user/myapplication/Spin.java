package com.example.user.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;

public class Spin extends android.support.v7.widget.AppCompatImageView {
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    private double mAddAngle = 0;

    public Spin(Context context) {
        super(context);
    }

    public Spin(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        final float centerOfWidth = getWidth() / 2;
        final float centerOfHeight = getHeight() / 2;
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrAngle = Math.toDegrees(Math.atan2(x - centerOfWidth, centerOfHeight - y));
                break;

            case MotionEvent.ACTION_MOVE:
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - centerOfWidth, centerOfHeight - y));
                animate(this, mAddAngle, mAddAngle + mCurrAngle - mPrevAngle);
                mAddAngle += mCurrAngle - mPrevAngle;
                break;

            case MotionEvent.ACTION_UP:
                performClick();
                break;

        }
        return true;
    }

    private void animate(View view, double fromDegrees, double toDegrees) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(0);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);
    }
}