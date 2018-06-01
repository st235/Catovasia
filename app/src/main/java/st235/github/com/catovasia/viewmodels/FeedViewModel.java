package st235.github.com.catovasia.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import st235.github.com.catovasia.data.NetRepository;
import st235.github.com.catovasia.models.pictures.Picture;
import st235.github.com.catovasia.data.net.Result;

/**
 * Provides feed from dataSources to view
 */
public class FeedViewModel extends ViewModel {
    private final NetRepository repository;
    private LiveData<Result<List<Picture>>> liveData;

    @Inject
    public FeedViewModel(@NonNull NetRepository repository) {
        this.repository = repository;
    }

    @NonNull
    public LiveData<Result<List<Picture>>> getFeedLiveData(int page){
        liveData = repository.getCatsPhotos(page);
        return liveData;
    }
}
