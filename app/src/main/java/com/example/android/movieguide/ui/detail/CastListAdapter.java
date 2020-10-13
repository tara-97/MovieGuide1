package com.example.android.movieguide.ui.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.movieguide.POJO.Cast;
import com.example.android.movieguide.R;

import java.util.List;

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.CastViewHolder> {

    private List<Cast> castDetails;
    private Context mContext;

    public CastListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View castLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_layout,parent,false);
        return new CastViewHolder(castLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        holder.bind(castDetails.get(position));

    }

    @Override
    public int getItemCount() {
        if(castDetails == null || castDetails.size() == 0) return 0;
        return castDetails.size();
    }

    public  class CastViewHolder extends RecyclerView.ViewHolder{
        private ImageView castImg;
        private TextView castName;
        private TextView castChar;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            castImg = itemView.findViewById(R.id.iv_cast_img);
            castName = itemView.findViewById(R.id.tv_cast_name);
            castChar = itemView.findViewById(R.id.tv_cast_char);

        }
        public void bind(Cast cast){
            String path = cast.getProfilePath();
            if(path != null){
                path = "https://image.tmdb.org/t/p/w342"+path;
            }
            Glide.with(mContext).load(path).placeholder(R.drawable.ic_launcher_foreground).fitCenter().into(castImg);
            castName.setText(cast.getName());
            castChar.setText("as "+cast.getCharacter());
        }
    }
    public void setCastDetails(List<Cast> casts){
        if(casts == null){
            return;
        }
        castDetails = casts;
        notifyDataSetChanged();
    }
}
