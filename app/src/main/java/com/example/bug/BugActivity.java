package com.example.bug;

import android.app.Activity;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class BugActivity extends Activity {

    public static final String FLING = "fling";
    private int screenWidth;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FrameLayout view = new FrameLayout(this);
        view.setId(R.id.bug);
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));


        final boolean fling = getIntent().getBooleanExtra(FLING, false);
        if (fling) {
            view.setOnClickListener(this::flingAnimation);
        } else {
            view.setOnClickListener(this::propertyAnimation);
        }

        setContentView(view);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
    }

    private void propertyAnimation(final View v) {
        v.animate().translationX(screenWidth).setDuration(1200).start();
    }

    private void flingAnimation(final View v) {

        final FlingAnimation fling = new FlingAnimation(v, DynamicAnimation.TRANSLATION_X);

        fling
                .setStartVelocity(2000)
                .setMinValue(0)
                .setMaxValue(screenWidth)
                .setFriction(0.1f)
                .start();
    }
}