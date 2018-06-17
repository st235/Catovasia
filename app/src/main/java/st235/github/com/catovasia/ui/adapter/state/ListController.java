package st235.github.com.catovasia.ui.adapter.state;

import java.util.List;

import st235.github.com.catovasia.ui.items.Item;

public interface ListController {

    void showError(boolean show, String message);
    void showProgress(boolean show);
    void setData(List<Item> list);
    void addData(List<Item> list);
    void showPageProgress(boolean show);
    void loadData();
    State getState();
    void setState(State state);
    void putData(List<Item> data);
}
