package com.example.user.kamusdicoding;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.support.v7.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishIndonesiaFragment extends Fragment {
    @BindView(R.id.search_kata_english)
    SearchView searchView;

    @BindView(R.id.rv_english)
    RecyclerView recyclerView;

    public ArrayList<Kamus> list = new ArrayList<>();
    KamusAdapter kamusAdapter;
    String kueri;

    public EnglishIndonesiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english_indonesia, container, false);

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
                new LoadAdapter().execute();
                return true;
            }
        });

        return  view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public class LoadAdapter extends AsyncTask<Void, Integer, Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            KamusHelpEnglish kamusHelpEnglish = new KamusHelpEnglish(getContext());
            kamusHelpEnglish.open();
            list = kamusHelpEnglish.getDataByName(kueri);
            kamusHelpEnglish.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            kamusAdapter.setKamus(list);
            Log.d("TAG", "onPostExecute: " + kamusAdapter.getItemCount());
            Log.d("TAG", "doInBackground: " + kamusAdapter.getItemCount());
            kamusAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
