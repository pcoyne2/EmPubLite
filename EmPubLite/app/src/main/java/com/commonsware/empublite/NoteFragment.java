package com.commonsware.empublite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Patrick Coyne on 9/13/2017.
 */

public class NoteFragment extends Fragment {
    private static final String KEY_POSITION = "position";
    private EditText editor = null;

    static NoteFragment newInstance(int position){
        NoteFragment frag=new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);

        frag.setArguments(args);

        return frag;
    }

    public interface Contract{
        void closeNotes();
    }

    private Contract getContract(){
        return (Contract)getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        if(TextUtils.isEmpty(editor.getText())){
            DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
            db.loadNote(getPosition());
        }
    }

    @Override
    public void onStop() {
        DatabaseHelper.getInstance(getActivity())
                .updateNote(getPosition(), editor.getText().toString());

        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.notes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
            editor.setText(null);
            getContract().closeNotes();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNoteLoaded(NoteLoadedEvent event){
        if(event.getPosition() == getPosition()){
            editor.setText(event.getProse());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.editor, container, false);
        editor = (EditText)result.findViewById(R.id.editor);
        return result;
    }

    private int getPosition(){
        return getArguments().getInt(KEY_POSITION, -1);
    }
}
