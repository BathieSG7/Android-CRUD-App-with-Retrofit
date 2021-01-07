package ssamba.ept.sn.bankingApp.service.config;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ssamba.ept.sn.bankingApp.model.Agence;
import ssamba.ept.sn.bankingApp.service.AgenceService;
import ssamba.ept.sn.bankingApp.service.ClientService;
import ssamba.ept.sn.bankingApp.service.CompteService;

public class APIUtils {


    private APIUtils(){
    };

    public static final String API_URL = "http://10.0.2.2:9090/";
    private static final Retrofit retrofit= RetrofitClient.getClient(API_URL);

    public static ClientService getClientService(){
        return retrofit.create(ClientService.class);
    }

    public static AgenceService getAgenceService(){
        return retrofit.create(AgenceService.class);
    }

    public static CompteService getCompteService(){
        return retrofit.create(CompteService.class);
    }


    /*
    public void add(Agence agence, Context context){
        Call<Agence> call = agenceService.addAgence(agence);
        call.enqueue(new Callback<Agence>() {
            @Override
            public void onResponse(Call<Agence> call, Response<Agence> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Agence créée avec succès!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Agence> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void update(Agence agence){
        Call<Agence> call = agenceService.updateAgence(agence.getId(), agence);

        call.enqueue(new Callback<Agence>() {
            @Override
            public void onResponse(Call<Agence> call, Response<Agence> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Agence mis à jour avec succès!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Agence> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    public void delete(int id){
        Call<Agence> call = agenceService.deleteAgence(id);
        call.enqueue(new Callback<Agence>() {
            @Override
            public void onResponse(Call<Agence> call, Response<Agence> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Agence Supprimé avec succès!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Agence> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
*/

}