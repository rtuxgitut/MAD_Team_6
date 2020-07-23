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
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> addressList = new ArrayList<>();

    public GongChaAdapter(Context context,ArrayList<String> nameList,ArrayList<String> addressList){
        this.nameList = nameList;
        this.context = context;
        this.addressList = addressList;

    }
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gong_cha_row,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.Store.setText(nameList.get(position));
        holder.Address.setText(addressList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();

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
