package xyz.codem.randomdinner;

import android.support.v4.app.Fragment;

public class MainActivity extends AbsFragActivity {

    @Override
    protected Fragment createFragment() {
        return new ListFragment();
    }

}
