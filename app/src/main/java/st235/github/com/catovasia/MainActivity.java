// Copyright (c) 2018 by Alexander Dadukin (st235@yandex.ru)
// All rights reserved.

package st235.github.com.catovasia;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import st235.github.com.catovasia.data.NetRepository;
import st235.github.com.catovasia.data.ResourceRepository;
import st235.github.com.catovasia.utils.ThreadUtils;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "[Catovasia] MainActivity";

    @Inject
    NetRepository netRepository;

    @Inject
    ResourceRepository resourceRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CatovasiaApp.get(getApplicationContext())
                .getCatovasiaComponent()
                .inject(this);

        resourceRepository.obtainCatNames()
                .start(new ThreadUtils.Callback<List<String>>() {
                    @Override
                    public void onReady(@NonNull List<String> strings) {
                        Log.d(TAG, String.valueOf(strings));
                    }

                    @Override
                    public void onError(@NonNull Exception e) {
                        Log.e(TAG, "There was an error", e);
                    }
                });
    }
}
