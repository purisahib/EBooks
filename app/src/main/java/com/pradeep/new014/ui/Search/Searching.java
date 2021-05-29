package com.pradeep.new014.ui.Search;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.pradeep.new014.R;

public class Searching extends Fragment{
    private Button rbseLink,rbseOpen,ncrtLink,ncrtOpen,cbseLink,cbseOpen;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_searching, container, false);
        rbseLink=(Button)view.findViewById(R.id.button_LinkRbse);
        rbseOpen=(Button)view.findViewById(R.id.button_openRbse);
        ncrtLink=(Button)view.findViewById(R.id.button_LinkNcert);
        ncrtOpen=(Button)view.findViewById(R.id.button_openNcert);
        cbseLink=(Button)view.findViewById(R.id.button_LinkCbse);
        cbseOpen=(Button)view.findViewById(R.id.button_openCbse);

        rbseLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "http://rajeduboard.rajasthan.gov.in/books/index.htm";
                Intent intentLink = new Intent(Intent.ACTION_SEND);
                intentLink.putExtra(Intent.EXTRA_TEXT, link);
                intentLink.setType("text/plain");

                Intent chooser = Intent.createChooser(intentLink, "Share with friend: ");
                    startActivity(chooser);
            }
        });
        rbseOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri website = Uri.parse("http://rajeduboard.rajasthan.gov.in/books/index.htm");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, website);
                startActivity(webIntent);
            }
        });
        ncrtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "http://ncert.nic.in/ebooks.html";
                Intent intentLink = new Intent(Intent.ACTION_SEND);
                intentLink.putExtra(Intent.EXTRA_TEXT, link);
                intentLink.setType("text/plain");

                Intent chooser = Intent.createChooser(intentLink, "Share with friend: ");
                startActivity(chooser);
            }
        });
        ncrtOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri website = Uri.parse("http://ncert.nic.in/ebooks.html");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, website);
                startActivity(webIntent);
            }
        });
        cbseLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "http://cbse.nic.in/ecbse/welcome.html";
                Intent intentLink = new Intent(Intent.ACTION_SEND);
                intentLink.putExtra(Intent.EXTRA_TEXT, link);
                intentLink.setType("text/plain");

                Intent chooser = Intent.createChooser(intentLink, "Share with friend: ");

                    startActivity(chooser);
            }
        });
        cbseOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri website = Uri.parse("http://cbse.nic.in/ecbse/welcome.html");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, website);
                startActivity(webIntent);
            }
        });http://cbse.nic.in/ecbse/welcome.htmlhttp://ncert.nic.in/ebooks.html
        return view;
    }
}