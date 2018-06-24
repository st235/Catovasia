package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import st235.github.com.catovasia.ui.items.Item;

class ProgressState extends State {

    ProgressState(@NonNull Paginator controller) {
        super(controller);
    }

    @Override
    public void setData(@NonNull List<Item> list) {
        super.setData(list);
        paginator.setCurrentState(new DataState(paginator));
        paginator.getViewController().setData(list);
        paginator.getViewController().showProgress(false);
        paginator.setCurrentPage(Paginator.FIRST_PAGE);
        paginator.getCurrentData().addAll(list);
        paginator.getViewController().showData(true, paginator.getCurrentData());
    }

    @Override
    public void refresh() {
        super.refresh();
        paginator.loadData(Paginator.FIRST_PAGE);
    }

    @Override
    public void release() {
        super.release();
        paginator.setCurrentState(new ReleasedState(paginator));
    }

    @Override
    public void fail(@Nullable String message) {
        super.fail(message);
        paginator.setCurrentState(new ErrorState(paginator));
        paginator.getViewController().showProgress(false);
        paginator.getViewController().showData(false, null);
        paginator.getViewController().showError(true, message);
    }
}
