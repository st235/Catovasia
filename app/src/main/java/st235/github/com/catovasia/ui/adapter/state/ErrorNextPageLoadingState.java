package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;

class ErrorNextPageLoadingState extends State{
    ErrorNextPageLoadingState(@NonNull Paginator controller) {
        super(controller);
    }

    @Override
    public void refresh() {
        super.refresh();
        paginator.setCurrentState(new ProgressState(paginator));
        paginator.getViewController().showProgress(true);
        paginator.getViewController().showError(false, null);
        paginator.loadData(Paginator.FIRST_PAGE);
        paginator.getCurrentData().clear();
    }

    @Override
    public void restart() {
        super.restart();
        paginator.setCurrentState(new ProgressPageState(paginator));
        paginator.getViewController().showPageProgress(true);
        paginator.getViewController().showNextPageLoadingError(false, null);
        paginator.loadData(paginator.getCurrentPage());
    }

    @Override
    public void release() {
        super.release();
    }
}
