package com.example.git_set_code.fragments;

import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.fragments.dialogs.SignatureDialog;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

import soup.neumorphism.NeumorphButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TemporarySite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TemporarySite extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText preDelivery, postDelivery, prodType, droppedProduct, startDate, startTime, endDate, endTime, grossGallons, netGallons, remainingFuel;
    int hour, minute;
    private Bitmap signatureBitmap = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TemporarySite() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TemporarySite.
     */
    // TODO: Rename and change types and number of parameters
    public static TemporarySite newInstance(String param1, String param2) {
        TemporarySite fragment = new TemporarySite();
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
        View rootView = inflater.inflate(R.layout.fragment_site_info, container, false);

        Button sourceButton = rootView.findViewById(R.id.save_cont_site);
        initItems(rootView);
        onSourceButtonClicked(sourceButton);
        initiateSignaturePad(rootView);
        dateListener();
        return  rootView;

    }
    private void initItems(View rootView) {
        preDelivery = rootView.findViewById(R.id.pre_delivery);
        postDelivery = rootView.findViewById(R.id.post_delivery);
        prodType = rootView.findViewById(R.id.product_dropped);
        startDate = rootView.findViewById(R.id.start_date);
        startTime = rootView.findViewById(R.id.start_time);
        endDate = rootView.findViewById(R.id.drop_end_date);
        endTime = rootView.findViewById(R.id.drop_end_time);
        grossGallons = rootView.findViewById(R.id.gross_gallons);
        netGallons = rootView.findViewById(R.id.net_gallons);
        remainingFuel = rootView.findViewById(R.id.remaining_fuel);

    }
    private void dateListener() {

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerInput(startDate);
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerInput(endDate);
            }
        });
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    timePickerInput(startTime);
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    timePickerInput(endTime);
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void timePickerInput(EditText inputTime) throws java.lang.InstantiationException, IllegalAccessException {
         MaterialTimePicker.Builder builder = MaterialTimePicker.Builder.class.newInstance().
                setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(Calendar.HOUR)
                .setMinute(Calendar.MINUTE)
                .setTitleText("Select Appointment time");
         final MaterialTimePicker materialTimePicker = builder.build();
        materialTimePicker.show(getParentFragmentManager(), "tag");
        materialTimePicker.addOnPositiveButtonClickListener(v -> {
            inputTime.setText(materialTimePicker.getHour() +" : "+materialTimePicker.getMinute());
        });
        inputTime.setTextColor(ContextCompat.getColor(getContext(),android.R.color.black));
    }

    private void datePickerInput(EditText inputDate) {
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker().setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        builder.setTitleText("SELECT A DATE");
        builder.setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar);
        final MaterialDatePicker materialDatePicker = builder.build();
        materialDatePicker.show(getParentFragmentManager(), "DATE_PICKER");
        materialDatePicker.addOnPositiveButtonClickListener(selection -> inputDate.setText(materialDatePicker.getHeaderText()));
        inputDate.setTextColor(ContextCompat.getColor(getContext(),android.R.color.black));
    }
    private void onSourceButtonClicked(Button sourceButton) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v);
                Toast.makeText(getContext(), "{" +
                        "\nRemaining fuel (Pre delivery): "+preDelivery.getText()+
                        "\nProduct Type: "+prodType.getText()+
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
        Navigation.findNavController(v).navigate(R.id.action_temporarySite_to_tripSummary);

    }
    private void initiateSignaturePad(View rootView) {
        NeumorphButton captureSignatureButton = rootView.findViewById(R.id.captureSignatureButton);
        captureSignatureButton.setOnClickListener(v -> {
            SignatureDialog signatureDialog = SignatureDialog.newInstance();
            signatureDialog.show(getChildFragmentManager(), "signatureDialog");
        });
    }
    public void captureSignature(Bitmap signatureBitmap){
        ImageView signatureView = getView().findViewById(R.id.signatureView);
        signatureView.setImageBitmap(signatureBitmap);
        signatureView.setVisibility(View.VISIBLE);
        //saved signature bitmap in this variable
        this.signatureBitmap = signatureBitmap;
    }
}