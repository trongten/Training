package com.firebase.ontap2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adap extends BaseAdapter {

    private Context context;
    private int layout;
    private List<En> arrayList;
    int positionSelect = -1;

    public Adap(Context context, int layout, List<En> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        }

        TextView txt1 = view.findViewById(R.id.textView5);
        TextView txt2 = view.findViewById(R.id.textView6);
        View view2 = view.findViewById(R.id.view);

        txt1.setText(arrayList.get(i).getName());
        txt2.setText(arrayList.get(i).getDep());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListEn.id = arrayList.get(i).getId();
                ListEn.txtname.setText(arrayList.get(i).getName());
                ListEn.txtdep.setText(arrayList.get(i).getDep());
                positionSelect =i;
                notifyDataSetChanged();
            }
        });

        if (positionSelect == i) {
            view2.setBackgroundColor(Color.GRAY);
        } else {
            view2.setBackgroundColor(Color.WHITE);
        }

        return view;
    }
}
