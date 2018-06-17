package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.List;

import st235.github.com.catovasia.ui.FeedFragment;
import st235.github.com.catovasia.ui.items.Item;

public class EndlessListController implements ListController {
    @NonNull
    private State currentState;
    private FeedFragment fragment;

    public EndlessListController(@NonNull Fragment fragment) {
        this.fragment = (FeedFragment) fragment;
        currentState = new EmptyState();
    }

    public void onCreateView(){
        currentState.getPage();
    }

    @Override
    public void showError(boolean show, String message) {
        fragment.showError(show, message);
    }

    @Override
    public void showProgress(boolean show) {
        fragment.showProgress(true);
    }

    @Override
    public void setData(List<Item> list) {
        fragment.setData(list);
    }

    @Override
    public void addData(List<Item> list) {
        fragment.addData(list);
    }

    @Override
    public void showPageProgress(boolean show) {
        fragment.showProgressPage(true);
    }

    @Override
    public void loadData() {
        fragment.loadData();
    }

    @NonNull
    @Override
    public State getState() {
        return currentState;
    }

    @Override
    public void setState(@NonNull State state) {
        currentState = state;
        if (currentState instanceof ProgressState) {
            fragment.showProgress(true);
        }
    }

    @Override
    public void putData(List<Item> data) {
        if (currentState instanceof ProgressState ||
                currentState instanceof PullToRefreshState) {
            fragment.setData(data);
            fragment.showProgress(false);
        }
    }

    public void onDestroyView(){
        fragment = null;
    }
}
