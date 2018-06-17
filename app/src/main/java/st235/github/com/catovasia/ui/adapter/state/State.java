package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import st235.github.com.catovasia.ui.items.Item;

public abstract class State {

    @NonNull
    protected final ListController controller;

    public State(@NonNull ListController controller) {
        this.controller = controller;
    }

    public void getPage(){
        Log.d(controller.getState().getClass().getName(), " -> getPage");
    }

    public void setData(List<Item> list){
        Log.d(controller.getState().getClass().getName(), " -> setData");
    }

    public void addData(List<Item> list){
        Log.d(controller.getState().getClass().getName()," -> addData");
    }

    public void refresh(){
        Log.d(controller.getState().getClass().getName(), " -> refresh");
    }
}
