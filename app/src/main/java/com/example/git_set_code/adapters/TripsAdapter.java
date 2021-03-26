package com.example.git_set_code.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {

    List<TripsData> tripsDataList;

    public TripsAdapter(Context context, List<TripsData> tripsData){
        this.tripsDataList = tripsData;
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }
    @NonNull
    @Override
    public TripsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsAdapter.ViewHolder holder, int position) {
        holder.getProductName().setText(tripsDataList.get(position).getProductDesc());
        holder.getVendorName().setText(tripsDataList.get(position).getDestinationName());
        holder.getTerminalName().setText(tripsDataList.get(position).getDestinationName());
        holder.getTerminalAddress().setText(tripsDataList.get(position).getAddress1()+", "+tripsDataList.get(position).getCity()+" "+tripsDataList.get(position).getStateAbbrev());
        holder.getSpecialInstructions().setText("NONE");
        holder.getQuantities().setText(tripsDataList.get(position).getRequestedQty());
        holder.getStops().setText(tripsDataList.get(position).getProductDesc());
    }

    @Override
    public int getItemCount() {
        return tripsDataList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView productName, vendorName, terminalName, terminalAddress, specialInstructions, quantities, stops;


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

        public TextView getProductName() {
            return productName;
        }
        public TextView getVendorName() {
            return vendorName;
        }

        public TextView getTerminalName() {
            return terminalName;
        }

        public TextView getTerminalAddress() {
            return terminalAddress;
        }

        public TextView getSpecialInstructions() {
            return specialInstructions;
        }

        public TextView getQuantities() {
            return quantities;
        }

        public TextView getStops() {
            return stops;
        }

    }
}
