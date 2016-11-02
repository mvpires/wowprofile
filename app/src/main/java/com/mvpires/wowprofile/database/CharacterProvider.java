package com.mvpires.wowprofile.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by mv_pi on 23/10/2016.
 */

public class CharacterProvider extends ContentProvider
{

    public static final String PATH = "wowprofile";
    public static final String AUTHORITY = "com.mvpires.wowprofile";

    public static Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public  static Uri CHARACTERS_URI = BASE_URI.withAppendedPath(BASE_URI, PATH);

    private static final int TYPE_GENERIC = 0;
    private static final int TYPE_ID = 1;

    private UriMatcher mMatcher;
    private CharacterDBHelper mHelper;

    public CharacterProvider()
    {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTHORITY, PATH, TYPE_GENERIC);
        mMatcher.addURI(AUTHORITY, PATH + "/#", TYPE_ID);
    }

    @Override
    public boolean onCreate() {

        mHelper = new CharacterDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        int uriType = mMatcher.match(uri);

        switch (uriType)
        {
            case TYPE_GENERIC:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY;
            case TYPE_ID:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY;
            default:
                throw new IllegalArgumentException("Invalid Uri");
        }

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        int uriType = mMatcher.match(uri);

        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor;

        switch (uriType)
        {
            case TYPE_GENERIC:
                cursor = database.query(CharacterContract.TABLE_NAME, projection, selection, selectionArgs,null, null, sortOrder);
                break;

            case TYPE_ID:
                long id = ContentUris.parseId(uri);
                cursor = database.query(CharacterContract.TABLE_NAME, projection, CharacterContract._ID + " = ?",
                        new String[]{String.valueOf(id)}, null, null, sortOrder);
                break;

            default: throw new IllegalArgumentException("Invalid Uri");
        }

        if (getContext() != null)
        {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }


        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = mMatcher.match(uri);

        if (uriType == TYPE_GENERIC)
        {
            SQLiteDatabase database = mHelper.getWritableDatabase();
            long id = database.insert(CharacterContract.TABLE_NAME, null, values);
            database.close();

            if (id == -1)
            {
                throw new RuntimeException("Error inserting character");
            }
            notifyChanges(uri);
            return ContentUris.withAppendedId(CHARACTERS_URI, id);
        }
        else
        {
            throw new IllegalArgumentException("Invalid Uri");
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = mMatcher.match(uri);

        if(uriType == TYPE_ID)
        {
            SQLiteDatabase database = mHelper.getWritableDatabase();
            long id = ContentUris.parseId(uri);
            int rowsAffected = database.delete(
                    CharacterContract.TABLE_NAME, CharacterContract._ID + "= ?",
                    new String[]{String.valueOf(id)});
            database.close();

            if (rowsAffected == 0)
            {
                throw new RuntimeException("Error deleting character");
            }
            notifyChanges(uri);

            return rowsAffected;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Uri");
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new IllegalArgumentException("Invalide Uri");
    }

    private void notifyChanges(Uri uri)
    {
        if (getContext() != null)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }
}
