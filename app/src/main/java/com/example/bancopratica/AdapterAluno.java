package com.example.bancopratica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bancopratica.entity.Aluno;

import java.util.List;

public class AdapterAluno extends RecyclerView.Adapter<AdapterAluno.MyViewHolder> {
    
    private List<Aluno> alunoList;
    private OnClick onClick;

    public AdapterAluno(List<Aluno> alunoList, OnClick onClick) {
        this.alunoList = alunoList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_aluno, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Aluno aluno = alunoList.get(position);
        
        holder.textViewIdAluno.setText( String.valueOf(aluno.getId()));
        holder.textViewNomeALuno.setText(aluno.getNome());
        
        holder.itemView.setOnClickListener(view -> onClick.onClickListener(aluno));
    }

    @Override
    public int getItemCount() {
        return alunoList.size();
    }
    
    public interface OnClick{
        void onClickListener(Aluno aluno);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        
        TextView textViewIdAluno, textViewNomeALuno;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIdAluno = itemView.findViewById(R.id.textViewIdAluno);
            textViewNomeALuno = itemView.findViewById(R.id.textViewNomeAluno);
        }
    }
}
