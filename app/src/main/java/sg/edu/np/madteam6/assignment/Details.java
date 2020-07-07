package sg.edu.np.madteam6.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Details extends AppCompatActivity {

    TextView title, cal, topping;
    ImageView mainImageview;

    String data1, data2, data3;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mainImageview = findViewById(R.id.mainImageView);
        title = findViewById(R.id.titleView);
        cal = findViewById(R.id.calView);
        topping = findViewById(R.id.toppingView);

        getData();
        setData();
    }

    private void getData(){
        if (getIntent().hasExtra("myImageview") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2") && getIntent().hasExtra("data3")) {
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            myImage = getIntent().getIntExtra("myImageview", 1);
        }else{
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        title.setText(data1);
        cal.setText(data2);
        topping.setText(data3);
        mainImageview.setImageResource(myImage);
    }
}