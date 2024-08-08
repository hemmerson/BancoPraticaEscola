package com.example.bancopratica;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bancopratica.banco.BancoManager;
import com.example.bancopratica.controle.ControleDisciplina;
import com.example.bancopratica.entity.Aluno;
import com.example.bancopratica.entity.Disciplina;

public class DetalhesDisciplinaActivity extends AppCompatActivity {
    
    private TextView textViewDisciplinaAluno, textViewDisciplina;
    private ImageButton imageButtonEditDisciplina, imageButtonDeleteDisciplina;
    
    private BancoManager bm;
    private ControleDisciplina controleDisciplina;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_disciplina);
        
        textViewDisciplinaAluno = findViewById(R.id.textViewDisciplinaAluno);
        textViewDisciplina = findViewById(R.id.textViewDisciplina);
        imageButtonEditDisciplina = findViewById(R.id.imageButtonEditDisciplina);
        imageButtonDeleteDisciplina = findViewById(R.id.imageButtonDeleteDisciplina);

        Disciplina disciplina = (Disciplina) getIntent().getSerializableExtra("disciplina");
        
        if (disciplina != null) {
            String nomeAluno = "Aluno: " + disciplina.getAluno().getNome();
            textViewDisciplinaAluno.setText(nomeAluno);
            textViewDisciplina.setText(disciplina.getNome());
            
            bm = new BancoManager(this);
            SQLiteDatabase db = bm.open();
            controleDisciplina = new ControleDisciplina(db);
            
            Aluno aluno = disciplina.getAluno();
            
            imageButtonEditDisciplina.setOnClickListener(v -> {
                Intent intent = new Intent(this, SalvarDisciplinaActivity.class);
                intent.putExtra("disciplina", disciplina);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
                finish();
            });
            
            imageButtonDeleteDisciplina.setOnClickListener(v -> {
                Toast.makeText(this, controleDisciplina.deletarDisciplina(disciplina), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, DetalhesAlunoActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
                finish();
            });
        }
    }
}