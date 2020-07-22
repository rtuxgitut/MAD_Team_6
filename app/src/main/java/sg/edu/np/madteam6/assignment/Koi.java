package sg.edu.np.madteam6.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
import java.util.Iterator;
import java.util.concurrent.TimeoutException;

public class Koi extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> nameList;
    KoiAdapter adapter;
    final String TAG = "bbt app";
TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koi);
        nameList = new ArrayList<>();

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
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = (Document) Jsoup.connect("https://www.koithe.com/en/global/koi-singapore").get();
                    Elements links = doc.select("div.titlebox");

                    for(Element link: links){
                        nameList.add(builder.append("\n").append(link.text()).toString());
                    }
                    /**for(int i = 1;i < links.size(); i++){
                        nameList.add(builder.append("\n").append(links.get(i).text()).toString());

                    }**/



                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView = findViewById(R.id.recyclerView);

                        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Koi.this));

                        adapter = new KoiAdapter(Koi.this,nameList);

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