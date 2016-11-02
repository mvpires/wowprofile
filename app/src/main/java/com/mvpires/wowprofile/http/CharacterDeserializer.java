package com.mvpires.wowprofile.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mvpires.wowprofile.R;
import com.mvpires.wowprofile.model.Character;

import java.lang.reflect.Type;

/**
 * Created by mv_pi on 23/10/2016.
 */

public class CharacterDeserializer implements JsonDeserializer<Character>  {
    Context mContext;
    public CharacterDeserializer(Context appContext) {
        this.mContext = appContext;
    }

    @Override
    public Character deserialize(JsonElement json,
                                 Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {

        try
        {
            JsonObject jsonObject = (JsonObject) json;
            Character character = new Character();
            character.setAvatarUrl(jsonObject.get("thumbnail").getAsString());
            character.setName(jsonObject.get("name").getAsString());
            character.setLastModified(jsonObject.get("lastModified").getAsString());
            character.setCharClass(jsonObject.get("class").getAsString());
            character.setFactionName(jsonObject.get("faction").getAsString());
            character.setGender(jsonObject.get("gender").getAsString());
            character.setLevel(jsonObject.get("level").getAsString());
            character.setRace(jsonObject.get("race").getAsString());
            character.setRealm(jsonObject.get("realm").getAsString());
           // JsonArray jsonArray = jsonObject.getAsJsonArray("talents");
           // character.setCharSpec(jsonObject.getAsJsonArray("talents").get(2).getAsJsonObject().get("spec").getAsJsonObject().get("name").getAsString());
            if(jsonObject.get("guild")!= null)
            {
                character.setGuildName(jsonObject.get("guild").getAsJsonObject().get("name").getAsString());
            }
            else
            {
                //character.setGuildName(mContext.getResources().getString(R.string.msg_guild_not_found));
                character.setGuildName("");
            }

            character.setHealth(jsonObject.get("stats").getAsJsonObject().get("health").getAsString());
            character.setIntelligence(jsonObject.get("stats").getAsJsonObject().get("int").getAsString());
            character.setAgility(jsonObject.get("stats").getAsJsonObject().get("agi").getAsString());
            character.setStrength(jsonObject.get("stats").getAsJsonObject().get("str").getAsString());
            character.setPower(jsonObject.get("stats").getAsJsonObject().get("power").getAsString());
            character.setPowerType(jsonObject.get("stats").getAsJsonObject().get("powerType").getAsString());

            return character;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
