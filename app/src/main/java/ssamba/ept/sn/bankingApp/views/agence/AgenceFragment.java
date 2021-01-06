package ssamba.ept.sn.bankingApp.views.Agence;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ssamba.ept.sn.bankingApp.R;
import ssamba.ept.sn.bankingApp.model.Client;
import ssamba.ept.sn.bankingApp.service.ClientService;
import ssamba.ept.sn.bankingApp.service.config.APIUtils;
import ssamba.ept.sn.bankingApp.views.Client.ClientAdapter;

public class AgenceFragment extends Fragment {

    Button btnAddClient;
    Button btnGetClientsList;
    ListView listView;

    ClientService clientService;
    List<Client> list = new ArrayList<Client>();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        clientService = APIUtils.getClientService();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddClient = (Button) getView().findViewById(R.id.btnAddClient);
        btnGetClientsList = (Button) getView().findViewById(R.id.btnGetClientsList);
        listView = (ListView) getView().findViewById(R.id.listView);

        //getClientsList();

        btnGetClientsList.setOnClickListener(v -> {
            getClientsList(); //get clients list
        });

        btnAddClient.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("client_name", "");
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_clientDetailsFragment,bundle);
        });
    }

    public void getClientsList(){
        Call<List<Client>> call = clientService.getClients();
        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ClientAdapter(getContext(), R.layout.list_client, list));
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
