package com.example.git_set_code.adapters;

// Importing necessary packages

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

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

/**
 * This activity class displays the summary of the source
 */
public class SourceSummaryAdapter extends RecyclerView.Adapter<SourceSummaryAdapter.ViewHolder> {

    private boolean expanded;
    List<SourceInformation> sourceInformationList;
    Context context;

    /**
     *  Constructor for the SourceSummary Adapter
     * @param context the Context Object
     * @param sourceInformationList the list of SourceInformation objects
     */
    public SourceSummaryAdapter(Context context, List<SourceInformation> sourceInformationList) {
        this.sourceInformationList = sourceInformationList;
        this.context = context;
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }

    public void setSourceInformationList(List<SourceInformation> sourceInformationList) {
        this.sourceInformationList = sourceInformationList;
    }

    /**
     * This method creates a new ViewHolder of the site_item layout whenever the RecyclerView needs it.
     * @param parent a ViewGroup object
     * @param viewType an int value
     * @return returns a SourceSummaryAdapter.ViewHolder object
     */
    @NonNull
    @Override
    public SourceSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method updates the RecyclerView.ViewHolder contents with the item at the given position
     * @param holder a SiteSummaryAdapter.ViewHolder object
     * @param position the position of the item
     */
    @Override
    public void onBindViewHolder(@NonNull SourceSummaryAdapter.ViewHolder holder, int position) {
        holder.getExpandableSummaryLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.getWayPointType().setText("Source: " + sourceInformationList.get(position).getDestinationCod());
        holder.getVendorName().setText(sourceInformationList.get(position).getDestinationCod());
        holder.getTerminalName().setText(sourceInformationList.get(position).getDestinationName());
        holder.getTerminalAddress().setText(sourceInformationList.get(position).getAddress1().trim() + ", " + sourceInformationList.get(position).getCity().trim() + " " + sourceInformationList.get(position).getStateAbbrev().trim());
        onSourceSummaryButtonClicked(holder.getFormButton(), sourceInformationList.get(position).getWaypointTypeDescription());
    }

    /**
     * This method listens to the click on the Source Summary Button and changes the view to the new view
     * @param sourceButton a NeumorphButton object
     * @param wayPointType a wayPointType object
     */
    private void onSourceSummaryButtonClicked(NeumorphButton sourceButton, String wayPointType) {
        sourceButton.setOnClickListener(new View.OnClickListener() {

            /**
             *This method overrides the onClick method and swaps the fragment to the new view
             * @param v, the View object
             */
            @Override
            public void onClick(View v) {
                swapFragment(v);
            }
        });
    }

    /**
     * This method swaps the current fragment to the new view
     * @param v, the View object
     */
    private void swapFragment(View v) {
        Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySource);

    }


    /**
     * Getter for the count of the items
     * @return an int value representing the size of the list of the source Information objects
     */
    @Override
    public int getItemCount() {
        return sourceInformationList.size();
    }

    /**
     * The ViewHolder class extends the RecyclerView.ViewHolder and caches views associated with the default preference layouts
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView vendorName, terminalName, terminalAddress, wayPointType;
        private final ConstraintLayout expandable_summary_layout;
        private final NeumorphCardView cardView;
        private final NeumorphButton formButton;

        /**
         * Constructor for the ViewHolder class
         * @param itemView, a View object
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wayPointType = itemView.findViewById(R.id.trip_summary_title);
            expandable_summary_layout = itemView.findViewById(R.id.expandable_layout_summary);
            cardView = itemView.findViewById(R.id.source_card);
            vendorName = itemView.findViewById(R.id.tv_vendor_name);
            terminalName = itemView.findViewById(R.id.tv_terminal_name);
            terminalAddress = itemView.findViewById(R.id.tv_terminal_address);
            formButton = itemView.findViewById(R.id.enter_information_source);
            expandOnClick(cardView);
            if (expanded) cardView.setShapeType(1);
            else cardView.setShapeType(0);

        }

        /**
         * This method listens to the click and expands the cardView when clicked
         * @param cardView, a NeumorphCardView object
         */
        private void expandOnClick(NeumorphCardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        /**
         * Getter for expandableSummaryLayout
         * @return expandable_summary_layout, a ConstraintLayout object
         */
        public ConstraintLayout getExpandableSummaryLayout() {
            return expandable_summary_layout;
        }

        /**
         * Getter for wayPointType
         * @return wayPointType, a TextView object
         */
        public TextView getWayPointType() {
            return wayPointType;
        }

        /**
         * Getter for vendorName
         * @return vendorName, a TextView object
         */
        public TextView getVendorName() {
            return vendorName;
        }

        /**
         *Getter for terminalName
         * @return terminalName, a TextView Object
         */
        public TextView getTerminalName() {
            return terminalName;
        }

        /**
         * Getter for terminalAddress
         * @return terminalAddress, a TextView object
         */
        public TextView getTerminalAddress() {
            return terminalAddress;
        }

        /**
         * Getter for FormButton
         * @return formButton, a NeumorphButton object
         */
        public NeumorphButton getFormButton() {
            return formButton;
        }


    }
}
