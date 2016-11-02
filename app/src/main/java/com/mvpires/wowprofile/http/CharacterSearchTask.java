package com.mvpires.wowprofile.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.mvpires.wowprofile.model.Character;

/**
 * Created by mv_pi on 23/10/2016.
 */

public class CharacterSearchTask extends AsyncTaskLoader<Character> {

    private Character mCharacter;
    private String mRealm;
    private String mCharName;
    private Context mContext;

    public CharacterSearchTask(Context context, String realm, String charName) {
        super(context);
        mRealm = realm;
        mCharName = charName;
        mContext = context;
    }

    @Override
    protected void onStartLoading()
    {
        super.onStartLoading();

        if(mCharacter == null || !mCharacter.getRealm().equals(mRealm) && !mCharacter.getName().equals(mCharName))
        {
            forceLoad();
        }
        else
        {
            deliverResult(mCharacter);
        }

    }

    @Override
    public Character loadInBackground() {
        mCharacter = CharacterHttp.searchCharacter(mRealm, mCharName, mContext);
        return mCharacter;
    }
}
