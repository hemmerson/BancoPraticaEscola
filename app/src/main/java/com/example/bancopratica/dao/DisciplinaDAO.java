package com.example.bancopratica.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bancopratica.banco.BancoContract;
import com.example.bancopratica.banco.BancoManager;
import com.example.bancopratica.entity.Aluno;
import com.example.bancopratica.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    
    private SQLiteDatabase db;
    
    public DisciplinaDAO(SQLiteDatabase db) { this.db = db; }
    
    public Long inserirDisciplina(Disciplina disciplina){
        ContentValues values = new ContentValues();
        values.put(BancoContract.DisciplinaTable.COLUMN_NAME, disciplina.getNome());
        values.put(BancoContract.DisciplinaTable.COLUMN_ALUNO_ID, disciplina.getAluno().getId());

        return db.insert(BancoContract.DisciplinaTable.TABLE_NAME, null, values);
    }
    
    public List<Disciplina> listarDisciplinas(Aluno aluno){
        List<Disciplina> disciplinas = new ArrayList<>();
        String selection = BancoContract.DisciplinaTable.COLUMN_ALUNO_ID + " = ?";
        String[] selectionArgs = { String.valueOf(aluno.getId()) };
        Cursor cursor = db.query(
          BancoContract.DisciplinaTable.TABLE_NAME,
          null,
          selection,
          selectionArgs,
          null,
          null,
          null      
        );
        
        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(BancoContract.DisciplinaTable._ID));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoContract.DisciplinaTable.COLUMN_NAME));
            disciplinas.add(new Disciplina(id, nome, aluno));
        }
        cursor.close();
        
        return disciplinas;
    }
    
    public int atualizarDisciplina(Disciplina disciplina){
        String whereClause = BancoContract.DisciplinaTable._ID + " = ?";
        String[] whereArgs = { String.valueOf(disciplina.getId()) };
        ContentValues values = new ContentValues();
        values.put(BancoContract.DisciplinaTable.COLUMN_NAME, disciplina.getNome());
        return db.update(BancoContract.DisciplinaTable.TABLE_NAME,
                values,
                whereClause,
                whereArgs);
    }
    
    public int deletarDisciplina(Disciplina disciplina){
        String whereClause = BancoContract.DisciplinaTable._ID + " = ?";
        String[] whereArgs = { String.valueOf(disciplina.getId()) };
        return db.delete(BancoContract.DisciplinaTable.TABLE_NAME,
                whereClause, whereArgs);
    }
}
