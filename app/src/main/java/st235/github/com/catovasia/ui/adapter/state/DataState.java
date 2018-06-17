package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;

public class DataState extends State {

    public DataState(@NonNull ListController controller) {
        super(controller);
    }

    @Override
    public void refresh() {
        controller.setState(new PullToRefreshState());
    }

    @Override
    public void getPage() {
        controller.setState(new ProgressPageState());
    }
}
