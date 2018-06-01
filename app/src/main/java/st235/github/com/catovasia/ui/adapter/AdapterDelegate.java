package st235.github.com.catovasia.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 *interface, which all adapter delegates must implement
 * T is for items type
 */
public interface AdapterDelegate<T> {
    /**
     * Mirror abstract methods in RecyclerView.Adapter
     */
    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    void onBindViewHolder(@NonNull T items, @NonNull RecyclerView.ViewHolder holder, int position);

    /**
     * Check if this adapter delegate uses this viewType for item in list by its position
     */
    boolean isForViewType(@NonNull T items, int position);
}
