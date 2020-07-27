package sg.edu.np.madteam6.assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SetLimitActivity extends AppCompatActivity {

    Button cfmButton;
    Button cclButton;
    EditText spendLimit;
    SharedPreferences prefs;
    int min = 20;
    int max = 50;
    final String TAG = "bbt app";
    final String DATABASE_KEY = "MY_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);

        prefs = getSharedPreferences(DATABASE_KEY, MODE_PRIVATE);

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
                String validation = spendLimit.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(SetLimitActivity.this);

                if(TextUtils.isEmpty(spendLimit.getText().toString()))
                {
                    //Validation to reject any empty inputs
                    spendLimit.setError("No empty fields allowed.");
                }
                else
                {
                    //Converts input to integer used for checking the number itself
                    final int inputToInt = Integer.parseInt(validation);

                    builder.setMessage("Are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (inputToInt >= min && inputToInt <= max)
                                    {
                                        //Get input text
                                        float limit = Float.parseFloat(spendLimit.getText().toString());

                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putFloat("MY_LIMIT", limit); //Key for "limit" figure
                                        editor.commit();

                                        //Message to inform user that limit is set successfully
                                        Toast.makeText(SetLimitActivity.this,
                                                "Spending limit set successfully.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), Tracker.class));
                                    }

                                    else
                                    {
                                        //Validation to reject any ridiculous numbers user tries to set
                                        spendLimit.setText("");
                                        spendLimit.setError("Set between $20 to $50 for the spending limit.");
                                    }
                                }
                            })
                            .setNegativeButton("No", null);

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }

    public void cancelSetLimit(){
        cclButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SetLimitActivity.this);

                builder.setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Message to inform user that he/she chose not to set a spending limit
                                Toast.makeText(SetLimitActivity.this,
                                        "Setting of spending limit cancelled.", Toast.LENGTH_SHORT).show();
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
