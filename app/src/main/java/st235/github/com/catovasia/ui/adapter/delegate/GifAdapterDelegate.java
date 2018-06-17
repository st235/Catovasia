package st235.github.com.catovasia.ui.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import st235.github.com.catovasia.ui.items.GifItem;
import st235.github.com.catovasia.ui.items.Item;

/**
 * Adapter delegate for Gif viewType
 */
public class GifAdapterDelegate implements AdapterDelegate<List<Item>> {

    @NonNull
    private final GifAdapterDelegate.Callback callback;

    public interface Callback {
        void onItemClick(@NonNull Item item);
        void loadGif(@NonNull String url, @NonNull String preview);
    }

    public GifAdapterDelegate(@NonNull Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new GifAdapterDelegate.GifViewHolder(inflater.inflate(viewType, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull List<Item> items, @NonNull RecyclerView.ViewHolder holder, int position) {
        GifItem item = (GifItem) items.get(position);
        holder.itemView.setOnClickListener(v -> callback.onItemClick(item));
        callback.loadGif(item.getUrl(), item.getPreview());
    }

    @Override
    public boolean isForViewType(@NonNull List<Item> items, int position) {
        return items.get(position) instanceof GifItem;
    }

    static class GifViewHolder extends RecyclerView.ViewHolder {

        GifViewHolder(View itemView) {
            super(itemView);
        }
    }
}
