package com.mvpires.wowprofile.http;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvpires.wowprofile.model.Character;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mv_pi on 22/10/2016.
 */

public class CharacterHttp {

    //%s 1 = realm ; s% 2 = character name

public static final String URL_STATS_GUILD = "https://us.api.battle.net/wow/character/%s/%s?fields=stats%2Cguild%2Ctalents&locale=en_US&apikey=dj6g6bh58m63tqzsyv4mk8xqby7sjezn";

    public static Character searchCharacter(String queryRealm, String queryCharName, Context context)
    {
        Character character = new Character();

        OkHttpClient client = new OkHttpClient();
        //String url = String.format(URL_STATS_GUILD, queryRealm, queryCharName, ',', ',');
        String url = "https://us.api.battle.net/wow/character/"+queryRealm+"/"+queryCharName+"?fields=guild%2C+stats%2C+talents&locale=en_US&apikey=dj6g6bh58m63tqzsyv4mk8xqby7sjezn";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;

        try
        {
            response = client.newCall(request).execute();
            String json = response.body().string();
            GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls();
            gsonBuilder.registerTypeAdapter(Character.class, new CharacterDeserializer(context));
            Gson gson = gsonBuilder.create();
            character = gson.fromJson(json,Character.class);
            return character;

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }



}
