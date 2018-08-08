package com.freelance.ascstb.animationapp;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvAnimate;
    private TextView tvTranslation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAnimate = findViewById(R.id.tvAnimate);
        tvTranslation = findViewById(R.id.tvTranslation);
    }

    //    Types of animation
    //property animation
    public void onAnimateTextView(View view) {

        switch (view.getId()) {
            //region Value Animator
            case R.id.btnValueAnimator:
                ValueAnimator valueAnimator;
                //using java code
//        valueAnimator = ValueAnimator.ofFloat(0f, 500f);
//        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//        valueAnimator.setDuration(2000);

                //using xml
                valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator_textview);


                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float progress = (float) valueAnimator.getAnimatedValue();
                        tvAnimate.setTranslationY(progress);
                        tvTranslation.setText(String.valueOf(progress));
                    }
                });

                valueAnimator.start();
                break;
            //endregion

            //region Object Animator
            case R.id.btnObjectAnimator:
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvAnimate, "translationY", 0f, 1000f);
                objectAnimator.setDuration(2000);
                objectAnimator.setInterpolator(new BounceInterpolator());
                objectAnimator.start();
                break;
            //endregion
        }
    }

    public void onTextViewClicked(View view) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    //view animation

    //drawable animation
}
