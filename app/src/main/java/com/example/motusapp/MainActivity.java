package com.example.motusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView teste;
    ToggleButton play;
    ImageButton prox;
    ImageButton anterior;
    ImageView abx,Aum;

    Socket cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teste = findViewById(R.id.teste);
        teste.setText("teste");

        anterior=findViewById(R.id.anterior);
        anterior.setOnClickListener(this);
        play=findViewById(R.id.playPause);
        play.setOnClickListener(this);
        prox=findViewById(R.id.proximo);
        prox.setOnClickListener(this);
        abx=findViewById(R.id.diminuir);
        abx.setOnClickListener(this);
        Aum=findViewById(R.id.aumentar);
        Aum.setOnClickListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    teste.setText("!");
                    cliente = new Socket("192.168.1.158",2222);
                    teste.setText("socket");
                    teste.setText("conectado");
                } catch (IOException e) {
                    //e.printStackTrace();
                    teste.setText("Erro de conexao");

                }

            }
        }).start();




    }



    @Override
    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    PrintStream saida = new PrintStream(cliente.getOutputStream());

                    if(view == play){
                        saida.println("A");
                    }
                    if(view==prox){
                        saida.println("B");
                    }
                    if(view==anterior){
                        saida.println("C");
                    }
                    if(view== Aum){
                        saida.println("D");
                    }
                    if(view==abx){
                        saida.println("E");
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}