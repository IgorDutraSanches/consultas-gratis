package com.birdsoft.consultasgratis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.birdsoft.consultasgratis.activity.ConsultaActivity;
import com.birdsoft.consultasgratis.activity.CovidActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer_layout;
    private long count = 0;

    @Override
    public void onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START);
            return;
        }
        if(System.currentTimeMillis() - count > 2000){
            count = System.currentTimeMillis();
            Toast.makeText(this, "Clique novamente para sair", Toast.LENGTH_SHORT).show();
            return;
        }
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer_layout = findViewById(R.id.drawer_layout);
        ((Toolbar)findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               drawer_layout.openDrawer(GravityCompat.START);
            }
        });

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
