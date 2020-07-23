package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LihoAdapter extends RecyclerView.Adapter<LihoAdapter.MyViewHolder>{
    Context context;
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> addressList = new ArrayList<>();

    public LihoAdapter(Context context, ArrayList<String> nameList,ArrayList<String> addressList){
       this.nameList = nameList;
       this.context = context;
       this.addressList = addressList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liho_row,parent,false);
      return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Store.setText(nameList.get(position));
        holder.Address.setText(addressList.get(position));
    }

    @Override
    public int getItemCount() {
       return nameList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Store;
        TextView Address;
        public MyViewHolder (View itemView) {
            super(itemView);
            Store = (TextView) itemView.findViewById(R.id.lihoStore);
            Address = (TextView) itemView.findViewById(R.id.lihoAddress);
        }
    }
}
