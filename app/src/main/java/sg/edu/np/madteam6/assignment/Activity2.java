package sg.edu.np.madteam6.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Activity2 extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[], s3[];
    int images[]={R.raw.milkteakoi, R.raw.caramelmilkteakoi, R.raw.goldenbubblekoi, R.raw.honeymilkteakoi, R.raw.ovaltinekoi};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.koi);
        s2 = getResources().getStringArray(R.array.koicalories);
        s3 = getResources().getStringArray(R.array.topping_cal);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}