package com.mvpires.wowprofile.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mv_pi on 22/10/2016.
 */

public class Character implements Serializable {

    /*
        Take the "thumbnail" value and replace "avatar.jpg" with "profilemain.jpg".
        Now, add this prefix: http://render-api-<REGION>.worldofwarcraft.com/static-render/<REGION>/.
        Your final URL should look something like this random forum user's profile link:
        http://render-api-us.worldofwarcraft.com/static-render/us/aggramar/177/34640049-profilemain.jpg


    */

    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("realm")
    private String realm;
    @SerializedName("class")
    private String charClass;
    @SerializedName("race")
    private String race;
    @SerializedName("gender")
    private String gender;
    @SerializedName("name")
    private String guildName;
    @SerializedName("faction")
    private String factionName;
    @SerializedName("str")
    private String strength;
    @SerializedName("int")
    private String intelligence;
    @SerializedName("agi")
    private String agility;
    @SerializedName("health")
    private String health;
    @SerializedName("power")
    private String power;
    @SerializedName("powerType")
    private String powerType;
    @SerializedName("lastModified")
    private String lastModified;
    @SerializedName("level")
    private String level;
    @SerializedName("thumbnail")
    private String avatarUrl;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getAgility() {
        return agility;
    }

    public void setAgility(String agility) {
        this.agility = agility;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }





}
