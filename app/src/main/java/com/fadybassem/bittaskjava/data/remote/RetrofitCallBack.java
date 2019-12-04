package com.fadybassem.bittaskjava.data.remote;

import android.app.ProgressDialog;
import android.content.Context;

import com.fadybassem.bittaskjava.ui.dialogues.CustomProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallBack<T> implements Callback<T> {

    private static final String TAG = "RetrofitCallback";
    private final Callback<T> mCallback;
    private Context context;
    private ProgressDialog Dialog;
    private CustomProgressDialog mProgress = new CustomProgressDialog();

    public RetrofitCallBack(Context context, Callback<T> callback, boolean isFirstPage, boolean isPaginationPage) {
        this.context = context;
        this.mCallback = callback;

        if (!isPaginationPage) {
            //  this.mProgress.setGravity(17);
        } else if (isFirstPage) {
            //  this.mProgress.setGravity(17);
        } else {
            //  this.mProgress.setGravity(80);
        }

        Dialog = CustomProgressDialog.showProgressDialog(context, "required");
    }

    public void onResponse(Call<T> call, Response<T> response) {
        mCallback.onResponse(call, response);
        Dialog.dismiss();
    }

    public void onFailure(Call<T> call, Throwable t) {
        mCallback.onFailure(call, t);
        Dialog.dismiss();
    }
}
