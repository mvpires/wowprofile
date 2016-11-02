package com.mvpires.wowprofile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mvpires.wowprofile.model.Character;

public class MainActivity extends AppCompatActivity implements OnCharacterClickListener {

    FloatingActionButton floatingActionButton;
    LocalBroadcastManager mLocalBroadcastManager;
    CharacterReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CharacterPageAdapter characterPageAdapter = new CharacterPageAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(characterPageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if(getResources().getBoolean(R.bool.tablet))
        {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

            mReceiver = new CharacterReceiver();
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
            mLocalBroadcastManager.registerReceiver(mReceiver,new IntentFilter(CharacterEvent.CHARACTER_LOADED));
            mLocalBroadcastManager.registerReceiver(mReceiver, new IntentFilter(CharacterEvent.CHARACTER_FAVORITE_UPDATED));

            floatingActionButton = (FloatingActionButton)findViewById(R.id.floating);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(CharacterEvent.UPDATE_CHARACTER_FAVORITE);
                    mLocalBroadcastManager.sendBroadcast(it);
                }
            });
        }
    }

    @Override
    public void onCharacterClick(View view, Character character, int position) {

        if(getResources().getBoolean(R.bool.phone))
        {
            Intent it = new Intent(MainActivity.this, CharacterDetailActivity.class);
            it.putExtra(CharacterDetailActivity.EXTRA_CHARACTER, character);
            startActivity(it);
        }
        else
        {
            CharacterDetailFragment characterDetailFragment = CharacterDetailFragment.newInstance(character);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.placeholderCharDetail, characterDetailFragment)
                    .commit();

        }

    }

    class CharacterReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            Character character = (Character)intent.getSerializableExtra(CharacterEvent.KEY_CHARACTER);
            floatingActionButton.setVisibility(View.VISIBLE);
            CharacterProfileUtils.switchAssetFavorite(context, floatingActionButton, character.getName(), character.getRealm());
        }
    }

    class CharacterPageAdapter extends FragmentPagerAdapter {
        public CharacterPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 1)
            {
                CharacterListFragment characterListFragment = new CharacterListFragment();
                return characterListFragment;

            }
            else
            {
                FavoriteCharactersFragment favoriteCharactersFragment = new FavoriteCharactersFragment();
                return favoriteCharactersFragment;
            }

        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            if (position == 1) return getString(R.string.tab_search);
            else return getString(R.string.tab_favorite);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
