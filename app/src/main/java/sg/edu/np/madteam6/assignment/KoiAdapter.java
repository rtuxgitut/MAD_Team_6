package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KoiAdapter extends RecyclerView.Adapter<KoiAdapter.MyViewholder>{
    Context context;
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> addressList = new ArrayList<>();

    public KoiAdapter(Context context,  ArrayList<String> nameList,  ArrayList<String> adressList){
        this.nameList = nameList;
        this.context = context;
        this.addressList = adressList;
    }
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.koi_row,parent,false);
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
            Store = (TextView) itemView.findViewById(R.id.koiStore);
            Address = (TextView) itemView.findViewById(R.id.koiAddress);

        }
    }
}
