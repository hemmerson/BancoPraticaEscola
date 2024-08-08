package com.example.bancopratica.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bancopratica.banco.BancoContract;
import com.example.bancopratica.entity.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private SQLiteDatabase db;

    public AlunoDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public Long adicionarAluno(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put(BancoContract.AlunoTable.COLUMN_NAME, aluno.getNome());
        return db.insert(BancoContract.AlunoTable.TABLE_NAME, null, values);
    }

    public List<Aluno> obterAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + BancoContract.AlunoTable.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndexOrThrow(BancoContract.AlunoTable._ID)));
            aluno.setNome(cursor.getString(cursor.getColumnIndexOrThrow(BancoContract.AlunoTable.COLUMN_NAME)));

            alunos.add(aluno);

        }
        cursor.close();

        return alunos;
    }

    public Aluno obterAlunoPorId(Long id) {
        Aluno aluno = new Aluno();
        String selectQuery = "SELECT * FROM " + BancoContract.AlunoTable.TABLE_NAME + " WHERE "
                + BancoContract.AlunoTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);

        if (cursor.moveToNext()) {
            aluno.setId(id);
            aluno.setNome(cursor.getString(cursor.getColumnIndexOrThrow(BancoContract.AlunoTable.COLUMN_NAME)));
        }
        cursor.close();

        return aluno;
    }
    
    public int atualizarAluno(Aluno aluno){
        String whereClause = BancoContract.AlunoTable._ID + " = ?";
        String[] whereArgs = { String.valueOf(aluno.getId()) };
        ContentValues values = new ContentValues();
        values.put(BancoContract.AlunoTable.COLUMN_NAME, aluno.getNome());
        
        return db.update(BancoContract.AlunoTable.TABLE_NAME,
                values,
                whereClause,
                whereArgs);
    }

    public int deletarAluno(Aluno aluno) {
        String whereClause = BancoContract.AlunoTable._ID + "=?";
        String[] whereArgs = {String.valueOf(aluno.getId())};
        return db.delete(BancoContract.AlunoTable.TABLE_NAME, whereClause, whereArgs);
    }

}
