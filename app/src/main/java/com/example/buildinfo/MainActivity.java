package com.example.buildinfo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv_info);

        // 👉 请求权限（Android 8+ 获取序列号需要）
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }

        showInfo();
    }

    private void showInfo() {
        if (tv != null) {
            tv.setText(getAllInfo());
        }
    }

    private String getAllInfo() {

        StringBuilder sb = new StringBuilder();

        sb.append("===== Build 信息 =====\n");

        sb.append("BOARD: ").append(safe(Build.BOARD)).append("\n");
        sb.append("BOOTLOADER: ").append(safe(Build.BOOTLOADER)).append("\n");
        sb.append("BRAND: ").append(safe(Build.BRAND)).append("\n");
        sb.append("CPU_ABI: ").append(safe(Build.CPU_ABI)).append("\n");
        sb.append("CPU_ABI2: ").append(safe(Build.CPU_ABI2)).append("\n");
        sb.append("DEVICE: ").append(safe(Build.DEVICE)).append("\n");
        sb.append("DISPLAY: ").append(safe(Build.DISPLAY)).append("\n");
        sb.append("FINGERPRINT: ").append(safe(Build.FINGERPRINT)).append("\n");
        sb.append("HARDWARE: ").append(safe(Build.HARDWARE)).append("\n");
        sb.append("HOST: ").append(safe(Build.HOST)).append("\n");
        sb.append("ID: ").append(safe(Build.ID)).append("\n");
        sb.append("MANUFACTURER: ").append(safe(Build.MANUFACTURER)).append("\n");
        sb.append("MODEL: ").append(safe(Build.MODEL)).append("\n");
        sb.append("PRODUCT: ").append(safe(Build.PRODUCT)).append("\n");
        sb.append("TAGS: ").append(safe(Build.TAGS)).append("\n");
        sb.append("TYPE: ").append(safe(Build.TYPE)).append("\n");
        sb.append("USER: ").append(safe(Build.USER)).append("\n");

        sb.append("\n===== 系统版本 =====\n");
        sb.append("SDK_INT: ").append(Build.VERSION.SDK_INT).append("\n");
        sb.append("RELEASE: ").append(safe(Build.VERSION.RELEASE)).append("\n");
        sb.append("CODENAME: ").append(safe(Build.VERSION.CODENAME)).append("\n");

        sb.append("\n===== Serial =====\n");
        sb.append("Serial: ").append(getSerialSafe()).append("\n");

        sb.append("\n===== 生成ID =====\n");
        sb.append(generateId()).append("\n");

        return sb.toString();
    }

    // ===== 核心：生成ID（你给的逻辑）=====
    private String generateId() {

        String r0 = "77"
                + (len(Build.BOARD) % 10)
                + (len(Build.BRAND) % 10)
                + (len(Build.CPU_ABI) % 10)
                + (len(Build.DEVICE) % 10)
                + (len(Build.DISPLAY) % 10)
                + (len(Build.HOST) % 10)
                + (len(Build.ID) % 10)
                + (len(Build.MANUFACTURER) % 10)
                + (len(Build.MODEL) % 10)
                + (len(Build.PRODUCT) % 10)
                + (len(Build.TAGS) % 10)
                + (len(Build.TYPE) % 10)
                + (len(Build.USER) % 10);

        try {
            String serial = getSerialSafe();
            return new UUID(r0.hashCode(), serial.hashCode()).toString();
        } catch (Exception e) {
            return new UUID(r0.hashCode(), -2050236998L).toString() + "f";
        }
    }

    // ===== 安全获取 Serial =====
    private String getSerialSafe() {

        try {
            if (Build.VERSION.SDK_INT >= 26) {

                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
                    return "NO_PERMISSION";
                }

                return Build.getSerial();
            } else {
                return Build.SERIAL;
            }
        } catch (Exception e) {
            return "ERROR";
        }
    }

    // ===== 工具 =====
    private String safe(String s) {
        return s == null ? "null" : s;
    }

    private int len(String s) {
        return s == null ? 0 : s.length();
    }
}
