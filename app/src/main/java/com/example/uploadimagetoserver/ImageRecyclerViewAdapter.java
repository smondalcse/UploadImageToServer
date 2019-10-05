package com.example.uploadimagetoserver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sanat on 5/2/2018.
 */

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ImageViewHolder>{

    private Context mCtx;
    private List<ImageModel> imageModelList;

    public ImageRecyclerViewAdapter(Context mCtx, List<ImageModel> imageModelList) {
        this.mCtx = mCtx;
        this.imageModelList = imageModelList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_listitem, null);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final ImageModel imageModel = imageModelList.get(position);

        holder.image_title.setText(imageModel.getImg_title());

        Glide.with(mCtx)
                .load(imageModel.getImage_name())
                .into(holder.circleImageView);

        holder.layout_listitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, imageModel.getImg_title(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, DisplayImageActivity.class);
                intent.putExtra("img_name", imageModel.getImg_title());
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageModelList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView image_title;
        CircleImageView circleImageView;
        RelativeLayout layout_listitem;

        public ImageViewHolder(View itemView) {
            super(itemView);

            image_title = (TextView) itemView.findViewById(R.id.image_name);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.Circle_image);

            layout_listitem = (RelativeLayout) itemView.findViewById(R.id.parent_layout);
        }
    }

}
