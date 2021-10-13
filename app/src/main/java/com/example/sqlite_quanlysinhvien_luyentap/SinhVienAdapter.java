package com.example.sqlite_quanlysinhvien_luyentap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTenSV;
        ImageButton imgbtnEdit, imgbtnDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTenSV = (TextView)convertView.findViewById(R.id.TENSINHVIEN);
            holder.imgbtnDelete = (ImageButton)convertView.findViewById(R.id.imgbtnXOASINHVIEN);
            holder.imgbtnEdit = (ImageButton)convertView.findViewById(R.id.imgbtnSUASINHVIEN);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        SinhVien sinhVien = sinhVienList.get(position);
        holder.txtTenSV.setText(sinhVien.getTenSV());
        holder.imgbtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.SuaSinhVien(sinhVien.getTenSV(), sinhVien.getIdSV());
            }
        });
        holder.imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.XoaSinhVien(sinhVien.getTenSV(), sinhVien.getIdSV());
            }
        });
        return convertView;
    }
}
