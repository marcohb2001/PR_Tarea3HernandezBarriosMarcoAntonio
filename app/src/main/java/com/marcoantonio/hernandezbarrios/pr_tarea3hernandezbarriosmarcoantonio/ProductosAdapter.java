package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptador para mostrar la lista de productos dentro del RecyclerView.
 * Encargado de:
 *  - Inflar el diseño de cada fila (item_producto.xml)
 *  - Asignar los datos del producto a cada vista
 *  - Gestionar el evento click sobre un producto
 *
 * Utilizado en la pantalla ListadoProductos.
 */
public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    /**
     * Interfaz para gestionar los clics realizados sobre un elemento de la lista.
     */
    public interface OnProductoClickListener {
        void onClick(Producto p);
    }

    private final List<Producto> lista;
    private final OnProductoClickListener listener;

    /**
     * Constructor del adaptador.
     *
     * @param lista    lista de productos a mostrar
     * @param listener callback que se ejecuta al pulsar un elemento
     */
    public ProductosAdapter(List<Producto> lista, OnProductoClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    /**
     * ViewHolder que almacena las vistas necesarias para cada elemento de la lista.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEstado;
        TextView txtNombre;
        TextView txtPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEstado = itemView.findViewById(R.id.imgEstado);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Producto p = lista.get(position);

        h.imgEstado.setImageResource(
                p.isDisponible() ? R.drawable.icono_verde : R.drawable.icono_rojo
        );

        h.txtNombre.setText(p.getNombre());
        h.txtPrecio.setText(String.format("%.2f €", p.getPrecio()));

        h.itemView.setOnClickListener(v -> listener.onClick(p));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}


