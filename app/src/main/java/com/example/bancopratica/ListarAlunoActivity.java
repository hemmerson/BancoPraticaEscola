package com.example.bancopratica;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bancopratica.banco.BancoManager;
import com.example.bancopratica.controle.Controle;
import com.example.bancopratica.controle.ControleAluno;
import com.example.bancopratica.entity.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListarAlunoActivity extends AppCompatActivity implements AdapterAluno.OnClick {
    
    private List<Aluno> alunos = new ArrayList<>();
    
    private BancoManager bm;
    private ControleAluno controleAluno;
    
    private AdapterAluno adapterAluno;
    
    private RecyclerView recyclerViewAlunos;
    private Button buttonNovoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_aluno);

        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos);
        buttonNovoAluno = findViewById(R.id.buttonNovoAluno);
        
        bm = new BancoManager(this);
        SQLiteDatabase db = bm.open();
        controleAluno = new ControleAluno(db);
        
        carregaListaAlunos();
        configRecyclerView();
        
        buttonNovoAluno.setOnClickListener(v -> {
            Intent intent = new Intent(this, InserirAlunoActivity.class);
            startActivity(intent);
        });
    }
    
    private void configRecyclerView(){
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlunos.setHasFixedSize(true);
        
        adapterAluno = new AdapterAluno(alunos, this);
        recyclerViewAlunos.setAdapter(adapterAluno);
    }
    
    private void carregaListaAlunos(){
        alunos = controleAluno.consultarAlunos();
    }

    @Override
    public void onClickListener(Aluno aluno) {
        Intent intent = new Intent(this, DetalhesAlunoActivity.class);
        intent.putExtra("aluno", aluno);
        startActivity(intent);
    }
}