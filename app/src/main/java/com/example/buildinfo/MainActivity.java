package com.example.buildinfo;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv_info);

        if (tv != null) {
            tv.setText(getAllInfo());
        }
    }

    private String getAllInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("BOARD: ").append(safe(Build.BOARD)).append("\n");
        sb.append("BRAND: ").append(safe(Build.BRAND)).append("\n");
        sb.append("CPU_ABI: ").append(safe(Build.CPU_ABI)).append("\n");
        sb.append("DEVICE: ").append(safe(Build.DEVICE)).append("\n");
        sb.append("MODEL: ").append(safe(Build.MODEL)).append("\n\n");

        sb.append("Generated ID:\n").append(generateId());

        return sb.toString();
    }

    private String generateId() {

        String r0 = "77"
                + (len(Build.BOARD) % 10)
                + (len(Build.BRAND) % 10)
                + (len(Build.CPU_ABI) % 10)
                + (len(Build.DEVICE) % 10)
                + (len(Build.MODEL) % 10);

        String serial;

        try {
            serial = Build.SERIAL; // Android 7 安全
        } catch (Exception e) {
            serial = "unknown";
        }

        return new UUID(r0.hashCode(), serial.hashCode()).toString();
    }

    private String safe(String s) {
        return s == null ? "null" : s;
    }

    private int len(String s) {
        return s == null ? 0 : s.length();
    }
}
