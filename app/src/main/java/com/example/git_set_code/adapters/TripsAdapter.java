package com.example.git_set_code.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;
import com.example.git_set_code.viewmodels.TripsData;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {

    List<TripsData> tripsDataList;
     boolean expanded;
    public TripsAdapter(Context context, List<TripsData> tripsData){
        this.tripsDataList = tripsData;
        this.expanded = false;
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }
    @NonNull
    @Override
    public TripsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsAdapter.ViewHolder holder, int position) {
        holder.getProductName().setText(tripsDataList.get(1).getProductDesc());
        holder.getStops().setText(String.valueOf(tripsDataList.get(0).getStops()));
        holder.getExpandableLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView  productName, stops;
        private final CardView cardView;
        private final ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.trips_card_view);
            productName = itemView.findViewById(R.id.tv_product_name);
            stops = itemView.findViewById(R.id.tv_stops);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
        public TextView getProductName() { return productName; }

        public TextView getStops() {
            return stops;
        }

        public ConstraintLayout getExpandableLayout(){return expandableLayout;}


    }
}
