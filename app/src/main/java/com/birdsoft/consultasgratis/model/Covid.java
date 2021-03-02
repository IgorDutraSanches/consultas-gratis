package com.birdsoft.consultasgratis.model;

public class Covid {
    private String state, deaths, cases, suspects, uf, deathssuspects, refuses;

    public String getDeaths() {
        return deaths;
    }

    public String getSuspects() {
        return suspects;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public void setSuspects(String suspects) {
        this.suspects = suspects;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDeathssuspects() {
        return deathssuspects;
    }

    public void setDeathssuspects(String deathssuspects) {
        this.deathssuspects = deathssuspects;
    }

    public String getRefuses() {
        return refuses;
    }

    public void setRefuses(String refuses) {
        this.refuses = refuses;
    }
}
