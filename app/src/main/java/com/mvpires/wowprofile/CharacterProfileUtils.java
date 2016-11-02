package com.mvpires.wowprofile;

import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import com.mvpires.wowprofile.database.CharacterContract;
import com.mvpires.wowprofile.database.CharacterProvider;

/**
 * Created by mv_pi on 22/10/2016.
 */

public class CharacterProfileUtils {

    public static boolean isFavorite(Context context, String charName, String realmName)
    {
        Cursor cursor = context.getContentResolver().query(
                CharacterProvider.CHARACTERS_URI,
                new String[]{CharacterContract._ID},
                CharacterContract.COL_CHARACTER_NAME + " = ? AND " +
                CharacterContract.COL_CHARACTER_REALM + "= ?",
                new String[]{charName, realmName},
                null
        );

        boolean isFavorite = false;
        if (cursor != null)
        {
            isFavorite = cursor.getCount() >0;
            cursor.close();
        }

        return isFavorite;
    }

public static void switchAssetFavorite(Context context, FloatingActionButton floatingActionButton, String charName, String realmName)
{
    if (isFavorite(context, charName, realmName))
    {
        floatingActionButton.setImageResource(R.drawable.ic_star_black_24dp);
    }
    else
    {
        floatingActionButton.setImageResource(R.drawable.ic_star_border_black_24dp);
    }
}


//    public void convertAPIClassValue(String value, Context context, ViewHolder viewHolder)
//    {
//        switch(value)
//        {
//            case "1":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_warrior));
//                viewHolder.imgClass.setImageResource(R.drawable.class_warrior);
//                break;
//            case "2":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_paladin));
//                viewHolder.imgClass.setImageResource(R.drawable.class_paladin);
//                break;
//            case "3":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_hunter));
//                viewHolder.imgClass.setImageResource(R.drawable.class_hunter);
//                break;
//            case "4":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_rogue));
//                viewHolder.imgClass.setImageResource(R.drawable.class_rogue);
//                break;
//            case "5":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_priest));
//                viewHolder.imgClass.setImageResource(R.drawable.class_priest);
//                break;
//            case "6":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_death_knight));
//                viewHolder.imgClass.setImageResource(R.drawable.class_deathknight);
//                break;
//            case "7":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_shaman));
//                viewHolder.imgClass.setImageResource(R.drawable.class_shaman);
//                break;
//            case "8":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_mage));
//                viewHolder.imgClass.setImageResource(R.drawable.class_mage);
//                break;
//            case "9":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_warlock));
//                viewHolder.imgClass.setImageResource(R.drawable.class_warlock);
//                break;
//            case "10":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_monk));
//                viewHolder.imgClass.setImageResource(R.drawable.class_monk);
//                break;
//            case "11":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_druid));
//                viewHolder.imgClass.setImageResource(R.drawable.class_druid);
//                break;
//            case "12":
//                viewHolder.txtClass.setText(context.getResources().getString(R.string.char_class_demon_hunter));
//                viewHolder.imgClass.setImageResource(R.drawable.class_demonhunter);
//                break;
//        }
//    }

}
