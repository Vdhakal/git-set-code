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
 * This activity is responsible for displaying the summary of the trips
 */
public class TripsSummaryAdapterOnline extends RecyclerView.Adapter<TripsSummaryAdapterOnline.ViewHolder> {

    private List<TripsData> tripsDataList;
    private boolean expanded;
    SlidebarStateHolder slidebarStateHolder;
    Context context;

    /**
     * Constructor for the TripsSummaryAdapterOnline class
     *
     * @param context,       a Context object
     * @param tripsDataList, a list of TripsData objects
     */
    public TripsSummaryAdapterOnline(Context context, List<TripsData> tripsDataList) {
        this.tripsDataList = tripsDataList;
        this.context = context;

    }

    /**
     * This method creates a new ViewHolder of the site_item layout whenever the RecyclerView needs it.
     *
     * @param parent   a ViewGroup object
     * @param viewType an int value
     * @return SiteSummaryAdapter.ViewHolder, a view holder of the site summary adapter
     */
    @NonNull
    @Override
    public TripsSummaryAdapterOnline.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method updates the RecyclerView.ViewHolder contents with the item at the given position
     *
     * @param holder   a SiteSummaryAdapter.ViewHolder object
     * @param position the position of the item
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
     * This method listens to the click on the Source Summary Button and changes the view to the new view
     *
     * @param sourceButton a NeumorphButton object
     * @param wayPointType a wayPointType object
     */
    private void onSourceSummaryButtonClicked(Button sourceButton, String wayPointType) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method listens to the click and calls the swapFragment class
             * @param v, the View object
             */
            @Override
            public void onClick(View v) {
                swapFragment(v, wayPointType);
            }
        });
    }

    /**
     * This method swaps the fragment
     *
     * @param v,            a View object
     * @param wayPointType, a String object
     */
    private void swapFragment(View v, String wayPointType) {
        if (wayPointType.equals("Source"))
            Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySource);
        if (wayPointType.trim().equals("Site Container"))
            Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySite);

    }

    /**
     * Getter for item count
     *
     * @return an int representing the size of the list
     */
    @Override
    public int getItemCount() {
        return tripsDataList.size();
    }

    /**
     * The ViewHolder class caches views associated with the default Preference layouts
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName, vendorName, terminalName, terminalAddress, specialInstructions, quantities, wayPointType;
        private final ConstraintLayout expandable_summary_layout;
        private final CardView cardView;
        private final Button formButton;

        /**
         * Constructor for ViewHolder class
         *
         * @param itemView, a View object
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
         * This method listens to a click and expands the cardView
         *
         * @param cardView, a CardView object
         */
        private void expandOnClick(CardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {

                /**
                 *This method listens to the click and expands the new cardView
                 * @param v, the View object
                 */
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        /**
         * Getter for expandableSummaryLayout
         *
         * @return expandable_summary_layout, a ConstraintLayout
         */
        public ConstraintLayout getExpandableSummaryLayout() {
            return expandable_summary_layout;
        }


        /**
         * Getter for wayPointType
         *
         * @return wayPointType, a TextView object
         */
        public TextView getWayPointType() {
            return wayPointType;
        }

        /**
         * Getter for productName
         *
         * @return productName, a TextView object
         */
        public TextView getProductName() {
            return productName;
        }

        /**
         * Getter for vendorName
         *
         * @return vendorName, a TextView object
         */
        public TextView getVendorName() {
            return vendorName;
        }

        /**
         * Getter for terminalName
         *
         * @return terminalName, a TextView object
         */
        public TextView getTerminalName() {
            return terminalName;
        }

        /**
         * Getter for terminalAddress
         *
         * @return terminalAddress, a TextView object
         */
        public TextView getTerminalAddress() {
            return terminalAddress;
        }

        /**
         * Getter for formButton
         *
         * @return formButton, a Button
         */
        public Button getFormButton() {
            return formButton;
        }

        /**
         * Getter for specialInstructions
         *
         * @return specialInstructions, a TextView object
         */
        public TextView getSpecialInstructions() {
            return specialInstructions;
        }

        /**
         * Getter for quantities
         *
         * @return quantities, a TextView object
         */
        public TextView getQuantities() {
            return quantities;
        }
    }
}
