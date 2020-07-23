package sg.edu.np.madteam6.assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SetLimitActivity extends AppCompatActivity {

    Button cfmButton;
    Button cclButton;
    EditText spendLimit;
    SharedPreferences prefs;

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
