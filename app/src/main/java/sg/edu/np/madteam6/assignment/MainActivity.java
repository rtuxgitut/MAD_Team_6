package sg.edu.np.madteam6.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[], s3[];
    int images[]={R.raw.almondmilktea,R.raw.caramelmilktea,R.raw.coffeemilktea,R.raw.earlgreymilktea,R.raw.matchamilktea,R.raw.milktea,R.raw.taromilktea,R.raw.thaimilktea};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.bubbletea);
        s2 = getResources().getStringArray(R.array.price);
        s3 = getResources().getStringArray(R.array.calories);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
