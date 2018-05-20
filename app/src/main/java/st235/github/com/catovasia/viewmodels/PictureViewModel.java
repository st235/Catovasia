package st235.github.com.catovasia.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import st235.github.com.catovasia.ui.FeedItem;

public class PictureViewModel extends ViewModel {

    private final MutableLiveData<FeedItem> selectedItem = new MutableLiveData<>();

    public void select(@NonNull FeedItem item){
        selectedItem.setValue(item);
    }

    @NonNull
    public LiveData<FeedItem> getSelectedItem() {
        return selectedItem;
    }
}
