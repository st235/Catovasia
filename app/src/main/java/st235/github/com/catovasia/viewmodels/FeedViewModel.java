package st235.github.com.catovasia.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import st235.github.com.catovasia.data.NetRepository;
import st235.github.com.catovasia.data.net.Result;
import st235.github.com.catovasia.models.gifs.Data;
import st235.github.com.catovasia.models.pictures.Picture;
import st235.github.com.catovasia.ui.adapter.ItemBuilder;
import st235.github.com.catovasia.ui.items.Item;

/**
 * Provides feed from dataSources to view
 */
public class FeedViewModel extends ViewModel {
    private static final int GIFS_PER_PAGE = 5;

    @NonNull
    private final NetRepository repository;
    @NonNull
    private final ItemBuilder builder;
    private LiveData<Result<List<Data>>> gifLiveData;
    private LiveData<List<Item>> gifItemsLiveData;
    private LiveData<Result<List<Picture>>> pictureLiveData;
    private LiveData<List<Item>> pictureItemsLiveData;
    private List<Item> items;
    private final MediatorLiveData<List<Item>> mediatorLiveData = new MediatorLiveData<>();

    @Inject
    public FeedViewModel(@NonNull NetRepository repository,
                         @NonNull ItemBuilder builder) {
        this.repository = repository;
        this.builder = builder;
    }

    @NonNull
    public LiveData<List<Item>> getFeedLiveData(int page) {
        int offset = calculateOffset(page);
        pictureLiveData = repository.getCatsPhotos(page);
        gifLiveData = repository.getCatGifs(offset);

        pictureItemsLiveData = Transformations.map(pictureLiveData, items ->
                builder.buildPictureItems(items.getData()));

        gifItemsLiveData = Transformations.map(gifLiveData, items ->
                builder.buildGifItems(items.getData()));

        mediatorLiveData.addSource(pictureItemsLiveData, pictureItems -> {
            if (gifItemsLiveData.getValue() != null && pictureItems !=null) {
                mediatorLiveData.setValue(builder.mergeItems(pictureItems, gifItemsLiveData.getValue()));
            }
        });
        mediatorLiveData.addSource(gifItemsLiveData, gifItems -> {
            if (pictureItemsLiveData.getValue() != null && gifItems !=null) {
                mediatorLiveData.setValue(builder.mergeItems(pictureItemsLiveData.getValue(), gifItems));
            }
        });
        return mediatorLiveData;
    }

    private int calculateOffset(int page) {
        if (page == 1) {
            return 0;
        } else {
            return (page - 1) * GIFS_PER_PAGE;
        }
    }
}
