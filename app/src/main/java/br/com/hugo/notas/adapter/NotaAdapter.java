package br.com.hugo.notas.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.hugo.notas.R;
import br.com.hugo.notas.model.Nota;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.MyViewHolder> {

    private List<Nota> lista;

    public NotaAdapter(List<Nota> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_nota_item, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Nota nota = lista.get(position);
        holder.txtTitulo.setText(nota.getTitulo());
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitulo;

        public MyViewHolder(View itemView){
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.txtTituloId);
        }
    }
}
