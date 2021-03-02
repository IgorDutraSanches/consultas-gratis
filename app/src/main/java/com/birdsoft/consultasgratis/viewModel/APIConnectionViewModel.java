package com.birdsoft.consultasgratis.viewModel;

import android.os.AsyncTask;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.birdsoft.consultasgratis.API;
import com.birdsoft.consultasgratis.model.Banco;
import com.birdsoft.consultasgratis.model.CEP;
import com.birdsoft.consultasgratis.model.CNPJ;
import com.birdsoft.consultasgratis.model.Covid;
import com.birdsoft.consultasgratis.model.DDD;
import com.birdsoft.consultasgratis.model.IP;
import com.birdsoft.consultasgratis.model.MD;
import com.birdsoft.consultasgratis.model.Placa;
import com.birdsoft.consultasgratis.model.Whois;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIConnectionViewModel extends ViewModel {

    public MutableLiveData<CEP> getCEP(String str, boolean one, LifecycleOwner owner){
        MutableLiveData<CEP> data = new MutableLiveData<>();
        getAPI(one ? API.getCEP(str) : API.getCEP2(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Gson gson = new Gson();
                data.setValue(gson.fromJson(new String(s.getBytes()), CEP.class));
            }
        });
        return data;
    }

    public MutableLiveData<IP> getIP(String str, boolean one, LifecycleOwner owner){
        MutableLiveData<IP> data = new MutableLiveData<>();
        getAPI(one ? API.getIP(str) : API.getIP2(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Gson gson = new Gson();
                data.setValue(gson.fromJson(new String(s.getBytes()), IP.class));
            }
        });
        return data;
    }

    public MutableLiveData<MD> getMD(String str, boolean one, LifecycleOwner owner){
        MutableLiveData<MD> data = new MutableLiveData<>();
        getAPI(one ? API.getMd4(str) : API.getMd5(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Gson gson = new Gson();
                data.setValue(gson.fromJson(new String(s.getBytes()), MD.class));
            }
        });
        return data;
    }

    public MutableLiveData<Banco> getBanco(String str, LifecycleOwner owner){
        MutableLiveData<Banco> data = new MutableLiveData<>();
        getAPI(API.getBanco(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Gson gson = new Gson();
                data.setValue(gson.fromJson(new String(s.getBytes()), Banco.class));
            }
        });
        return data;
    }

    public MutableLiveData<Placa> getPlaca(String str, LifecycleOwner owner){
        MutableLiveData<Placa> data = new MutableLiveData<>();
        getAPI(API.getPlaca(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Gson gson = new Gson();
                data.setValue(gson.fromJson(new String(s.getBytes()), Placa.class));
            }
        });
        return data;
    }

    public MutableLiveData<CNPJ> getCpnj(String str, LifecycleOwner owner){
        MutableLiveData<CNPJ> data = new MutableLiveData<>();
        getAPI(API.getCnpj(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Gson gson = new Gson();
                data.setValue(gson.fromJson(new String(s.getBytes()), CNPJ.class));
            }
        });
        return data;
    }

        public MutableLiveData<Whois> getWhois(String str, LifecycleOwner owner){
        MutableLiveData<Whois> data = new MutableLiveData<>();
        getAPI(API.getWhois(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Gson gson = new Gson();
                data.setValue(gson.fromJson(new String(s.getBytes()), Whois.class));
            }
        });
        return data;
    }

    public MutableLiveData<Covid> getCovid(String str, LifecycleOwner owner){
        MutableLiveData<Covid> data = new MutableLiveData<>();
        getAPI(API.getCovid(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Gson gson = new Gson();
                data.setValue(gson.fromJson(new String(s.getBytes()), Covid.class));
            }
        });
        return data;
    }

    public MutableLiveData<DDD> getDDD(String str, LifecycleOwner owner){
        MutableLiveData<DDD> data = new MutableLiveData<>();
        getAPI(API.getDDD(str)).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.equals("")){
                    Gson gson = new Gson();
                    data.setValue(gson.fromJson(new String(s.getBytes()), DDD.class));
                }else data.setValue(null);
            }
        });
        return data;
    }

    private MutableLiveData<String> getAPI(String api){
        MutableLiveData<String> data = new MutableLiveData<>();
        new Connection(data).execute(api);
        System.out.println(data.getValue());
        return data;
    }

    private class Connection extends AsyncTask<String, String, String> {
        private MutableLiveData<String> data;
        public Connection(MutableLiveData<String> data) {
            this.data = data;
        }

        @Override
        protected String doInBackground(String... strings) {
           String result = "";
            try {
                String url = strings[0];

                    URL urlUse = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) urlUse.openConnection();

               conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                conn.setUseCaches(false);
                conn.setAllowUserInteraction(false);
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.connect();

                if(conn.getResponseCode()==201 || conn.getResponseCode()==200)
                {
                    if (conn.getResponseCode() != 200) {
                        System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                    String output = "";
                    String line;
                    while ((line = br.readLine()) != null) {
                        output += line;
                    }

                    conn.disconnect();
                    result = output;
                }else result = "";


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
             data.setValue(s);
            super.onPostExecute(s);
        }
    }

}
