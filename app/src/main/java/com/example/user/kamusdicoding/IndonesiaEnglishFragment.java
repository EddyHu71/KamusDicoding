package com.example.user.kamusdicoding;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 */
public class IndonesiaEnglishFragment extends Fragment {
    @BindView(R.id.search_kata_indonesia)
    SearchView searchView;

    @BindView(R.id.rv_indonesia)
    RecyclerView recyclerView;

    String kueri = null;
    public ArrayList<Kamus> listKamus = new ArrayList<>();
    KamusAdapter kamusAdapter;

    public IndonesiaEnglishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indonesia_english, container, false);
        kamusAdapter = new KamusAdapter(getContext());
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kamusAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(kamusAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                kueri = searchView.getQuery().toString();
                new LoadData().execute();
                return true;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public class LoadData extends AsyncTask<Void, Integer, Void> {
            KamusModel kamusModel = new KamusModel();

        @Override
        public Void doInBackground(Void... voids) {
            KamusHelpIndonesia kamusHelperIndonesia = new KamusHelpIndonesia(getContext());
            kamusHelperIndonesia.open();
            listKamus = kamusHelperIndonesia.getDataByName(kueri);
            kamusHelperIndonesia.close();
            Log.d("TAG", "doInBackground executed " +kamusAdapter.getItemCount());
            return null;
        }
        @Override

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            kamusAdapter.setKamus(listKamus);
            Log.d("TAG","onPostExecuted executed "+kamusAdapter.getItemCount());
            kamusAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
