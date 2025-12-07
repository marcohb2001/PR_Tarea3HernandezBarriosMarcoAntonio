package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador utilizado en la pantalla EliminarProducto.
 * Permite seleccionar múltiples productos mediante un CheckBox.
 */
public class EliminarAdapter extends RecyclerView.Adapter<EliminarAdapter.ViewHolder> {

    private final List<Producto> lista;
    private final List<Producto> seleccionados = new ArrayList<>();

    public EliminarAdapter(List<Producto> lista) {
        this.lista = lista;
    }

    /**
     * @return lista de productos que el usuario ha marcado para eliminar.
     */
    public List<Producto> getSeleccionados() {
        return seleccionados;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox chkSeleccionar;
        TextView txtNombre, txtPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chkSeleccionar = itemView.findViewById(R.id.chkSeleccionar);
            txtNombre = itemView.findViewById(R.id.txtNombreDel);
            txtPrecio = itemView.findViewById(R.id.txtPrecioDel);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_eliminar_producto, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Producto p = lista.get(position);

        h.txtNombre.setText(p.getNombre());
        h.txtPrecio.setText(String.format("%.2f €", p.getPrecio()));

        // Estado del checkbox
        h.chkSeleccionar.setOnCheckedChangeListener(null);
        h.chkSeleccionar.setChecked(seleccionados.contains(p));

        h.chkSeleccionar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                seleccionados.add(p);
            } else {
                seleccionados.remove(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

