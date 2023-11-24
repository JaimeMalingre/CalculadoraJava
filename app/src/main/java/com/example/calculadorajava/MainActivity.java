package com.example.calculadorajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double primerDigito;
    double segundoDigito;
    boolean segundoNumero = false;
    String operation = "";
    double numActual = 0;
    String operacionCompleta = "";
    ArrayList<String> operaciones = new ArrayList<>();

    Button igual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        igual = findViewById(R.id.btnIgual);


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
                if (segundoNumero) {
                    operacionCompleta = "";
                    segundoNumero = false;
                }
                operacionCompleta += num.getText().toString();
                screen.setText(operacionCompleta);
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
                operacionCompleta += operation;
                screen.setText(operacionCompleta);
            });
        }


        eliminar.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length() > 1) {
                operacionCompleta = operacionCompleta.substring(0, operacionCompleta.length() - 1);
                numActual = Double.parseDouble(operacionCompleta);
                screen.setText(num.substring(0, num.length() - 1));
            } else {
                operacionCompleta = "";
                numActual = 0;
                screen.setText("0");
            }
        });

        clean.setOnClickListener(view -> {
            operacionCompleta = "";
            numActual = 0;
            screen.setText("0");
        });

        decimal.setOnClickListener(view -> {
            String currentText = screen.getText().toString();
            if (!currentText.contains(".")) {
                operacionCompleta += ".";
                screen.setText(currentText + ".");
            }
        });


        igual.setOnClickListener(view -> {
            try {
                String[] numeros1 = operacionCompleta.split("[-+*/√]");

                if (numeros1.length > 0) {
                    segundoDigito = Double.parseDouble(numeros1[1]);
                } else {
                    throw new IllegalStateException("Falta el segundo dígito");
                }
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
                        if (segundoDigito != 0) {
                            result = primerDigito / segundoDigito;
                        } else {
                            throw new ArithmeticException("División por cero");
                        }
                        break;
                    case "√":
                        if (segundoDigito >= 0) {
                            result = Math.sqrt(segundoDigito);
                        } else {
                            throw new ArithmeticException("Raíz cuadrada de número negativo");
                        }
                        break;
                    default:
                        throw new IllegalStateException("Operador inesperado: " + operation);
                }

                screen.setText(String.valueOf(result));

                primerDigito = result;
                operaciones.add(operacionCompleta);
                operacionCompleta = "";

                Intent intent = new Intent(MainActivity.this, MainResultado.class);
                startActivity(intent);
                intent.putExtra("resultado", result);
            } catch (ArithmeticException e) {
                screen.setText("No dividir entre 0");
            } catch (Exception e) {
                screen.setText("Falta segundo digito");
            }
        });
    }
}