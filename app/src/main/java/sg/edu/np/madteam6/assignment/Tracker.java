package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class Tracker extends AppCompatActivity {

    Button setLimitButton;
    Button addPurchase;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        prefs = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);

        float c = prefs.getFloat("MY_CURRENT", 0);
        NumberFormat format_c = NumberFormat.getCurrencyInstance();
        String current = format_c.format(c);

        float l = prefs.getFloat("MY_LIMIT", 0);
        NumberFormat format_l = NumberFormat.getCurrencyInstance();
        String limit = format_l.format(l);

        ((TextView) findViewById(R.id.displayCurrentSpent)).setText(current);
        ((TextView) findViewById(R.id.displayLimitSet)).setText(limit);

        configureSetLimitButton();
        configureAddPurchaseButton();
    }

    private void configureSetLimitButton(){
        setLimitButton = (Button) findViewById(R.id.limitButton);
        setLimitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SetLimitActivity.class));
            }
        });
    }

    private void configureAddPurchaseButton(){
        addPurchase = (Button) findViewById(R.id.addPurchaseButton);
        addPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PurchaseActivity.class));
            }
        });
    }
}