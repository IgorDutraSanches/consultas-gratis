package com.birdsoft.consultasgratis;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static void injector(ConsultaType consultaType, EditText search_bar) {
        InputFilter[] filterArray = new InputFilter[1];
        EditText editText = search_bar;
        switch (consultaType){
            case placa:
                filterArray[0] = new InputFilter.LengthFilter(8);
                search_bar.setFilters(filterArray);
                search_bar.addTextChangedListener(Mask.insertPlaca(search_bar));
                editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                break;
            case cep: case cep2:
                 filterArray[0] = new InputFilter.LengthFilter(9);
                search_bar.setFilters(filterArray);
                search_bar.addTextChangedListener(Mask.insertCep(search_bar));
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                break;
            case cnpj:
                filterArray[0] = new InputFilter.LengthFilter("##.###.###/####-##".length());
                search_bar.setFilters(filterArray);
                search_bar.addTextChangedListener(Mask.insertCnpj(search_bar));
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                break;
            case ddd:
                filterArray[0] = new InputFilter.LengthFilter("##".length());
                search_bar.setFilters(filterArray);
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                break;
            case ip: case ip2: case whois:
                filterArray[0] = new InputFilter.LengthFilter("000.000.000.000".length());
                search_bar.setFilters(filterArray);
                search_bar.addTextChangedListener(Mask.insertIP(search_bar));
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                break;
        }
    }

    public static String convertToStringTrim(String str, ConsultaType consultaType) {
        String z = "";
        switch (consultaType){
            case cep: case cep2: case placa: z = str.replace("-", ""); break;
            case md5: case md4: case ddd: case bank: case whois: case ip: case ip2: z = str; break;
             case cnpj: z = str.replace("-", "").replace("/","").replace(".",""); break;
        }
        return z;
    }

    public static String getStringDateTimeStamp(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static void toolbar(ConsultaType consultaType, Toolbar toolbar, EditText editText) {
        switch (consultaType){
            case bank: toolbar.setTitle("Consultar banco"); editText.setHint("Digite o código do banco"); break;
            case placa: toolbar.setTitle("Placa de veículos"); editText.setHint("Digite a placa"); break;
            case ip2: case ip: toolbar.setTitle("Consultar IP"); editText.setHint("Digite o IP"); break;
            case cep2: case cep: toolbar.setTitle("Consulta de CEP"); editText.setHint("Digite o CEP"); break;
            case ddd: toolbar.setTitle("Consultar DDD"); editText.setHint("Digite o DDD"); break;
            case md4: toolbar.setTitle("Consultar MD4"); editText.setHint("Digite o texto para criar a Hash"); break;
            case md5: toolbar.setTitle("Consultar MD5"); editText.setHint("Digite o texto para criar a Hash"); break;
            case cnpj: toolbar.setTitle("Consultar Cnpj"); editText.setHint("Digite o cnpj"); break;
            case covid: toolbar.setTitle("Consultar o Covid-19"); editText.setHint("Digite aqui..."); break;
            case whois: toolbar.setTitle("Consultar Whois"); editText.setHint("Digite o Whois"); break;
        }
    }


    public static void toolbar(ConsultaType consultaType, Toolbar toolbar, TextView textView) {
        switch (consultaType){
             case covid: toolbar.setTitle("Consultar o Covid-19"); break;
        }
    }
}
