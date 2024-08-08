package com.example.bancopratica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bancopratica.entity.Disciplina;

import java.util.List;

public class AdapterDisciplina extends RecyclerView.Adapter<AdapterDisciplina.MyViewHolder> {
    
    private List<Disciplina> disciplinaList;
    private OnCLick onCLick;

    public AdapterDisciplina(List<Disciplina> disciplinaList, OnCLick onCLick) {
        this.disciplinaList = disciplinaList;
        this.onCLick = onCLick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_disciplinas, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Disciplina disciplina = disciplinaList.get(position);
        
        holder.textViewIdDisciplina.setText( String.valueOf(disciplina.getId()));
        holder.textViewNomeDisciplina.setText(disciplina.getNome());
        
        holder.itemView.setOnClickListener(view -> onCLick.onClickListener(disciplina));
    }

    @Override
    public int getItemCount() {
        return disciplinaList.size();
    }
    
    public interface OnCLick{
        void onClickListener(Disciplina disciplina);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        
        TextView textViewIdDisciplina, textViewNomeDisciplina;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIdDisciplina = itemView.findViewById(R.id.textViewIdDisciplina);
            textViewNomeDisciplina = itemView.findViewById(R.id.textViewNomeDisciplina);
        }
    }
}
