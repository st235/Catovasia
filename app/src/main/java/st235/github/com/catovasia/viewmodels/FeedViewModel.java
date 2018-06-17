package st235.github.com.catovasia.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import st235.github.com.catovasia.data.NetRepository;
import st235.github.com.catovasia.data.net.Result;
import st235.github.com.catovasia.models.gifs.Data;
import st235.github.com.catovasia.models.pictures.Picture;
import st235.github.com.catovasia.ui.items.ItemBuilder;
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
    private final MediatorLiveData<Result<List<Item>>> mediatorLiveData = new MediatorLiveData<>();

    @Inject
    public FeedViewModel(@NonNull NetRepository repository,
                         @NonNull ItemBuilder builder) {
        this.repository = repository;
        this.builder = builder;
    }

    /**
     * Method receives LiveData<Result<List<Picture>>> and LiveData<Result<List<Gif>>> from repository.
     * If one of them returns error, this method returns error to view, if both return success,
     * MediatorLiveData will get value.
     * @param page for pagination
     * @return MediatorLiveData<Result<List<Item>>>
     */
    @NonNull
    public LiveData<Result<List<Item>>> getFeedLiveData(int page) {
        int offset = calculateOffset(page);
        pictureLiveData = repository.getCatsPhotos(page);
        gifLiveData = repository.getCatGifs(offset);

        if (pictureLiveData.getValue().getStatus() == Result.Status.ERROR){
            mediatorLiveData.setValue(Result.createError(pictureLiveData.getValue().getMessage(),
                    Collections.emptyList()));
            return mediatorLiveData;
        }
        if (gifLiveData.getValue().getStatus() == Result.Status.ERROR){
            mediatorLiveData.setValue(Result.createError(gifLiveData.getValue().getMessage(),
                    Collections.emptyList()));
            return mediatorLiveData;
        }
        pictureItemsLiveData = Transformations.map(pictureLiveData, resultItems ->
                builder.buildPictureItems(resultItems.getData()));
        gifItemsLiveData = Transformations.map(gifLiveData, items ->
                builder.buildGifItems(items.getData()));

        mediatorLiveData.addSource(pictureItemsLiveData, pictureItems -> {
            if (gifItemsLiveData.getValue() != null && pictureItems != null) {
                mediatorLiveData.setValue(builder.mergeItems(pictureItems, gifItemsLiveData.getValue()));
            }
        });
        mediatorLiveData.addSource(gifItemsLiveData, gifItems -> {
            if (pictureItemsLiveData.getValue() != null && gifItems != null) {
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
