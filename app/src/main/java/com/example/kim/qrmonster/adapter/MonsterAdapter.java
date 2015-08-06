package com.example.kim.qrmonster.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kim.qrmonster.R;
import com.example.kim.qrmonster.assets.MonsterImageView;
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
        MonsterImageView imageView = (MonsterImageView)convertView.findViewById(R.id.list_monster_image);
        //img.setImageResource(_arrayList.get(position).get_image        ());
        //set Image
        imageView.listMode(true);
        TypedArray array = null;
        switch (_arrayList.get(position).get_tier()){
            case 1:
                array = _context.getResources().obtainTypedArray(R.array.tier_one_monster_images);
                imageView.setImageResource(array.getResourceId(_arrayList.get(position).get_image(), R.drawable.monster_1));
                array.recycle();
                break;
            case 2:
                array = _context.getResources().obtainTypedArray(R.array.tier_two_monster_images);
                imageView.setImageResource(array.getResourceId(_arrayList.get(position).get_image(), R.drawable.monster_3));
                array.recycle();
                break;
            case 3:
                array = _context.getResources().obtainTypedArray(R.array.tier_three_monster_images);
                imageView.setImageResource(array.getResourceId(_arrayList.get(position).get_image(), R.drawable.monster_10));
                array.recycle();
                break;
            case 4:
                array = _context.getResources().obtainTypedArray(R.array.tier_four_monster_images);
                imageView.setImageResource(array.getResourceId(_arrayList.get(position).get_image(), R.drawable.monster_12));
                array.recycle();
                break;
            case 5:
                array = _context.getResources().obtainTypedArray(R.array.tier_five_monster_images);
                imageView.setImageResource(array.getResourceId(_arrayList.get(position).get_image(), R.drawable.monster_19));
                array.recycle();
                break;
        }


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
