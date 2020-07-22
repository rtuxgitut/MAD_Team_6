package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GongChaAdapter extends RecyclerView.Adapter<GongChaAdapter.MyViewholder> {
    Context context;
    String store[];
    String address[];

    public GongChaAdapter(Context context, String store[],String address[]){
        this.store = store;
        this.address = address;
        this.context = context;

    }
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gong_cha_row,parent,false);
        return new GongChaAdapter.MyViewholder(view);
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
            Store = (TextView) itemView.findViewById(R.id.gongChaStore);
            Address = (TextView) itemView.findViewById(R.id.gongChaAddress);

        }
    }
}
