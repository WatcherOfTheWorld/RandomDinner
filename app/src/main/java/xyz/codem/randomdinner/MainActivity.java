package xyz.codem.randomdinner;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AbsFragActivity {

    @Override
    protected Fragment createFragment() {
        return new ListFragment();
    }


}
