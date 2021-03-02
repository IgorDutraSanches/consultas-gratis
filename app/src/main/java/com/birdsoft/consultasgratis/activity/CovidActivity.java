package com.birdsoft.consultasgratis.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.birdsoft.consultasgratis.ConsultaType;
import com.birdsoft.consultasgratis.R;
import com.birdsoft.consultasgratis.Tools;
import com.birdsoft.consultasgratis.model.Covid;
import com.birdsoft.consultasgratis.model.Placa;
import com.birdsoft.consultasgratis.viewModel.APIConnectionViewModel;

import java.util.HashMap;
import java.util.Map;

public class CovidActivity extends AppCompatActivity {
    private TextView search_bar;
    private APIConnectionViewModel viewModel;
    private LinearLayout layoutResult, falhaLayout, inicioLayout;
    private ProgressBar progress_circular;
    private ConsultaType consultaType;
    private TextView result_text, error_text;
    private String uf = "";
    private int position = 0;
    private boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);
        viewModel = new ViewModelProvider(this).get(APIConnectionViewModel.class);
        ((Toolbar)findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        inicioLayout = findViewById(R.id.InicioLayout);
        error_text = findViewById(R.id.error_text);
        falhaLayout = findViewById(R.id.falhaLayout);
        progress_circular = findViewById(R.id.progress_circular);
        layoutResult = findViewById(R.id.layoutResult);
        search_bar = findViewById(R.id.search_bar);
        result_text = findViewById(R.id.result_text);
        consultaType = ConsultaType.valueOf(getIntent().getStringExtra("consulta"));
        Tools.toolbar(consultaType, ((Toolbar)findViewById(R.id.toolbar)), search_bar);
    }

    public void onBarSearch(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        String[] ufs = new String[]{"SP","MG","BA","SC","PR","RS","RJ","CE","GO","PA","ES","AM","PE","DF","MT","PB","MA","MS","PI","RN","SE","RO","AL","TO","AP","RR","AC"};
        String[] estados = new String[]{"São Paulo","Mainas Gerais","Bahia","Santa Catarina","Paraná","Rio Grande do Sul","Rio de Janeiro","Ceará","Goiás","Pará","Espírito Santo","Amazonas","Pernambuco","Destrito Federal","Mato Grosso","Paraiba","Maranhão","Mato Grosso do Sul","Piauí","Rio Grande do Norte","Sergipe","Rondônia","Alagoas","Tocantins","Amapá","Roraima","Acre"};
        dialog.setSingleChoiceItems(estados, position, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                position = which;
                isSelected = true;
                uf = ufs[which];
                dialog.dismiss();
                search_bar.setText(estados[which]);
            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void onClickSearch(View view) {
        if(isSelected){
            falhaLayout.setVisibility(View.GONE);
            layoutResult.setVisibility(View.GONE);
            inicioLayout.setVisibility(View.GONE);
            progress_circular.setVisibility(View.VISIBLE);
            error_text.setText("");
            viewModel.getCovid(uf, this).observe(this, new Observer<Covid>() {
                @Override
                public void onChanged(Covid covid) {
                    if(covid != null){
                        String content = "";
                        if(covid.getState() != null){
                            if(!covid.getState().equals("")){
                                content += "Estado: " + covid.getState() + "\n\n";
                            }
                        }
                        if(covid.getUf() != null){
                            if(!covid.getUf().equals("")){
                                content += "UF: " + covid.getUf() + "\n\n";
                            }
                        }
                        if(covid.getCases() != null){
                            if(!covid.getCases().equals("")){
                                content += "Casos: "+covid.getCases() + "\n\n";
                            }
                        }
                        if(covid.getDeaths() != null){
                            if(!covid.getDeaths().equals("")){
                                content += "Mortes: "+covid.getDeaths() + "\n\n";
                            }
                        }
                        if(covid.getSuspects() != null){
                            if(!covid.getSuspects().equals("")){
                                content += "Suspeitos: "+covid.getSuspects() + "\n\n";
                            }
                        }
                        if(covid.getRefuses() != null){
                            if(!covid.getRefuses().equals("")){
                                content += "Recusados: "+covid.getRefuses();
                            }
                        }

                        if(!content.equals("")){
                            result_text.setText(content);
                            progress_circular.setVisibility(View.GONE);
                            falhaLayout.setVisibility(View.GONE);
                            inicioLayout.setVisibility(View.GONE);
                            layoutResult.setVisibility(View.VISIBLE);
                            error_text.setText("");
                        }else{
                            progress_circular.setVisibility(View.GONE);
                            layoutResult.setVisibility(View.GONE);
                            inicioLayout.setVisibility(View.GONE);
                            falhaLayout.setVisibility(View.VISIBLE);
                            error_text.setText("Não foi encontrado nada referente ao estado selecionado!");
                        }
                    }else{
                        progress_circular.setVisibility(View.GONE);
                        layoutResult.setVisibility(View.GONE);
                        inicioLayout.setVisibility(View.GONE);
                        falhaLayout.setVisibility(View.VISIBLE);
                        error_text.setText("Não foi encontrado nada referente ao estado selecionado!");
                    }
                }
            });

        }else{
            Toast.makeText(this, "Selecione um estado primeiro", Toast.LENGTH_SHORT).show();
        }
    }
}