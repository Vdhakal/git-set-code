package com.example.git_set_code.fragments.dialogs;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.git_set_code.R;
import com.example.git_set_code.fragments.EditFragment;
import com.github.gcacace.signaturepad.views.SignaturePad;

public class SignatureDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot =  inflater.inflate(R.layout.signature_pad, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button saveButton = getView().findViewById(R.id.saveSignature);
        Button clearButton = getView().findViewById(R.id.clearSignature);
        SignaturePad signaturePad = getView().findViewById(R.id.signaturePad);
        saveButton.setOnClickListener(v-> {
            Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
            EditFragment editFragment = (EditFragment) getParentFragment();
            editFragment.captureSignature(signatureBitmap);
            dismiss();
        });

        clearButton.setOnClickListener(v -> {
            signaturePad.clear();
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        RelativeLayout root = new RelativeLayout(requireActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Dialog dialog = new Dialog(requireActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return dialog;
    }

    public static SignatureDialog newInstance() {
        return new SignatureDialog();
    }
}
