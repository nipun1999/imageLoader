package com.itparkbynipun.imageloader;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView imageRecycler;
    private DatabaseReference databaseImageRef;
    private ProgressDialog mprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageRecycler = (RecyclerView) (findViewById(R.id.imageRecycler));
        imageRecycler.setHasFixedSize(true);
        imageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mprogress = new ProgressDialog(MainActivity.this);
        databaseImageRef = FirebaseDatabase.getInstance().getReference().child("imageInfo");


    }

    @Override
    protected void onStart() {
        super.onStart();

        mprogress.setMessage("Loading Data from Server");
        mprogress.show();

        FirebaseRecyclerAdapter<getImage, imageviewholder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<getImage, imageviewholder>(
                getImage.class,
                R.layout.imagerow,
                MainActivity.imageviewholder.class,
                databaseImageRef


        ) {
            @Override
            protected void populateViewHolder(MainActivity.imageviewholder viewHolder, getImage model, int position) {
                viewHolder.setname(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImageURL());
                mprogress.dismiss();

            }


        };

        imageRecycler.setAdapter(firebaseRecyclerAdapter);


    }

    public static class imageviewholder extends RecyclerView.ViewHolder {
        View mview;


        public imageviewholder(View itemView) {
            super(itemView);
            mview = itemView;
        }


        public void setname(String title) {
            TextView post_desc = (TextView) (mview.findViewById(R.id.imageTitle));
            post_desc.setText(title);
        }

        public void setImage(Context ctx, String imageURL) {
            ImageView post_image = (ImageView) (mview.findViewById(R.id.imageView));
            Picasso.with(ctx).load(imageURL).into(post_image);
        }


    }
}
