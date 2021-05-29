package com.pradeep.new014.ui.contener;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pradeep.new014.R;

import java.util.ArrayList;

public class LayoutContainer extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private RecyclerViewAdapter myAdapter;
    private ArrayList<Book> list;
    private static String send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_container);
        myRecyclerView=(RecyclerView)findViewById(R.id.recycleview_id);
        myRecyclerView.setLayoutManager(new GridLayoutManager(LayoutContainer.this,3));
        list = new ArrayList<Book>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(send);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Book book=dataSnapshot1.getValue(Book.class);
                    list.add(book);
                }
                myAdapter = new RecyclerViewAdapter(LayoutContainer.this,list);
                myRecyclerView.setAdapter(myAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LayoutContainer.this,"Opsss..... Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void startOnActivityLayoutContainer(String collect){
        send=collect;
    }
}
