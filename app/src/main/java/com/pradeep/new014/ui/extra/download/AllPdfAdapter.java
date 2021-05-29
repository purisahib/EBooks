package com.pradeep.new014.ui.extra.download;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pradeep.new014.R;

import java.io.File;
import java.util.ArrayList;

/*
Created by Puri SabHib
 */
public class AllPdfAdapter extends ArrayAdapter<File> {
    Context acontext;
    ViewHolder aviewHolder;
    ArrayList<File> aal_pdf;
    public AllPdfAdapter(Context context1, ArrayList<File> al1_pdf) {
        super(context1, R.layout.activity_all_pdf_adapter, al1_pdf);
        this.acontext = context1;
        this.aal_pdf = al1_pdf;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        if (aal_pdf.size() > 0) {
            return aal_pdf.size();
        } else {
            return 1;
        }
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_all_pdf_adapter, parent, false);
            aviewHolder = new ViewHolder();
            aviewHolder.tv_filename = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(aviewHolder);
        } else {
            aviewHolder = (ViewHolder) view.getTag();
        }
        aviewHolder.tv_filename.setText(aal_pdf.get(position).getName());
        return view;
    }
    public class ViewHolder {
        TextView tv_filename;
    }
}
