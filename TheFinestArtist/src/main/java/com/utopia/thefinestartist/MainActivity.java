package com.utopia.thefinestartist;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    private static final String url = "http://thefinestartist.com";
    private SimpleWebViewFragment mFrag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mFrag = new SimpleWebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SimpleWebViewFragment.EXTRA_URL, url);
        mFrag.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, mFrag);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        if (mFrag == null || !mFrag.hasBack())
            super.onBackPressed();
        else
            mFrag.back();
    }
}
