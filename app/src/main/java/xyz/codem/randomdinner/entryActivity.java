package xyz.codem.randomdinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class entryActivity extends AppCompatActivity {
    TextView result;
    FloatingActionButton fab;
    DinnerArray dinners;
    protected Fragment createFragment(DinnerArray array) {
        dinners = array;
        ItemFragment item = new ItemFragment();
        item.dinners = array;
        item.result = result;
        item.fab = fab;
        return item;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete:
                DinnerList list = DinnerList.get(getApplicationContext());
                list.removeArray(dinners);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Get menu inflater.
        MenuInflater menuInflater = getMenuInflater();

        // Use app bar layout menu to inflate the tool bar.
        menuInflater.inflate(R.menu.entry_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().hide();

        Intent intent = getIntent();

        DinnerArray data = (DinnerArray) intent.getSerializableExtra("data");
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        setContentView(R.layout.activity_single_entry);
        result = findViewById(R.id.result);
        result.setText(data.getLastResult());
        fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(data.getName());

        if (fragment == null) {
            fragment = createFragment(data);
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
