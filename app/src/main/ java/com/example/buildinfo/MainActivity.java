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

        TextView tv = new TextView(this);
        tv.setText(getAllInfo());
        setContentView(tv);
    }

    private String getAllInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("BOARD: ").append(Build.BOARD).append("\n");
        sb.append("BRAND: ").append(Build.BRAND).append("\n");
        sb.append("CPU_ABI: ").append(Build.CPU_ABI).append("\n");
        sb.append("DEVICE: ").append(Build.DEVICE).append("\n");
        sb.append("DISPLAY: ").append(Build.DISPLAY).append("\n");
        sb.append("HOST: ").append(Build.HOST).append("\n");
        sb.append("ID: ").append(Build.ID).append("\n");
        sb.append("MANUFACTURER: ").append(Build.MANUFACTURER).append("\n");
        sb.append("MODEL: ").append(Build.MODEL).append("\n");
        sb.append("PRODUCT: ").append(Build.PRODUCT).append("\n");
        sb.append("TAGS: ").append(Build.TAGS).append("\n");
        sb.append("TYPE: ").append(Build.TYPE).append("\n");
        sb.append("USER: ").append(Build.USER).append("\n\n");

        sb.append("Generated ID: ").append(generateId());

        return sb.toString();
    }

    private String generateId() {

        String r0 = "77"
                + (Build.BOARD.length() % 10)
                + (Build.BRAND.length() % 10)
                + (Build.CPU_ABI.length() % 10)
                + (Build.DEVICE.length() % 10)
                + (Build.DISPLAY.length() % 10)
                + (Build.HOST.length() % 10)
                + (Build.ID.length() % 10)
                + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10)
                + (Build.PRODUCT.length() % 10)
                + (Build.TAGS.length() % 10)
                + (Build.TYPE.length() % 10)
                + (Build.USER.length() % 10);

        try {
            String serial = (Build.VERSION.SDK_INT >= 26)
                    ? Build.getSerial()
                    : Build.SERIAL;

            return new UUID(r0.hashCode(), serial.hashCode()).toString();

        } catch (Exception e) {
            return new UUID(r0.hashCode(), -2050236998L).toString() + "f";
        }
    }
}
