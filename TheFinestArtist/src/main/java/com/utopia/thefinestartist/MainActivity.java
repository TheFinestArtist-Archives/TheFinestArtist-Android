package com.utopia.thefinestartist;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

    private static final String url = "http://thefinestartist.com";
    private static Handler mUIHandler = new Handler();
    private SimpleWebViewFragment mFrag = null;
    private boolean mFinishApplication = false;

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
            tryToFinish();
        else
            mFrag.back();
    }

    private void tryToFinish() {
        if (mFinishApplication) {
            finish();
        } else {
            CustomToast.show(this, "Please press Back button again to exit.");
            mFinishApplication = true;
            mUIHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mFinishApplication = false;
                }
            }, 3000);
        }
    }
}
