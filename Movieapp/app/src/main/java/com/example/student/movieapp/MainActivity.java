package com.example.student.movieapp;



        import android.content.Context;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.NavigationView;
        import android.support.design.widget.Snackbar;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import com.example.student.movieapp.Adapter.ImageAdapter;
        import com.example.student.movieapp.Models.Movie;
        import com.example.student.movieapp.Network.APIService;
        import com.example.student.movieapp.Network.APIUtils;
        import com.example.student.movieapp.Network.MovieResponse;

        import java.util.ArrayList;
        import java.util.List;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.imageRecyclerView)
    RecyclerView mRecyclerView;

    private Context mContext;
    private ImageAdapter mImageAdapter;
    private List<Movie> movieList=new ArrayList<>();
    private APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext=MainActivity.this;
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mApiService=APIUtils.getApiService();
        mImageAdapter=new ImageAdapter(mContext,movieList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mImageAdapter);

        init();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init(){
        mApiService.getPopularMovies(APIUtils.API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    movieList=response.body().getResults();
                    mImageAdapter.updateAdapter(movieList);
                }else{
                    Snackbar.make(mRecyclerView,response.message(),Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Snackbar.make(mRecyclerView,t.getMessage(),Snackbar.LENGTH_SHORT).show();

            }
        });
    }

}

}
