package com.truedreamz.jetpackdatabinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.truedreamz.jetpackdatabinding.BR;

public class User extends BaseObservable {
    String username;
    String email;
    String userImage;
    String info;

    public ObservableField<Long> numberOfFriends = new ObservableField<>();
    public ObservableField<Long> numberOfArticles = new ObservableField<>();
    public ObservableField<Long> numberOfFavourite = new ObservableField<>();

    public User() {
    }


    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @BindingAdapter({"profileImage"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }

    @Bindable
    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
        notifyPropertyChanged(BR.userImage);
    }

    @Bindable
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
        notifyPropertyChanged(BR.info);
    }

    public ObservableField<Long> getNumberOfFriends() {
        return numberOfFriends;
    }


    public ObservableField<Long> getNumberOfArticles() {
        return numberOfArticles;
    }


    public ObservableField<Long> getNumberOfFavourite() {
        return numberOfFavourite;
    }
}
