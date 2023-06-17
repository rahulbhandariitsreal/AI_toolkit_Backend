package com.example.backend_aitoolkit;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AI_model implements Parcelable {


    private int U_id;
    private String AI_Name;
    private String AI_Relatedto;
    private String AI_Description;
    private int star_rated;

    private String country_flag_uri;
    private String profile_uri;

    private String ai_link;

    public AI_model() {

    }

    public AI_model(String AI_Name, String AI_Relatedto, String AI_Description, int star_rated, String country_flag_uri, String ai_link) {
        this.AI_Name = AI_Name;
        this.AI_Relatedto = AI_Relatedto;
        this.AI_Description = AI_Description;
        this.star_rated = star_rated;
        this.country_flag_uri = country_flag_uri;
        this.ai_link = ai_link;
    }

    public AI_model(int u_id, String AI_Name, String AI_Relatedto, String AI_Description, int star_rated, String country_flag_uri, String profile_uri, String ai_link) {
        U_id = u_id;
        this.AI_Name = AI_Name;
        this.AI_Relatedto = AI_Relatedto;
        this.AI_Description = AI_Description;
        this.star_rated = star_rated;
        this.country_flag_uri = country_flag_uri;
        this.profile_uri = profile_uri;
        this.ai_link = ai_link;
    }

    protected AI_model(Parcel in) {
        U_id = in.readInt();
        AI_Name = in.readString();
        AI_Relatedto = in.readString();
        AI_Description = in.readString();
        star_rated = in.readInt();
        country_flag_uri = in.readString();
        profile_uri = in.readString();
        ai_link = in.readString();
    }

    public static final Creator<AI_model> CREATOR = new Creator<AI_model>() {
        @Override
        public AI_model createFromParcel(Parcel in) {
            return new AI_model(in);
        }

        @Override
        public AI_model[] newArray(int size) {
            return new AI_model[size];
        }
    };

    public String getCountry_flag_uri() {
        return country_flag_uri;
    }

    public void setCountry_flag_uri(String country_flag_uri) {
        this.country_flag_uri = country_flag_uri;
    }

    public String getAi_link() {
        return ai_link;
    }

    public void setAi_link(String ai_link) {
        this.ai_link = ai_link;
    }

    public String getProfile_uri() {
        return profile_uri;
    }

    public void setProfile_uri(String profile_uri) {
        this.profile_uri = profile_uri;
    }

    public int getU_id() {
        return U_id;
    }

    public void setU_id(int u_id) {
        U_id = u_id;
    }

    public String getAI_Name() {
        return AI_Name;
    }

    public void setAI_Name(String AI_Name) {
        this.AI_Name = AI_Name;
    }

    public String getAI_Relatedto() {
        return AI_Relatedto;
    }

    public void setAI_Relatedto(String AI_Relatedto) {
        this.AI_Relatedto = AI_Relatedto;
    }

    public String getAI_Description() {
        return AI_Description;
    }

    public void setAI_Description(String AI_Description) {
        this.AI_Description = AI_Description;
    }

    public int getStar_rated() {
        return star_rated;
    }

    public void setStar_rated(int star_rated) {
        this.star_rated = star_rated;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(U_id);
        dest.writeString(AI_Name);
        dest.writeString(AI_Relatedto);
        dest.writeString(AI_Description);
        dest.writeInt(star_rated);
        dest.writeString(country_flag_uri);
        dest.writeString(profile_uri);
        dest.writeString(ai_link);
    }
}
