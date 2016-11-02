package com.mvpires.wowprofile;

import android.view.View;

import com.mvpires.wowprofile.model.Character;

/**
 * Created by mv_pi on 23/10/2016.
 */

public interface OnCharacterClickListener {
    void onCharacterClick(View view, Character character, int position);
}
