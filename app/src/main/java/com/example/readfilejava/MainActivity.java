package com.example.readfilejava;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.readBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                readFile();
            }
        });
    }

    private void readFile() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        File sdCard = Environment.getExternalStorageDirectory();// storage/emulated/0
        File file = new File(sdCard, "/Download/hello.txt");

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader((new FileReader(file)));
            String line;

            while((line = br.readLine()) != null) {
                text.append(line);
                text.append(("\n"));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        TextView tv = findViewById(R.id.fileContent);
        tv.setText(text.toString());
    }
}
