package com.example.student.movieapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.student.movieapp.Models.Movie;
import com.example.student.movieapp.Network.APIUtils;
import com.example.student.movieapp.R;

import java.util.List;

/**
 * Created by Student on 9/21/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private Context mContext;
    private List<Movie> movieList;

    public MovieAdapter(Context context,List<Movie>movies){
        this.mContext=context;
        this.movieList=movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View view=layoutInflater.inflate(R.layout.movie_row,parent,false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie=movieList.get(position);
        holder.mTitle.setText(movie.getTitle());
        holder.mRating.setText(String.valueOf(movie.getVoteAverage()));
        holder.mOverview.setText(movie.getOverview());
        holder.mReleaseDate.setText(movie.getReleaseDate());
        ImageView imageView=holder.mImage;
        String url= APIUtils.IMAGE_BASE_URL+movie.getPosterPath();
        Glide.with(mContext)
                .load(url)
                .into(imageView);
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
        TextView mTitle;
        TextView mRating;
        TextView mOverview;
        TextView mReleaseDate;
        private ViewHolder(View itemView) {
            super(itemView);
            mImage=(ImageView)itemView.findViewById(R.id.image);
            mTitle=(TextView)itemView.findViewById(R.id.title);
            mRating=(TextView)itemView.findViewById(R.id.rating);
            mOverview=(TextView)itemView.findViewById(R.id.overView);
            mReleaseDate=(TextView)itemView.findViewById(R.id.releaseDate);
        }
    }
}
