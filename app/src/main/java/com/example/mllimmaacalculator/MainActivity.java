package com.example.mllimmaacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int cem = 0;

    CardView btn;
    EditText etStaj, etLisey, etAdi, etEvezicilik;
    SwitchCompat swRehber, swTehsil;
    TextView amtxt, gvtxt, htxt, dtxt, tstxt, istxt, cttxt, hmtxt;
    Dialog dialog;
    Button dbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        etStaj.setText("0");
        etLisey.setText("0");
        etAdi.setText("0");
        etEvezicilik.setText("0");

        btn.setOnClickListener(v -> {
            cem = 0;

            if(etStaj.getText().toString().isEmpty() && etStaj.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Stajı daxil edin", Toast.LENGTH_SHORT).show();
            }
            else if(etLisey.getText().toString().isEmpty() && etLisey.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Ders yukunuzu daxil edin", Toast.LENGTH_SHORT).show();
            }
            else if(etAdi.getText().toString().isEmpty() && etAdi.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Ders yukunuzu daxil edin", Toast.LENGTH_SHORT).show();
            }
            else if(etEvezicilik.getText().toString().isEmpty() && etEvezicilik.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Ders yukunuzu daxil edin", Toast.LENGTH_SHORT).show();
            }
            else{
                if(swRehber.isChecked()){
                    cem = 40;
                }
                if(swTehsil.isChecked()){
                    ali();
                }
                else{
                    orta();
                }
            }
        });

        dbtn.setOnClickListener(v -> dialog.cancel());
    }

    private void ali(){

        int a = convert(etStaj.getText().toString());
        int b = convert(etLisey.getText().toString());
        int c = convert(etAdi.getText().toString());
        int k = convert(etEvezicilik.getText().toString());
        double s = 0.0;

        if(b + c + k > 36){
            Toast.makeText(MainActivity.this, "Həftəlik dərs yükü 36 saatdan artıq ola bilməz", Toast.LENGTH_LONG).show();
            return;
        }

        if(0 <= a && a <= 3){
            s = ((460.0 * 1.2 / 18 * b * 1.15) + (460.0 * 1.2 / 18 * c) + (460.0 * 1.2 / 76.2 * k)) + cem;
        }
        else if(3 < a && a <= 8){
            s = ((495.0 * 1.2 / 18 * b * 1.15) + (495.0 * 1.2 / 18 * c) + (495.0 * 1.2 / 76.2 * k)) + cem;
        }
        else if (8 < a && a <= 13){
            s = ((520.0 * 1.2 / 18 * b * 1.15) + (520.0 * 1.2 / 18 * c) + (520.0 * 1.2 / 76.2 * k)) + cem;
        }
        else if (13 < a && a <= 18){
            s = ((555.0 * 1.2 / 18 * b * 1.15) + (555.0 * 1.2 / 18 * c) + (555.0 * 1.2 / 76.2 * k)) + cem;
        }
        else if (a > 18){
            s = ((595.0 * 1.2 / 18 * b * 1.15) + (595.0 * 1.2 / 18 * c) + (595.0 * 1.2 / 76.2 * k)) + cem;
        }

        calc(s);
    }

    private void orta(){

        int a = convert(etStaj.getText().toString());
        int b = convert(etLisey.getText().toString());
        int c = convert(etAdi.getText().toString());
        int k = convert(etEvezicilik.getText().toString());
        double s = 0.0;

        if(0 <= a && a <= 3){
            s = ((420.0 * 1.2 / 18 * b * 1.15) + (420.0 * 1.2 / 18 * c) + (420.0 * 1.2 / 76.2 * k)) + cem;
        }
        else if(3 < a && a <= 8){
            s = ((445.0 * 1.2 / 18 * b * 1.15) + (445.0 * 1.2 / 18 * c) + (445.0 * 1.2 / 76.2 * k)) + cem;
        }
        else if (8 < a && a <= 13){
            s = ((460.0 * 1.2 / 18 * b * 1.15) + (460.0 * 1.2 / 18 * c) + (460.0 * 1.2 / 76.2 * k)) + cem;
        }
        else if (13 < a && a <= 18){
            s = ((490.0 * 1.2 / 18 * b * 1.15) + (490.0 * 1.2 / 18 * c) + (490.0 * 1.2 / 76.2 * k)) + cem;
        }
        else if (a > 18){
            s = ((510.0 * 1.2 / 18 * b * 1.15) + (510.0 * 1.2 / 18 * c) + (510 * 1.2 / 76.2 * k)) + cem;
        }

        calc(s);
    }

    @SuppressLint("DefaultLocale")
    private void calc(double s){
        double t, h;

        if (s < 200){
            t = s - s * 0.075;
            h = 0;
        }
        else{
            t = s - (s - 200) * 0.14 - s * 0.075;
            h = (s - 200) * 0.14;
        }
        hmtxt.setText(String.format("%.2f",s));
        amtxt.setText(String.format("%.2f",t));
        gvtxt.setText(String.format("%.2f",h));
        htxt.setText(String.format("%.2f",s * 0.02));
        dtxt.setText(String.format("%.2f",s * 0.03));
        tstxt.setText(String.format("%.2f",s * 0.02));
        istxt.setText(String.format("%.2f",s * 0.005));
        cttxt.setText(String.format("%.2f",s - t));
        dialog.show();
    }

    private void init(){
        btn = findViewById(R.id.btn);
        etStaj = findViewById(R.id.etStaj);
        etLisey = findViewById(R.id.etLisey);
        etAdi = findViewById(R.id.etAdi);
        etEvezicilik = findViewById(R.id.etEvezicilik);
        swRehber = findViewById(R.id.swRehber);
        swTehsil = findViewById(R.id.swTehsil);

        init_dialog();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void init_dialog(){
        // Start Dialog
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custon_layout);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        //End Dialog

        amtxt = dialog.findViewById(R.id.textView3);
        dbtn = dialog.findViewById(R.id.button);
        gvtxt = dialog.findViewById(R.id.textView11);
        htxt = dialog.findViewById(R.id.textView12);
        dtxt = dialog.findViewById(R.id.textView13);
        tstxt = dialog.findViewById(R.id.textView14);
        istxt = dialog.findViewById(R.id.textView15);
        cttxt = dialog.findViewById(R.id.textView16);
        hmtxt = dialog.findViewById(R.id.textView17);
    }


    public int convert(String str) {
        int val;

        try {
            val = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Düzgün yazılmayıb", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return val;
    }
}
