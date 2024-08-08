package com.example.bancopratica.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BancoHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "escola.db";
    private static final int DATABASE_VERSION = 1;
    
    public BancoHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALUNO_TABLE = "CREATE TABLE " + BancoContract.AlunoTable.TABLE_NAME + "( "
                + BancoContract.AlunoTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BancoContract.AlunoTable.COLUMN_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_ALUNO_TABLE);
        
        String CREATE_DISCIPLINA_TABLE = "CREATE TABLE " + BancoContract.DisciplinaTable.TABLE_NAME + "( "
                + BancoContract.DisciplinaTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BancoContract.DisciplinaTable.COLUMN_NAME + " TEXT,"
                + BancoContract.DisciplinaTable.COLUMN_ALUNO_ID + " INTEGER,"
                + "FOREIGN KEY (" + BancoContract.DisciplinaTable.COLUMN_ALUNO_ID + " )"
                + "REFERENCES " + BancoContract.AlunoTable.TABLE_NAME + "(" + BancoContract.AlunoTable._ID + ")"
                + ")";
        db.execSQL(CREATE_DISCIPLINA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoContract.DisciplinaTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BancoContract.AlunoTable.TABLE_NAME);
        
        onCreate(db);
    }
}
