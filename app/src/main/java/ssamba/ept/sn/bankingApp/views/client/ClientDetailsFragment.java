package ssamba.ept.sn.bankingApp.views.Client;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ssamba.ept.sn.bankingApp.MainActivity;
import ssamba.ept.sn.bankingApp.R;
import ssamba.ept.sn.bankingApp.model.Client;
import ssamba.ept.sn.bankingApp.service.ClientService;
import ssamba.ept.sn.bankingApp.service.config.APIUtils;
import ssamba.ept.sn.bankingApp.views.utils.NestedScreenFragment;


public class ClientDetailsFragment extends NestedScreenFragment {

    ClientService clientService;
    EditText edtUId;
    EditText edtClientname;
    Button btnSave;
    Button btnDel;
    TextView txtUId;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        clientService = APIUtils.getClientService();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setTitle("Clients");

       // view = getView();
        txtUId = (TextView) getView().findViewById(R.id.txtUId);
        edtUId = (EditText) getView().findViewById(R.id.edtUId);
        edtClientname = (EditText) getView().findViewById(R.id.edtClientname);
        btnSave = (Button) getView().findViewById(R.id.btnSave);
        btnDel = (Button) getView().findViewById(R.id.btnDel);

         Bundle bundle  = getArguments();
        //Bundle extras = getIntent().getExtras();
        final String clientId = bundle.getString("client_id");
        String clientNom = bundle.getString("client_name");

        edtUId.setText(clientId);
        edtClientname.setText(clientNom);

        if(clientId != null && clientId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(v -> {
            Client u = new Client();
            u.setNom(edtClientname.getText().toString());
            if(clientId != null && clientId.trim().length() > 0){
                //update client
                updateClient(Integer.parseInt(clientId), u);
            } else {
                //add client
                addClient(u);
            }
        });

        btnDel.setOnClickListener(v -> {
            deleteClient(Integer.parseInt(clientId));
            Navigation.findNavController(v).popBackStack();
        });
    }


    public void addClient(Client clt){
        Call<Client> call = clientService.addClient(clt);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Client created successfully!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "Client updated successfully!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "Client deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
