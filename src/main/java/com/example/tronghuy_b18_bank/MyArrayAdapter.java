package com.example.tronghuy_b18_bank;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class MyArrayAdapter  extends ArrayAdapter<TyGia> {
    Activity context;
    int resource;
    List<TyGia> listTG;

    public MyArrayAdapter( Activity context1, int resource1, List<TyGia> list) {
        super(context1, resource1, list);
        this.context = context1;
        this.resource = resource1;
        this.listTG = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= this.context.getLayoutInflater();
        convertView= inflater.inflate(resource,null);
        TyGia tyGia= this.listTG.get(position);

        ImageView img= convertView.findViewById(R.id.img);
        TextView txt_MuaTM=convertView.findViewById(R.id.txt_muaTM);
        TextView txt_BanTM=convertView.findViewById(R.id.txt_banTM);
        TextView txt_MuaCK=convertView.findViewById(R.id.txt_muaCK);
        TextView txt_BanCK=convertView.findViewById(R.id.txt_banCK);
        TextView txt_Type=convertView.findViewById(R.id.txt_Type);

        img.setImageBitmap(tyGia.getBitmap());
        txt_MuaTM.setText(tyGia.getMuaTienMat());
        txt_BanTM.setText(tyGia.getBanTienMat());
        txt_MuaCK.setText(tyGia.getMuaCk());
        txt_BanCK.setText(tyGia.getBanCk());
        txt_Type.setText(tyGia.getType());


        return convertView;

    }
}
