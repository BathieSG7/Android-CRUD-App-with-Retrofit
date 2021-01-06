package ssamba.ept.sn.bankingApp.views.compte;

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
import ssamba.ept.sn.bankingApp.model.Compte;

public class CompteAdapter extends ArrayAdapter<Compte> {

    private Context context;
    private List<Compte> comptes;
    final String TAG = this.getClass().getName();

    public CompteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Compte> objects) {
        super(context, resource, objects);
        this.context = context;
        this.comptes = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_compte, parent, false);

        ((TextView) rowView.findViewById(R.id.txtCompteCode)).setText("#CODE: " + comptes.get(pos).getId());
        ((TextView) rowView.findViewById(R.id.txtCompteDecouvert))
                .setText("Decouvert: " + comptes.get(pos).getDecouvert());
        ((TextView) rowView.findViewById(R.id.txtCompteSolde)).setText("Solde: " + comptes.get(pos).getSolde());
        ((TextView) rowView.findViewById(R.id.txtCompteClient))
                .setText("Client: " + comptes.get(pos).getClient());

        ((TextView) rowView.findViewById(R.id.txtCompteAgence))
                .setText("Agence: " + comptes.get(pos).getAgence());
        rowView.setOnClickListener(v -> {
            // Navigate to Compte Form
            Bundle infoCompte = new Bundle();
            infoCompte.putString("compte", new Gson().toJson(comptes.get(pos)));
            Navigation.findNavController(v).navigate(R.id.compteDetailsFragment, infoCompte);
        });

        return rowView;
    }
}