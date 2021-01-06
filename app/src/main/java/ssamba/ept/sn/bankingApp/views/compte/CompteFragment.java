package ssamba.ept.sn.bankingApp.views.compte;

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
import ssamba.ept.sn.bankingApp.model.Compte;
import ssamba.ept.sn.bankingApp.service.CompteService;
import ssamba.ept.sn.bankingApp.service.config.APIUtils;

public class CompteFragment extends Fragment {

    Button btnAddCompte;
    Button btnGetComptesList;
    ListView listView;

    CompteService compteService;
    List<Compte> list = new ArrayList<Compte>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        compteService = APIUtils.getCompteService();
        return inflater.inflate(R.layout.fragment_compte, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddCompte = getView().findViewById(R.id.btnAddCompte);
        btnGetComptesList =  getView().findViewById(R.id.btnGetComptesList);
        listView =  getView().findViewById(R.id.listView);

        // getComptesList();

        btnGetComptesList.setOnClickListener(v -> {
            getComptesList(); // get comptes list
        });

        btnAddCompte.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_compteFragment_to_compteDetailsFragment);
        });

    }

    public void getComptesList() {
        Call<List<Compte>> call = compteService.getComptes();
        call.enqueue(new Callback<List<Compte>>() {
            @Override
            public void onResponse(Call<List<Compte>> call, Response<List<Compte>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    //Log.d("getComptesList", "onResponse: "+list);
                    listView.setAdapter(new CompteAdapter(getContext(), R.layout.list_compte, list));
                }
            }

            @Override
            public void onFailure(Call<List<Compte>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
