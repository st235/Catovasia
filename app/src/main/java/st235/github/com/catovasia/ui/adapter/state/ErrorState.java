package st235.github.com.catovasia.ui.adapter.state;

public class ErrorState extends State {

    public ErrorState(Paginator controller) {
        super(controller);
    }

    @Override
    public void refresh() {
        super.refresh();
        paginator.setCurrentState(new ProgressState(paginator));
        paginator.getViewController().showError(false, null);
        paginator.loadData(Paginator.FIRST_PAGE);
        paginator.getCurrentData().clear();
    }

    @Override
    public void restart() {
        super.restart();
        paginator.setCurrentState(new ProgressState(paginator));
        paginator.getViewController().showPageProgress(true);
        paginator.getViewController().showError(false, null);
        paginator.loadData(Paginator.FIRST_PAGE);
        paginator.getCurrentData().clear();
    }

    @Override
    public void release() {
        super.release();
        paginator.setCurrentState(new ReleasedState(paginator));
    }
}
