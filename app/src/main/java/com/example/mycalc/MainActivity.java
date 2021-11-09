package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;
    private double argument1 ;
    private boolean needClear ;
//    private char operation;
    private String operation;
    String strRezult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.tvDisplay);
        resultText.setText("0");
        needClear = true;

        View.OnClickListener digitClick = (View v) -> {
            String txt = resultText.getText().toString();
            String digit = ((TextView)v).getText().toString();
            if(txt.equals("0") || needClear ){
                needClear = false;
                resultText.setText(digit);
            } else {
                if(resultText.length() < 8 ) { // Ограничение кол-ва цифр на экране - 8.
                    resultText.setText(txt + digit);
                }
            }
        };
        Button btn;
        for(int i = 0; i < 10; ++i){
            btn = findViewById(getResources().getIdentifier("btnDigit" + i, "id", getPackageName()));
            btn.setOnClickListener(digitClick);
        }
    }

    // Кнпка ",".
    public void dotClick(View view) {

        String txt = resultText.getText().toString();
        int index1 = txt.indexOf(','); // Индекс "."
        // Недопушение дублирования ","
        if(index1 == -1){
            resultText.setText(txt + ","); // пока точка, нужна запятая 
        }
    }

    // Кнпка "Сброс".
    public void onClearClick(View view) {
        resultText.setText("0");
    }

    // Кнопки мат. операций.
    public void operationClick(View v) {
        argument1 = Double.parseDouble((resultText.getText().toString()).replaceAll(",","."));
        needClear = true;
        // Запомнить операцию
        //operation =
        Button oper = (Button)v;
        operation = oper.getText().toString();
    };
    // Кнопка "Равно"
    public void operationRavno(View view) {
        double argument2;
        double rezult;
//        argument2 = Double.parseDouble( resultText.getText().toString());
        argument2 = Double.parseDouble((resultText.getText().toString()).replaceAll(",","."));

        switch(operation) {
            case "+":

                rezult = argument1 + argument2;
                strRezult = String.valueOf(rezult).replace(".",",");
                resultText.setText("" + strRezult);
                break;
            case "-":
                rezult = argument1 - argument2;
                strRezult = String.valueOf(rezult).replace(".",",");
                resultText.setText("" + strRezult);
                break;
            case "*":
                rezult = argument1 * argument2;
                strRezult = String.valueOf(rezult).replace(".",",");
                resultText.setText("" + strRezult);
                break;
            case "/":
                rezult = argument1 / argument2;
                strRezult = String.valueOf(rezult).replace(".",",");
                resultText.setText("" + strRezult);
                break;
            default:
                resultText.setText("ERROR");
        }

        needClear = true;
    }
}
