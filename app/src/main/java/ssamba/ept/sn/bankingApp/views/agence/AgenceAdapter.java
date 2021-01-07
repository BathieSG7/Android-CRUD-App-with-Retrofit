package ssamba.ept.sn.bankingApp.views.agence;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import ssamba.ept.sn.bankingApp.R;
import ssamba.ept.sn.bankingApp.model.Agence;


public class AgenceAdapter extends ArrayAdapter<Agence> {

    private Context context;
    private List<Agence> agences;
    final String TAG = this.getClass().getName();

    public AgenceAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Agence> objects) {
        super(context, resource, objects);
        this.context = context;
        this.agences = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_agence, parent, false);

        ((TextView) rowView.findViewById(R.id.txtAgenceCode)).setText("#CODE: "+ agences.get(pos).getId());
        ((TextView) rowView.findViewById(R.id.txtAgenceName)).setText("Nom: "+ agences.get(pos).getNom());
        ((TextView) rowView.findViewById(R.id.txtAgenceAddress)).setText("Adresse: "+ agences.get(pos).getAdresse());
        ((TextView) rowView.findViewById(R.id.txtAgencePhone)).setText("Téléphone: "+ agences.get(pos).getTelephone());


        rowView.setOnClickListener(v -> {
            //Navigate to Agence Form
            Bundle infoAgence = new Bundle();
            infoAgence.putString("agence", new Gson().toJson(agences.get(pos)));
            Navigation.findNavController(v).navigate(R.id.agenceDetailsFragment,infoAgence);
        });

        return rowView;
    }
}