package com.ucb.calculator;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private StringBuilder input = new StringBuilder();
    private String operator;
    private double operand1 = 0;
    private boolean lastNumeric;
    private boolean stateError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        // Number buttons
        findViewById(R.id.btn0).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn1).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn2).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn3).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn4).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn5).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn6).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn7).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn8).setOnClickListener(this::onNumeric);
        findViewById(R.id.btn9).setOnClickListener(this::onNumeric);

        // Decimal button
        findViewById(R.id.btnDecimal).setOnClickListener(this::onDecimalPoint);

        // Operation buttons
        findViewById(R.id.btnAdd).setOnClickListener(this::onOperator);
        findViewById(R.id.btnSubtract).setOnClickListener(this::onOperator);
        findViewById(R.id.btnMultiply).setOnClickListener(this::onOperator);
        findViewById(R.id.btnDivide).setOnClickListener(this::onOperator);

        // Equals button
        findViewById(R.id.btnEquals).setOnClickListener(this::onEqual);

        // Clear button
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            textViewResult.setText("0");
            input.setLength(0);
            operand1 = 0;
            operator = "";
            lastNumeric = false;
            stateError = false;
        });
    }

    private void onNumeric(View v) {
        if (stateError) {
            textViewResult.setText(((Button) v).getText());
            stateError = false;
        } else {
            input.append(((Button) v).getText());
            textViewResult.setText(input.toString());
        }
        lastNumeric = true;
    }

    private void onDecimalPoint(View v) {
        if (lastNumeric && !input.toString().contains(".")) {
            input.append(".");
            textViewResult.setText(input.toString());
            lastNumeric = false;
        }
    }

    private void onOperator(View v) {
        if (lastNumeric && !stateError) {
            operand1 = Double.parseDouble(input.toString());
            operator = ((Button) v).getText().toString();
            input.setLength(0);
            lastNumeric = false;
        }
    }

    private void onEqual(View v) {
        if (lastNumeric && !stateError) {
            double operand2 = Double.parseDouble(input.toString());
            double result;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        textViewResult.setText("Error");
                        stateError = true;
                        return;
                    }
                    result = operand1 / operand2;
                    break;
                default:
                    return;
            }
            textViewResult.setText(String.valueOf(result));
            input.setLength(0);
            operand1 = result;
        }
    }
}
