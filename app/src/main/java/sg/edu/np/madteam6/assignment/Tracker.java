package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;

public class Tracker extends AppCompatActivity {

    Button setLimitButton;
    Button addPurchase;
    Button resetButton;
    SharedPreferences prefs;
    final String TAG = "bbt app";
    final String DATABASE_KEY = "MY_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        prefs = getSharedPreferences(DATABASE_KEY, Context.MODE_PRIVATE);

        float c = prefs.getFloat("MY_CURRENT", 0); //Key for "current" figure
        NumberFormat format_c = NumberFormat.getCurrencyInstance(); //Format input to money figure
        String current = format_c.format(c);

        float l = prefs.getFloat("MY_LIMIT", 0); //Key for "limit" figure
        NumberFormat format_l = NumberFormat.getCurrencyInstance(); //Format input to money figure
        String limit = format_l.format(l);

        ((TextView) findViewById(R.id.displayCurrentSpent)).setText(current);
        ((TextView) findViewById(R.id.displayLimitSet)).setText(limit);

        configureSetLimitButton(); //Redirects user to Set Limit Activity
        configureAddPurchaseButton(); //Redirects user to Purchase Activity
        configureResetButton(); //Helps user to clear both current spending and maximum limit

        //creating bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //setting page as home
        bottomNavigationView.setSelectedItemId(R.id.tracker);
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
                        startActivity(new Intent(getApplicationContext()
                                , Liho.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to tracker");
                        return true;


                    case R.id.tracker:
                        return true;
                }

                return false;
            }
        });

    }

    private void configureSetLimitButton(){
        setLimitButton = findViewById(R.id.limitButton);
        setLimitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Brings user to set limit page
                startActivity(new Intent(getApplicationContext(), SetLimitActivity.class));
            }
        });
    }

    private void configureAddPurchaseButton(){
        addPurchase = findViewById(R.id.addPurchaseButton);
        addPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Brings user to view purchase page
                startActivity(new Intent(getApplicationContext(), PurchaseActivity.class));
            }
        });
    }

    private void configureResetButton() {
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Tracker.this);

                builder.setMessage("Are you sure you want to clear your tracking data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                prefs = getSharedPreferences(DATABASE_KEY, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                //Clears and remove keys used to store current spending and maximum limit
                                editor.remove("MY_CURRENT");
                                editor.remove("MY_LIMIT");
                                editor.apply();

                                //Informs user that tracking data is cleared
                                Toast.makeText(Tracker.this,
                                        "Tracking data has been cleared.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Tracker.class));
                            }
                        })
                        .setNegativeButton("No", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}