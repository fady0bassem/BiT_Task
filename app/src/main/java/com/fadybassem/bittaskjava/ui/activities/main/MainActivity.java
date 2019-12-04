package com.fadybassem.bittaskjava.ui.activities.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fadybassem.bittaskjava.R;
import com.fadybassem.bittaskjava.data.model.pojo.HomeModel;
import com.fadybassem.bittaskjava.data.model.pojo.ProfileModel;
import com.fadybassem.bittaskjava.data.remote.ApiClient;
import com.fadybassem.bittaskjava.data.remote.ApiInterface;
import com.fadybassem.bittaskjava.ui.activities.base.ActivityBase;
import com.fadybassem.bittaskjava.ui.adapters.HomeAdapter;
import com.fadybassem.bittaskjava.ui.dialogues.CustomAlertDialog;
import com.fadybassem.bittaskjava.ui.dialogues.CustomProgressDialog;
import com.fadybassem.bittaskjava.utils.ErrorCodes;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ActivityBase {

    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.name_textview)
    TextView name_textview;
    @BindView(R.id.location_textview)
    TextView location_textview;
    @BindView(R.id.bio_textview)
    TextView bio_textview;
    @BindView(R.id.posts_textview)
    TextView posts_textview;
    @BindView(R.id.followers_textview)
    TextView followers_textview;
    @BindView(R.id.following_textview)
    TextView following_textview;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private Context context = this;

    private ApiInterface apiInterface;
    private Dialog dialog;

    private HomeAdapter adapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        load_profile();
    }

    private void load_profile() {
        dialog = CustomProgressDialog.loadingIndicatorView(context, true);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileModel> call = apiInterface.PROFILE_MODEL_CALL();
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    boolean status = response.body().getStatus();

                    if (status) {
                        setData(response.body());
                        load_home();
                    } else {
                        CustomAlertDialog.getInstance().showInfoDialog(getString(R.string.error), getString(R.string.error_try_again), getString(R.string.close), context, 0);
                    }

                } else {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_RESPONSE_ERROR, 0);
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                dialog.dismiss();
                if (t instanceof IOException) {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_NETWORK_FAILURE, 0);
                } else {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_CONVERSION_ISSUE, 0);
                }
            }
        });
    }

    private void load_home() {
        dialog = CustomProgressDialog.loadingIndicatorView(context, true);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HomeModel> call = apiInterface.HOME_MODEL_CALL();
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    boolean status = response.body().getStatus();

                    if (status) {
                        set_recyclerview(response.body());
                    } else {
                        CustomAlertDialog.getInstance().showInfoDialog(getString(R.string.error), getString(R.string.error_try_again), getString(R.string.close), context, 0);
                    }

                } else {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_RESPONSE_ERROR, 0);
                }
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                dialog.dismiss();
                if (t instanceof IOException) {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_NETWORK_FAILURE, 0);
                } else {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_CONVERSION_ISSUE, 0);
                }
            }
        });
    }

    private void setData(ProfileModel body) {
        if (body != null) {
            if (body.getData() != null) {
                if (body.getData().getFullName() != null)
                    name_textview.setText(body.getData().getFullName());

                if (body.getData().getLocation() != null)
                    location_textview.setText(body.getData().getLocation());

                if (body.getData().getBio() != null)
                    bio_textview.setText(body.getData().getBio());

                if (body.getData().getProfilePicture() != null) {
                    Picasso.get().load(body.getData().getProfilePicture())
                            .error(android.R.drawable.ic_menu_report_image)
                            .into(imageview);
                }

                if (body.getData().getCounts() != null) {
                    if (body.getData().getCounts().getPosts() != null)
                        posts_textview.setText(String.valueOf(body.getData().getCounts().getPosts()));

                    if (body.getData().getCounts().getFollowers() != null)
                        followers_textview.setText(String.valueOf(body.getData().getCounts().getFollowers()));

                    if (body.getData().getCounts().getFollowing() != null)
                        following_textview.setText(String.valueOf(body.getData().getCounts().getFollowing()));
                }
            }
        }
    }

    private void set_recyclerview(HomeModel body) {
//        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,
//                LinearLayoutManager.VERTICAL);
//        recyclerview.addItemDecoration(dividerItemDecoration);


        if (adapter == null) {
            adapter = new HomeAdapter(context, body.getData());
            recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerview.setAdapter(adapter);
        }
    }
}
