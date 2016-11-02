package com.mvpires.wowprofile.database;

import android.provider.BaseColumns;

/**
 * Created by mv_pi on 23/10/2016.
 */

public interface CharacterContract extends BaseColumns {

    String TABLE_NAME = "Characters";
    String COL_CHARACTER_NAME = "Name";
    String COL_CHARACTER_REALM = "Realm";
    String COL_CHARACTER_CLASS = "Class";
   // String COL_CHARACTER_SPEC = "Specialization";
    String COL_CHARACTER_RACE = "Race";
    String COL_CHARACTER_GENDER= "Gender";
    String COL_GUILD_NAME = "GuildName";
    String COL_CHARACTER_FACTION_NAME = "FactionName";
    String COL_CHARACTER_STRENGTH = "Strength";
    String COL_CHARACTER_INTELLIGENCE = "Intelligence";
    String COL_CHARACTER_AGILITY = "Agility";
    String COL_CHARACTER_HEALTH = "Health";
    String COL_CHARACTER_POWER = "Power";
    String COL_CHARACTER_POWER_TYPE = "PowerType";
    String COL_LAST_MODIFIED = "LastModified";
    String COL_CHARACTER_LEVEL = "Level";
    String COL_CHARACTER_AVATAR = "AvatarUrl";


    String[] LIST_COLUMNS = new String[]{
            CharacterContract._ID,
            CharacterContract.COL_CHARACTER_NAME,
            CharacterContract.COL_CHARACTER_CLASS,
            CharacterContract.COL_CHARACTER_FACTION_NAME,
            CharacterContract.COL_CHARACTER_GENDER,
            CharacterContract.COL_GUILD_NAME,
            CharacterContract.COL_CHARACTER_LEVEL,
            CharacterContract.COL_CHARACTER_RACE,
            CharacterContract.COL_CHARACTER_REALM,
            CharacterContract.COL_CHARACTER_AVATAR

    };
}
