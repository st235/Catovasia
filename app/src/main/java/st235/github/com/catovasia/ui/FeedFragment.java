package st235.github.com.catovasia.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import st235.github.com.catovasia.R;
import st235.github.com.catovasia.viewmodels.FeedViewModel;
import st235.github.com.catovasia.viewmodels.PictureViewModel;

public class FeedFragment extends Fragment {

    private PictureViewModel pictureViewModel;
    private FeedViewModel feedViewModel;
    private int page;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pictureViewModel = ViewModelProviders.of(getActivity()).get(PictureViewModel.class);
        feedViewModel = ViewModelProviders.of(getActivity()).get(FeedViewModel.class);
        feedViewModel.getFeedLiveData(page).observe(this, listResult -> {
            //show list of cats
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    public void onItemClickListener(FeedItem item){
        pictureViewModel.select(item);
    }
}
