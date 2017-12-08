package com.plug.mod2class7.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plug.mod2class7.R;
import com.plug.mod2class7.modelos.Direccion;
import com.plug.mod2class7.modelos.Usuario;

import java.util.ArrayList;

/**
 * Created by OSKAR on 26/07/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Usuario> listaUsuario;

    public UserAdapter(Context context, ArrayList<Usuario> listaUsuario) {
        this.context = context;
        this.listaUsuario = listaUsuario;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre;
        TextView tvUsuario;
        TextView tvCorreo;
        TextView tvDireccion;
        TextView tvCompania;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNombre=(TextView)itemView.findViewById(R.id.tvNombre);
            tvUsuario=(TextView)itemView.findViewById(R.id.tvUsuario);
            tvCorreo=(TextView)itemView.findViewById(R.id.tvCorreo);
            tvDireccion=(TextView)itemView.findViewById(R.id.tvDireccion);
            tvCompania=(TextView)itemView.findViewById(R.id.tvCompania);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder=(MyViewHolder) holder;
        if(viewHolder!=null){
            Usuario usuario=listaUsuario.get(position);
            viewHolder.tvNombre.setText(usuario.getName());
            viewHolder.tvUsuario.setText(usuario.getUsername());
            viewHolder.tvCorreo.setText(usuario.getEmail());
            viewHolder.tvDireccion.setText(usuario.getAddress().getCity());
            viewHolder.tvCompania.setText(usuario.getCompany().getName());
        }


    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }
}
