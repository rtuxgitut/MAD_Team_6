package sg.edu.np.madteam6.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PurchaseActivity extends AppCompatActivity {

    private Button addPurchase;
    private Button goBack;
    private EditText bbtInput;
    private EditText priceInput;
    private RecyclerView recyclerView;
    private ViewPurchaseAdapter adapter;

    private List<ViewPurchaseModel> taskList;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase);

        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);

        addPurchase = findViewById(R.id.addPurchaseButton);
        goBack = findViewById(R.id.backButton);
        recyclerView = findViewById(R.id.purchaseRecyclerView);
        bbtInput = findViewById(R.id.bbtInput);
        priceInput = findViewById(R.id.priceInput);

        taskList = PreConfig.readListFromPref(this);

        if (taskList == null)
            taskList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ViewPurchaseAdapter(getApplicationContext(), taskList);
        recyclerView.setAdapter(adapter);

        confirmPurchaseDetails();
        cancelPurchaseDetails();
    }

    public void confirmPurchaseDetails(){
        addPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get input text
                float previous_data =  prefs.getFloat("MY_CURRENT", 0);
                float current = Float.parseFloat(priceInput.getText().toString());
                NumberFormat format = NumberFormat.getCurrencyInstance();
                String print_current = format.format(current);


                current += previous_data;

                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat("MY_CURRENT", current);
                editor.commit();

                ViewPurchaseModel purchaseModel = new ViewPurchaseModel(bbtInput.getText().toString(), priceInput.getText().toString(), getDate());
                taskList.add(purchaseModel);
                PreConfig.writeListInPref(getApplicationContext(), taskList);
                Collections.reverse(taskList);
                adapter.setTaskModelList(taskList);

                if (!bbtInput.getText().equals(""))
                {
                    bbtInput.setText("");
                }

                if (!priceInput.getText().equals(""))
                {
                    priceInput.setText("");
                }
            }
        });
    }

    public void cancelPurchaseDetails(){
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Tracker.class));
            }
        });
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(dateFormat.format(date));
    }
}