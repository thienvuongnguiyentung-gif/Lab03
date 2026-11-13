package com.example.lab03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnWyswietlDane;
    private Button btnOtworzStrone;
    private Button btnUruchomLokalizacje;
    private TextView wejscieAdresWww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ekranGlowny), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnWyswietlDane = (Button) findViewById(R.id.btnWyswietlDane);
        btnOtworzStrone = (Button) findViewById(R.id.btnOtworzStrone);
        btnUruchomLokalizacje = (Button) findViewById(R.id.btnUruchomLokalizacje);
        wejscieAdresWww = (TextView) findViewById(R.id.wejscieAdresWww);

        btnWyswietlDane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View widok) {
                Intent intencjaDanych = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intencjaDanych);
            }
        });

        btnOtworzStrone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View widok) {
                String wpisanyAdres = wejscieAdresWww.getText().toString();
                Intent intencjaWww = new Intent(Intent.ACTION_VIEW, Uri.parse(wpisanyAdres));
                startActivity(intencjaWww);
            }
        });

        btnUruchomLokalizacje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View widok) {
                Intent intencjaLokalizacji = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intencjaLokalizacji);
            }
        });
    }
}