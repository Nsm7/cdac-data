package com.sunbeaminfo.application3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onWrite(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            writeFile();
        } else {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission granted :)", Toast.LENGTH_SHORT).show();
            writeFile();
        } else {
            Toast.makeText(this, "Permission NOT granted :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeFile() {
        try {
            File file = new File("/sdcard/myfile.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("I love my India");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRead(View v) {
        File file = new File("/sdcard/myfile.txt");
        try {
            FileReader reader = new FileReader(file);
            int ch = reader.read();
            Log.e("MainActivity", "" + ch);

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
