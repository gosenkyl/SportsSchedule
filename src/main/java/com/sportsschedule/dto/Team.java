package com.sportsschedule.dto;

import javax.persistence.*;

@Entity
@Table(name="TEAM")
public class Team {

    @Id
    @Column(name="team_id")
    @GeneratedValue
    private Integer teamId;

    private String city;
    private String mascot;

    @Column(name="primary_color")
    private String primaryColor;
    @Column(name="secondary_color")
    private String secondaryColor;
    @Column(name="lon_location")
    private Double lonLocation;
    @Column(name="lat_location")
    private Double latLocation;
    //private byte[] logo;
    @Column(name="logo_url")
    private String logoURL;
    @Column(name="nfl_abbr")
    private String nflAbbr;

    @ManyToOne
    @JoinColumn(name="sport_id")
    private Sport sport;

    public Team(){
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Double getLonLocation() {
        return lonLocation;
    }

    public void setLonLocation(Double lonLocation) {
        this.lonLocation = lonLocation;
    }

    public Double getLatLocation() {
        return latLocation;
    }

    public void setLatLocation(Double latLocation) {
        this.latLocation = latLocation;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getNflAbbr() {
        return nflAbbr;
    }

    public void setNflAbbr(String nflAbbr) {
        this.nflAbbr = nflAbbr;
    }

    /* public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }*/
}
