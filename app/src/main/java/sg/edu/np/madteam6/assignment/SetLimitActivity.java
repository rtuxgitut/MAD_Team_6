package sg.edu.np.madteam6.assignment;

import android.content.Intent;
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
    //Defining variable
    final String TAG = "bbt app";
    public static final String EXTRA_NUMBER = "sg.edu.np.madteam6.EXTRA_NUMBER";
    Button cfmButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);
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
                                , Direction.class));
                        overridePendingTransition(0,0);
                        Log.v(TAG,"going to Location");
                        return true;


                    case R.id.tracker:

                        return true;
                }

                return false;
            }
        });

        cfmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        //Stores input collected if clicked
        cfmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                confirmLimit();
            }
        });

        //Abandons input if clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSetLimit();
            }
        });

    }

    public void confirmLimit(){
        EditText editText = (EditText) findViewById(R.id.spentLimitInput);
        double number = Integer.parseInt(editText.getText().toString()); //Parses the data to int format
        Button cfmButton = (Button) findViewById(R.id.confirmButton);
        cfmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //Finishes activity
            }
        });

        Intent intent = new Intent(this, Tracker.class);
        intent.putExtra(EXTRA_NUMBER, number); //Transfers int data over to Tracker activity
        startActivity(intent);
    }

    public void cancelSetLimit(){
        Button cclButton = (Button) findViewById(R.id.cancelButton);
        cclButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //Finishes activity
            }
        });
    }

}
