package st235.github.com.catovasia.ui.adapter.state;

import java.util.List;

import st235.github.com.catovasia.ui.items.Item;

public class PullToRefreshState extends State {

    @Override
    public void setData(ListController controller, List<Item> list) {
        controller.setData(list);
    }
}
