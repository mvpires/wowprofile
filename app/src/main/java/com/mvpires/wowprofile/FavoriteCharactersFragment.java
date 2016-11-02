package com.mvpires.wowprofile;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mvpires.wowprofile.database.CharacterContract;
import com.mvpires.wowprofile.database.CharacterProvider;
import com.mvpires.wowprofile.model.Character;


public class FavoriteCharactersFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    OnCharacterClickListener mCharacterClickListener;
    CharacterCursorAdapter mCharacterCursorAdapter;
    boolean mFirstRun;

    public FavoriteCharactersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirstRun = savedInstanceState == null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite_characters, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listview_favorite_characters);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(mCharacterClickListener != null)
                {
                    Cursor cursor = mCharacterCursorAdapter.getCursor();
                    selectCharacter(view, position, cursor);
                }

            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            listView.setNestedScrollingEnabled(true);
        }

        mCharacterCursorAdapter = new CharacterCursorAdapter(getActivity(), null);
        listView.setAdapter(mCharacterCursorAdapter);
        listView.setEmptyView(view.findViewById(R.id.empty_view));

        getLoaderManager().initLoader(0, null, this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnCharacterClickListener) {
            mCharacterClickListener = (OnCharacterClickListener) context;
        }
    }

    private void selectCharacter(View view, int position, Cursor cursor)
    {
        if(cursor.moveToPosition(position))
        {
            Character character = new Character();
            character.setCharClass(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_CLASS)));
            character.setFactionName(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_FACTION_NAME)));
            character.setRace(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_RACE)));
            character.setGender(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_GENDER)));
            character.setName(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_NAME)));
            character.setLevel(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_LEVEL)));
            character.setGuildName(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_GUILD_NAME)));
            character.setRealm(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_REALM)));
            character.setAvatarUrl(cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_AVATAR)));

            mCharacterClickListener.onCharacterClick(view, character, position);

        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                CharacterProvider.CHARACTERS_URI,
                CharacterContract.LIST_COLUMNS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
        mCharacterCursorAdapter.swapCursor(data);

        if(data != null
                && data.getCount() > 0
                && getResources().getBoolean(R.bool.tablet)
                && mFirstRun)
        {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    selectCharacter(null, 0, data);
                }
            });
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCharacterCursorAdapter.swapCursor(null);
    }

}
