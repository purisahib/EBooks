package com.pradeep.new014;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.pradeep.new014.ui.extra.Help;
import com.pradeep.new014.ui.extra.download.Download;
import com.pradeep.new014.ui.extra.About;
import com.pradeep.new014.ui.searchbox.SearchingActiviy;

public class HomeActivity extends AppCompatActivity {
    private TextView name, email;
    //private Button btn_logout;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
// self
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Searching...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //sessionManager.logout();
                //FirebaseAuth.getInstance().signOut();
                Intent intentSearch= new Intent(HomeActivity.this, SearchingActiviy.class);
                startActivity(intentSearch);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.accountingBusiness, R.id.artsOther, R.id.comics, R.id.searching, R.id.admin)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.game_menu, menu);
        //return true;

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_about:
                about();
                return true;
            case R.id.action_download:
                download();
                return true;
            //case R.id.action_help:
              //  help();
                //return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void help() {
        Intent intenthelp= new Intent(HomeActivity.this, Help.class);
        startActivity(intenthelp);
    }

    private void about() {
        Intent intentAbout= new Intent(HomeActivity.this, About.class);
        startActivity(intentAbout);
    }
    private void download() {
        Intent intentDownload= new Intent(HomeActivity.this, Download.class);
        startActivity(intentDownload);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
