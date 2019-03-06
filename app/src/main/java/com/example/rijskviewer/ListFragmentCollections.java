package com.example.rijskviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ListFragmentCollections extends FragmentStatePagerAdapter {

    public ListFragmentCollections(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ListArtists list = new ListArtists();
        Bundle bundle = new Bundle();
        position = position+1;
        bundle.putString("message","hey hey from :" +position);
        list.setArguments(bundle);

        return list;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
