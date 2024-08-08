package com.example.bancopratica.entity;

import java.io.Serializable;

public class Disciplina implements Serializable {
    
    private Long id;
    private String nome;
    private Aluno aluno;
    
    public Disciplina(){}

    public Disciplina(String nome, Aluno aluno) {
        this.nome = nome;
        this.aluno = aluno;
    }

    public Disciplina(Long id, String nome, Aluno aluno) {
        this.id = id;
        this.nome = nome;
        this.aluno = aluno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", aluno=" + aluno +
                '}';
    }
}
