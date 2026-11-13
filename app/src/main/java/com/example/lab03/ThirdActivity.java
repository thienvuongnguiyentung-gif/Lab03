package com.example.lab03;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    private Button btnZacznijSledzenie, btnZatrzymajSledzenie;
    private TextView widokWartoscSzerokosci, widokWartoscDlugosci;
    public LocationManager zarzadcaLokalizacji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ekranTrzeci), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnZacznijSledzenie = (Button) findViewById(R.id.btnZacznijSledzenie);
        btnZatrzymajSledzenie = (Button) findViewById(R.id.btnZatrzymajSledzenie);
        widokWartoscSzerokosci = (TextView) findViewById(R.id.widokWartoscSzerokosci);
        widokWartoscDlugosci = (TextView) findViewById(R.id.widokWartoscDlugosci);

        btnZacznijSledzenie.setEnabled(true);
        btnZatrzymajSledzenie.setEnabled(false);

        zarzadcaLokalizacji = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener lokalizacjaListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location aktualnaPozycja) {
                String odczytanaSzerokosc = Double.toString(aktualnaPozycja.getLatitude());
                String odczytanaDlugosc = Double.toString(aktualnaPozycja.getLongitude());

                widokWartoscSzerokosci.setText(odczytanaSzerokosc);
                widokWartoscDlugosci.setText(odczytanaDlugosc);
            }

            @Override
            public void onStatusChanged(String dostawca, int status, Bundle dodatki) {
            }

            @Override
            public void onProviderEnabled(String dostawca) {
            }

            @Override
            public void onProviderDisabled(String dostawca) {
            }
        };

        btnZacznijSledzenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View widok) {
                btnZacznijSledzenie.setEnabled(false);
                btnZatrzymajSledzenie.setEnabled(true);

                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 99);
                    return;
                }
                zarzadcaLokalizacji.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lokalizacjaListener);
            }
        });

        btnZatrzymajSledzenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View widok) {
                btnZacznijSledzenie.setEnabled(true);
                btnZatrzymajSledzenie.setEnabled(false);
                zarzadcaLokalizacji.removeUpdates(lokalizacjaListener);
            }
        });
    }
}