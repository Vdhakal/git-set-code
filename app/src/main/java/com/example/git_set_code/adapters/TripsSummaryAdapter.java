package com.example.git_set_code.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;
import com.example.git_set_code.viewmodels.TripsData;

import java.util.List;

public class TripsSummaryAdapter extends RecyclerView.Adapter<TripsSummaryAdapter.ViewHolder> {

    List<TripsData> tripsDataList;

    public TripsSummaryAdapter(Context context, List<TripsData> tripsData){
        this.tripsDataList = tripsData;
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }
    @NonNull
    @Override
    public TripsSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsSummaryAdapter.ViewHolder holder, int position) {
        holder.getWayPointType().setText(tripsDataList.get(position).getWaypointTypeDescription()+" "+position);
        holder.getProductName().setText(tripsDataList.get(position).getProductDesc());
        holder.getVendorName().setText(tripsDataList.get(position).getDestinationCod());
        holder.getTerminalName().setText(tripsDataList.get(position).getDestinationName());
        holder.getTerminalAddress().setText(tripsDataList.get(position).getAddress1().trim()+", "+tripsDataList.get(position).getCity().trim()+" "+tripsDataList.get(position).getStateAbbrev().trim());
        holder.getSpecialInstructions().setText("NONE");
        holder.getQuantities().setText(String.valueOf(tripsDataList.get(position).getRequestedQty()));
        holder.getStops().setText(String.valueOf(tripsDataList.size()));
    }

    @Override
    public int getItemCount() {
        return tripsDataList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView productName, vendorName, terminalName, terminalAddress, specialInstructions, quantities, stops, wayPointType;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wayPointType = itemView.findViewById(R.id.title);
            productName = itemView.findViewById(R.id.tv_product_name);
            vendorName = itemView.findViewById(R.id.tv_vendor_name);
            terminalName = itemView.findViewById(R.id.tv_terminal_name);
            terminalAddress = itemView.findViewById(R.id.tv_terminal_address);
            specialInstructions = itemView.findViewById(R.id.tv_special_instructions);
            quantities = itemView.findViewById(R.id.tv_quantities);
            stops = itemView.findViewById(R.id.tv_stops);

        }
        public TextView getWayPointType() {
            return wayPointType;
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
