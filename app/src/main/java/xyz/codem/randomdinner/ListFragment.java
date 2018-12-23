package xyz.codem.randomdinner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment{
    private RecyclerView recyclerView;
    private DinnerAdapter mAdapter;
    private static int REQUEST = 0;
    DinnerList dinnerList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    /**
     * onResume()
     * update item in adapter
     */
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setShowTitle(true);
                builder.setToolbarColor(Color.WHITE);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(getActivity(),  Uri.parse("http://codem.xyz/"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dinnerList = DinnerList.get(getActivity());
        View view = inflater.inflate(R.layout.activity_main, container, false);

        FloatingActionButton fab=  view.findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dinnerList.addNewArray(new DinnerArray());
                FragmentManager manager = getFragmentManager();
                NamePickerFragment dialog = NamePickerFragment.newInstance("Add a Set");
                dialog.setTargetFragment(ListFragment.this,REQUEST);
                dialog.show(manager, "");
            }
        });
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI() {

        List<DinnerArray> dinners = dinnerList.getEntry();
        Log.d("list",dinners.size()+"");

        mAdapter = new DinnerAdapter(dinners);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST){
            DinnerArray array = new DinnerArray();
            array.setName((String)data.getSerializableExtra("name"));
            DinnerList.get(getContext()).addNewArray(array);
            updateUI();
        }
    }

    private class DinnerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitle;
        private TextView mLastDate;
        private TextView mLastResult;
        private DinnerArray dinners;

        public DinnerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_entry_fragment, parent, false));
            itemView.setOnClickListener(this);
            mTitle = itemView.findViewById(R.id.item_name);
            mLastDate = itemView.findViewById(R.id.last_time);
            mLastResult = itemView.findViewById(R.id.last_result);
        }

        public void bind(DinnerArray dinners) {
            this.dinners = dinners;
            mTitle.setText(dinners.getName());
            mLastResult.setText(dinners.getLastResult());
            mLastDate.setText(dinners.getTimeString());
        }

        @Override
        public void onClick(View v) {
            Log.d("0","启动活动");
            Intent intent = new Intent(getActivity(),entryActivity.class);
            intent.putExtra("data", dinners);
            startActivity(intent);
        }
    }

    private class DinnerAdapter extends RecyclerView.Adapter<DinnerHolder> {

        private List<DinnerArray> mDinners;

        public DinnerAdapter(List<DinnerArray> dinners) {
            mDinners = dinners;
        }

        @Override
        public DinnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new DinnerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(DinnerHolder holder, int position) {
            holder.bind(mDinners.get(position));

        }

        @Override
        public int getItemCount() {
            return mDinners.size();
        }
    }
}
