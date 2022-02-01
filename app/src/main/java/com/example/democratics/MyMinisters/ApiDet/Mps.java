package com.example.democratics.MyMinisters.ApiDet;

public class Mps {

    private String name;
    private String state_or_ut;
    private String constituency;
    private String party;
    private String education;
    private int criminal_cases;
    private Long assets;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getCriminalCases() {
        return criminal_cases;
    }

    public void setCriminalCases(int criminalCases) {
        this.criminal_cases = criminalCases;
    }

    public Long getAssets() {
        return assets;
    }

    public void setAssets(Long assets) {
        this.assets = assets;
    }

    public String getState() {
        return state_or_ut;
    }

    public void setState(String state) {
        this.state_or_ut = state;
    }

    public Mps(String name, String state, String constituency, String party, String education, int criminalCases, Long assets) {
        this.name = name;
        this.state_or_ut = state;
        this.constituency = constituency;
        this.party = party;
        this.education = education;
        this.criminal_cases = criminalCases;
        this.assets = assets;
    }


}
