package com.truedreamz.jetpackdatabinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.truedreamz.jetpackdatabinding.databinding.ActivityMainBinding;
import com.truedreamz.jetpackdatabinding.model.Article;
import com.truedreamz.jetpackdatabinding.model.User;
import com.truedreamz.jetpackdatabinding.ui_utils.GridItemDecoration;
import com.truedreamz.jetpackdatabinding.ui_utils.ImageUtil;
import com.truedreamz.jetpackdatabinding.view.ArticleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ArticleAdapter.ArticlesAdapterListener {
    private User user;
    private ActivityMainBinding binding;
    private AppClickHandlers handler;
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new AppClickHandlers(this);
        loadUserData();
        init();
    }

    private void loadUserData() {
        user = new User();
        user.setUsername("Mr. Sherlock Holmes");
        user.setUserImage("https://tse4.mm.bing.net/th?id=OIP.gHhJlg-RAvR-XosdERsIMQHaEo&pid=Api&w=1440&h=900&rs=1&p=0");

        // updating ObservableField
        user.numberOfArticles.set(1500L);
        user.numberOfFriends.set(205090L);
        user.numberOfFavourite.set(10L);
        // display user
        binding.setUser(user);
        // assign click handlers
        binding.setHandlers(handler);
    }

    private void init() {
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridItemDecoration(3, ImageUtil.dpToPx(MainActivity.this, 4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        articleAdapter = new ArticleAdapter(getArticles(), this);
        recyclerView.setAdapter(articleAdapter);
    }

    private ArrayList<Article> getArticles() {
        ArrayList<Article> articles = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Article article = new Article();
            article.setImageUrl("https://tse3.mm.bing.net/th?id=OIP.f1bDVK_DN6xiTaYAM79ucgHaEK&pid=Api");
            articles.add(article);
        }

        return articles;
    }

    @Override
    public void onArticleClicked(Article article) {
        Toast.makeText(getApplicationContext(), "Article clicked! " + article.getImageUrl(), Toast.LENGTH_SHORT).show();
    }

    public class AppClickHandlers {

        Context context;

        public AppClickHandlers(Context context) {
            this.context = context;
        }

        public void onFriendsClicked(View view) {
            Toast.makeText(context, "Friends is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onFavouriteClicked(View view) {
            Toast.makeText(context, "Favourite is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onArticlesClicked(View view) {
            Toast.makeText(context, "Article is clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
