package sg.edu.np.madteam6.assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SetLimitActivity extends AppCompatActivity {

    Button cfmButton;
    Button cclButton;
    EditText spendLimit;
    SharedPreferences prefs;
    final String TAG = "bbt app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);

        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);

        cfmButton = (Button) findViewById(R.id.confirmButton);
        cclButton = (Button) findViewById(R.id.cancelButton);
        spendLimit = (EditText) findViewById(R.id.spentLimitInput);

        confirmSetLimit();
        cancelSetLimit();

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
                        Log.v(TAG,"going to Liho");
                        return true;


                    case R.id.tracker:
                        return true;
                }

                return false;
            }
        });

    }

    public void confirmSetLimit(){
        cfmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Get input text
                float limit = Float.parseFloat(spendLimit.getText().toString());

                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat("MY_LIMIT", limit);
                editor.commit();

                startActivity(new Intent(getApplicationContext(), Tracker.class));
            }
        });
    }

    public void cancelSetLimit(){
        cclButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Tracker.class));
            }
        });
    }
}
