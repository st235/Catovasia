package st235.github.com.catovasia.ui.items;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import st235.github.com.catovasia.data.net.Result;
import st235.github.com.catovasia.models.gifs.Data;
import st235.github.com.catovasia.models.pictures.Picture;
import st235.github.com.catovasia.ui.items.GifItem;
import st235.github.com.catovasia.ui.items.Item;
import st235.github.com.catovasia.ui.items.PictureItem;

/**
 * Class for building items data ready to show in ui
 */
@Singleton
public class ItemBuilder {
    private static final int GIF_PLACE = 6;

    private final LiveData<List<Item>> liveData = new MutableLiveData<>();

    /**
     * Use this method in viewModels to merge pictire items and gifs items in one list.
     * @return Items list
     */
    @NonNull
    public Result<List<Item>> mergeItems(@NonNull List<Item> pictureItems, @NonNull List<Item> gifItems) {
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < pictureItems.size(); i++) {
            if ((i != 0) && (i % GIF_PLACE == 0)) {
                result.add(gifItems.get((i / GIF_PLACE) - 1));
            }
            result.add(pictureItems.get(i));
        }
        result.add(gifItems.get(gifItems.size() - 1));
        return Result.createSuccess(result);
    }

    @NonNull
    public List<Item> buildPictureItems(@NonNull List<Picture> pictures) {
        List<Item> pictureList = new ArrayList<>();
        for (Picture picture : pictures) {
            PictureItem item = new PictureItem(picture.getFullscreenPicture(),
                    picture.getThumbnailPicture(), picture.getColor());
            pictureList.add(item);
        }
        return pictureList;
    }

    @NonNull
    public List<Item> buildGifItems(@NonNull List<Data> gifs) {
        List<Item> gifList = new ArrayList<>();
        for (Data data:gifs){
            GifItem item = new GifItem(data.getEmbedUrl(),
                    data.getGifData().getOriginal().getUrl(),
                    data.getGifData().getPreviewGif().getUrl());
            gifList.add(item);
        }
        return gifList;
    }
}
