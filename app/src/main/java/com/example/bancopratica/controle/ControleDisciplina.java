package com.example.bancopratica.controle;

import android.database.sqlite.SQLiteDatabase;

import com.example.bancopratica.dao.AlunoDAO;
import com.example.bancopratica.dao.DisciplinaDAO;
import com.example.bancopratica.entity.Aluno;
import com.example.bancopratica.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class ControleDisciplina {
    
    private AlunoDAO alunoDAO;
    private DisciplinaDAO disciplinaDAO;
    private SQLiteDatabase db;

    public ControleDisciplina(SQLiteDatabase db) {
        this.db = db;
        alunoDAO = new AlunoDAO(db);
        disciplinaDAO = new DisciplinaDAO(db);
    }
    
    public String adicionarDisciplina(Disciplina disciplina){
        if (disciplinaDAO.inserirDisciplina(disciplina) > 0L)
            return "Cadastrado com Sucesso!";
        return "Erro ao cadastrar!";
    }
    
    public List<Disciplina> consultarDisciplinasPorAluno(Aluno aluno){
        return disciplinaDAO.listarDisciplinas(aluno);
    }

    public String atualizarDisciplina(Disciplina disciplina) {
        if (disciplinaDAO.atualizarDisciplina(disciplina) > 0) {
            return "Atualizado com sucesso!";
        }
        return "Erro ao atualizar";
    }

    public String deletarDisciplina(Disciplina disciplina) {
        if (disciplinaDAO.deletarDisciplina(disciplina) > 0) {
            return "Deletada com sucesso!";
        }
        return "Erro ao deletar!";
    }
}
