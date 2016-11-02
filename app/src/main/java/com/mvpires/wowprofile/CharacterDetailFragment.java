package com.mvpires.wowprofile;

import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mvpires.wowprofile.database.CharacterContract;
import com.mvpires.wowprofile.database.CharacterProvider;
import com.mvpires.wowprofile.http.CharacterSearchTask;
import com.mvpires.wowprofile.model.Character;



public class CharacterDetailFragment extends Fragment {

    private static final String EXTRA_CHARACTER = "character";
    public static final int LOADED_DB = 0;
    public static final int LOADED_WEB = 1;

    ImageView imgAvatar;
    ImageView imgFaction;
    ImageView imgClass;
    TextView txtCharacterGuild;
    TextView txtCharacterClass;
    TextView txtCharacterLevel;
    TextView txtCharacterName;
    TextView txtCharacterStr;
    TextView txtCharacterAgi;
    TextView txtCharacterInt;
    TextView txtCharacterPower;
    TextView txtCharacterPowerType;
    TextView txtCharacterHealth;
    TextView txtCharacterFaction;
    TextView txtCharacterRace;
    TextView txtCharacterGender;
    View mView;

    Character mCharacter;
    LocalBroadcastManager mLocalBroadcastManager;
    CharacterEventReceiver mReceiver;


    public CharacterDetailFragment() {
        // Required empty public constructor
    }

