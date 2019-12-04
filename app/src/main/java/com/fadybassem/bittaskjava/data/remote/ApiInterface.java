package com.fadybassem.bittaskjava.data.remote;

import com.fadybassem.bittaskjava.data.model.pojo.HomeModel;
import com.fadybassem.bittaskjava.data.model.pojo.ProfileModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("profile")
    Call<ProfileModel> PROFILE_MODEL_CALL();

    @GET("home")
    Call<HomeModel> HOME_MODEL_CALL();
}
