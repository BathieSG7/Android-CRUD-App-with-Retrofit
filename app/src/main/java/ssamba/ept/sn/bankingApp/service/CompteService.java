package ssamba.ept.sn.bankingApp.service;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ssamba.ept.sn.bankingApp.model.Compte;


public interface CompteService {

    @GET("compte/")
    Call<List<Compte>> getCompte();

    @POST("compte/")
    Call<Compte> addCompte(@Body Compte user);

    @PUT("compte/{id}")
    Call<Compte> updateCompte(@Path("id") int id, @Body Compte compte);

    @DELETE("compte/{id}")
    Call<Compte> deleteCompte(@Path("id") int id);
}