package ssamba.ept.sn.bankingApp.views.agence;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import ssamba.ept.sn.bankingApp.model.Agence;
import ssamba.ept.sn.bankingApp.service.AgenceService;
import ssamba.ept.sn.bankingApp.service.config.APIUtils;

public class AgenceFragment extends Fragment {

    Button btnAddAgence;
    Button btnGetAgencesList;
    ListView listView;

    AgenceService agenceService;
    List<Agence> list = new ArrayList<Agence>();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        agenceService = APIUtils.getAgenceService();
        return inflater.inflate(R.layout.fragment_agence, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddAgence = (Button) getView().findViewById(R.id.btnAddAgence);
        btnGetAgencesList = (Button) getView().findViewById(R.id.btnGetAgencesList);
        listView = (ListView) getView().findViewById(R.id.listView);

        //getAgencesList();

        btnGetAgencesList.setOnClickListener(v -> {
            getAgencesList(); //get agences list
        });

        btnAddAgence.setOnClickListener(v -> {
            //Bundle bundle = new Bundle();
            //bundle.putString("nom", "");
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_agenceDetailsFragment);
        });


    }

    public void getAgencesList(){
        Call<List<Agence>> call = agenceService.getAgences();
        call.enqueue(new Callback<List<Agence>>() {
            @Override
            public void onResponse(Call<List<Agence>> call, Response<List<Agence>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new AgenceAdapter(getContext(), R.layout.list_agence, list));
                }
            }

            @Override
            public void onFailure(Call<List<Agence>> call, Throwable t) {
                
               if(getContext()!=null)
                    Toast.makeText(getContext(), "Une ERREUR s'est produite!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
