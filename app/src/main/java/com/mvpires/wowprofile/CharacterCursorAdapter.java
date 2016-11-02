package com.mvpires.wowprofile;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.mvpires.wowprofile.database.CharacterContract;

/**
 * Created by mv_pi on 24/10/2016.
 */

public class CharacterCursorAdapter extends SimpleCursorAdapter {

    public static final int LAYOUT = R.layout.item_char_list;

    public CharacterCursorAdapter(Context context, Cursor c) {
        super(context, LAYOUT, c, CharacterContract.LIST_COLUMNS, null, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        View view = LayoutInflater.from(context).inflate(LAYOUT, parent, false);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.itemListView = view;

        viewHolder.imgClass = (ImageView)view.findViewById(R.id.imageView_char_class_item);
        viewHolder.imgFaction = (ImageView) view.findViewById(R.id.imageView_char_faction_item);
        viewHolder.imgRace = (ImageView)view.findViewById(R.id.imageView_char_race_item);
        viewHolder.imgGender = (ImageView)view.findViewById(R.id.imageView_char_gender_item);

        viewHolder.txtClass = (TextView)view.findViewById(R.id.textView_char_class_item);
        viewHolder.txtName = (TextView)view.findViewById(R.id.textView_char_name_item);
        viewHolder.txtLevel = (TextView)view.findViewById(R.id.textView_char_level_item);
        viewHolder.txtGuildName = (TextView)view.findViewById(R.id.textView_char_guild_name_item);

        view.setTag(viewHolder);


        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        ViewHolder viewHolder = (ViewHolder)view.getTag();

        String charClass = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_CLASS));

