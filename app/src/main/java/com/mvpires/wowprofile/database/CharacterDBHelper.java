package com.mvpires.wowprofile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mv_pi on 23/10/2016.
 */

public class CharacterDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME ="charactersDb";
    public static final int DB_VERSION = 1;

    public CharacterDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + CharacterContract.TABLE_NAME + " (" +
                CharacterContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CharacterContract.COL_CHARACTER_NAME + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_REALM + " TEXT NOT NULL, "+
                CharacterContract.COL_CHARACTER_CLASS + " TEXT NOT NULL, " +
               // CharacterContract.COL_CHARACTER_SPEC + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_RACE + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_GENDER + " TEXT NOT NULL, " +
                CharacterContract.COL_GUILD_NAME + " TEXT, " +
                CharacterContract.COL_CHARACTER_FACTION_NAME + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_STRENGTH + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_INTELLIGENCE + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_AGILITY + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_HEALTH + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_POWER + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_POWER_TYPE + " TEXT NOT NULL, " +
                CharacterContract.COL_LAST_MODIFIED + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_LEVEL + " TEXT NOT NULL, " +
                CharacterContract.COL_CHARACTER_AVATAR + " TEXT NOT NULL)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
