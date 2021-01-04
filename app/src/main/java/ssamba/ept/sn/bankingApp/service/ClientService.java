package ssamba.ept.sn.bankingApp.service;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ssamba.ept.sn.bankingApp.model.Client;


public interface ClientService {

    @GET("client/")
    Call<List<Client>> getClients();

    @POST("client/")
    Call<Client> addClient(@Body Client client);

    @PUT("client/{id}")
    Call<Client> updateClient(@Path("id") int id, @Body Client client);

    @DELETE("client/{id}")
    Call<Client> deleteClient(@Path("id") int id);
}