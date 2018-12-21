package xyz.codem.randomdinner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private DinnerAdapter mAdapter;
    DinnerList dinnerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dinnerList = DinnerList.get(getActivity());
        List<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("项目3");
        DinnerArray dinners = new DinnerArray();
        dinners.addEntry("item1");
        dinners.addEntry("item2");
        dinners.addEntry("item3");
        dinnerList.addNewArray(dinners);
        dinnerList.addNewArray(new DinnerArray());


        View view = inflater.inflate(R.layout.activity_main, container, false);

        recyclerView = (RecyclerView) view
                .findViewById(R.id.recycler_view);
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


    private class DinnerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitle;
        private TextView mLastDate;
        private TextView mLastResult;
        private DinnerArray dinners;

        public DinnerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_entry_fragment, parent, false));
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.item_name);
            mLastDate = (TextView) itemView.findViewById(R.id.last_time);
            mLastResult = (TextView) itemView.findViewById(R.id.last_result);
        }

        public void bind(DinnerArray dinners) {
            this.dinners = dinners;
            mTitle.setText(dinners.getName());
            mLastResult.setText(dinners.getLastResult());
            mLastDate.setText(dinners.getLastTime().toString());
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
