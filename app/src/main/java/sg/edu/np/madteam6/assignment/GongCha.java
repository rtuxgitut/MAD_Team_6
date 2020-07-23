package sg.edu.np.madteam6.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class GongCha extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> addressList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    GongChaAdapter adapter;
    final String TAG = "bbt app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gong_cha);
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
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = (Document) Jsoup.connect("https://shopsinsg.com/gong-cha-bubble-tea-shops-in-singapore.html").get();
                    Elements links = doc.select("p>strong");
                    Elements tests = doc.select("h2~*");
                    for(Element link: links){
                        nameList.add(builder.append("\n").append(link.text()).toString());
                        builder.delete(0, builder.length());
                    }

                    for(Element link: tests){
                        {
                            addressList.add(builder.append("\n").append(link.text()).toString());
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
                        recyclerView.setLayoutManager(new LinearLayoutManager(GongCha.this));

                        adapter = new GongChaAdapter(GongCha.this,nameList,addressList);

                        recyclerView.setAdapter(adapter);

                    }
                });
            }
        }).start();

    }



    public void sendMessageliho(View view) {
        Intent intent = new Intent(GongCha.this, Liho.class);
        startActivity(intent);
    }


    public void sendMessagekoi(View view) {
        Intent intent = new Intent(GongCha.this, Koi.class);
        startActivity(intent);
    }
}