package ssamba.ept.sn.bankingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ssamba.ept.sn.bankingApp.model.Client;
import ssamba.ept.sn.bankingApp.service.ClientService;
import ssamba.ept.sn.bankingApp.service.config.APIUtils;


public class MainActivit extends AppCompatActivity {

    Button btnAddClient;
    Button btnGetClientsList;
    ListView listView;

    ClientService clientService;
    List<Client> list = new ArrayList<Client>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        setTitle("SAMA BANQUE CRUD App");

        btnAddClient = (Button) findViewById(R.id.btnAddClient);
        btnGetClientsList = (Button) findViewById(R.id.btnGetClientsList);
        listView = (ListView) findViewById(R.id.listView);
        clientService = APIUtils.getClientService();

        btnGetClientsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get clients list
                getClientsList();
            }
        });

        btnAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivit.this, ClientActivity.class);
                intent.putExtra("client_name", "");
                startActivity(intent);
            }
        });
    }

    public void getClientsList(){
        Call<List<Client>> call = clientService.getClients();
        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ClientAdapter(MainActivit.this, R.layout.list_client, list));
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
