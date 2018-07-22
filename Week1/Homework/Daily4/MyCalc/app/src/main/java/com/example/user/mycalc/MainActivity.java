package com.example.user.mycalc;

import android.content.res.Configuration;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.mycalc.utils.Operations;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private TextView tvFormula;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFormula = findViewById(R.id.tvFormula);
        tvResult = findViewById(R.id.tvResult);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayout glOtherButtons = findViewById(R.id.glOtherButtons);
            glOtherButtons.setVisibility(View.VISIBLE);

            GridLayout glButtons = findViewById(R.id.glButtons);
            List<View> Buttons = glButtons.getTouchables();

            for (View v : Buttons) {
                Button btn = (Button) v;
                btn.getLayoutParams().height = 180;
            }
        }
    }

    public void onNumber(View view) {
        String Character = ((Button) view).getText().toString();
        Operations.addCharacterToFormula(Character, tvFormula);
    }

    //region Operations
    public void onDelete(View view) {
        tvFormula.setText("");
        tvResult.setText("Result");
    }

    public void onDivision(View view) {
        Operations.addCharacterToFormula("/", tvFormula);
    }

    public void onMultiplication(View view) {
        Operations.addCharacterToFormula("*", tvFormula);
    }

    public void onsubtraction(View view) {
        Operations.addCharacterToFormula("-", tvFormula);
    }

    public void onAddition(View view) {
        Operations.addCharacterToFormula("+", tvFormula);
    }

    public void onResult(View view) {
        try {
            String Formula = tvFormula.getText().toString();
            Log.d(TAG, "onResult: Formula: " + Formula);
            double result = Operations.eval(Formula);
            Log.d(TAG, "onResult: " + result);
            String strResult = String.valueOf(result);
            tvResult.setText(strResult);
        } catch (Exception e) {
            e.printStackTrace();
            tvResult.setText("Not Valid!");
        }
    }
    //endregion

    //region Other Operations
    public void onRootSquare(View view) {
        Operations.addCharacterToFormula("sqrt", tvFormula);
    }

    public void onsPow2(View view) {
        Operations.addCharacterToFormula("pow", tvFormula);
    }

    public void onPow3(View view) {
        Operations.addCharacterToFormula("pows", tvFormula);
    }


    //endregion
}
