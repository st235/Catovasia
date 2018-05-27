package st235.github.com.catovasia.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import st235.github.com.catovasia.ui.items.Item;

/**
 * Provides picture from feed to view
 */
public class PictureViewModel extends ViewModel {
    private final MutableLiveData<Item> selectedItem = new MutableLiveData<>();

    public void select(@NonNull Item item){
        selectedItem.setValue(item);
    }

    @NonNull
    public LiveData<Item> getSelectedItem() {
        return selectedItem;
    }
}
