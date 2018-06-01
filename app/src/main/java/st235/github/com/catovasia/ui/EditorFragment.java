package st235.github.com.catovasia.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import st235.github.com.catovasia.R;
import st235.github.com.catovasia.viewmodels.PictureViewModel;

/**
 * Fragment to show picture with ability to edit it
 */
public class EditorFragment extends Fragment {
    private PictureViewModel pictureViewModel;

    public static EditorFragment newInstance() {
        return new EditorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pictureViewModel = ViewModelProviders.of(getActivity()).get(PictureViewModel.class);
        pictureViewModel.getSelectedItem().observe(this, item -> {
           //show picture
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editor, container, false);
    }
}
