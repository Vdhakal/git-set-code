package com.example.git_set_code.fragments;

// Importing necessary packages

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.fragments.dialogs.SignatureDialog;
import com.example.git_set_code.permissions.PermissionsRequestor;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String TAG = "Maps";
    private static final String ARG_PARAM2 = "param2";
    private PermissionsRequestor permissionsRequestor;
    private SignaturePad signaturePad = null;
    private Bitmap signatureBitmap = null;
    private Bitmap bolBitmap = null;
    Button scanBill;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Constructor for EditFragment
     */
    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * This method inflates the UI
     *
     * @param savedInstanceState, a Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * This method return a View by creating avIew from the inflaters and containers received
     *
     * @param inflater,           a LayoutInflater object
     * @param container,          a ViewGroup object
     * @param savedInstanceState, a Bundle
     * @return a View object
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.edit_fragment, container, false);

        return rootView;
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView signatureView = getView().findViewById(R.id.signatureView);
        signatureView.setVisibility(View.GONE);
        handleAndroidPermissions();

        initiateSignaturePad();
    }

    /**
     *
     */
    private void handleAndroidPermissions() {
        permissionsRequestor = new PermissionsRequestor(getActivity());
        permissionsRequestor.request(new PermissionsRequestor.ResultListener() {

            @Override
            public void permissionsGranted() {
                initiateBolCapture();
            }

            @Override
            public void permissionsDenied() {
                Log.e(TAG, "Permissions denied by user.");
            }
        });

    }

    /**
     * This method initiates the signature pad
     */
    private void initiateSignaturePad() {
        Button captureSignatureButton = getView().findViewById(R.id.captureSignatureButton);
        captureSignatureButton.setOnClickListener(v -> {
            SignatureDialog signatureDialog = SignatureDialog.newInstance();
            signatureDialog.show(getChildFragmentManager(), "signatureDialog");
        });
    }

    /**
     * This method captures the signature
     *
     * @param signatureBitmap,a Bitmap
     */
    public void captureSignature(Bitmap signatureBitmap) {
        ImageView signatureView = getView().findViewById(R.id.signatureView);
        signatureView.setImageBitmap(signatureBitmap);
        signatureView.setVisibility(View.VISIBLE);
        //saved signature bitmap in this variable
        this.signatureBitmap = signatureBitmap;
    }

    /**
     * This method initiates the Bol capture
     */
    private void initiateBolCapture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 1888);
    }

    /**
     * This method takes the requestCode and resultCode and saves it to bolBitMap
     *
     * @param requestCode, an int representing the request code
     * @param resultCode,  an int representing the result code
     * @param data,        an Intent object
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1888 && resultCode == Activity.RESULT_OK) {
            //photo bitmap saved here
            bolBitmap = (Bitmap) data.getExtras().get("data");
            saveToGallery(bolBitmap);

        }
    }

    /**
     * This method saves the bitmap to the gallery
     *
     * @param bitmap, a BitMap object
     */
    public void saveToGallery(Bitmap bitmap) {
        FileOutputStream outputStream = null;
        File file = getContext().getExternalFilesDir(null);
        File dir = new File(file.getAbsolutePath() + "/BillOfLading");
        String filename = String.format("%d.png", System.currentTimeMillis());
        File outPutFile = new File(dir, filename);
        try {
            outputStream = new FileOutputStream(outPutFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        try {
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}