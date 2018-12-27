package com.truedreamz.jetpackdatabinding.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truedreamz.jetpackdatabinding.R;
import com.truedreamz.jetpackdatabinding.databinding.ArticleRowItemBinding;
import com.truedreamz.jetpackdatabinding.model.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> articleList;
    private LayoutInflater layoutInflater;
    private ArticlesAdapterListener listener;

    public ArticleAdapter(List<Article> articleList, ArticlesAdapterListener listener) {
        this.articleList = articleList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (null == layoutInflater) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ArticleRowItemBinding articleRowItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.article_row_item, viewGroup, false);
        return new ArticleViewHolder(articleRowItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder articleViewHolder, final int i) {
        articleViewHolder.rowItemBinding.setArticle(articleList.get(i));
        articleViewHolder.rowItemBinding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onArticleClicked(articleList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private ArticleRowItemBinding rowItemBinding;

        public ArticleViewHolder(final ArticleRowItemBinding binding) {
            super(binding.getRoot());
            this.rowItemBinding = binding;
        }
    }

    public interface ArticlesAdapterListener {
        void onArticleClicked(Article post);
    }
}
