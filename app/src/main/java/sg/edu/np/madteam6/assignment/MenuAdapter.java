package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    String data1[];
    int brandLogo[];
    Context context;

    public MenuAdapter(Context ct, String brand[], int brandlogo[]){
        context = ct;
        data1 = brand;
        brandLogo = brandlogo;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.brandsdisplay, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, final int position) {
        holder.mytext1.setText(data1[position]);
        holder.myImage.setImageResource(brandLogo[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    Intent intent = new Intent(context, Activity2.class);
                    context.startActivity(intent);
                }
                if (position == 1){
                    Intent intent = new Intent(context, Activity3.class);
                    context.startActivity(intent);
                }
                if (position == 2){
                    Intent intent = new Intent(context, Activity4.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        TextView mytext1;
        ImageView myImage;
        ConstraintLayout mainLayout;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            mytext1 = itemView.findViewById(R.id.brandtitle);
            myImage = itemView.findViewById(R.id.brandimage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
    public interface OnMenuListener{}
}
