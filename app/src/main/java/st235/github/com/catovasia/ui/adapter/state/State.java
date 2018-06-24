package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import st235.github.com.catovasia.ui.items.Item;

/**
 * Abstract class to indicate stats for data lists.
 */
public abstract class State {

    @NonNull
    final Paginator paginator;

    State(@NonNull Paginator controller) {
        this.paginator = controller;
    }

    /**
     * Load next page of data.
     */
    public void loadPage(){
        Log.d(paginator.getCurrentState().getClass().getName(), " -> getPage");
    }

    /**
     * set data in adapter (clean list and set new data) when it was loaded
     * @param list of Items
     */
    public void setData(@NonNull List<Item> list){
        Log.d(paginator.getCurrentState().getClass().getName(), " -> setData");
    }

    /**
     * reload data when "pull to refresh" was called
     */
    public void refresh(){
        Log.d(paginator.getCurrentState().getClass().getName(), " -> refresh");
    }

    /**
     * Call to restart loading
     */
    public void restart(){
        Log.d(paginator.getCurrentState().getClass().getName(), " -> restart");
    }

    /**
     * Called when view has beed destroyed
     */
    public void release(){
        Log.d(paginator.getCurrentState().getClass().getName(), " -> release");
    }

    public void fail(@Nullable String message){
        Log.d(paginator.getCurrentState().getClass().getName(), " -> fail");
    }
}
