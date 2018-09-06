package xyz.codem.randomdinner;

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
    private RecyclerView mCrimeRecyclerView;
    private DinnerAdapter mAdapter;
    DinnerList dinnerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dinnerList = new DinnerList();
        List<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        DinnerArray dinners = new DinnerArray(list);
        dinnerList.addNewArray(dinners);


        View view = inflater.inflate(R.layout.activity_main, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {

        List<DinnerArray> dinners = dinnerList.getEntry();
        Log.d("list",dinners.size()+"");

        mAdapter = new DinnerAdapter(dinners);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }


    private class DinnerHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mLastDate;
        private TextView mLastResult;

        public DinnerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_entry_fragment, parent, false));
            mTitle = (TextView) itemView.findViewById(R.id.item_name);
            mLastDate = (TextView) itemView.findViewById(R.id.last_time);
            mLastResult = (TextView) itemView.findViewById(R.id.last_result);
        }

        public void bind(DinnerArray dinners) {
            mTitle.setText(dinners.getName());
            mLastResult.setText(dinners.getLastResult());
            mLastDate.setText(dinners.getLastTime());
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
