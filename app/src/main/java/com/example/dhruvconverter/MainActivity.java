package com.example.dhruvconverter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private Button converterbutton;
    private EditText inputnum;
    private TextView outputnum;
    private TextView forc;
    private TextView typeindicator;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch typeswitch;
    private int typeof;

// ADD THE CELCIUS OR FAHRENHEIT INDICATOR SOON
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        converterbutton = findViewById(R.id.convertbutton);
        inputnum = findViewById(R.id.inputnum);
        outputnum = findViewById(R.id.outputnum);
        typeswitch = findViewById(R.id.tempswitch);
        typeindicator = findViewById(R.id.typeindicator);
        forc = findViewById(R.id.forc);
        int secs = 1;

        forc.setVisibility(View.INVISIBLE);
        typeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                forc.setVisibility(View.INVISIBLE);
                outputnum.setText("-----");
                if (typeswitch.isChecked()) {
                    typeindicator.setText("Convert to Fahrenheit");
                    typeof = 1;
                }
                else {
                    typeindicator.setText("Convert to Celsius");
                    typeof = 0;
                }
            }
        });
        converterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputnum.getWindowToken(), 0);
                String temp_string = inputnum.getText().toString();
                try {
                    int tempint = Integer.parseInt(temp_string);
                    if (typeof == 0) {
                        int cel = ((tempint - 32) * 5)/9;
                        Toast.makeText(MainActivity.this, "Converting to Celsius", Toast.LENGTH_SHORT).show();
                        Utils.delay(secs, new Utils.DelayCallback() {
                            @Override
                            public void afterDelay() {
                                outputnum.setText(String.valueOf(cel));
                                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
                                forc.setText("C");
                                forc.setVisibility(View.VISIBLE);
                            }
                        });
                    } else if (typeof == 1) {
                        int fah = (int) ((tempint * 1.8) + 32);
                        Toast.makeText(MainActivity.this, "Converting to Fahrenheit", Toast.LENGTH_SHORT).show();
                        Utils.delay(secs, new Utils.DelayCallback() {
                            @Override
                            public void afterDelay() {
                                outputnum.setText(String.valueOf(fah));
                                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
                                forc.setText("F");
                                forc.setVisibility(View.VISIBLE);
                            }

                        });
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                    }

                }

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}