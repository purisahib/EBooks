package com.pradeep.new014.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pradeep.new014.R;

import java.util.ArrayList;


public class Admin extends Fragment {
    EditText questionText;
    Button questionSend;
    TextView questionRecive;
    // recycler
    private RecyclerView myRecyclerVideoView;
    private RecyclerAdapterVideoView myAdapter;
    private ArrayList<Video> list;

    //private Intent intentLogin;
    private ProgressBar progressBarAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin, container, false);

        questionText =(EditText)root.findViewById(R.id.questionText);
        questionRecive=(TextView)root.findViewById(R.id.questionRecive);
        progressBarAdmin = (ProgressBar) root.findViewById(R.id.progressBarAdmin);
        progressBarAdmin.setVisibility(View.GONE);
        questionSend = (Button) root.findViewById(R.id.questionSend);
        questionSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        myRecyclerVideoView=(RecyclerView)root.findViewById(R.id.recyclerViewVideo);
        myRecyclerVideoView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<Video>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Video");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Video video=dataSnapshot1.getValue(Video.class);
                    list.add(video);
                }
                myAdapter = new RecyclerAdapterVideoView(getContext(),list);
                myRecyclerVideoView.setAdapter(myAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Opsss..... Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
    private void registerUser() {
        String aa= questionText.getText().toString().trim();
        progressBarAdmin.setVisibility(View.VISIBLE);
        // we will store the additional field in fire base data base
        //Data data = new Data(aa);
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("/QuestionAndAnswer/");
        reference.push().setValue(aa).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBarAdmin.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Data Inseration success", Toast.LENGTH_LONG).show();
                    questionRecive.setText(aa);
                } else {
                    //display a failer message
                    Toast.makeText(getContext(),"Data Inseration not success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
