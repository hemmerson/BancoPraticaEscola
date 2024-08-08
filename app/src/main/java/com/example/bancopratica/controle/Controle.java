package com.example.bancopratica.controle;

import android.database.sqlite.SQLiteDatabase;

import com.example.bancopratica.dao.AlunoDAO;
import com.example.bancopratica.dao.DisciplinaDAO;
import com.example.bancopratica.entity.Aluno;
import com.example.bancopratica.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class Controle {
    
    private AlunoDAO alunoDAO;
    private DisciplinaDAO disciplinaDAO;
    private SQLiteDatabase db;

    public Controle(SQLiteDatabase db) {
        this.db = db;
        alunoDAO = new AlunoDAO(db);
        disciplinaDAO = new DisciplinaDAO(db);
    }
    
    public List<Long> adicionarAluno(){
        Aluno aluno;
        List<Long> ids = new ArrayList<>();
        
        aluno = new Aluno("Hemmerson");
        Long alunoId = alunoDAO.adicionarAluno(aluno);
        ids.add(alunoId);
        
        aluno = new Aluno("Miguel");
        alunoId = alunoDAO.adicionarAluno(aluno);
        ids.add(alunoId);
        
        return ids;
    }
    
    public String consultarAlunos(){
        List<Aluno> alunos = alunoDAO.obterAlunos();
        return alunos.toString();
    }
    
    public String consultarAlunoPorId(Long id){
        Aluno aluno = alunoDAO.obterAlunoPorId(id);
        return aluno.toString();
    }
    
    public String atualizarAluno(){
        List<Aluno> alunos = alunoDAO.obterAlunos();
        for(Aluno aluno: alunos){
            if (aluno.getNome().equals("Hemmerson")){
                aluno.setNome("Hemmerson Rosa");
                if (alunoDAO.atualizarAluno(aluno) > 0) {
                    return "Atualizado com sucesso!";
                }
            }
        }
        return "Erro ao atualizar";
    }
    
    public String deletarAluno(){
        List<Aluno> alunos = alunoDAO.obterAlunos();
        if (alunoDAO.deletarAluno(alunos.get(1)) > 0){
            return "Deletado com sucesso!";
        }
        return "Erro ao deletar!";
    }
    
    //Disciplina ==============
    
    public List<Long> adicionarDisciplinas(){
        Disciplina disciplina;
        List<Long> ids = new ArrayList<>();
        List<Aluno> alunos = alunoDAO.obterAlunos();
        
        disciplina = new Disciplina("MAT101", alunos.get(0));
        Long disciplinaId = disciplinaDAO.inserirDisciplina(disciplina);
        ids.add(disciplinaId);
        
        disciplina = new Disciplina("BIO101", alunos.get(0));
        disciplinaId = disciplinaDAO.inserirDisciplina(disciplina);
        ids.add(disciplinaId);
        
        return ids;
    }
    
    public String consultarDisciplinasPorAluno(){
        List<Aluno> alunos = alunoDAO.obterAlunos();
        List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas(alunos.get(0));
        return disciplinas.toString();
    }
}
