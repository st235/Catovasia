package st235.github.com.catovasia.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import st235.github.com.catovasia.ui.items.Item;

/**
 * Class witch extends RecyclerView.Adapter. It can be used for any lists.
 * Add here adapter delegates or remove them.
 */
public class MainAdapter extends RecyclerView.Adapter {
    @NonNull
    private final AdapterDelegateManager<List<Item>> adapterDelegateManager;
    @NonNull
    private final List<Item> items;

    public MainAdapter(@NonNull List<Item> items) {
        this.items = items;
        adapterDelegateManager = new AdapterDelegateManager<>();
    }

    /**
     * Use if to add new adapter delegate
     */
    public void addDelegate(int viewType, @NonNull AdapterDelegate adapterDelegate){
        adapterDelegateManager.addDelegate(viewType, adapterDelegate);
    }

    /**
     *Use if to remove adapter delegate
     */
    public void removeDelegate(@NonNull AdapterDelegate delegate){
        adapterDelegateManager.removeDelegate(delegate);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return adapterDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        adapterDelegateManager.onBindViewHolder(items, position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegateManager.getItemViewType(items, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
