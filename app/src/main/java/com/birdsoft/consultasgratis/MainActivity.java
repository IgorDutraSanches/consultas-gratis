package com.birdsoft.consultasgratis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.birdsoft.consultasgratis.activity.ConsultaActivity;
import com.birdsoft.consultasgratis.activity.CovidActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onTileClicked(View view) {
        LinearLayout linearLayout = (LinearLayout)view;
        if(!linearLayout.getTag().toString().equals(ConsultaType.covid.toString())) {
            Intent intent = new Intent(this, ConsultaActivity.class);
            intent.putExtra("consulta", linearLayout.getTag().toString());
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, CovidActivity.class);
            intent.putExtra("consulta", linearLayout.getTag().toString());
            startActivity(intent);
        }
    }

    public void onShare(View view) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "");
        intent.putExtra("android.intent.extra.TEXT", getPlainText());
        startActivity(Intent.createChooser(intent, "Consultas gratis"));
    }

    private String getPlainText() {
        String text = "Baixe este app de consultas gratis, consulte placa de veículos, cep, ip e muito mais.\n";
        return text + "https://play.google.com/store/apps/details?id=com.birdsoft.consultasgratis";
    }

    public void onShowGit(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/IgorDutraSanches/consultas_gratis")));
    }
}