package ssamba.ept.sn.bankingApp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ssamba.ept.sn.bankingApp.model.Client;
import ssamba.ept.sn.bankingApp.service.ClientService;
import ssamba.ept.sn.bankingApp.service.config.APIUtils;


public class ClientActivity extends AppCompatActivity {

    ClientService clientService;
    EditText edtUId;
    EditText edtClientname;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        setTitle("Clients");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtClientname = (EditText) findViewById(R.id.edtClientname);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        clientService = APIUtils.getClientService();

        Bundle extras = getIntent().getExtras();
        final String clientId = extras.getString("client_id");
        String clientNom = extras.getString("client_name");

        edtUId.setText(clientId);
        edtClientname.setText(clientNom);

        if(clientId != null && clientId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client u = new Client();
                u.setNom(edtClientname.getText().toString());
                if(clientId != null && clientId.trim().length() > 0){
                    //update client
                    updateClient(Integer.parseInt(clientId), u);
                } else {
                    //add client
                    addClient(u);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteClient(Integer.parseInt(clientId));

                Intent intent = new Intent(ClientActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addClient(Client clt){
        Call<Client> call = clientService.addClient(clt);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ClientActivity.this, "Client created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateClient(int id, Client u){
        Call<Client> call = clientService.updateClient(id, u);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ClientActivity.this, "Client updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteClient(int id){
        Call<Client> call = clientService.deleteClient(id);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ClientActivity.this, "Client deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
