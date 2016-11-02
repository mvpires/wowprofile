package com.mvpires.wowprofile;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvpires.wowprofile.model.Character;

import java.util.List;

/**
 * Created by mv_pi on 25/10/2016.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private List<Character> mCharacters;
    private Context mContext;
    private OnCharacterClickListener mCharacterClickListener;

    public CharacterAdapter(Context context, List<Character> characters)
    {
        mCharacters = characters;
        mContext = context;
    }

    public void setCharacterClickListener(OnCharacterClickListener listener)
    {
        this.mCharacterClickListener = listener;
    }



    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_char_list, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =  viewHolder.getAdapterPosition();

                if (mCharacterClickListener != null)
                {
                    mCharacterClickListener.onCharacterClick(view, mCharacters.get(position), position);
                }
            }
        });



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CharacterAdapter.ViewHolder holder, int position) {

        Character character = mCharacters.get(position);

        switch(character.getCharClass())
        {
            case "1":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_warrior));
                holder.imgClass.setImageResource(R.drawable.class_warrior);
                break;
            case "2":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_paladin));
                holder.imgClass.setImageResource(R.drawable.class_paladin);
                break;
            case "3":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_hunter));
                holder.imgClass.setImageResource(R.drawable.class_hunter);
                break;
            case "4":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_rogue));
                holder.imgClass.setImageResource(R.drawable.class_rogue);
                break;
            case "5":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_priest));
                holder.imgClass.setImageResource(R.drawable.class_priest);
                break;
            case "6":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_death_knight));
                holder.imgClass.setImageResource(R.drawable.class_deathknight);
                break;
            case "7":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_shaman));
                holder.imgClass.setImageResource(R.drawable.class_shaman);
                break;
            case "8":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_mage));
                holder.imgClass.setImageResource(R.drawable.class_mage);
                break;
            case "9":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_warlock));
                holder.imgClass.setImageResource(R.drawable.class_warlock);
                break;
            case "10":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_monk));
                holder.imgClass.setImageResource(R.drawable.class_monk);
                break;
            case "11":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_druid));
                holder.imgClass.setImageResource(R.drawable.class_druid);
                break;
            case "12":
                holder.txtClass.setText(mContext.getResources().getString(R.string.char_class_demon_hunter));
                holder.imgClass.setImageResource(R.drawable.class_demonhunter);
                break;
        }


        switch (character.getFactionName())
        {
            //Alliance
            case "0":
                holder.imgFaction.setImageResource(R.drawable.faction_alliance);
                break;
            //Horde
            case "1":
                holder.imgFaction.setImageResource(R.drawable.faction_horde);
                break;
        }


        switch (character.getGender()) {

            //Male
            case "0":

                holder.imgGender.setImageResource(R.drawable.gender_male);

                switch (character.getRace()) {

                    case "1":
                        holder.imgRace.setImageResource(R.drawable.race_human_male);

                        break;
                    case "2":

                        holder.imgRace.setImageResource(R.drawable.race_orc_male);
                        break;
                    case "3":
                        holder.imgRace.setImageResource(R.drawable.race_dwarf_male);
                        break;
                    case "4":
                        holder.imgRace.setImageResource(R.drawable.race_nightelf_male);
                        break;
                    case "5":
                        holder.imgRace.setImageResource(R.drawable.race_undead_male);
                        break;
                    case "6":
                        holder.imgRace.setImageResource(R.drawable.race_tauren_male);
                        break;
                    case "7":
                        holder.imgRace.setImageResource(R.drawable.race_gnome_male);
                        break;
                    case "8":
                        holder.imgRace.setImageResource(R.drawable.race_troll_male);
                        break;
                    case "9":
                        holder.imgRace.setImageResource(R.drawable.race_goblin_male);
                        break;
                    case "10":
                        holder.imgRace.setImageResource(R.drawable.race_bloodelf_male);
                        break;
                    case "11":
                        holder.imgRace.setImageResource(R.drawable.race_draenei_male);
                        break;
                    case "22":
                        holder.imgRace.setImageResource(R.drawable.race_worgen_male);
                        break;
                    case "24":
                        holder.imgRace.setImageResource(R.drawable.race_pandaren_male);
                        break;
                    case "25":
                        holder.imgRace.setImageResource(R.drawable.race_pandaren_male);
                        break;
                    case "26":
                        holder.imgRace.setImageResource(R.drawable.race_pandaren_male);
                        break;
                }
                break;

            //Female
            case "1":

                holder.imgGender.setImageResource(R.drawable.gender_female);
                switch (character.getRace()) {

                    case "1":
                        holder.imgRace.setImageResource(R.drawable.race_human_female);

                        break;
                    case "2":
                        holder.imgRace.setImageResource(R.drawable.race_orc_female);
                        break;
                    case "3":
                        holder.imgRace.setImageResource(R.drawable.race_dwarf_female);
                        break;
                    case "4":
                        holder.imgRace.setImageResource(R.drawable.race_nightelf_female);
                        break;
                    case "5":
                        holder.imgRace.setImageResource(R.drawable.race_undead_female);
                        break;
                    case "6":
                        holder.imgRace.setImageResource(R.drawable.race_tauren_female);
                        break;
                    case "7":
                        holder.imgRace.setImageResource(R.drawable.race_gnome_female);
                        break;
                    case "8":
                        holder.imgRace.setImageResource(R.drawable.race_troll_female);
                        break;
                    case "9":
                        holder.imgRace.setImageResource(R.drawable.race_goblin_female);
                        break;
                    case "10":
                        holder.imgRace.setImageResource(R.drawable.race_bloodelf_female);
                        break;
                    case "11":
                        holder.imgRace.setImageResource(R.drawable.race_draenei_female);
                        break;
                    case "22":
                        holder.imgRace.setImageResource(R.drawable.race_worgen_female);
                        break;
                    case "24":
                        holder.imgRace.setImageResource(R.drawable.race_pandaren_female);
                        break;
                    case "25":
                        holder.imgRace.setImageResource(R.drawable.race_pandaren_female);
                        break;
                    case "26":
                        holder.imgRace.setImageResource(R.drawable.race_pandaren_female);
                        break;
                }
                break;

        }

        holder.txtLevel.setText(character.getLevel());
        holder.txtGuildName.setText(character.getGuildName());
        holder.txtName.setText(character.getName());
        holder.charRealm = character.getRealm();

    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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

        public ViewHolder(View itemView) {
            super(itemView);

            itemListView = itemView;

            imgClass = (ImageView)itemView.findViewById(R.id.imageView_char_class_item);
            imgFaction = (ImageView) itemView.findViewById(R.id.imageView_char_faction_item);
            imgRace = (ImageView)itemView.findViewById(R.id.imageView_char_race_item);
            imgGender = (ImageView)itemView.findViewById(R.id.imageView_char_gender_item);

            txtClass = (TextView)itemView.findViewById(R.id.textView_char_class_item);
            txtName = (TextView)itemView.findViewById(R.id.textView_char_name_item);
            txtLevel = (TextView)itemView.findViewById(R.id.textView_char_level_item);
            txtGuildName = (TextView)itemView.findViewById(R.id.textView_char_guild_name_item);

        }
    }

}
