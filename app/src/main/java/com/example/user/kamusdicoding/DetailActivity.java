package com.example.user.kamusdicoding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_judul)
    TextView judul_detail;
    @BindView(R.id.detail_terjemahan)
    TextView terjemahan_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Kamus kamus = getIntent().getParcelableExtra(KamusAdapter.KAMUS);
        judul_detail.setText(kamus.getJudul());
        terjemahan_detail.setText(kamus.getTerjemahan());

    }
}
