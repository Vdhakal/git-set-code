package com.example.git_set_code.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.git_set_code.R;
import com.example.git_set_code.fragments.dialogs.SignatureDialog;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import soup.neumorphism.NeumorphButton;

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
    EditText prodType, startDate, startTime, endDate, endTime, grossGallons, netGallons, remainingFuel;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Bitmap signatureBitmap = null;
    private Bitmap bolBitmap = null;
    private View rootView = null;
    private String tempBolPath = null;
    private Uri photoUri = null;
    private File photoFile = null;
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
        rootView = inflater.inflate(R.layout.fragment_source_info, container, false);
        Button sourceButton = rootView.findViewById(R.id.save_cont_source);
        initItems(rootView);
        onSourceButtonClicked(sourceButton);
        dateListener();
        addScanBillOfLadingListener(rootView);
        return rootView;
    }
    private void dateListener() {

        startDate.setOnClickListener(v -> datePickerInput(startDate));
        endDate.setOnClickListener(v -> datePickerInput(endDate));
        startTime.setOnClickListener(v -> {
            try {
                timePickerInput(startTime);
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        endTime.setOnClickListener(v -> {
            try {
                timePickerInput(endTime);
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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

    private void initItems(View rootView) {
        prodType = rootView.findViewById(R.id.product_type_source);
        startDate = rootView.findViewById(R.id.start_date_source);
        startTime = rootView.findViewById(R.id.start_time_source);
        endDate = rootView.findViewById(R.id.drop_end_date_source);
        endTime = rootView.findViewById(R.id.drop_end_time_source);
        grossGallons = rootView.findViewById(R.id.gross_gallons_source);
        netGallons = rootView.findViewById(R.id.net_gallons_source);
        remainingFuel = rootView.findViewById(R.id.remaining_fuel_source);

    }

    private void onSourceButtonClicked(Button sourceButton) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v);
                Toast.makeText(getContext(), "{" +
                        "\nProduct Dropped: "+prodType.getText()+
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
    private void addScanBillOfLadingListener(View rootView) {
        View formLayout = rootView.findViewById(R.id.formLayout);
        NeumorphButton scanBillButton = formLayout.findViewById(R.id.scanBillButtonsource);
        scanBillButton.setOnClickListener(v -> {
            initiateBolCapture();
        });
    }

    private void initiateBolCapture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            photoFile = createTempImageFile();
            photoUri = FileProvider.getUriForFile(requireContext(), "com.example.git_set_code", photoFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(cameraIntent, 1888);
        } catch (Exception e) {
            Log.i("prativaDebug", "Cannot start camera intent "+e.toString());
        }
    }

    private File createTempImageFile() throws IOException {
        String tempFileName = String.format("%d", System.currentTimeMillis());
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File tempFile=File.createTempFile(
                tempFileName,
                ".png",
                storageDir
        );
        tempFile.deleteOnExit();
        tempBolPath = tempFile.getAbsolutePath();
        return tempFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1888 && resultCode == Activity.RESULT_OK) {
            //photoUri is the complete absolutepath of saved picture
            //bolBitmap is the bitmap of captured image
            bolBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath()); //captured image bitmap saved here
            View formLayout = rootView.findViewById(R.id.formLayout);
            ImageView bolView = formLayout.findViewById(R.id.bolImage);
            bolView.setVisibility(View.VISIBLE);
            Glide.with(requireContext())
                    .load(photoUri)
                    .into(bolView);
        }
    }


}