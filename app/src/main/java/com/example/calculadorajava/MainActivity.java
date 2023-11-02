package com.example.calculadorajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double primerDigito;
    String operation ="";

    String operacionCompleta = "";
    Button igualBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView NameBoton = findViewById(R.id.btnIgual);
        igualBtn = findViewById(R.id.btnIgual);

        //Botones de numero

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);

        Button suma = findViewById(R.id.btnSuma);
        Button resta = findViewById(R.id.btnRestar);
        Button multi = findViewById(R.id.btnMultiplicar);
        Button divison = findViewById(R.id.btnDivision);
        Button decimal = findViewById(R.id.btnComa);
        Button eliminar = findViewById(R.id.btnEliminar);
        Button igual = findViewById(R.id.btnIgual);
        Button clean = findViewById(R.id.cleanCalculadora);
        Button raiz = findViewById(R.id.btnRaiz);

        TextView screen = findViewById(R.id.txtResultado);


        ArrayList<Button> numeros = new ArrayList<>();
        numeros.add(btn0);
        numeros.add(btn1);
        numeros.add(btn2);
        numeros.add(btn3);
        numeros.add(btn5);
        numeros.add(btn4);
        numeros.add(btn6);
        numeros.add(btn7);
        numeros.add(btn8);
        numeros.add(btn9);


        for (Button num : numeros) {
            num.setOnClickListener(view -> {
                if (!screen.getText().toString().equals("0")) {
                    operacionCompleta += num.getText().toString(); // Agregar el número a la operación completa
                    screen.setText(operacionCompleta); // Mostrar la operación completa en el TextView
                } else {
                    operacionCompleta = num.getText().toString(); // Iniciar una nueva operación
                    screen.setText(operacionCompleta);
                }
            });
        }


        ArrayList<Button> operando = new ArrayList<>();

        operando.add(suma);
        operando.add(resta);
        operando.add(multi);
        operando.add(divison);
        operando.add(raiz);


        for (Button boton1 : operando) {
            boton1.setOnClickListener(view -> {
                primerDigito = Double.parseDouble(screen.getText().toString());
                operation = boton1.getText().toString();
                operacionCompleta += operation; // Agregar el operador a la operación completa
                screen.setText(operacionCompleta); // Mostrar la operación completa en el TextView
                operacionCompleta = ""; // Limpiar la variable para la siguiente operación
            });
        }


        eliminar.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length() > 1) {
                screen.setText(num.substring(0, num.length() - 1));
            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("0");
            }
        });


        decimal.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")) {
                screen.setText(screen.getText().toString() + ".");
            }
        });


        clean.setOnClickListener(view -> {
            screen.setText("0");
        });


        igual.setOnClickListener(view -> {
            double segundoDigito = Double.parseDouble(screen.getText().toString());
            double result;
            switch (operation) {
                case "+":
                    result = primerDigito + segundoDigito;
                    break;
                case "-":
                    result = primerDigito - segundoDigito;
                    break;
                case "*":
                    result = primerDigito * segundoDigito;
                    break;
                case "/":
                    result = primerDigito / segundoDigito;
                    break;
                case "√":

                default:
                    throw new IllegalStateException("Unexpected value: " + operation);
            }
            screen.setText(String.valueOf(result));
            primerDigito = result;
        });
    }
}