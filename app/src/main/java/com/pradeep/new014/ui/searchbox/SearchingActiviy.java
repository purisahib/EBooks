package com.pradeep.new014.ui.searchbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pradeep.new014.R;
import com.pradeep.new014.ui.contener.Book;
import com.pradeep.new014.ui.contener.PdfReaderActivity;

// puri sahib
public class SearchingActiviy extends AppCompatActivity implements SingleChoiceDialogFragment.SingleChoiceListener {
    private EditText mSearchField;
    private Button categoryButtonSearch;
    static String stringCategery="Title";
    private RecyclerView mResultList;
    private DatabaseReference mUserDarabase;
    private ImageButton mSearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        mUserDarabase= FirebaseDatabase.getInstance().getReference("Book");
        mUserDarabase= FirebaseDatabase.getInstance().getReference("Book1");
        mUserDarabase= FirebaseDatabase.getInstance().getReference("Java");
        mUserDarabase= FirebaseDatabase.getInstance().getReference("Php");

        mSearchField=(EditText)findViewById(R.id.search_field);
        mSearchBtn=(ImageButton)findViewById(R.id.buttonSearch);
        categoryButtonSearch=(Button)findViewById(R.id.categoryButtonSearch);
        mResultList=(RecyclerView)findViewById(R.id.result_list);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mResultList.setHasFixedSize(true);
        categoryButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment singleChoiceDilog = new SingleChoiceDialogFragment();
                singleChoiceDilog.setCancelable(false);
                singleChoiceDilog.show(getSupportFragmentManager(),"Single Choice Dialog");
            }
        });
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText=mSearchField.getText().toString().trim();
                firebaseUserSearch(searchText);
            }
        });
    }

    private void firebaseUserSearch(String searchText) {
        Toast.makeText(SearchingActiviy.this, "Started Search", Toast.LENGTH_SHORT).show();
        Query firebaseSearchQuery= mUserDarabase.orderByChild(stringCategery).startAt(searchText).endAt(searchText+"\uf8ff");
        FirebaseRecyclerAdapter<Book,UserViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Book, UserViewHolder>(
                Book.class,
                R.layout.list_layout,
                UserViewHolder.class,
                firebaseSearchQuery
        ){
            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, Book model, int position){
                viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getAuthor(),model.getCategory(), model.getDescription(), model.getBookl(), model.getThumbnail());
            }
        };
    }

    @Override
    public void onPositiveButtonClick(String[] list, int position) {
        stringCategery=list[position];
    }
    @Override
    public void onNegativeButtonClicked() {
        Toast.makeText(this, "No One Select", Toast.LENGTH_SHORT).show();
    }

    //View holder class
    public class UserViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public UserViewHolder(View itemView){
            super(itemView);
            mView=itemView;
        }
        public void setDetails(Context ctx, String userTitle, String userAuthor, String category, String description,String link, String userImage) {
            TextView user_title = (TextView) mView.findViewById(R.id.name_text);
            TextView user_author = (TextView) mView.findViewById(R.id.author_text);
            TextView user_category = (TextView) mView.findViewById(R.id.category_text);
            TextView user_description = (TextView) mView.findViewById(R.id.description_text);
            ImageButton user_image = (ImageButton) mView.findViewById(R.id.profile_image);
            user_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentStartActivity=new Intent(ctx, PdfReaderActivity.class );
                    PdfReaderActivity pdfReaderActivity=new PdfReaderActivity();
                    pdfReaderActivity.getUrlvalue(link);
                    startActivity(intentStartActivity);
                }
            });

            user_title.setText(userTitle);
            user_author.setText(userAuthor);
            user_category.setText(category);
            user_description.setText(description);

            Glide.with(ctx).load(userImage).into(user_image);
        }
    }

}
