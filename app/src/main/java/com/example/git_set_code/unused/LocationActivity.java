//package com.example.git_set_code;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.git_set_code.viewmodels.SavedDataViewModel;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//
//import java.util.List;
//
//public class LocationActivity extends AppCompatActivity {
//    //Constants
//    public static final int DEFAULT_FAST_INTERVAL = 2000;
//    public static final int DEFAULT_INTERVAL = 10000;
//    public static final String LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;
//    public static final int REQUEST_CODE = 1;
//
//    //Global variables
//    FusedLocationProviderClient fusedLocationProviderClient;
//    LocationRequest locationRequest;
//    Location currentLocation;
//    List<Location> savedLocations;
//
//    //Database variables
//    private SavedDataViewModel savedDataViewModel;
//
//    //UI elements
//    TextView latitude, longtitude, address;
//    RadioButton highAccuracy, lowAccuracy;
//    Button saveLocation;
//    EditText destinationAddress;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        latitude = findViewById(R.id.latVal);
//        longtitude = findViewById(R.id.lonVal);
//        address = findViewById(R.id.addrValue);
//        highAccuracy = findViewById(R.id.highAccuracy);;
//        lowAccuracy = findViewById(R.id.lowAccuracy);
////        viewMap = findViewById(R.id.viewMap);
//        saveLocation = findViewById(R.id.button);
//        destinationAddress = findViewById(R.id.destInput);
//
//        locationRequest = new LocationRequest() ;
//        locationRequest.setFastestInterval(DEFAULT_FAST_INTERVAL);
//        locationRequest.setInterval(DEFAULT_INTERVAL);
//
//        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//
////        viewButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this, ViewSavedActivity.class);
////                startActivity(intent);
////            }
////        });
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//
//        final SavedDataAdapter adapter = new SavedDataAdapter();
//        recyclerView.setAdapter(adapter);
//        savedDataViewModel = new ViewModelProvider(this).get(SavedDataViewModel.class);
//        savedDataViewModel.getAllData().observe(this, new Observer<List<SavedData>>() {
//            @Override
//            public void onChanged(List<SavedData> savedData) {
//                adapter.setSavedData(savedData);
//            }
//        });
//
//        saveLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String destAddress = destinationAddress.getText().toString();
//                double latitude = currentLocation.getLatitude();
//                double longitude = currentLocation.getLongitude();
//                if(destAddress.trim().isEmpty()){
//                    Toast.makeText(LocationActivity.this, "Please enter a valid address", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                else{
//                    SavedData savedData = new SavedData(latitude, longitude, destAddress);
//                    savedDataViewModel.insert(savedData);
//                    Toast.makeText(LocationActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        highAccuracy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isHighAccuracyChecked = highAccuracy.isChecked();
//                if(isHighAccuracyChecked){
//                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                }else{
//                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//                }
//
//            }
//        });
//
////        viewMap.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////            }
////        });
//        updateGPS();
//    }
////    Switch viewMap;
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode){
//            case REQUEST_CODE:
//                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    updateGPS();
//                }else{
//                    final Toast toast = new Toast(this);
//                    toast.setDuration(Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
//                    toast.setText("Sorry, This app requires location permissions to be granted to function correctly. Goodbye!");
//                    toast.show();
//                    finish();
//                }
//                break;
//        }
//    }
//
//    private void updateGPS(){
//
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        if(checkSelfPermission(LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED){
//
//            fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback(){
//                @Override
//                public void onLocationResult(LocationResult locationResult) {
//                    super.onLocationResult(locationResult);
//                    currentLocation = locationResult.getLastLocation();
//                    latitude.setText(String.valueOf(locationResult.getLastLocation().getLatitude()));
//                    longtitude.setText(String.valueOf(locationResult.getLastLocation().getLongitude()));
//                    getAddress(locationResult);
//                }
//            }, getMainLooper());
//
//        }else{
//            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
//                requestPermissions(new String[]{LOCATION_PERMISSION}, REQUEST_CODE);
//        }
//    }
//
//    private void getAddress(LocationResult locationResult) {
//        Geocoder geocoder = new Geocoder(LocationActivity.this);
//        try{
//            List<Address> addresses = geocoder.getFromLocation(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude(), 1);
//            address.setText(addresses.get(0).getAddressLine(0));
//        }catch (Exception e){
//            address.setText("Could not retrieve address.");
//        }
//    }
//}
