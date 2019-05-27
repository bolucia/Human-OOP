package com.example.student.movieapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.student.movieapp.Models.Movie;
import com.example.student.movieapp.Network.APIUtils;
import com.example.student.movieapp.R;
import com.example.student.movieapp.movies.MovieDetailActivity;

import java.util.List;

/**
 * Created by Student on 9/21/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    private Context mContext;
    private List<Movie> movieList;

    public ImageAdapter(Context context, List<Movie>movies){
        this.mContext=context;
        this.movieList=movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View view=layoutInflater.inflate(R.layout.image_row,parent,false);
        return new ImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie=movieList.get(position);
        ImageView imageView=holder.mImage;
        final String url= APIUtils.IMAGE_BASE_URL+movie.getPosterPath();
        Glide.with(mContext)
                .load(url)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(MovieDetailActivity.newIntent(
                        mContext,
                        url,
                        movie.getTitle(),
                        movie.getVoteAverage(),
                        movie.getOverview(),
                        movie.getReleaseDate()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void updateAdapter(List<Movie>movies){
        movieList=movies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        private ViewHolder(View itemView) {
            super(itemView);
            mImage=(ImageView)itemView.findViewById(R.id.poster);
        }
    }
}
