package ssamba.ept.sn.bankingApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.List;

import ssamba.ept.sn.bankingApp.model.Client;


public class ClientAdapter extends ArrayAdapter<Client> {

    private Context context;
    private List<Client> clients;

    public ClientAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Client> objects) {
        super(context, resource, objects);
        this.context = context;
        this.clients = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_client, parent, false);

        TextView txtClientId = (TextView) rowView.findViewById(R.id.txtClientId);
        TextView txtClientname = (TextView) rowView.findViewById(R.id.txtClientname);

        txtClientId.setText(String.format("#ID: %d", clients.get(pos).getId()));
        txtClientname.setText(String.format("CLIENT NAME: %s", clients.get(pos).getNom()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Client Form
                Intent intent = new Intent(context, ClientActivity.class);
                intent.putExtra("client_id", String.valueOf(clients.get(pos).getId()));
                intent.putExtra("client_name", clients.get(pos).getNom());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}