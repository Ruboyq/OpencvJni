package com.example.opencvjni;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        final TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        setContentView(R.layout.activity_main);
        final ImageView iv = findViewById(R.id.iv_photo);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(MainActivity.this.stringFromJNI());
                Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.pig);
                iv.setImageBitmap(MainActivity.this.opBitmap(bitmap, Bitmap.Config.ARGB_8888));
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native Bitmap opBitmap(Bitmap bitmap, Bitmap.Config argb8888);
}
