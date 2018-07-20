package com.example.user.acronymsapp.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.acronymsapp.model.APIResult;
import com.example.user.acronymsapp.model.Lf;

import java.util.List;

public class HandlerCallback implements Handler.Callback {
    public static final String TAG = HandlerCallback.class.getSimpleName() + "_TAG";
    Context context;
    RecyclerView rvDefinitions;

    public HandlerCallback(Context context, RecyclerView rvDefinitions) {
        this.context = context;
        this.rvDefinitions = rvDefinitions;
    }

    @Override
    public boolean handleMessage(Message message) {
        Log.d(TAG, "handleMessage: ");
        List<Lf> Definitions = HandlerUtils.getDefault().parseMessage(message);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvDefinitions.setLayoutManager(layoutManager);
        Log.d(TAG, "handleMessage: setlayoutManager");
        rvDefinitions.setAdapter(new RVDefinitionAdapter(Definitions, new RVDefinitionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Lf item) {

            }
        }));

        Log.d(TAG, "handleMessage: end");
        return false;
    }
}
