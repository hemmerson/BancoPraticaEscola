package com.example.bancopratica.banco;

import android.provider.BaseColumns;

public final class BancoContract {
    private BancoContract (){}
    
    public static class AlunoTable implements BaseColumns {
        public static final String TABLE_NAME = "aluno";
        public static final String COLUMN_NAME = "nome";
    }
    
    public static class DisciplinaTable implements BaseColumns {
        public static final String TABLE_NAME = "disciplina";
        public static final String COLUMN_NAME = "nome";
        public static final String COLUMN_ALUNO_ID = "aluno_id";
    }
}
