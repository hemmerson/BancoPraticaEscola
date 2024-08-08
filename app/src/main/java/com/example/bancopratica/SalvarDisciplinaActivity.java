package com.example.bancopratica;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class SalvarDisciplinaActivity extends AppCompatActivity {
    
    private Disciplina disciplinaExistente;
    private Aluno aluno;
    
    private BancoManager bm;
    private ControleDisciplina controleDisciplina;
    
    private TextView textViewNovaDisciplina, textViewAlunoDisciplina;
    private EditText editTextNomeDisciplina;
    private Button buttonSalvarDisciplina;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_disciplina);

        textViewNovaDisciplina = findViewById(R.id.textViewNovaDisciplina);
        textViewAlunoDisciplina = findViewById(R.id.textViewAlunoDisciplina);
        editTextNomeDisciplina = findViewById(R.id.editTextNomeDisciplina);
        buttonSalvarDisciplina = findViewById(R.id.buttonSalvarDisciplina);
        
        bm = new BancoManager(this);
        SQLiteDatabase db = bm.open();
        controleDisciplina = new ControleDisciplina(db);
        
        disciplinaExistente = (Disciplina) getIntent().getSerializableExtra("disciplina");
        aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if (aluno == null) {
            Toast.makeText(this, "Selecione um Aluno", Toast.LENGTH_SHORT).show();
            finish();
        }

        String alunoNome = "Aluno: " + aluno.getNome();
        textViewAlunoDisciplina.setText(alunoNome);
        
        if (disciplinaExistente != null) {
            editTextNomeDisciplina.setText(disciplinaExistente.getNome());
            buttonSalvarDisciplina.setText(R.string.atualizar);
            textViewNovaDisciplina.setText(R.string.atualizar_disciplina);
        }
        
        buttonSalvarDisciplina.setOnClickListener(v -> {
            salvarOuAtualizarDisciplina();
        });
        
    }
    
    private void salvarOuAtualizarDisciplina(){
        String nome = editTextNomeDisciplina.getText().toString();
        if (!nome.isEmpty()){
            if (disciplinaExistente == null){
                Disciplina disciplina = new Disciplina(nome, aluno);
                Toast.makeText(this, controleDisciplina.adicionarDisciplina(disciplina), Toast.LENGTH_SHORT).show();
            }
            else {
                disciplinaExistente.setNome(nome);
                Toast.makeText(this, controleDisciplina.atualizarDisciplina(disciplinaExistente), Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(this, DetalhesAlunoActivity.class);
            intent.putExtra("aluno", aluno);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Por favor, insira um nome", Toast.LENGTH_SHORT).show();
        }
    }
}