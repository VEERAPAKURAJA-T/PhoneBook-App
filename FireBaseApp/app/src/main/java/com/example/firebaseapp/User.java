package com.example.firebaseapp;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/*
Add Bindable annotation and notify data set changed method.So again guys let's start with the base observable.
Here I need to extend base observable.Base observable is a class provided by Android's data binding library.
And you can see it.We imported it from the package Android x dot data bind.
It serves as a base class for implementing observable properties and objects that notify changes to their properties.
You can extend Base Observable in your ViewModel or model classes, and annotate getter methods with add Bindable to create observable properties.
When a property within a base observable object changes, you can call notify property changed method
to notify the data binding framework about the change which triggers UI updates.
 */
public class User extends BaseObservable {
    String username;
    String phoneNumber;
    String groupUser;

    public User(String username, String phoneNumber, String groupUser) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.groupUser = groupUser;
    }
    //In order to work with firebase we have to put empty constructor

    public User() {
    }
    /*
    When the value of add Bindable property changes, it triggers a data binding update, causing associated
UI elements to automatically reflect the updated value.
     */
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        /*
        notify property Changed is a method call used to notify the data binding framework that the value of an observable property has changed.
This method is typically used within classes that extend base observable to trigger the updates in the
UI and in the views when a property's value changes.
         */
        notifyPropertyChanged(BR.username);//BR -binding resource
    }
    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }
    @Bindable
    public String getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(String groupUser) {
        this.groupUser = groupUser;
        notifyPropertyChanged(BR.groupUser);
    }
}

