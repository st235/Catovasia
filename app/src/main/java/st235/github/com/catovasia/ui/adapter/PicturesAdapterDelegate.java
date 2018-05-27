package st235.github.com.catovasia.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import st235.github.com.catovasia.ui.items.Item;
import st235.github.com.catovasia.ui.items.PictureItem;

/**
 * Adapter delegate for Gif viewType
 */
public class PicturesAdapterDelegate implements AdapterDelegate<List<Item>> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PictureViewHolder(inflater.inflate(viewType, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull List<Item> items, @NonNull RecyclerView.ViewHolder holder, int position) {
        PictureViewHolder pictureViewHolder = (PictureViewHolder) holder;
        PictureItem item = (PictureItem) items.get(position);
        //TODO(Chog0): bind data with databinding
    }

    @Override
    public boolean isForViewType(@NonNull List<Item> items, int position) {
        return items.get(position) instanceof PictureItem;
    }

    static class PictureViewHolder extends RecyclerView.ViewHolder{

        PictureViewHolder(View itemView) {
            super(itemView);
        }
    }
}
