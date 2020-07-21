package sg.edu.np.madteam6.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Tracker extends AppCompatActivity {
    //Defining variables
    final String TAG = "bbt app";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        //creating bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //setting page as tracker
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
                        Log.v(TAG,"going to main menu");
                        return true;

                    case R.id.location:

                        startActivity(new Intent(getApplicationContext()
                                , Liho.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to Location");
                        return true;


                    case R.id.tracker:

                        return true;
                }

                return false;
            }
        });

        textView = findViewById(R.id.displayLimit);

        Intent intent = getIntent();
        //Store value received from input in SetLimitActivity
        double number = intent.getDoubleExtra(SetLimitActivity.EXTRA_NUMBER, 0.00);

        //Default text to display money limit
        TextView limitView = (TextView) findViewById(R.id.displayLimit);
        limitView.setText("$0 out of $" + number);
        configureSetLimitButton();
    }

    private void configureSetLimitButton(){
        Button nextButton = (Button) findViewById(R.id.limitButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Allows user to go to the Set Limits page
                startActivity(new Intent(Tracker.this, SetLimitActivity.class));
            }
        });
    }

}
