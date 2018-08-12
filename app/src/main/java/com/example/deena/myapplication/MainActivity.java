package com.example.deena.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView msg;
    private ImageView img1, img2, img3, img;
    private Wheel wheel1, wheel2, wheel3;
    private Button btn;
    private boolean isStarted;
    int count=0;
    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        btn = (Button) findViewById(R.id.btn);
        msg = (TextView) findViewById(R.id.msg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStarted) {
                    count = count + 1;
                    wheel1.stopWheel();
                    wheel2.stopWheel();
                    wheel3.stopWheel();
                    String s = "";
                    btn.setPressed(true);
                    if (wheel1.currentIndex == 0 || wheel2.currentIndex == 0 || wheel3.currentIndex == 0)
                        s += "Bill Gates ";
                    if (wheel1.currentIndex == 1 || wheel2.currentIndex == 1 || wheel3.currentIndex == 1)
                        s += "Mark Zuckerberg ";
                    if (wheel1.currentIndex == 2 || wheel2.currentIndex == 2 || wheel3.currentIndex == 2)
                        s += "Mukesh Ambani ";
                    if (wheel1.currentIndex == 3 || wheel2.currentIndex == 3 || wheel3.currentIndex == 3)
                        s += "Indira Gandhi ";
                    if (wheel1.currentIndex == 4 || wheel2.currentIndex == 4 || wheel3.currentIndex == 4)
                        s += "Steve Jobs ";
                    if(count>1) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                        btn.setPressed(false);
                    }
                } else {
                    wheel1 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img1.setImageResource(img);
                                }
                            });
                        }
                    }, 100, randomLong(0, 150));
                    wheel1.start();
                    wheel2 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img2.setImageResource(img);
                                }
                            });
                        }
                    }, 100, randomLong(100, 200));

                    wheel2.start();
                    wheel3 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img3.setImageResource(img);
                                }
                            });
                        }
                    }, 100, randomLong(150, 250));
                    wheel3.start();
                    btn.setText("Stop");
                    isStarted = true;
                }
            }
        });
    }
}
