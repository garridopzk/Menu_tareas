package com.example.menu_tareas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private EditText tareaEditText;
    private TextView tareasTextView;
    private Button menuButton;

    private List<String> tareasList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tareaEditText = findViewById(R.id.ingreso);
        tareasTextView = findViewById(R.id.tareas);
        menuButton = findViewById(R.id.menu_button);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMenu();
            }
        });
    }

    private void mostrarMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("Menu");

        String[] opciones = {"Añadir Tarea", "Borrar Tarea", "Buscar Tarea"};
        builder.setItems(opciones, (dialog, which) -> {
            switch (which) {
                case 0:
                    añadirTarea();
                    break;
                case 1:
                    borrarTarea();
                    break;
                case 2:
                    buscarTarea();
                    break;
            }
        });

        builder.show();
    }

    private void añadirTarea() {
        String tarea = tareaEditText.getText().toString();
        if (!tarea.isEmpty()) {
            tareasList.add(tarea);
            actualizarTareas();
            tareaEditText.getText().clear();
            Toast.makeText(MainActivity2.this, "Tarea añadida", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity2.this, "Por favor, ingrese una tarea", Toast.LENGTH_SHORT).show();
        }
    }

    private void borrarTarea() {
        String tarea = tareaEditText.getText().toString();
        if (!tarea.isEmpty() && tareasList.contains(tarea)) {
            tareasList.remove(tarea);
            actualizarTareas();
            tareaEditText.getText().clear();
            Toast.makeText(MainActivity2.this, "Tarea borrada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity2.this, "Tarea no encontrada", Toast.LENGTH_SHORT).show();
        }
    }

    private void buscarTarea() {
        String tarea = tareaEditText.getText().toString();
        if (!tarea.isEmpty()) {
            List<String> resultado = new ArrayList<>();
            for (String t : tareasList) {
                if (t.toLowerCase().contains(tarea.toLowerCase())) {
                    resultado.add(t);
                }
            }
            if (!resultado.isEmpty()) {
                tareasTextView.setText(join(resultado, "\n"));
                Toast.makeText(MainActivity2.this, "Tareas encontradas", Toast.LENGTH_SHORT).show();
            } else {
                tareasTextView.setText("No se encontraron tareas");
                Toast.makeText(MainActivity2.this, "No se encontraron tareas", Toast.LENGTH_SHORT).show();
            }
        } else {
            actualizarTareas();
        }
    }

    private void actualizarTareas() {
        tareasTextView.setText(join(tareasList, "\n"));
    }

    private String join(List<String> list, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(item).append(delimiter);
        }
        return sb.toString();
    }
}
