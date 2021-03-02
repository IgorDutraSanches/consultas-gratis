package com.birdsoft.consultasgratis.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.birdsoft.consultasgratis.model.Banco;
import com.birdsoft.consultasgratis.model.CEP;
import com.birdsoft.consultasgratis.model.CNPJ;
import com.birdsoft.consultasgratis.model.DDD;
import com.birdsoft.consultasgratis.model.IP;
import com.birdsoft.consultasgratis.model.MD;
import com.birdsoft.consultasgratis.model.Placa;
import com.birdsoft.consultasgratis.model.Whois;
import com.birdsoft.consultasgratis.viewModel.APIConnectionViewModel;

public class ConsultaActivity extends AppCompatActivity {

    private EditText search_bar;
    private APIConnectionViewModel viewModel;
    private LinearLayout layoutResult, falhaLayout, inicioLayout;
    private ProgressBar progress_circular;
    private ConsultaType consultaType;
    private TextView result_text, error_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
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
        Tools.injector(consultaType, search_bar);
        Tools.toolbar(consultaType, ((Toolbar)findViewById(R.id.toolbar)), search_bar);
    }

    public void onClickSearch(View view) {
        String str = search_bar.getText().toString().trim().toLowerCase();
        switch (consultaType){
            case placa:
                if(!str.equals("")){
                    falhaLayout.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.GONE);
                    inicioLayout.setVisibility(View.GONE);
                    progress_circular.setVisibility(View.VISIBLE);
                    error_text.setText("");
                    viewModel.getPlaca(Tools.convertToStringTrim(str, consultaType), this).observe(this, new Observer<Placa>() {
                        @Override
                        public void onChanged(Placa placa) {
                            if(placa != null){
                                String content = "";
                                if(placa.getAno() != null){
                                    if(!placa.getAno().equals("")){
                                        content += "Ano: " + placa.getAno() + "\n\n";
                                    }
                                }
                                if(placa.getAnoModelo() != null){
                                    if(!placa.getAnoModelo().equals("")){
                                        content += "Ano Modelo: " + placa.getAnoModelo() + "\n\n";
                                    }
                                }
                                if(placa.getChassi() != null){
                                    if(!placa.getChassi().equals("")){
                                        content += "Chassi: "+placa.getChassi() + "\n\n";
                                    }
                                }
                                if(placa.getCor() != null){
                                    if(!placa.getCor().equals("")){
                                        content += "Cor: "+placa.getCor() + "\n\n";
                                    }
                                }
                                if(placa.getMarca() != null){
                                    if(!placa.getMarca().equals("")){
                                        content += "Marca: "+placa.getMarca() + "\n\n";
                                    }
                                }
                                if(placa.getData() != null){
                                    if(!placa.getData().equals("")){
                                        content += "Data: "+placa.getData() + "\n\n";
                                    }
                                }

                                if(placa.getMunicipio() != null){
                                    if(!placa.getMunicipio().equals("")){
                                        content += "Municipio: "+placa.getMunicipio();
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
                                    error_text.setText("Não foi encontrado nada referente a placa digitada!");
                                }
                            }else{
                                progress_circular.setVisibility(View.GONE);
                                layoutResult.setVisibility(View.GONE);
                                inicioLayout.setVisibility(View.GONE);
                                falhaLayout.setVisibility(View.VISIBLE);
                                error_text.setText("Não foi encontrado nada referente a placa digitada!");
                            }
                        }
                    });
                }else
                    Toast.makeText(this, "Digite a placa, para continuar", Toast.LENGTH_SHORT).show();
                break;
            case bank:
                if(!str.equals("")){
                    falhaLayout.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.GONE);
                    inicioLayout.setVisibility(View.GONE);
                    progress_circular.setVisibility(View.VISIBLE);
                    error_text.setText("");
                    viewModel.getBanco(Tools.convertToStringTrim(str, consultaType), this).observe(this, new Observer<Banco>() {
                        @Override
                        public void onChanged(Banco banco) {
                            if(banco != null){
                                String content = "";
                                if(banco.getCode() != null){
                                    if(!banco.getCode().equals("")){
                                        content += "Código bancário: " + banco.getCode() + "\n\n";
                                    }
                                }
                                if(banco.getName() != null){
                                    if(!banco.getName().equals("")){
                                        content += "Nome: " + banco.getName() + "\n\n";
                                    }
                                }
                                if(banco.getFullName() != null){
                                    if(!banco.getFullName().equals("")){
                                        content += "Nome completo: "+banco.getFullName() + "\n\n";
                                    }
                                }
                                if(banco.getIspb() != null){
                                    if(!banco.getIspb().equals("")) {
                                        content += "ISPB: " + banco.getIspb();
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
                                    error_text.setText("Não foi encontrado nada referente ao código do banco digitado!");
                                }
                            }else{
                                progress_circular.setVisibility(View.GONE);
                                layoutResult.setVisibility(View.GONE);
                                inicioLayout.setVisibility(View.GONE);
                                falhaLayout.setVisibility(View.VISIBLE);
                                error_text.setText("Não foi encontrado nada referente ao código do banco digitado!");
                            }
                        }
                    });
                }else
                    Toast.makeText(this, "Digite o código do banco, para continuar", Toast.LENGTH_SHORT).show();
                break;
            case cnpj:
                if(!str.equals("")){
                    falhaLayout.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.GONE);
                    inicioLayout.setVisibility(View.GONE);
                    progress_circular.setVisibility(View.VISIBLE);
                    error_text.setText("");
                    viewModel.getCpnj(Tools.convertToStringTrim(str, consultaType), this).observe(this, new Observer<CNPJ>() {
                        @Override
                        public void onChanged(CNPJ cnpj) {
                            if(cnpj != null){
                                String content = "";
                                if(cnpj.getCnpj() != null){
                                    if(!cnpj.getCnpj().equals("")){
                                        content += "CNPJ: " + cnpj.getCnpj() + "\n\n";
                                    }
                                }
                                if(cnpj.getNome() != null){
                                    if(!cnpj.getNome().equals("")){
                                        content += "Nome: " + cnpj.getNome() + "\n\n";
                                    }
                                }
                                if(cnpj.getCep() != null){
                                    if(!cnpj.getCep().equals("")){
                                        content += "CEP: "+cnpj.getCep() + "\n\n";
                                    }
                                }
                                if(cnpj.getTelefone() != null){
                                    if(!cnpj.getTelefone().equals("")) {
                                        content += "Telefone: " + cnpj.getTelefone() + "\n\n";
                                    }
                                }

                                if(cnpj.getEmail() != null){
                                    if(!cnpj.getEmail().equals("")){
                                        content += "E-mail: "+cnpj.getEmail() + "\n\n";
                                    }
                                }

                                if(cnpj.getSituacao() != null){
                                    if(!cnpj.getSituacao().equals("")){
                                        content += "Situação: "+cnpj.getSituacao();
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
                                    error_text.setText("Não foi encontrado nada referente ao Cnpj digitado!");
                                }
                            }else{
                                progress_circular.setVisibility(View.GONE);
                                layoutResult.setVisibility(View.GONE);
                                inicioLayout.setVisibility(View.GONE);
                                falhaLayout.setVisibility(View.VISIBLE);
                                error_text.setText("Não foi encontrado nada referente ao Cnpj digitado!");
                            }
                        }
                    });
                }else
                    Toast.makeText(this, "Digite o Cnpj, para continuar", Toast.LENGTH_SHORT).show();
                break;
            case whois:
                if(!str.equals("")){
                    falhaLayout.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.GONE);
                    inicioLayout.setVisibility(View.GONE);
                    progress_circular.setVisibility(View.VISIBLE);
                    error_text.setText("");
                    viewModel.getWhois(Tools.convertToStringTrim(str, consultaType), this).observe(this, new Observer<Whois>() {
                        @Override
                        public void onChanged(Whois whois) {
                            if(whois != null){
                                String content = "";
                                if(whois.getZip() != null){
                                    if(!whois.getZip().equals("")){
                                        content += "IP: " + whois.getZip() + "\n\n";
                                    }
                                }
                                if(whois.getCountry() != null){
                                    if(!whois.getCountry().equals("")){
                                        content += "País: " + whois.getCountry() + "\n\n";
                                    }
                                }
                                if(whois.getCountryCode() != null){
                                    if(!whois.getCountryCode().equals("")){
                                        content += "Código do país: "+whois.getCountryCode() + "\n\n";
                                    }
                                }
                                if(whois.getRegion() != null){
                                    if(!whois.getRegion().equals("")) {
                                        content += "Região: " + whois.getRegion() + "\n\n";
                                    }
                                }

                                if(whois.getRegionName() != null){
                                    if(!whois.getRegionName().equals("")){
                                        content += "Nome da Região: "+whois.getRegionName() + "\n\n";
                                    }
                                }

                                if(whois.getCity() != null){
                                    if(!whois.getCity().equals("")){
                                        content += "Cidade: "+whois.getCity()+"\n\n";
                                    }
                                }

                                if(whois.getZip() != null){
                                    if(!whois.getZip().equals("")){
                                        content += "Código postal: "+whois.getZip()+"\n\n";
                                    }
                                }

                                if(whois.getLat() != null){
                                    if(!whois.getLat().equals("")){
                                        content += "Lat: "+whois.getLat()+"\n\n";
                                    }
                                }

                                if(whois.getLon() != null){
                                    if(!whois.getLon().equals("")){
                                        content += "Lon: "+whois.getLon()+"\n\n";
                                    }
                                }

                                if(whois.getTimezone() != null){
                                    if(!whois.getTimezone().equals("")){
                                        content += "Fuso horário: "+whois.getTimezone()+"\n\n";
                                    }
                                }

                                if(whois.getIsp() != null){
                                    if(!whois.getIsp().equals("")){
                                        content += "ISP: "+whois.getIsp()+"\n\n";
                                    }
                                }

                                if(whois.getOrg() != null){
                                    if(!whois.getOrg().equals("")){
                                        content += "Org: "+whois.getOrg()+"\n\n";
                                    }
                                }

                                if(whois.getAs() != null){
                                    if(!whois.getAs().equals("")){
                                        content += "ASN: "+whois.getAs();
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
                                    error_text.setText("Não foi encontrado nada referente ao digitado!");
                                }
                            }else{
                                progress_circular.setVisibility(View.GONE);
                                layoutResult.setVisibility(View.GONE);
                                inicioLayout.setVisibility(View.GONE);
                                falhaLayout.setVisibility(View.VISIBLE);
                                error_text.setText("Não foi encontrado nada referente ao digitado!");
                            }
                        }
                    });
                }else
                    Toast.makeText(this, "Digite o Whois, para continuar", Toast.LENGTH_SHORT).show();
                break;
            case md4: case md5:
                if(!str.equals("")){
                    falhaLayout.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.GONE);
                    inicioLayout.setVisibility(View.GONE);
                    progress_circular.setVisibility(View.VISIBLE);
                    error_text.setText("");
                    viewModel.getMD(Tools.convertToStringTrim(str, consultaType), consultaType==ConsultaType.md4, this).observe(this, new Observer<MD>() {
                        @Override
                        public void onChanged(MD md) {
                            if(md != null){
                                String content = "";
                                if(md.getDigest() != null){
                                    if(!md.getDigest().equals("")){
                                        content += "HASH: " + md.getDigest() + "\n\n";
                                    }
                                }
                                if(md.getType() != null){
                                    if(!md.getType().equals("")){
                                        content += "Tipo: " + md.getType();
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
                                    error_text.setText("Não foi encontrado nada referente ao digitado!");
                                }
                            }else{
                                progress_circular.setVisibility(View.GONE);
                                layoutResult.setVisibility(View.GONE);
                                inicioLayout.setVisibility(View.GONE);
                                falhaLayout.setVisibility(View.VISIBLE);
                                error_text.setText("Não foi encontrado nada referente ao digitado!");
                            }
                        }
                    });
                }else
                    Toast.makeText(this, "Digite o HASH, para continuar", Toast.LENGTH_SHORT).show();
                break;
            case ip: case ip2:
                if(!str.equals("")){
                    falhaLayout.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.GONE);
                    inicioLayout.setVisibility(View.GONE);
                    progress_circular.setVisibility(View.VISIBLE);
                    error_text.setText("");
                    viewModel.getIP(Tools.convertToStringTrim(str, consultaType), consultaType==ConsultaType.cep, this).observe(this, new Observer<IP>() {
                        @Override
                        public void onChanged(IP ip) {
                            if(ip != null){
                                String content = "";
                                if(ip.getIp() != null){
                                    if(!ip.getIp().equals("")){
                                        content += "IP: " + ip.getIp() + "\n\n";
                                    }
                                }
                                if(ip.getType() != null){
                                    if(!ip.getType().equals("")){
                                        content += "Tipo: " + ip.getType() + "\n\n";
                                    }
                                }
                                if(ip.getContinent() != null){
                                    if(!ip.getContinent().equals("")){
                                        content += "Continente: "+ip.getContinent() + "\n\n";
                                    }
                                }
                                if(ip.getCountry() != null){
                                    if(!ip.getCountry().equals("")) {
                                        content += "País: " + ip.getCountry() + "\n\n";
                                    }
                                }

                                if(ip.getCountry_capital() != null){
                                    if(!ip.getCountry_capital().equals("")){
                                        content += "Capital: "+ip.getCountry_capital() + "\n\n";
                                    }
                                }

                                if(ip.getRegion() != null){
                                    if(!ip.getRegion().equals("")){
                                        content += "Região: "+ip.getRegion() +"\n\n";
                                    }
                                }


                                if(ip.getCity() != null){
                                    if(!ip.getCity().equals("")){
                                        content += "Cidade: "+ip.getCity() +"\n\n";
                                    }
                                }

                                if(ip.getCurrency()!= null){
                                    if(!ip.getCurrency().equals("")){
                                        content += "Moeda: "+ip.getCurrency() +"\n\n";
                                    }
                                }

                                if(ip.getVersion() != null){
                                    if(!ip.getVersion().equals("")){
                                        content += "Versão: "+ip.getVersion() +"\n\n";
                                    }
                                }

                                if(ip.getRegion_code() != null){
                                    if(!ip.getRegion_code().equals("")){
                                        content += "Código da Região: "+ip.getRegion_code() +"\n\n";
                                    }
                                }

                                if(ip.getCountry_code() != null){
                                    if(!ip.getCountry_code().equals("")){
                                        content += "Código do País: "+ip.getCountry_code() +"\n\n";
                                    }
                                }

                                if(ip.getCountry_name() != null){
                                    if(!ip.getCountry_name().equals("")){
                                        content += "Nome do País: "+ip.getCountry_name() +"\n\n";
                                    }
                                }

                                if(ip.getCountry_capital() != null){
                                    if(!ip.getCountry_capital().equals("")){
                                        content += "Capital do País: "+ip.getCountry_capital() +"\n\n";
                                    }
                                }

                                if(ip.getContinent_code() != null){
                                    if(!ip.getContinent_code().equals("")){
                                        content += "Código do Continente: "+ip.getContinent_code() +"\n\n";
                                    }
                                }

                                if(ip.getPostal() != null){
                                    if(!ip.getPostal().equals("")){
                                        content += "Código postal: "+ip.getPostal() +"\n\n";
                                    }
                                }
                                if(ip.getLatitude() != null){
                                    if(!ip.getLatitude().equals("")){
                                        content += "Latitude: "+ip.getLatitude() +"\n\n";
                                    }
                                }

                                if(ip.getLongitude() != null){
                                    if(!ip.getLongitude().equals("")){
                                        content += "Longitude: "+ip.getLongitude() +"\n\n";
                                    }
                                }
                                if(ip.getTimezone() != null){
                                    if(!ip.getTimezone().equals("")){
                                        content += "Fuso horário: "+ip.getTimezone() +"\n\n";
                                    }
                                }

                                if(ip.getCountry_calling_code() != null){
                                    if(!ip.getCountry_calling_code().equals("")){
                                        content += "Código de Chamada do País: "+ip.getCountry_calling_code() +"\n\n";
                                    }
                                }
                                if(ip.getCurrency_name() != null){
                                    if(!ip.getCurrency_name().equals("")){
                                        content += "Nome da moeda: "+ip.getCurrency_name() +"\n\n";
                                    }
                                }

                                if(ip.getCountry_area() != null){
                                    if(!ip.getCountry_area().equals("")){
                                        content += "Área do País: "+ip.getCountry_area() +"\n\n";
                                    }
                                }
                                if(ip.getCountry_population() != null){
                                    if(!ip.getCountry_population().equals("")){
                                        content += "População do País: "+ip.getCountry_population() +"\n\n";
                                    }
                                }

                                if(ip.getAsn() != null){
                                    if(!ip.getAsn().equals("")){
                                        content += "ASN: "+ip.getAsn() +"\n\n";
                                    }
                                }
                                if(ip.getOrg() != null){
                                    if(!ip.getOrg().equals("")){
                                        content += "ORG: "+ip.getOrg();
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
                                    error_text.setText("Não foi encontrado nada referente ao IP digitado!");
                                }
                            }else{
                                progress_circular.setVisibility(View.GONE);
                                layoutResult.setVisibility(View.GONE);
                                inicioLayout.setVisibility(View.GONE);
                                falhaLayout.setVisibility(View.VISIBLE);
                                error_text.setText("Não foi encontrado nada referente ao IP digitado!");
                            }
                        }
                    });
                }else
                    Toast.makeText(this, "Digite o IP, para continuar", Toast.LENGTH_SHORT).show();
                break;
            case cep2: case cep:{
                if(!str.equals("")){
                    falhaLayout.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.GONE);
                    inicioLayout.setVisibility(View.GONE);
                    progress_circular.setVisibility(View.VISIBLE);
                    error_text.setText("");
                    viewModel.getCEP(Tools.convertToStringTrim(str, consultaType), consultaType == ConsultaType.cep ? true : false, this).observe(this, new Observer<CEP>() {
                        @Override
                        public void onChanged(CEP cep) {
                            if(cep != null){
                                String content = "";
                                if(cep.getCep() != null){
                                    if(!cep.getCep().equals("")){
                                        content += "CEP: " + cep.getCep() + "\n\n";
                                    }
                                }
                                if(cep.getState() != null){
                                    if(!cep.getState().equals("")){
                                        content += "Estado: " + cep.getState() + "\n\n";
                                    }
                                }
                                if(cep.getCity() != null){
                                    if(!cep.getCity().equals("")){
                                        content += "Cidade: " + cep.getCity() + "\n\n";
                                    }
                                }
                                if(cep.getNeighborhood() != null){
                                    if(!cep.getNeighborhood().equals("")){
                                        content += "Vizinhança: " + cep.getNeighborhood() + "\n\n";
                                    }
                                }
                                if(cep.getStreet() != null){
                                    if(!cep.getStreet().equals("")){
                                        content += "Rua: " + cep.getStreet() + "\n\n";
                                    }
                                }
                                if(cep.getDistrict() != null){
                                    if(!cep.getDistrict().equals("")){
                                        content += "Distrito: " + cep.getDistrict();
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
                                    error_text.setText("Não foi encontrado nada referente ao CEP digitado!");
                                }
                            }else{
                                progress_circular.setVisibility(View.GONE);
                                layoutResult.setVisibility(View.GONE);
                                inicioLayout.setVisibility(View.GONE);
                                falhaLayout.setVisibility(View.VISIBLE);
                                error_text.setText("Não foi encontrado nada referente ao CEP digitado!");
                            }
                        }
                    });
                }else
                    Toast.makeText(this, "Digite o CEP, para continuar", Toast.LENGTH_SHORT).show();
            }break;
            case ddd:{
                if(!str.equals("")){
                    falhaLayout.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.GONE);
                    inicioLayout.setVisibility(View.GONE);
                    progress_circular.setVisibility(View.VISIBLE);
                    error_text.setText("");
                    viewModel.getDDD(Tools.convertToStringTrim(str, consultaType), this).observe(this, new Observer<DDD>() {
                        @Override
                        public void onChanged(DDD ddd) {
                            if(ddd != null){
                                String content = "";
                                if(ddd.getState() != null){
                                    if(!ddd.getState().equals("")){
                                        content += "Estado: " + ddd.getState();
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
                                    error_text.setText("Não foi encontrado nada referente ao DDD digitado!");
                                }
                            }else{
                                progress_circular.setVisibility(View.GONE);
                                layoutResult.setVisibility(View.GONE);
                                inicioLayout.setVisibility(View.GONE);
                                falhaLayout.setVisibility(View.VISIBLE);
                                error_text.setText("Não foi encontrado nada referente ao DDD digitado!");
                            }
                        }
                    });
                }else
                    Toast.makeText(this, "Digite o DDD, para continuar", Toast.LENGTH_SHORT).show();
            }break;
        }
    }

}