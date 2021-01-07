package ssamba.ept.sn.bankingApp.views.compte;

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
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ssamba.ept.sn.bankingApp.R;
import ssamba.ept.sn.bankingApp.model.Compte;
import ssamba.ept.sn.bankingApp.service.CompteService;
import ssamba.ept.sn.bankingApp.service.config.APIUtils;
import ssamba.ept.sn.bankingApp.views.utils.NestedScreenFragment;

public class CompteDetailsFragment extends NestedScreenFragment {

    CompteService compteService;
    EditText edtCptId;
    EditText edtCptDecouvert;
    EditText edtCptAgence;
    EditText edtCptSolde;
    EditText edtCptClient;
    Button btnSave;
    Button btnDel;
    TextView txtCptId;




    final String TAG = this.getClass().getName();
    private Boolean isUpdating;
    private Compte compte;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        compteService = APIUtils.getCompteService();
        compte = new Compte();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compte_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // setTitle("Comptes");
        isUpdating = !(getArguments() == null);
        // view = getView();
        txtCptId = getView().findViewById(R.id.txtCptId);



        edtCptId = getView().findViewById(R.id.edtCptId);
        edtCptDecouvert =  getView().findViewById(R.id.edtCptDecouvert);
        edtCptSolde = getView().findViewById(R.id.edtCptSolde);
        edtCptClient = getView().findViewById(R.id.edtTxtCptClient);
        edtCptAgence =  getView().findViewById(R.id.edtTxtCptAgence);

        btnSave = getView().findViewById(R.id.btnSave);
        btnDel =  getView().findViewById(R.id.btnDel);

        Log.d(TAG, "onViewCreated: " + getArguments());

        if (isUpdating) {
            compte = new Gson().fromJson(getArguments().getString("compte"), Compte.class);
            edtCptId.setText(compte.getId() + "");
            edtCptDecouvert.setText(compte.getDecouvert() + "");
            edtCptSolde.setText(compte.getSolde() + "");
            edtCptClient.setText(compte.getClient()+"");
            edtCptAgence.setText(compte.getAgence()+"");
            edtCptAgence.setFocusable(false);
            edtCptClient.setFocusable(false);
            edtCptId.setFocusable(false);
        } else {
            edtCptId.setFocusable(false);
            txtCptId.setVisibility(View.INVISIBLE);
            edtCptId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(v -> {
            if (!validateForm())
                return;
            if (isUpdating)
                updateCompte(compte);
            else
                addCompte(compte);

        });

        btnDel.setOnClickListener(v -> {
            deleteCompte(compte.getId());
            Navigation.findNavController(v).popBackStack();
        });
    }

    public Boolean validateForm() {
        if (edtCptDecouvert.getText().length() != 0 && edtCptClient.getText().length() != 0
                && edtCptDecouvert.getText().length() != 0 && edtCptAgence.getText().length()!=0) {
            compte.setSolde(Double.parseDouble(edtCptSolde.getText().toString()));
            compte.setClient(Double.parseDouble(edtCptClient.getText().toString()));
            compte.setDecouvert(Double.parseDouble(edtCptDecouvert.getText().toString()));
            compte.setAgence(Double.parseDouble((edtCptAgence.getText().toString())));
            return true;
        } else {
            Toast.makeText(getContext(), "ERREUR ! L'UNE DES ENTRÉES EST VIDE", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void addCompte(Compte compte) {
        Call<Compte> call = compteService.addCompte(compte);
        Log.d(TAG, "addCompte: "+compte);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Compte créée avec succès!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(), "ERREUR SERVEUR! CODE:"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Compte> call, Throwable t) {
               if(getContext()!=null)
                    Toast.makeText(getContext(), "Une ERREUR s'est produite!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateCompte(Compte compte) {
        Log.d(TAG, "updateCompte: " + compte);
        Call<Compte> call = compteService.updateCompte(compte.getId(), compte);

        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Compte mis à jour avec succès!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Compte> call, Throwable t) {
                
               if(getContext()!=null)
                    Toast.makeText(getContext(), "Une ERREUR s'est produite!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    public void deleteCompte(int id) {
        Call<Compte> call = compteService.deleteCompte(id);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Compte Supprimé avec succès!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Compte> call, Throwable t) {

               if(getContext()!=null)
                    Toast.makeText(getContext(), "Une ERREUR s'est produite!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /*
     * @Override public boolean onOptionsItemSelected(MenuItem item) { switch
     * (item.getItemId()) { case android.R.id.home: finish(); return true; }
     * 
     * return super.onOptionsItemSelected(item); }
     */
}
