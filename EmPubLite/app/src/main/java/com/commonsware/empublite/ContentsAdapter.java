package com.commonsware.empublite;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

/**
 * Created by Patrick Coyne on 9/11/2017.
 */

public class ContentsAdapter extends FragmentStatePagerAdapter {
    final BookContents contents;

    public ContentsAdapter(Activity ctx, BookContents contents) {
        super(ctx.getFragmentManager());

        this.contents = contents;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return contents.getChapterTitle(position);
    }

    @Override
    public Fragment getItem(int position) {
        String path = contents.getChapterFile(position);

        return SimpleContentFragment.newInstance("file:///android_asset/book"+path);
    }

    @Override
    public int getCount() {
        return contents.getChapterCount();
    }
}
