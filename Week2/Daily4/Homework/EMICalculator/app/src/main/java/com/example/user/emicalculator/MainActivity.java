package com.example.user.emicalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    Button emiCalcBtn;
    private EditText result;
    private EditText P;
    private EditText I;
    private EditText Y;
    private EditText TI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        P = (EditText) findViewById(R.id.principal);
        I = (EditText) findViewById(R.id.interest);
        Y = (EditText) findViewById(R.id.years);
        TI = (EditText) findViewById(R.id.interest_total);

        result = (EditText) findViewById(R.id.emi);
        emiCalcBtn = (Button) findViewById(R.id.btn_calculate2);

        SeekBar sbYears = (SeekBar) findViewById(R.id.sbYears);
        sbYears.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Y.setText(String.valueOf(i));
                Calculate_EMI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public float calPric(float p) {

        return (float) (p);

    }

    public float calInt(float i) {

        return (float) (i / 12 / 100);

    }

    public float calMonth(float y) {

        return (float) (y * 12);

    }

    public float calDvdnt(float Rate, float Months) {

        return (float) (Math.pow(1 + Rate, Months));

    }

    public float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {

        return (float) (Principal * Rate * Dvdnt);

    }

    public float calDivider(float Dvdnt) {

        return (float) (Dvdnt - 1);

    }

    public float calEmi(float FD, Float D) {

        return (float) (FD / D);

    }

    public float calTa(float emi, Float Months) {

        return (float) (emi * Months);

    }

    public float calTotalInt(float TA, float Principal) {

        return (float) (TA - Principal);

    }

    public void onCalculateEMI(View view) {
        Calculate_EMI();
    }

    private void Calculate_EMI() {
        String st1 = P.getText().toString();
        String st2 = I.getText().toString();
        String st3 = Y.getText().toString();

        if (TextUtils.isEmpty(st1)) {
            P.setError("Enter Prncipal Amount");
            P.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(st2)) {
            I.setError("Enter Interest Rate");
            I.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(st3)) {
            Y.setError("Enter Years");
            Y.requestFocus();
            return;
        }

        float p = Float.parseFloat(st1);
        float i = Float.parseFloat(st2);
        float y = Float.parseFloat(st3);

        float Principal = calPric(p);

        float Rate = calInt(i);

        float Months = calMonth(y);

        float Dvdnt = calDvdnt(Rate, Months);

        float FD = calFinalDvdnt(Principal, Rate, Dvdnt);

        float D = calDivider(Dvdnt);

        float emi = calEmi(FD, D);

        float TA = calTa(emi, Months);

        float ti = calTotalInt(TA, Principal);


        result.setText(String.valueOf(emi));


        TI.setText(String.valueOf(ti));

        emiCalcBtn.requestFocus();
    }
}