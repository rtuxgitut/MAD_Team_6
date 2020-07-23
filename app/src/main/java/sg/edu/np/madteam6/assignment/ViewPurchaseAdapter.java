package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class ViewPurchaseAdapter extends RecyclerView.Adapter<ViewPurchaseAdapter.Holder> {
    private Context context;
    private List<ViewPurchaseModel> taskModelList;

    public ViewPurchaseAdapter(Context context, List<ViewPurchaseModel> taskModelList) {
        this.context = context;
        this.taskModelList = taskModelList;
        Collections.reverse(taskModelList);
    }

    public void setTaskModelList(List<ViewPurchaseModel> taskModelList) {
        this.taskModelList = taskModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context)
                .inflate(R.layout.list_task_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Log.d("Daily", "taskModelList: " + taskModelList);
        holder.bbtName.setText("Order: " + taskModelList.get(position).getBbt_name());
        holder.bbtPrice.setText("Price: $" + String.valueOf(taskModelList.get(position).getBbt_price()));
        holder.bbtDate.setText("Date: " + String.valueOf(taskModelList.get(position).getBbt_date()));
    }

    @Override
    public int getItemCount() {
        return taskModelList != null ? taskModelList.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView bbtName, bbtPrice, bbtDate;
        public Holder(@NonNull View itemView) {
            super(itemView);
            bbtName = itemView.findViewById(R.id.bbt_name);
            bbtPrice = itemView.findViewById(R.id.bbt_price);
            bbtDate = itemView.findViewById(R.id.bbt_date);
        }
    }
}
