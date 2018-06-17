package st235.github.com.catovasia.ui.adapter.state;

public class ErrorState extends State {

    public ErrorState(ListController controller) {
        super(controller);
        controller.showError(true, "");
    }

    @Override
    public void getPage() {
        controller.setState(new ProgressState(controller));
        controller.showError(false, "");
    }
}
