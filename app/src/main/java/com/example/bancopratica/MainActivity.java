package com.example.bancopratica;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bancopratica.banco.BancoManager;
import com.example.bancopratica.controle.Controle;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private ImageButton imageButtonListarAlunos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonListarAlunos = findViewById(R.id.imageButtonListarAlunos);
        imageButtonListarAlunos.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListarAlunoActivity.class);
            startActivity(intent);
        });
    }
    
}