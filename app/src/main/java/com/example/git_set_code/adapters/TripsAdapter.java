package com.example.git_set_code.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<TripsData> tripsDataList;

    public TripsAdapter(Context context, List<TripsData> tripsData){
        this.inflater = LayoutInflater.from(context);
        this.tripsDataList = tripsData;

    }
    @NonNull
    @Override
    public TripsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.trip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsAdapter.ViewHolder holder, int position) {
        holder.productName.setText(tripsDataList.get(position).getProductDesc());
        holder.vendorName.setText(tripsDataList.get(position).getDestinationName());
        holder.terminalName.setText(tripsDataList.get(position).getDestinationName());
        holder.terminalAddress.setText(tripsDataList.get(position).getAddress1()+", "+tripsDataList.get(position).getCity()+" "+tripsDataList.get(position).getStateAbbrev());
        holder.specialInstructions.setText("NONE");
        holder.quantities.setText(tripsDataList.get(position).getRequestedQty());
        holder.stops.setText(tripsDataList.get(position).getProductDesc());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productName, vendorName, terminalName, terminalAddress, specialInstructions, quantities, stops;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.tv_product_name);
            vendorName = itemView.findViewById(R.id.tv_vendor_name);
            terminalName = itemView.findViewById(R.id.tv_terminal_name);
            terminalAddress = itemView.findViewById(R.id.tv_terminal_address);
            specialInstructions = itemView.findViewById(R.id.tv_special_instructions);
            quantities = itemView.findViewById(R.id.tv_quantities);
            stops = itemView.findViewById(R.id.tv_stops);

        }
    }
}
