package com.example.mydiary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2019/12/24.
 */

public class DiaryAdapter extends ArrayAdapter<Diary> {
    private int resourceId;

    public DiaryAdapter(Context context, int textViewResourceId, List<Diary> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Diary diary = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.day = (TextView)view.findViewById(R.id.dayText);
            viewHolder.weekday = (TextView)view.findViewById(R.id.weekdayText);
            viewHolder.date = (TextView)view.findViewById(R.id.dateText);
            viewHolder.summary = (TextView)view.findViewById(R.id.summaryText);
            viewHolder.mood = (ImageView)view.findViewById(R.id.mood);
            viewHolder.weather = (ImageView)view.findViewById(R.id.weather);
            view.setTag(viewHolder);
        } else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.day.setText(diary.getDay()+"");
        viewHolder.weekday.setText(diary.getWeekday()+"");
        viewHolder.date.setText(diary.getYear()+"."+diary.getMonth()+"."+diary.getDay());
        viewHolder.summary.setText(diary.getSummary());
        viewHolder.mood.setImageResource(diary.getMoodPic());
        viewHolder.weather.setImageResource(diary.getWeatherPic());
        return view;
    }

    class ViewHolder{
        TextView day;
        TextView weekday;
        TextView date;
        TextView summary;
        ImageView mood;
        ImageView weather;
    }
}
