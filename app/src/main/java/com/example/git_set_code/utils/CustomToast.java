package com.example.git_set_code.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.git_set_code.R;

public class CustomToast {
    public static void showToast(Activity activity, String toastMsg){
        Toast toast = new Toast(activity.getApplicationContext());
        View view = activity.getLayoutInflater().inflate(R.layout.toast_layout, null);
        TextView textView = view.findViewById(R.id.toast_msg);
        textView.setText(toastMsg);
        textView.setPadding(30,0,30,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
        toast.setGravity(Gravity.BOTTOM,0,200);
    }
}
