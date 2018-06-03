package st235.github.com.catovasia.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import st235.github.com.catovasia.data.NetRepository;
import st235.github.com.catovasia.data.net.Result;
import st235.github.com.catovasia.models.gifs.Data;
import st235.github.com.catovasia.models.pictures.Picture;
import st235.github.com.catovasia.ui.adapter.ItemBuilder;

/**
 * Provides feed from dataSources to view
 */
public class FeedViewModel extends ViewModel {
    private static final int GIFS_PER_PAGE = 6;

    @NonNull
    private final NetRepository repository;
    @NonNull
    private final ItemBuilder builder;
    private LiveData<Result<List<Data>>> gifLiveData;
    private LiveData<Result<List<Picture>>> pictureLiveData;

    @Inject
    public FeedViewModel(@NonNull NetRepository repository,
                         @NonNull ItemBuilder builder) {
        this.repository = repository;
        this.builder = builder;
    }

    @NonNull
    public LiveData<Result<List<Picture>>> getFeedLiveData(int page) {
        int offset = calculateOffset(page);
        pictureLiveData = repository.getCatsPhotos(page);
        gifLiveData = repository.getCatGifs(offset);
        //TODO (Chg0): here u need to sync 2 network/db requests and then call builder.build(pics, gifs)
        return liveData;
    }

    private int calculateOffset(int page) {
        if (page == 1) {
            return 0;
        } else {
            return (page - 1) * GIFS_PER_PAGE;
        }
    }
}
