package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etNumber1;
    EditText etNumber2;
    Spinner spinnerOperation;
    Button btnCalculate;
    TextView labelResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        spinnerOperation = findViewById(R.id.spinnerOperation);
        btnCalculate = findViewById(R.id.btnCalculate);
        labelResult = findViewById(R.id.labelResult);

        String[] operatorsArray = getResources().getStringArray(R.array.operators);

        List list = new ArrayList(Arrays.asList(operatorsArray));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerOperation.setAdapter(dataAdapter);
    }

    public void calculate(View view) {
        double number1;
        double number2;
        double result = 0;

        try {
            number1 = Double.parseDouble(etNumber1.getText().toString());
            number2 = Double.parseDouble(etNumber2.getText().toString());

            if (spinnerOperation.getSelectedItem().equals("+")) {
                result = number1 + number2;
            } else if (spinnerOperation.getSelectedItem().equals("-")) {
                result = number1 - number2;
            } else if (spinnerOperation.getSelectedItem().equals("*")) {
                result = number1 * number2;
            } else if (spinnerOperation.getSelectedItem().equals("/")) {
                if (number2 == 0) {
                    labelResult.setText("NULOU DĚLIT NELZE!");
                    return;
                }

                result = number1 / number2;
            }

            NumberFormat nf = new DecimalFormat("#.###");
            labelResult.setText(nf.format(result));
        } catch (NumberFormatException e) {
            labelResult.setText("Chybné zadání...");
        }
    }
}

