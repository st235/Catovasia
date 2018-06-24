package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;

public class EmptyState extends State {

    EmptyState(@NonNull Paginator paginator) {
        super(paginator);
    }

    @Override
    public void restart() {
        super.refresh();
        paginator.setCurrentState(new ProgressState(paginator));
        paginator.loadData(Paginator.FIRST_PAGE);
        paginator.getViewController().showProgress(true);
        paginator.getCurrentData().clear();
    }

    @Override
    public void refresh() {
        super.refresh();
        paginator.setCurrentState(new ProgressPageState(paginator));
        paginator.loadData(Paginator.FIRST_PAGE);
        paginator.getCurrentData().clear();
    }

    @Override
    public void release() {
        super.release();
        paginator.setCurrentState(new ReleasedState(paginator));
    }
}
