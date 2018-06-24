package st235.github.com.catovasia.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.List;

import st235.github.com.catovasia.R;
import st235.github.com.catovasia.data.net.Result;
import st235.github.com.catovasia.ui.adapter.state.DataState;
import st235.github.com.catovasia.ui.adapter.delegate.GifAdapterDelegate;
import st235.github.com.catovasia.ui.adapter.state.ErrorState;
import st235.github.com.catovasia.ui.adapter.delegate.MainAdapter;
import st235.github.com.catovasia.ui.adapter.delegate.PicturesAdapterDelegate;
import st235.github.com.catovasia.ui.items.GifItem;
import st235.github.com.catovasia.ui.items.Item;
import st235.github.com.catovasia.ui.items.PictureItem;
import st235.github.com.catovasia.viewmodels.FeedViewModel;
import st235.github.com.catovasia.viewmodels.PictureViewModel;

/**
 * Fragment for cats feed
 */
public class FeedFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = FeedFragment.class.getName();

    private PictureViewModel pictureViewModel;
    private EndlessListController controller;
    private FeedViewModel feedViewModel;
    private MainAdapter adapter;
    private int page = 1;

    private ConstraintLayout zeroData;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button tryAgain;

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
        controller = new EndlessListController(this);
        adapter = new MainAdapter();
        adapter.addDelegate(PictureItem.VIEW_TYPE, new PicturesAdapterDelegate(callbackPicture));
        adapter.addDelegate(GifItem.VIEW_TYPE, new GifAdapterDelegate(callbackGif));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progress);
        zeroData = view.findViewById(R.id.zerodata);
        tryAgain = view.findViewById(R.id.error_button);
        tryAgain.setOnClickListener(this);
        controller.onCreateView();
        return view;
    }

    public void showError(boolean show, @NonNull String message) {
        if (show) {
            zeroData.setVisibility(View.VISIBLE);
        }else {
            zeroData.setVisibility(View.GONE);
        }
        Log.e(TAG, message);
    }

    public void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void showProgressPage(boolean show) {
        if (show) {
            //show
        }else {
            //hide
        }
    }

    public void setData(List<Item> list) {
        adapter.setItems(list);
    }

    public void addData(List<Item> list) {
        adapter.addItems(list);
    }

    public void loadData() {
        feedViewModel.getFeedLiveData(page).observe(this, listResult -> {
            if (listResult != null) {
                if (listResult.getStatus() == Result.Status.ERROR) {
                    controller.setState(new ErrorState(controller));
                } else {
                    controller.putData(listResult.getData());
                    controller.setState(new DataState(controller));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.error_button) {
            //obtainData();
            controller.getState().getPage();
        }
    }

    @Override
    public void onDestroyView() {
        controller.onDestroyView();
        super.onDestroyView();
    }
}
