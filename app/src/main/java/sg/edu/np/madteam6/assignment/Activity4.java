package sg.edu.np.madteam6.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Activity4 extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[], s3[];
    int images[]={R.raw.milktealiho, R.raw.caramelliho, R.raw.earlgreyliho, R.raw.brownsugarliho, R.raw.taromilktealiho, R.raw.avacadoliho};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.liho);
        s2 = getResources().getStringArray(R.array.lihocalories);
        s3 = getResources().getStringArray(R.array.topping_cal);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}