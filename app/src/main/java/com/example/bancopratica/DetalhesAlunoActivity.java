package com.example.bancopratica;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bancopratica.banco.BancoManager;
import com.example.bancopratica.controle.ControleAluno;
import com.example.bancopratica.controle.ControleDisciplina;
import com.example.bancopratica.entity.Aluno;
import com.example.bancopratica.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DetalhesAlunoActivity extends AppCompatActivity implements AdapterDisciplina.OnCLick {
    
    private TextView textViewAluno;
    private ImageButton imageButtonEditAluno, imageButtonDeleteAluno;
    private RecyclerView recyclerViewDisciplina;
    private Button buttonNovaDisciplina;
    
    private List<Disciplina> disciplinas = new ArrayList<>();
    private BancoManager bm;
    private ControleAluno controleAluno;
    private ControleDisciplina controleDisciplina;
    
    private AdapterDisciplina adapterDisciplina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_aluno);
        
        textViewAluno = findViewById(R.id.textViewAluno);
        imageButtonEditAluno = findViewById(R.id.imageButtonEditAluno);
        imageButtonDeleteAluno = findViewById(R.id.imageButtonDeleteAluno);
        recyclerViewDisciplina = findViewById(R.id.recyclerViewDisciplinas);
        buttonNovaDisciplina = findViewById(R.id.buttonNovaDisciplina);

        Aluno aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if (aluno != null) {
            textViewAluno.setText(aluno.getNome());
            
            bm = new BancoManager(this);
            SQLiteDatabase db = bm.open();
            controleAluno = new ControleAluno(db);
            controleDisciplina = new ControleDisciplina(db);
            
            imageButtonEditAluno.setOnClickListener(v -> {
                Intent intent = new Intent(this, InserirAlunoActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
                finish();
            });
            
            imageButtonDeleteAluno.setOnClickListener(v -> {
                Toast.makeText(this, controleAluno.deletarAluno(aluno), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ListarAlunoActivity.class);
                startActivity(intent);
                finish();
            });
            
            carregaLista(aluno);
            configRecyclerView();
        }
        
        buttonNovaDisciplina.setOnClickListener(v -> {
            Intent intent = new Intent(this, SalvarDisciplinaActivity.class);
            intent.putExtra("aluno", aluno);
            startActivity(intent);
            finish();
        });
    }
    
    private void carregaLista(Aluno aluno){
        disciplinas = controleDisciplina.consultarDisciplinasPorAluno(aluno);
    }

    private void configRecyclerView(){
        recyclerViewDisciplina.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDisciplina.setHasFixedSize(true);

        adapterDisciplina = new AdapterDisciplina(disciplinas, this);
        recyclerViewDisciplina.setAdapter(adapterDisciplina);
    }

    @Override
    public void onClickListener(Disciplina disciplina) {
        Intent intent = new Intent(this, DetalhesDisciplinaActivity.class);
        intent.putExtra("disciplina", disciplina);
        startActivity(intent);
        finish();
    }
}