package com.example.git_set_code.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;
import com.example.git_set_code.cache.TripsObject;
import com.example.git_set_code.helperClasses.SlidebarStateHolder;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.Truck;

import java.util.List;

public class SourceSummaryAdapter extends RecyclerView.Adapter<SourceSummaryAdapter.ViewHolder> {

    private boolean expanded;
    List<SourceInformation> sourceInformationList;
    Context context;
    public SourceSummaryAdapter(Context context, List<SourceInformation> sourceInformationList){
        this.sourceInformationList = sourceInformationList;
        this.context = context;
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }

    public void setSourceInformationList(List<SourceInformation> sourceInformationList) {
        this.sourceInformationList = sourceInformationList;
    }


    @NonNull
    @Override
    public SourceSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceSummaryAdapter.ViewHolder holder, int position) {
        holder.getExpandableSummaryLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.getWayPointType().setText("Source: "+sourceInformationList.get(position).getDestinationCod());
        holder.getVendorName().setText(sourceInformationList.get(position).getDestinationCod());
        holder.getTerminalName().setText(sourceInformationList.get(position).getDestinationName());
        holder.getTerminalAddress().setText(sourceInformationList.get(position).getAddress1().trim()+", "+sourceInformationList.get(position).getCity().trim()+" "+sourceInformationList.get(position).getStateAbbrev().trim());
        onSourceSummaryButtonClicked(holder.getFormButton(),sourceInformationList.get(position).getWaypointTypeDescription());
    }

    private void onSourceSummaryButtonClicked(Button sourceButton, String wayPointType) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v);
            }
        });
    }
    private void swapFragment(View v){
        Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySource);

    }
    @Override
    public int getItemCount() {
        return sourceInformationList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView  vendorName, terminalName, terminalAddress, wayPointType;
        private final ConstraintLayout expandable_summary_layout;
        private final CardView cardView;
        private final Button formButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wayPointType = itemView.findViewById(R.id.trip_summary_title);
            expandable_summary_layout = itemView.findViewById(R.id.expandable_layout_summary);
            cardView = itemView.findViewById(R.id.trip_summary_card_view);
            vendorName = itemView.findViewById(R.id.tv_vendor_name);
            terminalName = itemView.findViewById(R.id.tv_terminal_name);
            terminalAddress = itemView.findViewById(R.id.tv_terminal_address);
            formButton = itemView.findViewById(R.id.enter_information);
            expandOnClick(cardView);

        }

        private void expandOnClick(CardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        public ConstraintLayout getExpandableSummaryLayout() {
            return expandable_summary_layout;
        }

        public TextView getWayPointType() {
            return wayPointType;
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

        public Button getFormButton() {
            return formButton;
        }



    }
}
