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

        // 🔥 防止空指针闪退（Android 7 常见问题）
        if (tv != null) {
            tv.setText(getAllInfo());
        }
    }

    private String getAllInfo() {

        StringBuilder sb = new StringBuilder();

        // ===== 安全输出 Build 信息 =====
        sb.append("BOARD: ").append(safe(Build.BOARD)).append("\n");
        sb.append("BRAND: ").append(safe(Build.BRAND)).append("\n");
        sb.append("CPU_ABI: ").append(safe(Build.CPU_ABI)).append("\n");
        sb.append("DEVICE: ").append(safe(Build.DEVICE)).append("\n");
        sb.append("DISPLAY: ").append(safe(Build.DISPLAY)).append("\n");
        sb.append("HOST: ").append(safe(Build.HOST)).append("\n");
        sb.append("ID: ").append(safe(Build.ID)).append("\n");
        sb.append("MANUFACTURER: ").append(safe(Build.MANUFACTURER)).append("\n");
        sb.append("MODEL: ").append(safe(Build.MODEL)).append("\n");
        sb.append("PRODUCT: ").append(safe(Build.PRODUCT)).append("\n");
        sb.append("TAGS: ").append(safe(Build.TAGS)).append("\n");
        sb.append("TYPE: ").append(safe(Build.TYPE)).append("\n");
        sb.append("USER: ").append(safe(Build.USER)).append("\n\n");

        sb.append("Generated ID: ").append(generateId());

        return sb.toString();
    }

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

        String serial = "unknown";

        try {
            // 🔥 Android 7 兼容关键点
            if (Build.VERSION.SDK_INT >= 26) {
                serial = Build.getSerial();
            } else {
                serial = Build.SERIAL;
            }
        } catch (Exception e) {
            serial = "error";
        }

        return new UUID(r0.hashCode(), serial.hashCode()).toString();
    }

    // ===== 防空工具方法 =====
    private String safe(String s) {
        return s == null ? "null" : s;
    }

    private int len(String s) {
        return s == null ? 0 : s.length();
    }
}
