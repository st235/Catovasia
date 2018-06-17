package st235.github.com.catovasia.ui.adapter.state;

import android.support.annotation.NonNull;

import java.util.List;

import st235.github.com.catovasia.ui.items.Item;

public class ProgressPageState extends State {

    public ProgressPageState(@NonNull ListController controller) {
        super(controller);
        controller.showPageProgress(true);
    }

    @Override
    public void addData(List<Item> list) {
        controller.addData(list);
        controller.setState(new DataState(controller));
    }
}
