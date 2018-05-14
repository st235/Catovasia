package st235.github.com.catovasia.data;

import android.content.res.AssetManager;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import st235.github.com.catovasia.R;
import st235.github.com.catovasia.data.assets.AssetFile;
import st235.github.com.catovasia.data.assets.AssetFolder;
import st235.github.com.catovasia.data.assets.AssetsProvider;
import st235.github.com.catovasia.utils.ThreadUtils;

@Singleton
public class ResourceRepository {

    @NonNull
    private final AssetManager assetManager;

    @NonNull
    private final AssetsProvider assetsProvider;

    @NonNull
    private final ThreadUtils threadUtils;

    @Inject
    ResourceRepository(@NonNull AssetManager assetManager,
                       @NonNull AssetsProvider assetsProvider,
                       @NonNull ThreadUtils threadUtils) {
        this.assetManager = assetManager;
        this.assetsProvider = assetsProvider;
        this.threadUtils = threadUtils;
    }

    @MainThread
    public void obtainCatNames(@NonNull final ThreadUtils.Callback<List<String>> callback) {
        ThreadUtils.MainToWorkerMarshaller<List<String>> marshaller
                = threadUtils.createMainToWorkerMarshaller(() -> {
            AssetFolder namesFolder = assetsProvider.provide("names").get(0);
            AssetFile file = namesFolder.find("default");

            if (file == null) {
                throw new IllegalStateException("File cannot be null");
            }

            String path = String.format(Locale.US, "%1$s/%2$s", namesFolder.getName(),
                    file.getFileName());

            List<String> names = new ArrayList<>();
            try (BufferedReader br =
                         new BufferedReader(new InputStreamReader(assetManager.open(path)))) {
                String line;
                while ((line = br.readLine()) != null) {
                    names.add(line);
                }
            }

            return names;
        }, callback);
        marshaller.start();
    }
}
