//package com.example.git_set_code;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.git_set_code.database.SavedData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SavedDataAdapter extends RecyclerView.Adapter<SavedDataAdapter.SavedDataHolder> {
//    private List<SavedData> savedDatas = new ArrayList<>();
//    @NonNull
//    @Override
//    public SavedDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_data, parent,false);
//        return new SavedDataHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SavedDataHolder holder, int position) {
//        SavedData currsavedData = savedDatas.get(position);
//        holder.latitude_textview.setText(String.valueOf(currsavedData.getLatitude()));
//        holder.longtitude_textview.setText(String.valueOf(currsavedData.getLongtitude()));
//        holder.usrInput_textview.setText(String.valueOf(currsavedData.getUserInput()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return savedDatas.size();
//    }
//
//    public void setSavedData(List<SavedData> savedDatas){
//        this.savedDatas = savedDatas;
//        notifyDataSetChanged();
//    }
//    class SavedDataHolder extends RecyclerView.ViewHolder{
//        private TextView latitude_textview;
//        private TextView longtitude_textview;
//        private TextView usrInput_textview;
//
//        public SavedDataHolder(@NonNull View itemView) {
//            super(itemView);
//            latitude_textview = itemView.findViewById(R.id.text_view_lat_value);
//            longtitude_textview = itemView.findViewById(R.id.text_view_long_value);
//            usrInput_textview = itemView.findViewById(R.id.text_view_usrInp_value);
//        }
//    }
//}
