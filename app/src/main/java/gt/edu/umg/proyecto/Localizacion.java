package gt.edu.umg.proyecto;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class Localizacion extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private TextView locationTv;
    private static final int REQUESTED_CODE_LOCATION_PERMISSION = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationTv = findViewById(R.id.locationTv);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();

    }
    private void getCurrentLocation(){
        if(ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED

        ){
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUESTED_CODE_LOCATION_PERMISSION
            );
            return;

        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this,location -> {
            if(location != null){
                locationTv.setText(
                        "Latitud:" + location.getLatitude() + "\n" +
                                "Longitud:" + location.getLongitude()
                );
            } else{
                locationTv.setText("No se obtuvo la ubicacion");

            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUESTED_CODE_LOCATION_PERMISSION && grantResults.length >0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            } else {
                locationTv.setText("Permiso denegado");
            }
        }
    }
}