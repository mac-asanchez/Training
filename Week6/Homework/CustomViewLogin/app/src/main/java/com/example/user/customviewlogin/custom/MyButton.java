package com.example.user.customviewlogin.custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.customviewlogin.R;
import com.squareup.picasso.Picasso;

public class MyButton extends android.support.v7.widget.AppCompatButton {
    private static final String TAG = MyButton.class.getSimpleName() + "_TAG";
    String imageUrl;
    ImageView iv;

    public MyButton(Context context) {
        super(context);
        Log.d(TAG, "MyButton: ");
        init(context, null, 0, 0);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MyButton: ");
        init(context, attrs, 0, 0);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "MyButton: ");
        init(context, attrs, defStyleAttr, 0);
    }

    public void init(final Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        Log.d(TAG, "init: ");
        final LayoutInflater layoutInflater = LayoutInflater.from(context);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog settingsDialog = new Dialog(context);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                View v = layoutInflater.inflate(R.layout.image_dialog, null);
                iv = v.findViewById(R.id.ivMyImage);

                Picasso.get().load(imageUrl).into(iv);

                settingsDialog.setContentView(v);
                settingsDialog.show();
            }
        });
    }

    public void setImage(String imageUrl) {
        Log.d(TAG, "setImage: ");
        this.imageUrl = imageUrl;
    }
}
