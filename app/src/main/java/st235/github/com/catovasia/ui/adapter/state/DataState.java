package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;

class DataState extends State {

    DataState(@NonNull Paginator controller) {
        super(controller);
    }

    @Override
    public void loadPage() {
        super.loadPage();
        paginator.loadData(paginator.getCurrentPage());
        paginator.getViewController().showPageProgress(true);
        paginator.setCurrentState(new ProgressPageState(paginator));
    }

    @Override
    public void refresh() {
        super.refresh();
        paginator.loadData(Paginator.FIRST_PAGE);
        paginator.setCurrentState(new ProgressState(paginator));
        paginator.getCurrentData().clear();
        paginator.getViewController().showData(false, null);
    }

    @Override
    public void release() {
        super.release();
        paginator.setCurrentState(new ReleasedState(paginator));
    }
}
