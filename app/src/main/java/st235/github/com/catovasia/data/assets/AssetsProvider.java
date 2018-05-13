// Copyright © 2018 by Alexander Dadukin (st235@yandex.ru)
// All rights reserved.

package st235.github.com.catovasia.data.assets;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetsProvider {

    private static final String ROOT = "";

    private AssetManager assetManager;
    private Map<String, List<AssetFolder>> foldersMap = new HashMap<>();

    public AssetsProvider(@NonNull AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @WorkerThread
    public List<AssetFolder> provide(@NonNull String mask) {
        if (isEmpty(mask)) {
            List<AssetFolder> folders = new ArrayList<>();
            foldersMap.put(mask, folders);
            load(mask, folders);
        }

        return foldersMap.get(mask);
    }

    private boolean isEmpty(@NonNull String key) {
        return !foldersMap.containsKey(key) ||
                foldersMap.get(key).isEmpty();
    }

    private void load(@NonNull String mask,
                      @NonNull List<AssetFolder> folders) {
        if (folders.size() > 0) return;

        try {
            String[] assetsFolders = assetManager.list(ROOT);
            for (String folder: assetsFolders) addFolder(folder, mask, folders);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void addFolder(@NonNull String path,
                           @NonNull String mask,
                           @NonNull List<AssetFolder> folders) throws IOException {
        if (!path.contains(mask)) return;

        String[] files = assetManager.list(path);

        AssetFolder folder = obtainFolder(path, files);
        folders.add(folder);
    }

    private AssetFolder obtainFolder(@NonNull String folder,
                                     @NonNull String[] files) {
        AssetFolder assetFolder = new AssetFolder(folder);

        for (String file: files) {
            assetFolder.addFile(folder, file);
        }

        return assetFolder;
    }
}
