package com.example.bancopratica;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bancopratica.banco.BancoManager;
import com.example.bancopratica.controle.ControleAluno;
import com.example.bancopratica.entity.Aluno;

public class InserirAlunoActivity extends AppCompatActivity {
    
    private Aluno alunoExistente;

    private BancoManager dm;
    private ControleAluno controle;
    
    private EditText editTextNomeAluno;
    private Button buttonInserirAluno;
    private TextView textViewNovoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_aluno);

        editTextNomeAluno = findViewById(R.id.editTextNomeAluno);
        buttonInserirAluno = findViewById(R.id.buttonInserirAluno);
        textViewNovoAluno = findViewById(R.id.textViewNovoAluno);

        dm = new BancoManager(this);
        SQLiteDatabase db = dm.open();
        controle = new ControleAluno(db);
        
        alunoExistente = (Aluno) getIntent().getSerializableExtra("aluno");
        
        if (alunoExistente != null) {
            editTextNomeAluno.setText(alunoExistente.getNome());
            buttonInserirAluno.setText(R.string.atualizar);
            textViewNovoAluno.setText(R.string.atualizar_aluno);
        }
        
        buttonInserirAluno.setOnClickListener(v -> {
            salvarOuAtualizarAluno();
        });
    }
    
    private void salvarOuAtualizarAluno(){
        String nome = editTextNomeAluno.getText().toString();
        if (!nome.isEmpty()){
            if (alunoExistente == null){
                Aluno novoAluno = new Aluno(nome);
                Toast.makeText(this, controle.adicionarAluno(novoAluno), Toast.LENGTH_SHORT).show();
            }
            else {
                alunoExistente.setNome(nome);
                Toast.makeText(this, controle.atualizarAluno(alunoExistente), Toast.LENGTH_SHORT).show();
            }
            
            Intent intent = new Intent(this, ListarAlunoActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Por favor, insira um nome", Toast.LENGTH_SHORT).show();
        }
    }
}