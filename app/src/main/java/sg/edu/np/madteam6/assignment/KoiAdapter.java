package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KoiAdapter extends RecyclerView.Adapter<KoiAdapter.MyViewholder>{
    Context context;
    String store[];
    String address[];

    public KoiAdapter(Context context, String store[],String address[]){
        this.store = store;
        this.context = context;
        this.address = address;
    }
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.koi_row,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.Store.setText(store[position]);
        holder.Address.setText(address[position]);
    }

    @Override
    public int getItemCount() {
        return store.length;
    }

    class MyViewholder extends  RecyclerView.ViewHolder{
        TextView Store;
        TextView Address;
        public MyViewholder(@NonNull View itemView) {

            super(itemView);
            Store = (TextView) itemView.findViewById(R.id.koiStore);
            Address = (TextView) itemView.findViewById(R.id.koiAddress);
        }
    }
}