        switch (charClass)
        {
            case "1":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_warrior));
                viewHolder.imgClass.setImageResource(R.drawable.class_warrior);
                break;
            case "2":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_paladin));
                viewHolder.imgClass.setImageResource(R.drawable.class_paladin);
                break;
            case "3":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_hunter));
                viewHolder.imgClass.setImageResource(R.drawable.class_hunter);
                break;
            case "4":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_rogue));
                viewHolder.imgClass.setImageResource(R.drawable.class_rogue);
                break;
            case "5":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_priest));
                viewHolder.imgClass.setImageResource(R.drawable.class_priest);
                break;
            case "6":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_death_knight));
                viewHolder.imgClass.setImageResource(R.drawable.class_deathknight);
                break;
            case "7":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_shaman));
                viewHolder.imgClass.setImageResource(R.drawable.class_shaman);
                break;
            case "8":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_mage));
                viewHolder.imgClass.setImageResource(R.drawable.class_mage);
                break;
            case "9":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_warlock));
                viewHolder.imgClass.setImageResource(R.drawable.class_warlock);
                break;
            case "10":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_monk));
                viewHolder.imgClass.setImageResource(R.drawable.class_monk);
                break;
            case "11":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_druid));
                viewHolder.imgClass.setImageResource(R.drawable.class_druid);
                break;
            case "12":
                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_demon_hunter));
                viewHolder.imgClass.setImageResource(R.drawable.class_demonhunter);
                break;
        }



        String charFaction = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_FACTION_NAME));

        switch (charFaction)
        {
            case "0":
                viewHolder.imgFaction.setImageResource(R.drawable.faction_alliance);
                break;
            case "1":
                viewHolder.imgFaction.setImageResource(R.drawable.faction_horde);
                break;
        }

        String charRace = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_RACE));
        String charGender = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_GENDER));
        switch (charGender) {

            //Male
            case "0":

                viewHolder.imgGender.setImageResource(R.drawable.gender_male);

            switch (charRace) {

                case "1":
                    viewHolder.imgRace.setImageResource(R.drawable.race_human_male);

                    break;
                case "2":

                    viewHolder.imgRace.setImageResource(R.drawable.race_orc_male);
                    break;
                case "3":
                    viewHolder.imgRace.setImageResource(R.drawable.race_dwarf_male);
                    break;
                case "4":
                    viewHolder.imgRace.setImageResource(R.drawable.race_nightelf_male);
                    break;
                case "5":
                    viewHolder.imgRace.setImageResource(R.drawable.race_undead_male);
                    break;
                case "6":
                    viewHolder.imgRace.setImageResource(R.drawable.race_tauren_male);
                    break;
                case "7":
                    viewHolder.imgRace.setImageResource(R.drawable.race_gnome_male);
                    break;
                case "8":
                    viewHolder.imgRace.setImageResource(R.drawable.race_troll_male);
                    break;
                case "9":
                    viewHolder.imgRace.setImageResource(R.drawable.race_goblin_male);
                    break;
                case "10":
                    viewHolder.imgRace.setImageResource(R.drawable.race_bloodelf_male);
                    break;
                case "11":
                    viewHolder.imgRace.setImageResource(R.drawable.race_draenei_male);
                    break;
                case "22":
                    viewHolder.imgRace.setImageResource(R.drawable.race_worgen_male);
                    break;
                case "24":
                    viewHolder.imgRace.setImageResource(R.drawable.race_pandaren_male);
                    break;
                case "25":
                    viewHolder.imgRace.setImageResource(R.drawable.race_pandaren_male);
                    break;
                case "26":
                    viewHolder.imgRace.setImageResource(R.drawable.race_pandaren_male);
                    break;
            }
                break;

            //Female
            case "1":

                viewHolder.imgGender.setImageResource(R.drawable.gender_female);
                switch (charRace) {

                    case "1":
                        viewHolder.imgRace.setImageResource(R.drawable.race_human_female);
                        break;
                    case "2":
                        viewHolder.imgRace.setImageResource(R.drawable.race_orc_female);
                        break;
                    case "3":
                        viewHolder.imgRace.setImageResource(R.drawable.race_dwarf_female);
                        break;
                    case "4":
                        viewHolder.imgRace.setImageResource(R.drawable.race_nightelf_female);
                        break;
                    case "5":
                        viewHolder.imgRace.setImageResource(R.drawable.race_undead_female);
                        break;
                    case "6":
                        viewHolder.imgRace.setImageResource(R.drawable.race_tauren_female);
                        break;
                    case "7":
                        viewHolder.imgRace.setImageResource(R.drawable.race_gnome_female);
                        break;
                    case "8":
                        viewHolder.imgRace.setImageResource(R.drawable.race_troll_female);
                        break;
                    case "9":
                        viewHolder.imgRace.setImageResource(R.drawable.race_goblin_female);
                        break;
                    case "10":
                        viewHolder.imgRace.setImageResource(R.drawable.race_bloodelf_female);
                        break;
                    case "11":
                        viewHolder.imgRace.setImageResource(R.drawable.race_draenei_female);
                        break;
                    case "22":
                        viewHolder.imgRace.setImageResource(R.drawable.race_worgen_female);
                        break;
                    case "24":
                        viewHolder.imgRace.setImageResource(R.drawable.race_pandaren_female);
                        break;
                    case "25":
                        viewHolder.imgRace.setImageResource(R.drawable.race_pandaren_female);
                        break;
                    case "26":
                        viewHolder.imgRace.setImageResource(R.drawable.race_pandaren_female);
                        break;
                }
                break;

        }



        String charName = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_NAME));
        viewHolder.txtName.setText(charName);
        String charLevel = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_LEVEL));
        viewHolder.txtLevel.setText(charLevel);
        String guildName = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_GUILD_NAME));
        viewHolder.txtGuildName.setText(guildName);
        viewHolder.charRealm = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_REALM));
        viewHolder.charAvatar = cursor.getString(cursor.getColumnIndex(CharacterContract.COL_CHARACTER_AVATAR));
    }


    class ViewHolder
    {
        ImageView imgFaction;
        ImageView imgClass;
        ImageView imgRace;
        ImageView imgGender;
        TextView txtName;
        TextView txtClass;
        TextView txtLevel;
        TextView txtGuildName;
        View itemListView;
        String charRealm;
        String charAvatar;
    }




}
