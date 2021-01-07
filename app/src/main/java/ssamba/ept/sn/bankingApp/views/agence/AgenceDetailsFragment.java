package ssamba.ept.sn.bankingApp.views.agence;

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
import ssamba.ept.sn.bankingApp.model.Agence;
import ssamba.ept.sn.bankingApp.service.AgenceService;
import ssamba.ept.sn.bankingApp.service.config.APIUtils;
import ssamba.ept.sn.bankingApp.views.utils.NestedScreenFragment;


public class AgenceDetailsFragment extends NestedScreenFragment {

    AgenceService agenceService;
    EditText edtAgId;
    EditText edtAgName;
    Button btnSave;
    Button btnDel;
    TextView txtAgId;
    TextView edtAgAddress;
    TextView edtAgPhone;

    final String TAG = this.getClass().getName();
    private  Boolean isUpdating;
    private Agence agence ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        agenceService = APIUtils.getAgenceService();
        agence = new Agence();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agence_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setTitle("Agences");
        isUpdating = !(getArguments()==null) ;
       // view = getView();
        txtAgId = (TextView) getView().findViewById(R.id.txtAgId);
        edtAgId = (EditText) getView().findViewById(R.id.edtAgId);
        edtAgName = (EditText) getView().findViewById(R.id.edtAgName);
        edtAgAddress = (EditText) getView().findViewById(R.id.edtAgAddress);
        edtAgPhone =  (EditText) getView().findViewById(R.id.edtTxtAgPhone);
        btnSave = (Button) getView().findViewById(R.id.btnSave);
        btnDel = (Button) getView().findViewById(R.id.btnDel);

        Log.d(TAG, "onViewCreated: "+getArguments());

        if(isUpdating){
            agence = new Gson().fromJson( getArguments().getString("agence"), Agence.class);
            edtAgId.setText(agence.getId()+"");
            edtAgName.setText(agence.getNom());
            edtAgAddress.setText(agence.getAdresse());
            edtAgPhone.setText(agence.getTelephone());
        }  else{
            edtAgId.setFocusable(false);
            txtAgId.setVisibility(View.INVISIBLE);
            edtAgId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(v -> {
            if (!validateForm()) return;
            if(isUpdating)
                updateAgence(agence);
            else
                addAgence(agence);

        });

        btnDel.setOnClickListener(v -> {
            deleteAgence(agence.getId());
            Navigation.findNavController(v).popBackStack();
        });
    }


        public Boolean validateForm(){
            if(edtAgName.getText().length()!=0 && edtAgPhone.getText().length()!=0 && edtAgName.getText().length()!=0 ){
                agence.setAdresse(edtAgName.getText().toString());
                agence.setTelephone(edtAgPhone.getText().toString());
                agence.setNom(edtAgName.getText().toString());
                return true;
            } else  {
                Toast.makeText(getContext(), "ERREUR ! L'UNE DES ENTRÉES EST VIDE", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

    public void addAgence(Agence agence){
        Call<Agence> call = agenceService.addAgence(agence);
        call.enqueue(new Callback<Agence>() {
            @Override
            public void onResponse(Call<Agence> call, Response<Agence> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Agence créée avec succès!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Agence> call, Throwable t) {
                
               if(getContext()!=null)
                    Toast.makeText(getContext(), "Une ERREUR s'est produite!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateAgence(Agence agence){
        Log.d(TAG, "updateAgence: "+agence);
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
                
               if(getContext()!=null)
                    Toast.makeText(getContext(), "Une ERREUR s'est produite!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    public void deleteAgence(int id){
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
                
               if(getContext()!=null)
                    Toast.makeText(getContext(), "Une ERREUR s'est produite!", Toast.LENGTH_SHORT).show();
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
