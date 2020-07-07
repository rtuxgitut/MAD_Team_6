package sg.edu.np.madteam6.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String brand[];
    int brandlogo[] = {R.raw.koilogo, R.raw.gongchalogo, R.raw.lihologo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        brand = getResources().getStringArray(R.array.brands);

        MenuAdapter menuAdapter = new MenuAdapter(this, brand, brandlogo);
        recyclerView.setAdapter(menuAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
