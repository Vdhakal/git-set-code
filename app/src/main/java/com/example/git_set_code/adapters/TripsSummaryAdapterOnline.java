package com.example.git_set_code.adapters;

//Importing necessary packages

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
import com.example.git_set_code.helperClasses.SlidebarStateHolder;
import com.example.git_set_code.viewmodels.TripsData;


import java.util.List;

/**
 *
 */
public class TripsSummaryAdapterOnline extends RecyclerView.Adapter<TripsSummaryAdapterOnline.ViewHolder> {

    private List<TripsData> tripsDataList;
    private boolean expanded;
    SlidebarStateHolder slidebarStateHolder;
    Context context;

    /**
     * @param context
     * @param tripsDataList
     */
    public TripsSummaryAdapterOnline(Context context, List<TripsData> tripsDataList) {
        this.tripsDataList = tripsDataList;
        this.context = context;

    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public TripsSummaryAdapterOnline.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull TripsSummaryAdapterOnline.ViewHolder holder, int position) {
        holder.getExpandableSummaryLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.getWayPointType().setText(tripsDataList.get(position).getWaypointTypeDescription() + " " + position);
        holder.getProductName().setText(tripsDataList.get(position).getProductDesc());
        holder.getVendorName().setText(tripsDataList.get(position).getDestinationCod());
        holder.getTerminalName().setText(tripsDataList.get(position).getDestinationName());
        holder.getTerminalAddress().setText(tripsDataList.get(position).getAddress1().trim() + ", " + tripsDataList.get(position).getCity().trim() + " " + tripsDataList.get(position).getStateAbbrev().trim());
        holder.getSpecialInstructions().setText("NONE");
        holder.getQuantities().setText(String.valueOf(tripsDataList.get(position).getRequestedQty()));
        onSourceSummaryButtonClicked(holder.getFormButton(), tripsDataList.get(position).getWaypointTypeDescription());
    }

    /**
     * @param sourceButton
     * @param wayPointType
     */
    private void onSourceSummaryButtonClicked(Button sourceButton, String wayPointType) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v, wayPointType);
            }
        });
    }

    /**
     * @param v
     * @param wayPointType
     */
    private void swapFragment(View v, String wayPointType) {
        if (wayPointType.equals("Source"))
            Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySource);
        if (wayPointType.trim().equals("Site Container"))
            Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySite);

    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return tripsDataList.size();
    }

    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName, vendorName, terminalName, terminalAddress, specialInstructions, quantities, wayPointType;
        private final ConstraintLayout expandable_summary_layout;
        private final CardView cardView;
        private final Button formButton;

        /**
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wayPointType = itemView.findViewById(R.id.trip_summary_title);
            expandable_summary_layout = itemView.findViewById(R.id.expandable_layout_summary);
            cardView = itemView.findViewById(R.id.trip_summary_card_view);
            productName = itemView.findViewById(R.id.tv_product_name);
            vendorName = itemView.findViewById(R.id.tv_vendor_name);
            terminalName = itemView.findViewById(R.id.tv_terminal_name);
            terminalAddress = itemView.findViewById(R.id.tv_terminal_address);

            specialInstructions = itemView.findViewById(R.id.tv_special_instructions);
            quantities = itemView.findViewById(R.id.tv_quantities);
            formButton = itemView.findViewById(R.id.enter_information);
            expandOnClick(cardView);

        }

        /**
         * @param cardView
         */
        private void expandOnClick(CardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {

                /**
                 *
                 * @param v
                 */
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        /**
         * @return
         */
        public ConstraintLayout getExpandableSummaryLayout() {
            return expandable_summary_layout;
        }


        /**
         * @return
         */
        public TextView getWayPointType() {
            return wayPointType;
        }

        /**
         * @return
         */
        public TextView getProductName() {
            return productName;
        }

        /**
         * @return
         */
        public TextView getVendorName() {
            return vendorName;
        }

        /**
         * @return
         */
        public TextView getTerminalName() {
            return terminalName;
        }

        /**
         * @return
         */
        public TextView getTerminalAddress() {
            return terminalAddress;
        }

        /**
         * @return
         */
        public Button getFormButton() {
            return formButton;
        }

        /**
         * @return
         */
        public TextView getSpecialInstructions() {
            return specialInstructions;
        }

        /**
         * @return
         */
        public TextView getQuantities() {
            return quantities;
        }
    }
}
