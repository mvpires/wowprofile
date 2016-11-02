package com.mvpires.wowprofile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mvpires.wowprofile.http.CharacterSearchTask;
import com.mvpires.wowprofile.model.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Character> {

    public static final String QUERY_NAME_PARAM = "name";
    public static final String QUERY_REALM_PARAM = "realm";
    public static final int LOADER_ID = 0;

    RecyclerView mRecyclerView;
    View mSearchResult;
    CharacterAdapter mCharacterAdapter;
    List<Character> mCharacters;
    LoaderManager mLoaderManager;
    View mEmptyView;
    EditText mEdtNameField;
    EditText mEdtRealmField;
    Button mSearchButton;
    Button mSearchAgainResult;
    Button mSearchAgainEmpty;
    View mView;
    View mRelativeSearchField;
    boolean mFirstSearch = true;



    public CharacterListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCharacters = new ArrayList<>();
        mCharacterAdapter = new CharacterAdapter(getActivity(),mCharacters);
        mCharacterAdapter.setCharacterClickListener(new OnCharacterClickListener() {
            @Override
            public void onCharacterClick(View view, Character character, int position) {
                Activity activity = getActivity();
                if(activity instanceof OnCharacterClickListener)
                {
                    ((OnCharacterClickListener)activity).onCharacterClick(view, character, position);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_character_list, container, false);

        mRecyclerView  = (RecyclerView)mView.findViewById(R.id.recyclerView_characters_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRelativeSearchField = mView.findViewById(R.id.relativeSearchField);
        mSearchAgainResult = (Button) mView.findViewById(R.id.btnSearchAgain);
        mSearchAgainEmpty = (Button) mView.findViewById(R.id.btnSearchEmpty);
        mEmptyView = mView.findViewById(R.id.empty_view);
        mSearchResult = mView.findViewById(R.id.relativeLayoutList);
        mEdtNameField = (EditText)mView.findViewById(R.id.editText_search_name);
        mEdtRealmField = (EditText)mView.findViewById(R.id.editText_search_realm);
        mSearchButton = (Button)mView.findViewById(R.id.search_button);
        mEmptyView.setVisibility(View.GONE);
        mSearchResult.setVisibility(View.GONE);

        mRecyclerView.setAdapter(mCharacterAdapter);


        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                String nameField = mEdtNameField.getText().toString().trim().toLowerCase();
                String realmField = mEdtRealmField.getText().toString().trim().toLowerCase();

                if(nameField != null || nameField != "") bundle.putString(QUERY_NAME_PARAM, nameField);
                if(realmField != null || realmField != "") bundle.putString(QUERY_REALM_PARAM, realmField);

                if(mFirstSearch)
                {
                    mLoaderManager = getLoaderManager();
                    mLoaderManager.initLoader(LOADER_ID, bundle, CharacterListFragment.this);
                    mFirstSearch = false;
                }
                else
                {
                    mLoaderManager.restartLoader(LOADER_ID, bundle, CharacterListFragment.this);
                }

            }
        });

        

        mSearchAgainResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRelativeSearchField.setVisibility(View.VISIBLE);
                mSearchResult.setVisibility(View.GONE);

            }
        });

        mSearchAgainEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEmptyView.setVisibility(View.GONE);
                mRelativeSearchField.setVisibility(View.VISIBLE);
            }
        });


        return mView;
    }


    @Override
    public Loader<Character> onCreateLoader(int id, Bundle args) {
      String queryName = args != null ? args.getString(QUERY_NAME_PARAM): null;
      String queryRealm = args != null ? args.getString(QUERY_REALM_PARAM): null;

        return new CharacterSearchTask(getContext(), queryRealm, queryName);
    }

    @Override
    public void onLoadFinished(Loader<Character> loader, Character data) {
        mCharacters.clear();
        if(data != null)
        {
            mCharacters.add(data);
            mEmptyView.setVisibility(View.GONE);
            mRelativeSearchField.setVisibility(View.GONE);
            mSearchResult.setVisibility(View.VISIBLE);
        }
        else
        {
            mRelativeSearchField.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }

        mCharacterAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(Loader<Character> loader) {

    }
}
