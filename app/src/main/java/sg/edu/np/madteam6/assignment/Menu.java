package sg.edu.np.madteam6.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {
    RecyclerView recyclerView;
    final String TAG = "bbt app";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //creating bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //setting page as menu
        bottomNavigationView.setSelectedItemId(R.id.menu);
        //setting up listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                /* creating intents to go to another page */
                switch(menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainMenu.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to home");
                        return true;

                    case R.id.menu:
                        return true;

                    case R.id.location:
                        startActivity(new Intent(getApplicationContext()
                                , Direction.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to Location");
                        return true;


                    case R.id.tracker:
                        startActivity(new Intent(getApplicationContext()
                                , Tracker.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to Tracker");
                        return true;
                }

                return false;
            }
        });


        String s1[], s2[], s3[];
        int images[]={R.raw.almondmilktea,R.raw.caramelmilktea,R.raw.coffeemilktea,R.raw.earlgreymilktea,R.raw.matchamilktea,R.raw.milktea,R.raw.taromilktea,R.raw.thaimilktea};


            recyclerView = findViewById(R.id.recyclerView);

            s1 = getResources().getStringArray(R.array.bubbletea);
            s2 = getResources().getStringArray(R.array.price);
            s3 = getResources().getStringArray(R.array.calories);

            MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, images);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }

