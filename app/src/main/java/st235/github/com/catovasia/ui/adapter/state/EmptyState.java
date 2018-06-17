package st235.github.com.catovasia.ui.adapter.state;

public class EmptyState extends State {

    @Override
    public void getPage(ListController controller) {
        controller.loadData();
        controller.setState(new ProgressState());
    }
}
