package ro.ase.seminar03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.ase.seminar03.Product.Produs;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductVH> {
    private final List<Produs> items;

    public ProductAdapter(List<Produs> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH h, int position) {
        Produs p = items.get(position);

        h.tvMarca.setText(nullCheck(p.getMarca()));
        h.tvPret.setText("Pret: " + p.getPret());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    private String nullCheck(String s) {
        return s == null ? "-" : s;
    }

    static class ProductVH extends RecyclerView.ViewHolder {
        TextView tvMarca, tvPret;

        ProductVH(@NonNull View itemView) {
            super(itemView);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvPret = itemView.findViewById(R.id.tvPret);
        }
    }
}

