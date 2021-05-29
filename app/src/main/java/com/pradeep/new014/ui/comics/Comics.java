package com.pradeep.new014.ui.comics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pradeep.new014.R;
import com.pradeep.new014.ui.contener.Book;
import com.pradeep.new014.ui.contener.RecyclerViewAdapter;

import java.util.ArrayList;

public class Comics extends Fragment {
    private RecyclerView myRecyclerView;
    private RecyclerViewAdapter myAdapter;
    private ArrayList<Book> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comics, container, false);
        myRecyclerView=(RecyclerView)view.findViewById(R.id.recycleviewComics);
        LinearLayoutManager linearLayoutManager=new GridLayoutManager(getContext(), 3);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<Book>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Book");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Book book=dataSnapshot1.getValue(Book.class);
                    list.add(book);
                }
                myAdapter = new RecyclerViewAdapter(getContext(),list);
                myRecyclerView.setAdapter(myAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Opsss..... Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
