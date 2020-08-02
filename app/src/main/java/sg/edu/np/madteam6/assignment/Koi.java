package sg.edu.np.madteam6.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Koi extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> addressList = new ArrayList<>();
    KoiAdapter adapter;
    final String TAG = "bbt app";
TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koi);
        nameList = new ArrayList<>();
        addressList = new ArrayList<>();
        getWebsite();


        //creating bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //setting page as home
        bottomNavigationView.setSelectedItemId(R.id.location);
        //setting up listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //creating intents to go to another page
                switch(menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainMenu.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to home");
                        return true;

                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext()
                                , Menu.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to Menu");
                        return true;

                    case R.id.location:

                        return true;


                    case R.id.tracker:
                        startActivity(new Intent(getApplicationContext()
                                , Tracker.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to tracker");
                        return true;
                }

                return false;
            }
        });


    }

    void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //builder that adds location string to array list
                final StringBuilder builder = new StringBuilder();
                try {
                    //connect to website
                    Document doc = (Document) Jsoup.connect("https://shopsinsg.com/koi-cafes-in-singapore.html").get();
                    //looks for html tag
                    Elements links = doc.select("p>strong");
                    //looks for html tag
                    Elements tests = doc.select("h2~*");
                    //loop  to add store address to array
                    for(Element link: links){
                        //add to array list
                            nameList.add(builder.append("\n").append(link.text()).toString());
                        //clear location from builder
                            builder.delete(0, builder.length());
                    }
                    //loop  to add store Name to array
                    for(Element link: tests){
                        {
                            //add to array list
                            addressList.add(builder.append("\n").append(link.text()).toString());
                            //clear location from builder
                            builder.delete(0, builder.length());
                        }
                    }

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView = findViewById(R.id.recyclerView);

                        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Koi.this));

                        adapter = new KoiAdapter(Koi.this,nameList,addressList);

                        recyclerView.setAdapter(adapter);

                    }
                });
            }
        }).start();

    }




    public void sendMessageliho(View view) {
        Intent intent = new Intent(Koi.this, Liho.class);
        startActivity(intent);
    }

    public void sendMessagegc(View view) {
        Intent intent = new Intent(Koi.this, GongCha.class);
        startActivity(intent);
    }


}