    public static CharacterDetailFragment newInstance(Character character) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CHARACTER, character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_detail, container, false);
        mView = view;


        imgAvatar = (ImageView)view.findViewById(R.id.imgCharacterAvatar);
        imgFaction = (ImageView)view.findViewById(R.id.imgFaction);
        imgClass = (ImageView)view.findViewById(R.id.imgClass);

        txtCharacterGuild = (TextView)view.findViewById(R.id.textViewCharacterGuild);
        txtCharacterClass = (TextView)view.findViewById(R.id.textViewCharacterClass);
        txtCharacterLevel = (TextView)view.findViewById(R.id.textViewCharacterLevel);
        txtCharacterName = (TextView)view.findViewById(R.id.txtCharacterName);
        txtCharacterStr = (TextView)view.findViewById(R.id.textViewCharacterStr);
        txtCharacterAgi = (TextView)view.findViewById(R.id.textViewCharacterAgi);
        txtCharacterInt = (TextView)view.findViewById(R.id.textViewCharacterInt);
        txtCharacterPower = (TextView)view.findViewById(R.id.textViewCharacterPower);
        txtCharacterPowerType = (TextView)view.findViewById(R.id.textViewCharacterPowerType);
        txtCharacterHealth = (TextView)view.findViewById(R.id.textViewCharacterHealth);
        txtCharacterFaction = (TextView)view.findViewById(R.id.textViewCharacterFaction);
        txtCharacterRace = (TextView)view.findViewById(R.id.textViewCharacterRace);
        txtCharacterGender = (TextView)view.findViewById(R.id.textViewCharacterGender);

        if (savedInstanceState == null)
        {
            mCharacter = (Character)getArguments().getSerializable(EXTRA_CHARACTER);
        }
        else
        {
            mCharacter = (Character)savedInstanceState.getSerializable(EXTRA_CHARACTER);
        }

        boolean isFavorite = CharacterProfileUtils.isFavorite(getActivity(), mCharacter.getName(), mCharacter.getRealm());
        if(isFavorite)
        {
            getLoaderManager().initLoader(LOADED_DB, null, mCursorCallback);
        }
        else
        {
            getLoaderManager().initLoader(LOADED_WEB, null, mCharacterCallback);
        }

        mReceiver = new CharacterEventReceiver();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mLocalBroadcastManager.registerReceiver(mReceiver, new IntentFilter(CharacterEvent.UPDATE_CHARACTER_FAVORITE));


        return  view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_CHARACTER, mCharacter);

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }

    LoaderManager.LoaderCallbacks mCharacterCallback = new LoaderManager.LoaderCallbacks<Character>() {
        @Override
        public Loader<Character> onCreateLoader(int id, Bundle args) {

            return new CharacterSearchTask(getActivity(), mCharacter.getRealm(), mCharacter.getName());
        }

        @Override
        public void onLoadFinished(Loader<Character> loader, Character data) {

            updateUI(data);

        }

        @Override
        public void onLoaderReset(Loader<Character> loader) {

        }
    };

    LoaderManager.LoaderCallbacks mCursorCallback = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(getActivity(),
                    CharacterProvider.CHARACTERS_URI,
                    null,
                    CharacterContract.COL_CHARACTER_NAME + " = ?"
                            + " AND " + CharacterContract.COL_CHARACTER_REALM + "= ?",
                    new String[]{mCharacter.getName(), mCharacter.getRealm()},
                    null
            );
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            if(data != null && data.moveToFirst())
            {
                Character character = new Character();
                character.setId(data.getLong(data.getColumnIndex(CharacterContract._ID)));
                character.setCharClass(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_CLASS)));
                character.setGuildName(data.getString(data.getColumnIndex(CharacterContract.COL_GUILD_NAME)));
                character.setLevel(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_LEVEL)));
                character.setName(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_NAME)));
                character.setStrength(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_STRENGTH)));
                character.setAgility(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_AGILITY)));
                character.setIntelligence(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_INTELLIGENCE)));
                character.setPower(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_POWER)));
                character.setPowerType(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_POWER_TYPE)));
                character.setHealth(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_HEALTH)));
                character.setFactionName(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_FACTION_NAME)));
                character.setRace(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_RACE)));
                character.setGender(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_GENDER)));
                character.setRealm(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_REALM)));
                character.setAvatarUrl(data.getString(data.getColumnIndex(CharacterContract.COL_CHARACTER_AVATAR)));
                updateUI(character);

            }

        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    class CharacterEventReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(CharacterEvent.UPDATE_CHARACTER_FAVORITE))
            {
                switchFavoriteAsset();
            }
        }
    }

    private void updateUI(Character character)
    {
        mCharacter = character;
        txtCharacterGuild.setText(mCharacter.getGuildName());
        txtCharacterLevel.setText(mCharacter.getLevel());
        txtCharacterName.setText(mCharacter.getName());
        txtCharacterStr.setText(mCharacter.getStrength());
        txtCharacterAgi.setText(mCharacter.getAgility());
        txtCharacterInt.setText(mCharacter.getIntelligence());
        txtCharacterPower.setText(mCharacter.getPower());
        txtCharacterPowerType.setText(mCharacter.getPowerType());
        txtCharacterHealth.setText(mCharacter.getHealth());
        txtCharacterFaction.setText(mCharacter.getFactionName());
        txtCharacterRace.setText(mCharacter.getRace());
        txtCharacterGender.setText(mCharacter.getGender());

        String avatarUrl = "http://render-api-us.worldofwarcraft.com/static-render/us/" + mCharacter.getAvatarUrl();
        Glide.with(imgAvatar.getContext()).load(avatarUrl).into(imgAvatar);

        switch (mCharacter.getCharClass())
        {

            case "1":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_warrior));
                imgClass.setImageResource(R.drawable.class_warrior);
                break;
            case "2":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_paladin));
                imgClass.setImageResource(R.drawable.class_paladin);
                break;
            case "3":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_hunter));
               imgClass.setImageResource(R.drawable.class_hunter);
                break;
            case "4":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_rogue));
                imgClass.setImageResource(R.drawable.class_rogue);
                break;
            case "5":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_priest));
                imgClass.setImageResource(R.drawable.class_priest);
                break;
            case "6":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_death_knight));
                imgClass.setImageResource(R.drawable.class_deathknight);
                break;
            case "7":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_shaman));
                imgClass.setImageResource(R.drawable.class_shaman);
                break;
            case "8":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_mage));
               imgClass.setImageResource(R.drawable.class_mage);
                break;
            case "9":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_warlock));
                imgClass.setImageResource(R.drawable.class_warlock);
                break;
            case "10":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_monk));
                imgClass.setImageResource(R.drawable.class_monk);
                break;
            case "11":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_druid));
                imgClass.setImageResource(R.drawable.class_druid);
                break;
            case "12":
                txtCharacterClass.setText(getActivity().getResources().getString(R.string.char_class_demon_hunter));
                imgClass.setImageResource(R.drawable.class_demonhunter);
                break;

        }

        switch (mCharacter.getGender())
        {
            case "0":
                txtCharacterGender.setText(getActivity().getResources().getString(R.string.char_gender_male));
                break;
            case "1":
                txtCharacterGender.setText(getActivity().getResources().getString(R.string.char_gender_female));
                break;
        }

        switch(mCharacter.getFactionName())
        {
            case "0":
                txtCharacterFaction.setText(getActivity().getResources().getString(R.string.char_faction_alliance));
                imgFaction.setImageResource(R.drawable.faction_alliance);
                break;
            case "1":
                txtCharacterFaction.setText(getActivity().getResources().getString(R.string.char_faction_horde));
                imgFaction.setImageResource(R.drawable.faction_horde);
                break;
        }

        switch (mCharacter.getRace()) {

            case "1":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_human));

                break;
            case "2":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_orc));
                break;
            case "3":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_dwarf));
                break;
            case "4":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_night_elf));
                break;
            case "5":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_undead));
                break;
            case "6":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_tauren));
                break;
            case "7":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_gnome));
                break;
            case "8":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_troll));
                break;
            case "9":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_goblin));
                break;
            case "10":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_blood_elf));
                break;
            case "11":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_draenei));
                break;
            case "22":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_worgen));
                break;
            case "24":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_pandaren));
                break;
            case "25":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_pandaren));
                break;
            case "26":
                txtCharacterRace.setText(getActivity().getResources().getString(R.string.char_race_pandaren));
                break;
        }


        notifyUpdate(CharacterEvent.CHARACTER_LOADED);

        if(getResources().getBoolean(R.bool.tablet))
        {
            imgAvatar.setVisibility(View.VISIBLE);
            avatarUrl = "http://render-api-us.worldofwarcraft.com/static-render/us/" + mCharacter.getAvatarUrl();
            Glide.with(imgAvatar.getContext()).load(avatarUrl).into(imgAvatar);
        }

    }

    private void switchFavoriteAsset()
    {
        if(mCharacter == null) return;

        boolean isFavorite = CharacterProfileUtils.isFavorite(getActivity(), mCharacter.getName(), mCharacter.getRealm());
        boolean success = false;

        if(isFavorite)
        {
            if(deleteFavorite(mCharacter.getId()));
            success = true;
            //mCharacter.setId(0);
            getLoaderManager().destroyLoader(LOADED_DB);
        }
        else
        {
            long id = insertFavorite(mCharacter);
            success = id > 0;
            mCharacter.setId(id);
        }

        if(success)
        {
            notifyUpdate(CharacterEvent.CHARACTER_FAVORITE_UPDATED);

            Snackbar.make(getView(),
                    isFavorite ? R.string.msg_favorite_removed : R.string.msg_favorite_added,
                    Snackbar.LENGTH_LONG)
                    .setAction(R.string.msg_undo, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switchFavoriteAsset();
                        }
                    }).show();
        }
    }

    private void notifyUpdate(String action)
    {
        Intent it = new Intent(action);
        it.putExtra(CharacterEvent.KEY_CHARACTER, mCharacter);
        mLocalBroadcastManager.sendBroadcast(it);

    }

    private long insertFavorite(Character character)
    {
        ContentValues contentValues = new ContentValues();
        //contentValues.put(CharacterContract._ID, character.getId());
        contentValues.put(CharacterContract.COL_CHARACTER_CLASS, character.getCharClass());
        contentValues.put(CharacterContract.COL_CHARACTER_NAME, character.getName());
        contentValues.put(CharacterContract.COL_GUILD_NAME, character.getGuildName());
        contentValues.put(CharacterContract.COL_CHARACTER_HEALTH, character.getHealth());
        contentValues.put(CharacterContract.COL_CHARACTER_LEVEL, character.getLevel());
        contentValues.put(CharacterContract.COL_CHARACTER_STRENGTH, character.getStrength());
        contentValues.put(CharacterContract.COL_CHARACTER_POWER, character.getPower());
        contentValues.put(CharacterContract.COL_CHARACTER_POWER_TYPE, character.getPowerType());
        contentValues.put(CharacterContract.COL_CHARACTER_INTELLIGENCE, character.getIntelligence());
        contentValues.put(CharacterContract.COL_CHARACTER_AGILITY, character.getAgility());
        contentValues.put(CharacterContract.COL_CHARACTER_GENDER, character.getGender());
        contentValues.put(CharacterContract.COL_CHARACTER_REALM, character.getRealm());
        contentValues.put(CharacterContract.COL_CHARACTER_RACE, character.getRace());
        contentValues.put(CharacterContract.COL_CHARACTER_FACTION_NAME, character.getFactionName());
        contentValues.put(CharacterContract.COL_LAST_MODIFIED, character.getLastModified());
        contentValues.put(CharacterContract.COL_CHARACTER_AVATAR, character.getAvatarUrl());

        Uri uri = getActivity().getContentResolver().insert(CharacterProvider.CHARACTERS_URI, contentValues);
        return ContentUris.parseId(uri);
    }

    private boolean deleteFavorite(long characterId)
    {
        return getActivity().getContentResolver().delete(
                ContentUris.withAppendedId(CharacterProvider.CHARACTERS_URI, characterId),
                null, null) > 0;

    }





}
