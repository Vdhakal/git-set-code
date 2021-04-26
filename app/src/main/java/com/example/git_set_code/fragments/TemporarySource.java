package com.example.git_set_code.fragments;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.git_set_code.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TemporarySource#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TemporarySource extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText preDelivery, postDelivery, droppedProduct, startDate, startTime, endDate, endTime, grossGallons, netGallons, remainingFuel;
    public TemporarySource() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TemporarySource.
     */
    // TODO: Rename and change types and number of parameters
    public static TemporarySource newInstance(String param1, String param2) {
        TemporarySource fragment = new TemporarySource();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_source_info, container, false);
        Button sourceButton = rootView.findViewById(R.id.save_cont_source);
        initItems(rootView);
        onSourceButtonClicked(sourceButton);

        return rootView;
    }

    private void initItems(View rootView) {
        preDelivery = rootView.findViewById(R.id.pre_delivery);
        postDelivery = rootView.findViewById(R.id.post_delivery);
        droppedProduct = rootView.findViewById(R.id.product_dropped);
        startDate = rootView.findViewById(R.id.start_date);
        startTime = rootView.findViewById(R.id.start_time);
        endDate = rootView.findViewById(R.id.drop_end_date);
        endTime = rootView.findViewById(R.id.drop_end_time);
        grossGallons = rootView.findViewById(R.id.gross_gallons);
        netGallons = rootView.findViewById(R.id.net_gallons);
        remainingFuel = rootView.findViewById(R.id.remaining_fuel);

    }

    private void onSourceButtonClicked(Button sourceButton) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v);
                Toast.makeText(getContext(), "{" +
                        "\nPre delivery: "+preDelivery.getText()+
                        "\nPost delivery: "+postDelivery.getText()+
                        "\nProduct Dropped: "+droppedProduct.getText()+
                        "\nStart Date: "+startDate.getText()+
                        "\nStart Time: "+startTime.getText()+
                        "\nEnd Date: "+endDate.getText()+
                        "\nEnd Time: "+endTime.getText()+
                        "\nGross Gallons: "+grossGallons.getText()+
                        "\nNet Gallons: "+netGallons.getText()+
                        "\nRemaining Fuel: "+remainingFuel.getText()+"\n}", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void swapFragment(View v){
        Navigation.findNavController(v).navigate(R.id.action_temporarySource_to_tripSummary);
    }
}