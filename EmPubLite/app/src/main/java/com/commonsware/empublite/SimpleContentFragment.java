package com.commonsware.empublite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;

/**
 * Created by Patrick Coyne on 9/9/2017.
 */

public class SimpleContentFragment extends WebViewFragment {

    private static final String KEY_FILE = "file";

    static SimpleContentFragment newInstance(String file){
        SimpleContentFragment f = new SimpleContentFragment();
        Bundle args = new Bundle();

        args.putString(KEY_FILE, file);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View result = super.onCreateView(inflater, container, savedInstanceState);
        getWebView().getSettings().setJavaScriptEnabled(true);
        getWebView().getSettings().setSupportZoom(true);
        getWebView().getSettings().setBuiltInZoomControls(true);
        getWebView().loadUrl(getPage());

        return result;
    }

    private String getPage(){
        return getArguments().getString(KEY_FILE);
    }
}
