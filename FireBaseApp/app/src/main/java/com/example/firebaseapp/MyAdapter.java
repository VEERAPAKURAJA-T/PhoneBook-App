package com.example.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseapp.databinding.ItemCardBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> {
    private Context context;
    private ArrayList<User> userArrayList;

    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initialize the ViewHolder and Inflates the Item layout
        ItemCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_card,
                parent,
                false
        );
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
   //bind data to an existing ViewHolder
        //populates the views in the ViewHolder with data from the dataset.
        User currentUser= userArrayList.get(position);
        holder.itemCardBinding.setUser(currentUser);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
/*
How to add data binding to the recycler view.
In the previous videos, we started here to find the view by ID for the correct view.
Now, since I'm using the data binding, I'll start by creating an instance of the data binding of the
item card.
 */
        private ItemCardBinding itemCardBinding;

        public UserViewHolder( ItemCardBinding itemCardBinding) {
            super(itemCardBinding.getRoot());
            this.itemCardBinding = itemCardBinding;
            /*
            The super keyword is used to call the constructor of the parent class, which is the recycler view dot Viewholder.
In this case, and item binding or item card binding dot get root method.Returns the root view of the item layout by passing the root view to
super method.You are initializing the parent Viewholder class with the root view, which is what Recycler View dot
Viewholder expects and to handle the click events on the Recycler View items, you can use item binding dot get root.
             */
            //Handling click events on recyclerView Items:
            itemCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Getting the clicked item position
                    int position = getAdapterPosition();
                }
            });
        }
    }
}
