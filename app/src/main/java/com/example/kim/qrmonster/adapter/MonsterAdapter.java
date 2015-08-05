package com.example.kim.qrmonster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.qrmonster.R;
import com.example.kim.qrmonster.units.Monster;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by kim on 2015-08-05.
 */
public class MonsterAdapter extends BaseAdapter {
    Context _context;
    LayoutInflater _inflater;
    ArrayList<Monster> _arrayList;
    int _layout;

    public MonsterAdapter(Context context, int layout, ArrayList<Monster> arrayList) {
        _context = context;
        _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _arrayList = arrayList;
        _layout = layout;
    }

    @Override
    public int getCount() {
        return _arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return _arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(_context).inflate(_layout, parent, false);
        }
        ImageView img = (ImageView)convertView.findViewById(R.id.list_monster_image);
        //img.setImageResource(_arrayList.get(position).get_image());

        TextView name = (TextView)convertView.findViewById(R.id.list_monster_name);
        name.setText(_arrayList.get(position).get_name());
        TextView attack = (TextView)convertView.findViewById(R.id.list_monster_attack);
        attack.setText("Attack: " + Integer.toString(_arrayList.get(position).get_attack()));
        TextView defence = (TextView)convertView.findViewById(R.id.list_monster_defence);
        defence.setText("Defence: " + Integer.toString(_arrayList.get(position).get_defence()));
        TextView hp = (TextView)convertView.findViewById(R.id.list_monster_hp);
        hp.setText("Health: " + Integer.toString(_arrayList.get(position).get_health()));

        return convertView;

    }
}
