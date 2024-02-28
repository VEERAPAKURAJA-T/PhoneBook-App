package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.firebaseapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     private ArrayList<User> users;
     private RecyclerView recyclerView;
     private MyAdapter userAdapter;
     private ActivityMainBinding binding;
     private FirebaseDatabase database;
     private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );
        database=FirebaseDatabase.getInstance();
        databaseReference =database.getReference("Users");
        //RecyclerView with data binding
        recyclerView = binding.recyclerView;

        //Fetch the data from the firebase into RecyclerView
        /*
        Add value Event listener is a method used to attach an event listener to a specific location in the database.
This event listener monitors changes at the specified database reference.In this case, it's at the users node and allows you to respond
to those changes in real time.It's a fundamental mechanism for building real time applications with Firebase, and to listen for changes
at the specified location.You call the Add Value Event listener method on the database reference and pass in an instance of value
event listener.The value event listener is an interface with two key callback methods on data change and on cancel.
         */
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*
                Here we need to get the data and get the list of users in the database.
So for that I'll use a for each loop data snapshot object.And here I name it as data snapshot object.And to get the data from here we need to
use snapshot dot get children.This is a for Each loop is commonly used in Firebase real time database to iterate through the child nodes or children
of a specific snapshot.Snapshot in Firebase real time database represent a specific point in the database at a particular location.
It contains the data and structure of the database at the location at the moment the snapshot was created.Get children method is used and
called on at Data Snapshot object and returns an iterable interface containing all immediate child nodes of that snapshot and the for loop in
your code here iterates through these child nodes one by one.For each child node, the data snapshot variable represents the current child node
within the loop iteration.Then I need to display and get the items from the data snapshot object that we created here and here.
I need to start by creating an object user user equal data snapshot dot get value.And what is the type of the value that we are getting? It's of
type user class.So here I need to specify user dot class.Make sure guys that you include a no arguments constructor in your model class in order
to use it with Firebase list users Dot add this user for every user inside the snapshot.Go and add it to the users array list in our main activity.
Then I need to use this notify data set.Changed code go up and after the loop notify the data set changed.
                 */
            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                User user =dataSnapshot.getValue(User.class);
                users.add(user);
            }
                //notify an adapter associated with a recyclerview that the underlying dataset has changed.
                userAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
         users=new ArrayList<>();
        userAdapter= new MyAdapter(this,users);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        /*
        In this video we'll learn how to write data and how to insert data into our database.
I'll start by creating database instance by using Firebase Database.And you can see I imported this class from the Google Firebase database package.
Firebase database database equal to Firebase database dot get instance.Firebase dot get instance method is a method provided by
Firebase Realtime Database SDK for Android that allows you to obtain an instance of the Firebase real time database.
You typically use this method to initialize and access the database in your Android application.Once you've obtained an instance of the database,
you can use it to get references to specific locations or nodes within your database. These references allow you to read and write data at those
locations.I start using database reference, my reference equal to database dot get reference method, and here I need to specify the node and
this node you want to access within your database.For example path is messages node will be named as messages with a reference to a specific node.
You can perform various database operations like reading data with add listeners for single value event method, writing data with set value method
and others.In this video, we'll learn about set value method.So I start with my reference dot set value method.And here I need to set the value pass
an argument of type string to this set value method.I'll use hello from our course.
         */
        //Write a message to the database
        //Initialize and access the firebase
       // FirebaseDatabase database =FirebaseDatabase.getInstance();
        //Get a reference to a specific node in the database
      //  DatabaseReference myRef = database.getReference("messages");
        //Write a value to the specified database location
       // myRef.setValue("Hello from our course ! ");
        /*
In the previous video, we learned how to write a value to a specified database location in Firebase Realtime Database.In this video, we'll learn how to read any change in
the value of the specified database location in Firebase Realtime Database.To make your app data update in real time, you should add a value Event listener to the reference you
just created.So I'll start with my reference which is referred to this reference.Add the path and the node called messages.My ref dot add value event listener.
And here I need to pass new Value event listener.This code snippet is used to attach an event listener to a Firebase Realtime Database reference, and
in this case my ref in an Android application.This listener is specifically used for listening to changes in the data located at a specified database reference, and
which is in this case messages node.So my ref is a reference to a specific location, which is the node within your Firebase Realtime database.You can think of it as a
pointer to where the data is stored, or where you want to listen for changes and valueListener.This method is used to add an event listener to the database reference.
It allows you to listen for changes in the data at the location and the new value event listener.This part defines an anonymous inner class that implements the value event
listener interface.The value event listener interface has two callback methods on data change and on cancelled on data changed method.This method is called when the data
at the specified database reference changes.The dataSnapshot parameter contains the updated data, and you can extract and process this data as needed.This is where you handle
the changes to that data.So for that, I'll handle the changes in the data by using a string called value or new value equals to data snapshot, which is
the snapshot here dot get value method.And here I need to use string dot class.Here I am extracting and processing the updated data from the data snapshot and data snapshot
 is an object that represents the specific snapshot of data at a particular location.In the Firebase Realtime database, you can use methods like Get Value, Get Children and
 others provided by Data Snapshot to access the data stored at the database location to which this listener is attached,

        TextView textView = findViewById(R.id.textview);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String newValue = snapshot.getValue(String.class);
                textView.setText(newValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
              //Handle Errors here
            }
        });
        */
    }
}