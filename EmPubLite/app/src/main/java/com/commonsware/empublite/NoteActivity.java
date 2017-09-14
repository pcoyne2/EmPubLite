package com.commonsware.empublite;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by Patrick Coyne on 9/13/2017.
 */

public class NoteActivity extends Activity implements NoteFragment.Contract{
    public static final String EXTRA_POSITION="position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            int position=getIntent().getIntExtra(EXTRA_POSITION, -1);

            if (position >= 0) {
                Fragment f=NoteFragment.newInstance(position);

                getFragmentManager().beginTransaction()
                        .add(android.R.id.content, f).commit();
            }
        }
    }

    @Override
    public void closeNotes() {
        finish();
    }
}

