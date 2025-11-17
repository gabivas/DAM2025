package ro.ase.seminar03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodVH> {

    public interface OnItemClick {
        void onClick(Food item, int position);
    }

    private final List<Food> items;
    private final OnItemClick click;

    private final DecimalFormat money = new DecimalFormat("#,##0.00");
    private final SimpleDateFormat dateFmt = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

    public FoodAdapter(List<Food> items, OnItemClick click) {
        this.items = items;
        this.click = click;
    }

    @NonNull
    @Override
    public FoodVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new FoodVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodVH h, int position) {
        Food f = items.get(position);

        h.tvName.setText(nullCheck(f.getNume()));
        h.tvLivrator.setText("Livrator: " + nullCheck(f.getLivrator()));
        h.tvProduct.setText("Produs: " + getProduct(f.getProduse()));
        h.tvPriceQty.setText("Preț: " + money.format(f.getPret()) + " " + symbol(f.getValuta())
                + "  •  Cantitate: " + f.getCantitate());
        h.tvAddress.setText("Adresă: " + nullCheck(f.getAdresa()));
        h.tvDeliveryDate.setText("Livrare: " + (f.getDataLivrare() != null ? dateFmt.format(f.getDataLivrare()) : "-"));

        h.itemView.setOnClickListener(v -> {
            if (click != null) click.onClick(f, h.getBindingAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    private String symbol(Valuta v) {
        if (v == null) return "";
        switch (v) {
            case RON:
                return "RON";
            case EUR:
                return "€";
            default:
                return "";
        }
    }

    private String getProduct(Produse p) {
        if (p == null) return "-";
        switch (p) {
            case APA:
                return "Apă";
            case CARTOFI:
                return "Cartofi";
            case SUC:
                return "Suc";
            default:
                return p.name();
        }
    }

    private String nullCheck(String s) {
        return s == null ? "-" : s;
    }

    static class FoodVH extends RecyclerView.ViewHolder {
        TextView tvName, tvProduct, tvPriceQty, tvAddress, tvDeliveryDate, tvLivrator;

        FoodVH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            tvPriceQty = itemView.findViewById(R.id.tvPriceQty);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvDeliveryDate = itemView.findViewById(R.id.tvDeliveryDate);
            tvLivrator = itemView.findViewById(R.id.tvLivrator);
        }
    }
}

