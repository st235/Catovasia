package st235.github.com.catovasia.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import st235.github.com.catovasia.R;
import st235.github.com.catovasia.ui.adapter.GifAdapterDelegate;
import st235.github.com.catovasia.ui.adapter.MainAdapter;
import st235.github.com.catovasia.ui.adapter.PicturesAdapterDelegate;
import st235.github.com.catovasia.ui.items.GifItem;
import st235.github.com.catovasia.ui.items.Item;
import st235.github.com.catovasia.ui.items.PictureItem;
import st235.github.com.catovasia.viewmodels.FeedViewModel;
import st235.github.com.catovasia.viewmodels.PictureViewModel;

/**
 * Fragment for cats feed
 */
public class FeedFragment extends Fragment {
    private PictureViewModel pictureViewModel;
    private FeedViewModel feedViewModel;
    private MainAdapter adapter;
    private int page = 1;

    private PicturesAdapterDelegate.Callback callbackPicture = new PicturesAdapterDelegate.Callback() {
        @Override
        public void onItemClick(@NonNull Item item) {
            pictureViewModel.select(item);
        }

        @Override
        public void loadPicture(@NonNull String url, @NonNull String thumbUrl) {
            //glide load picture
        }
    };

    private GifAdapterDelegate.Callback callbackGif = new GifAdapterDelegate.Callback() {
        @Override
        public void onItemClick(@NonNull Item item) {
            pictureViewModel.select(item);
        }

        @Override
        public void loadGif(@NonNull String url, @NonNull String preview) {
            //glide load gif
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pictureViewModel = ViewModelProviders.of(getActivity()).get(PictureViewModel.class);
        feedViewModel = ViewModelProviders.of(getActivity()).get(FeedViewModel.class);
        feedViewModel.getFeedLiveData(page).observe(this, listResult -> {
            adapter = new MainAdapter(listResult);
        });
        adapter.addDelegate(PictureItem.VIEW_TYPE, new PicturesAdapterDelegate(callbackPicture));
        adapter.addDelegate(GifItem.VIEW_TYPE, new GifAdapterDelegate(callbackGif));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }
}
