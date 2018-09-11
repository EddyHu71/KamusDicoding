package com.example.user.kamusdicoding;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Loader extends AppCompatActivity {
    @BindView(R.id.progressing)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        ButterKnife.bind(Loader.this);
        new LoadData().execute();

    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        KamusHelpEnglish kamusHelperEnglish;
        KamusHelpIndonesia kamusHelperIndonesia;
        Preferencing preferencing;
        double progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute() {
            preferencing = new Preferencing(Loader.this);
            kamusHelperIndonesia = new KamusHelpIndonesia(Loader.this);
            kamusHelperEnglish = new KamusHelpEnglish(Loader.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Boolean jalan = preferencing.getFirstRun();
            if (jalan) {
                ArrayList<Kamus> kamusItemIndonesia = preLoadRaw(1);
                ArrayList<Kamus> kamusItemEnglish = preLoadRaw(2);
                kamusHelperIndonesia.open();

                progress = 30;
                publishProgress((int) progress);
                Double progressMax = 80.0;
                Double progressDiff = (progressMax - progress) / (kamusItemEnglish.size() + kamusItemIndonesia.size());
                kamusHelperIndonesia.beginTransaction();

                try {
                    for (Kamus data : kamusItemIndonesia) {
                        kamusHelperEnglish.insertTransaction(data);
                        progress += progressDiff;
                        publishProgress((int) progress);
                        Log.d("TAG", "doInBackground executed");
                    }
                    kamusHelperIndonesia.setTransactionSuccess();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("TAG", "Loader doInBackground not found");
                }
                kamusHelperIndonesia.endTransaction();
                kamusHelperIndonesia.close();

                kamusHelperEnglish.open();
                kamusHelperEnglish.beginTransaction();
                try {

                    for (Kamus kata : kamusItemEnglish) {
                        kamusHelperEnglish.insertTransaction(kata);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }

                    kamusHelperEnglish.setTransactionSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("TAG", "Loader doInBackground not found");
                }

                kamusHelperEnglish.endTransaction();
                kamusHelperEnglish.close();
                preferencing.setFirstRun(false);
                publishProgress((int) maxprogress);

            }
            else {
                try {
                    synchronized (this) {
                        this.wait(2000);

                        // publishProgress(50);

                        this.wait(2000);

                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(Loader.this, MainActivity.class);
            startActivity(intent);
        }

        public ArrayList<Kamus> preLoadRaw(int id) {
            Log.d("TAG", "preLoadRaw executed");
            ArrayList<Kamus> kamusArrayList = new ArrayList<>();
            String line = null;
            BufferedReader bufferedReader;

            try {
                InputStream raw_dict = null;
                if (id == 1) {
                    raw_dict = getResources().openRawResource(R.raw.indonesia_english);
                } else if (id == 2) {
                    raw_dict = getResources().openRawResource(R.raw.english_indonesia);
                }
                bufferedReader = new BufferedReader(new InputStreamReader(raw_dict));
                int count = 0;
                do {
                    line = bufferedReader.readLine();
                    String[] splitstr = line.split("\t");
                    Kamus kata;
                    kata = new Kamus(splitstr[0], splitstr[1]);
                    Log.d("TAG", "preLoadRaw gets: " + kata.getJudul());
                    kamusArrayList.add(kata);
                    count++;

                } while (line != null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return kamusArrayList;
        }
    }
}
