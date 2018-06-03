package st235.github.com.catovasia.ui.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Singleton;

import st235.github.com.catovasia.ui.items.GifItem;
import st235.github.com.catovasia.ui.items.Item;
import st235.github.com.catovasia.ui.items.PictureItem;

/**
 * Class for building items data ready to show in ui
 */
@Singleton
public class ItemBuilder {
    private final LiveData<List<Item>> liveData = new MutableLiveData<>();
    /**
     * use this method in viewModels
     * @return Items list
     */

    public LiveData<List<Item>> build(){
        return liveData;
    }

    private List<PictureItem> buildPictureItems(){

    }

    private List<GifItem> buildGifItems(){

    }
}
