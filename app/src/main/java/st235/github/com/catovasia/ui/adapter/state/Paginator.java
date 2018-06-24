package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import st235.github.com.catovasia.ui.items.Item;

public class Paginator {
    private static final int GIFS_PER_PAGE = 5;
    public static final int FIRST_PAGE = 1;

    @NonNull
    private State currentState = new EmptyState(this);
    @NonNull
    private List<Item> currentData = new ArrayList<>();
    @NonNull
    private ViewController viewController;
    private int currentPage = 0;

    public Paginator(@NonNull ViewController viewController) {
        this.viewController = viewController;
    }

    public interface ViewController {
        void showError(boolean show, String message);

        void showProgress(boolean show);

        void setData(List<Item> list);

        void showPageProgress(boolean show);

        void loadData(int page, int offset);

        void showNextPageLoadingError(boolean show, String message);

        void showData(boolean show, List<Item> list);
    }

    public void refresh(){
        currentState.refresh();
    }

    public void release(){
        currentState.release();
    }

    public void loadData(int page){
        int offset = calculateOffset(page);
        viewController.loadData(page, offset);
    }

    private int calculateOffset(int page) {
        if (page == 1) {
            return 0;
        } else {
            return (page - 1) * GIFS_PER_PAGE;
        }
    }

    public void onSuccess(@NonNull List<Item> listData){
        currentState.setData(listData);
    }

    public void onError(@Nullable String message){
        currentState.fail(message);
    }

    @NonNull
    public ViewController getViewController() {
        return viewController;
    }

    public void setViewController(@NonNull ViewController viewController) {
        this.viewController = viewController;
    }

    @NonNull
    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(@NonNull State currentState) {
        this.currentState = currentState;
    }

    @NonNull
    public List<Item> getCurrentData() {
        return currentData;
    }

    public void setCurrentData(@NonNull List<Item> currentData) {
        this.currentData = currentData;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
