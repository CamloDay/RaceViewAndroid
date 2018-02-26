package i_neda.com.raceview.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import i_neda.com.raceview.R;
import i_neda.com.raceview.data.RunnerItem;

/**
 * Created by cam on 23/02/2018.
 * displays the data for each runner in a view
 */

public class RunnerListAdapter extends ArrayAdapter<RunnerItem> {
    private Context mContext;
    private List<RunnerItem> mRunnerItemList;

    private static class ViewHolder{
        private TextView mTvNumber;
        private TextView mTvHorseName;
        private TextView mTvJockeyName;
        private TextView mTvForm;
        private TextView mTvOdds;
    }

    public RunnerListAdapter(Context context, List<RunnerItem> runnerItemList){
        super(context, R.layout.item_runner, runnerItemList);
        this.mContext = context;
        this.mRunnerItemList = runnerItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            v = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_runner, null, false);
            ViewHolder vh = new ViewHolder();
            vh.mTvNumber = (TextView) v.findViewById(R.id.tvNumber);
            vh.mTvHorseName = (TextView) v.findViewById(R.id.tvHorseName);
            vh.mTvJockeyName = (TextView) v.findViewById(R.id.tvJockeyName);
            vh.mTvForm = (TextView) v.findViewById(R.id.tvForm);
            vh.mTvOdds = (TextView) v.findViewById(R.id.tvOdds);
            v.setTag(vh);
        }

        ViewHolder vh = (ViewHolder)v.getTag();
        RunnerItem runnerItem = mRunnerItemList.get(position);
        vh.mTvNumber.setText(String.valueOf(runnerItem.getNumber()));
        vh.mTvHorseName.setText(runnerItem.getHorseName());
        vh.mTvJockeyName.setText(runnerItem.getJockeyName());
        vh.mTvForm.setText(runnerItem.getForm());
        vh.mTvOdds.setText(String.valueOf(runnerItem.getOdds()));

        return v;
    }
}
