package com.example.menu_tareas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextUser;
    EditText editTextPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.usuario);
        editTextPassword = findViewById(R.id.contraseña);

        Button inicio = findViewById(R.id.inicio);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();

                if (user.equals("niki") && password.equals("garrido")) {
                    Intent intent = new Intent(v.getContext(), MainActivity2.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}