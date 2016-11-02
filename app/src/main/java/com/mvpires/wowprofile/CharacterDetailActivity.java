package com.mvpires.wowprofile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mvpires.wowprofile.model.Character;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CHARACTER = "character";

    Character mCharacter;
    FloatingActionButton mFloatingActionButton;
    LocalBroadcastManager mLocalBroadcastManager;
    CharacterReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        mCharacter = (Character)getIntent().getSerializableExtra(EXTRA_CHARACTER);
        CharacterDetailFragment characterDetailFragment = CharacterDetailFragment.newInstance(mCharacter);

        mReceiver = new CharacterReceiver();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastManager.registerReceiver(mReceiver, new IntentFilter(CharacterEvent.CHARACTER_LOADED));
        mLocalBroadcastManager.registerReceiver(mReceiver, new IntentFilter(CharacterEvent.CHARACTER_FAVORITE_UPDATED));

        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.floating);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CharacterEvent.UPDATE_CHARACTER_FAVORITE);
                mLocalBroadcastManager.sendBroadcast(it);
            }
        });

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.placeholderCharDetail, characterDetailFragment).commit();
        }
    }

    class CharacterReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            Character character = (Character)intent.getSerializableExtra(CharacterEvent.KEY_CHARACTER);
            mFloatingActionButton.setVisibility(View.VISIBLE);
            CharacterProfileUtils.switchAssetFavorite(context, mFloatingActionButton,character.getName(), character.getRealm());
        }
    }
}
