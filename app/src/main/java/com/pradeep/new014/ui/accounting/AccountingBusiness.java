package com.pradeep.new014.ui.accounting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pradeep.new014.R;
import com.pradeep.new014.ui.contener1.RecyclerAdapterView;
import com.pradeep.new014.ui.contener1.Title;

import java.util.ArrayList;

public class AccountingBusiness extends Fragment {
    private RecyclerView myRecyclerView;
    private RecyclerAdapterView myAdapter;
    private ArrayList<Title> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_accounting_business, container, false);
        myRecyclerView=(RecyclerView)root.findViewById(R.id.recycleviewAccounting);
        myRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        list = new ArrayList<Title>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("BookName2");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Title title=dataSnapshot1.getValue(Title.class);
                    list.add(title);
                }
                myAdapter = new RecyclerAdapterView(getContext(),list);
                myRecyclerView.setAdapter(myAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Opsss..... Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });



        return root;
    }
}
