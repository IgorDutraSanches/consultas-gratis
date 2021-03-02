package com.birdsoft.consultasgratis;

public class API {
    public static String getCEP(String cep) { return "https://brasilapi.com.br/api/cep/v1/" + cep; }
    public static String getCEP2(String cep) { return "https://ws.apicep.com/cep.json?code=" + cep; }
    public static String getIP(String ip) { return "https://ipwhois.app/json/" + ip; }
    public static String getIP2(String id) { return "https://ipapi.co/" + id + "/json"; }
    public static String getCnpj(String cnpj) { return "https://www.receitaws.com.br/v1/cnpj/" + cnpj; }
    public static String getBanco(String banco) { return "https://brasilapi.com.br/api/banks/v1/" + banco; }
    public static String getCovid(String uf) { return "https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/" + uf; }
    public static String getWhois(String whois) { return "http://ip-api.com/json/" + whois; }
    public static String getMd4(String md4) { return "https://api.hashify.net/hash/md4/hex?value=" + md4; }
    public static String getMd5(String md5) { return "https://api.hashify.net/hash/md5/hex?value=" + md5; }
    public static String getPlaca(String placa) { return "https://apicarros.com/v1/consulta/"+placa+"/json"; }
    public static String getDDD(String DDD) { return "https://brasilapi.com.br/api/ddd/v1/"+DDD; }

}
