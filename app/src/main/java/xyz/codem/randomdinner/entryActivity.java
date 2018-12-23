package xyz.codem.randomdinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class entryActivity extends AppCompatActivity {
    TextView result;
    protected Fragment createFragment(DinnerArray array) {
        ItemFragment item = new ItemFragment();
        item.dinners = array;

        return item;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        DinnerArray data = (DinnerArray) intent.getSerializableExtra("data");
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        setContentView(R.layout.activity_single_entry);

        if (fragment == null) {
            fragment = createFragment(data);
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        result = findViewById(R.id.result);
        result.setText(data.getLastResult());
    }

}
