package com.pradeep.new014.ui.home;

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
import com.pradeep.new014.ui.contener1.RecyclerAdapterView;
import com.pradeep.new014.ui.contener1.Title;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private RecyclerView myRecyclerView;
    private RecyclerViewAdapter myAdapter;
    private ArrayList<Book> list;

    //extra
    private RecyclerView recyclerViewone, recyclerViewtwo, recyclerViewthree, recyclerViewfour;
    private ArrayList<Title> listone, listtwo, listthree, listfour;
    private RecyclerAdapterView adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        myRecyclerView=(RecyclerView)root.findViewById(R.id.recycleview_id1);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
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
//one
        recyclerViewone=(RecyclerView)root.findViewById(R.id.recycleviewone);
        recyclerViewone.setLayoutManager(new GridLayoutManager(getContext(),3));
        listone = new ArrayList<Title>();
        DatabaseReference refOne = FirebaseDatabase.getInstance().getReference().child("BooksName");
        refOne.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Title title=dataSnapshot1.getValue(Title.class);
                    listone.add(title);
                }
                adapter = new RecyclerAdapterView(getContext(),listone);
                recyclerViewone.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Opsss..... Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
        //two
        recyclerViewtwo=(RecyclerView)root.findViewById(R.id.recycleviewtwo);
        recyclerViewtwo.setLayoutManager(new GridLayoutManager(getContext(),3));
        listtwo = new ArrayList<Title>();
        DatabaseReference refTwo = FirebaseDatabase.getInstance().getReference().child("BookName1");
        refTwo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Title title=dataSnapshot1.getValue(Title.class);
                    listtwo.add(title);
                }
                adapter = new RecyclerAdapterView(getContext(),listtwo);
                recyclerViewtwo.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Opsss..... Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
        //three
        recyclerViewthree=(RecyclerView)root.findViewById(R.id.recycleviewthree);
        recyclerViewthree.setLayoutManager(new GridLayoutManager(getContext(),3));
        listthree = new ArrayList<Title>();
        DatabaseReference refThree = FirebaseDatabase.getInstance().getReference().child("BookName2");
        refThree.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Title title=dataSnapshot1.getValue(Title.class);
                    listthree.add(title);
                }
                adapter = new RecyclerAdapterView(getContext(),listthree);
                recyclerViewthree.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Opsss..... Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
        //four
        recyclerViewfour=(RecyclerView)root.findViewById(R.id.recycleviewfour);
        recyclerViewfour.setLayoutManager(new GridLayoutManager(getContext(),3));
        listfour = new ArrayList<Title>();
        DatabaseReference refFour = FirebaseDatabase.getInstance().getReference().child("BookName3");
        refFour.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Title title=dataSnapshot1.getValue(Title.class);
                    listone.add(title);
                }
                adapter = new RecyclerAdapterView(getContext(),listfour);
                recyclerViewfour.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Opsss..... Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
