package st235.github.com.catovasia.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Class witch manages all adapter delegates
 * @param <T> is for items type
 */
public class AdapterDelegateManager<T> {
    public static final int INVALID_VIEW_TYPE = -1;

    @NonNull
    private SparseArrayCompat<AdapterDelegate> delegates = new SparseArrayCompat<>();

    void addDelegate(int viewType, @NonNull AdapterDelegate<T> adapterDelegate){
        delegates.put(viewType, adapterDelegate);
    }

    int getItemViewType(@NonNull T items, int position) {
        AdapterDelegate<T> delegate;
        for (int i = 0; i < delegates.size(); i++) {
            delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i);
            }
        }
        return INVALID_VIEW_TYPE;
    }

    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterDelegate<T> delegate = delegates.get(viewType);
        return delegate.onCreateViewHolder(parent, viewType);
    }

    void onBindViewHolder(@NonNull T items, int position,
                                 @NonNull RecyclerView.ViewHolder holder) {
        AdapterDelegate<T> delegate = delegates.get(holder.getItemViewType());
        delegate.onBindViewHolder(items, holder, position);
    }

    void removeDelegate(@NonNull AdapterDelegate delegate) {
        int indexToDelete = delegates.indexOfValue(delegate);
        if (indexToDelete >= 0) {
            delegates.removeAt(indexToDelete);
        }
    }
}
