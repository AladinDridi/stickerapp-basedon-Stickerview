package com.example.aladin_bureau.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class imageAdapter extends BaseAdapter {
    int ressource;
    Context context;
    mystickers msticker=new mystickers();
    List<mystickers>mystickersList;
    public imageAdapter(Context context,List<mystickers>mystickersList){
        this.context=context;
        this.mystickersList=mystickersList;
    }
    @Override
    public int getCount() {
        return mystickersList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          View view=convertView;
          placeholder holder=new placeholder();
          if(view==null){
              LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
              view=inflater.inflate(R.layout.itemadapter_layout,parent,false);
              holder.imagev=(ImageView)view.findViewById(R.id.imagedusticker);
              view.setTag(holder);
          }else{
              holder=(placeholder)view.getTag();
          }
          holder.imagev.setImageResource(mystickersList.get(position).getImg());

        return view;
    }
public class placeholder{
        ImageView imagev;
    }
}
