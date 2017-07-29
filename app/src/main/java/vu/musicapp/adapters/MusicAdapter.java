package vu.musicapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import vu.musicapp.R;
import vu.musicapp.models.MusicModel;

/**
 * Created by mac-vuongvu on 7/19/17.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicAdapterViewHolder> {
    private List<MusicModel> musicModelList = new ArrayList<>();
    private Context context;
    private View.OnClickListener onclickListener;
    View view;
    //
    public void setOnclikListener(View.OnClickListener onclickListener) {
        this.onclickListener = onclickListener;
    }
    public MusicAdapter(List<MusicModel> musicModelList, Context context) {
        this.musicModelList = musicModelList;
        this.context = context;
    }

    @Override
    public MusicAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_music, parent, false);
        view.setOnClickListener(onclickListener);
        return new MusicAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicAdapterViewHolder holder, int position) {
        holder.setData(musicModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return musicModelList.size();
    }



    public  class MusicAdapterViewHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MusicAdapterViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item);
            textView  =itemView.findViewById(R.id.tv_item);
            view = itemView;

        }

        public  void setData(MusicModel musicModel) {
            if (musicModel != null) {
                Picasso.with(context).load(musicModel.getImageID()).into(imageView);
                textView.setText(musicModel.getKey());
                view.setTag(musicModel);
            }

        }
    }


}
