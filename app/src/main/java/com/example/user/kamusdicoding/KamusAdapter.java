package com.example.user.kamusdicoding;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.MyViewHolder>{
    public KamusAdapter(Context context) {
        this.context = context;
    }

    private Context context;
    private ArrayList<Kamus> kamus = new ArrayList<>();
    public static final String KAMUS = "Kamus";

    public void setKamus(ArrayList<Kamus> kamus) {
        this.kamus = kamus;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kamus_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KamusAdapter.MyViewHolder holder, final int position) {
        holder.judul.setText(kamus.get(position).getJudul());
        holder.judul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("Kamus", kamus.get(position));
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return kamus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.judul)
        TextView judul;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
