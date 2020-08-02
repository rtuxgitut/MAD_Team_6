package sg.edu.np.madteam6.assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PurchaseActivity extends AppCompatActivity {

    private Button goBack;
    private Button addPurchase;
    private EditText bbtInput;
    private EditText priceInput;
    private RecyclerView recyclerView;
    private ViewPurchaseAdapter adapter;
    float min = (float) 2.7;
    float max = 9;

    private List<ViewPurchaseModel> taskList;
    private SharedPreferences prefs;
    private static final String DATABASE_KEY = "MY_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase);

        //Defining variables
        prefs = getSharedPreferences(DATABASE_KEY, MODE_PRIVATE);

        goBack = findViewById(R.id.backButton);
        addPurchase = findViewById(R.id.addPurchaseButton);
        recyclerView = findViewById(R.id.purchaseRecyclerView);
        bbtInput = findViewById(R.id.bbtInput);
        priceInput = findViewById(R.id.priceInput);

        taskList = PreConfig.readListFromPref(this);

        //taskList == recyclerView
        if (taskList == null)
            taskList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ViewPurchaseAdapter(getApplicationContext(), taskList);
        recyclerView.setAdapter(adapter);

        cancelPurchaseDetails();
        confirmPurchaseDetails();
    }

    public void cancelPurchaseDetails(){
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PurchaseActivity.this);

                builder.setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(PurchaseActivity.this,
                                        "Going back to Tracker page.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Tracker.class));
                            }
                        })
                        .setNegativeButton("No", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void confirmPurchaseDetails(){
        addPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String float_input = priceInput.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(PurchaseActivity.this);

                if (TextUtils.isEmpty(bbtInput.getText().toString()) || TextUtils.isEmpty(priceInput.getText().toString()))
                {
                    Toast.makeText(PurchaseActivity.this,
                            "No empty fields allowed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final float inputToFloat = Float.parseFloat(float_input);

                    builder.setMessage("Are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (inputToFloat >= min && inputToFloat <= max)
                                    {
                                        //Get input text
                                        float previous_data =  prefs.getFloat("MY_CURRENT", 0);
                                        float current = Float.parseFloat(priceInput.getText().toString());

                                        //Increments input made by user along with inputs stored in shared preference
                                        current += previous_data;

                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putFloat("MY_CURRENT", current); //Key for "current" figure
                                        editor.commit();

                                        Toast.makeText(PurchaseActivity.this,
                                                "Purchase added successfully.", Toast.LENGTH_SHORT).show();

                                        ViewPurchaseModel purchaseModel = new ViewPurchaseModel(bbtInput.getText().toString(), priceInput.getText().toString(), getDate());
                                        taskList.add(purchaseModel);
                                        PreConfig.writeListInPref(getApplicationContext(), taskList);
                                        Collections.reverse(taskList);
                                        adapter.setTaskModelList(taskList);

                                        //Ensures that input fields are cleared after adding purchase
                                        priceInput.setText("");
                                        bbtInput.setText("");
                                    }

                                    else
                                    {
                                        //No bubble tea drinks cost more than SGD $7
                                        Toast.makeText(PurchaseActivity.this,
                                                "Price of bubble tea is unrealistic. Try again!", Toast.LENGTH_SHORT).show();
                                        priceInput.setText("");
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

    private String getDate() {
        //Get date where user made a bubble tea purchase.
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(dateFormat.format(date));
    }
}