package com.example.bancopratica.controle;

import android.database.sqlite.SQLiteDatabase;

import com.example.bancopratica.dao.AlunoDAO;
import com.example.bancopratica.dao.DisciplinaDAO;
import com.example.bancopratica.entity.Aluno;
import com.example.bancopratica.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class ControleAluno {

    private final AlunoDAO alunoDAO;

    public ControleAluno(SQLiteDatabase db) {
        alunoDAO = new AlunoDAO(db);
    }

    public String adicionarAluno(Aluno aluno) {
        if (alunoDAO.adicionarAluno(aluno) > 0L)
            return "Cadastrado com Sucesso!";
        return "Erro ao cadastrar!";
    }

    public List<Aluno> consultarAlunos() {
        return alunoDAO.obterAlunos();
    }

    public Aluno consultarAlunoPorId(Long id) {
        return alunoDAO.obterAlunoPorId(id);
    }

    public String atualizarAluno(Aluno aluno) {
        if (alunoDAO.atualizarAluno(aluno) > 0) {
            return "Atualizado com sucesso!";
        }
        return "Erro ao atualizar";
    }

    public String deletarAluno(Aluno aluno) {
        if (alunoDAO.deletarAluno(aluno) > 0) {
            return "Deletado com sucesso!";
        }
        return "Erro ao deletar!";
    }

}
