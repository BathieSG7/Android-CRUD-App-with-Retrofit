package ssamba.ept.sn.bankingApp.service;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ssamba.ept.sn.bankingApp.model.Agence;


public interface AgenceService {

    @GET("agence/")
    Call<List<Agence>> getAgences();

    @POST("agence/")
    Call<Agence> addAgence(@Body Agence user);

    @PUT("agence/{code}")
    Call<Agence> updateAgence(@Path("code") int code, @Body Agence agence);

    @DELETE("agence/{code}")
    Call<Agence> deleteAgence(@Path("code") int code);
}