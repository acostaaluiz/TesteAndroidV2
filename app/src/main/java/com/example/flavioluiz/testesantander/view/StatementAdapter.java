package com.example.flavioluiz.testesantander.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flavioluiz.testesantander.R;
import com.example.flavioluiz.testesantander.repository.response.Statement;

import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.StatementHolder>{

    List<Statement> myStatements;

    public StatementAdapter(List<Statement> myStatements){
        this.myStatements = myStatements;
    }

    @NonNull
    @Override
    public StatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.statement_list_layout, parent, false);

        StatementHolder rch = new StatementHolder(v);

        return rch;
    }

    @Override
    public void onBindViewHolder(@NonNull StatementHolder holder, int position) {

        Statement myStatement = this.myStatements.get(position);

        holder.txtTitleValue.setText(myStatement.getTitle());
        holder.txtDateValue.setText(myStatement.getDate());
        holder.txtDescValue.setText(myStatement.getDescription());
        holder.txtPriceValue.setText(myStatement.getValue());
    }

    @Override
    public int getItemCount() {
        return this.myStatements.size();
    }

    public static class StatementHolder extends RecyclerView.ViewHolder{

        public TextView txtTitleValue;
        public TextView txtDateValue;
        public TextView txtDescValue;
        public TextView txtPriceValue;

        public StatementHolder(View itemView) {
            super(itemView);

            txtTitleValue = itemView.findViewById(R.id.txtTitleValue);
            txtDateValue = itemView.findViewById(R.id.txtDateValue);
            txtDescValue = itemView.findViewById(R.id.txtDescValue);
            txtPriceValue = itemView.findViewById(R.id.txtPriceValue);
        }
    }
}
