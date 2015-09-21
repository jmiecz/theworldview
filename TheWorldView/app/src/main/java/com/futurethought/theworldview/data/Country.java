package com.futurethought.theworldview.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Josh Mieczkowski on 9/20/2015.
 */
public class Country implements Parcelable {
    @JsonProperty("name")
    private String name;

    @JsonProperty("capital")
    private String capital;

    @JsonProperty("altSpellings")
    private ArrayList<String> alternateSpellings = new ArrayList<>();

    @JsonProperty("region")
    private String region;

    @JsonProperty("subregion")
    private String subRegion;

    @JsonProperty("population")
    private String population;

    @JsonProperty("area")
    private String area;

    @JsonProperty("borders")
    private ArrayList<String> borders = new ArrayList<>();

    @JsonProperty("nativeName")
    private String nativeName;

    @JsonProperty("alpha2Code")
    private String alpha2Code;

    @JsonProperty("alpha3Code")
    private String alpha3Code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public ArrayList<String> getAlternateSpellings() {
        return alternateSpellings;
    }

    public void setAlternateSpellings(ArrayList<String> alternateSpellings) {
        this.alternateSpellings = alternateSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.capital);
        dest.writeStringList(this.alternateSpellings);
        dest.writeString(this.region);
        dest.writeString(this.subRegion);
        dest.writeString(this.population);
        dest.writeString(this.area);
        dest.writeStringList(this.borders);
        dest.writeString(this.nativeName);
        dest.writeString(this.alpha2Code);
        dest.writeString(this.alpha3Code);
    }

    public Country() {
    }

    protected Country(Parcel in) {
        this.name = in.readString();
        this.capital = in.readString();
        this.alternateSpellings = in.createStringArrayList();
        this.region = in.readString();
        this.subRegion = in.readString();
        this.population = in.readString();
        this.area = in.readString();
        this.borders = in.createStringArrayList();
        this.nativeName = in.readString();
        this.alpha2Code = in.readString();
        this.alpha3Code = in.readString();
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
