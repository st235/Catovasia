// Copyright © 2018 by Alexander Dadukin (st235@yandex.ru)
// All rights reserved.

package st235.github.com.catovasia.data.assets;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AssetFolder {

    @NonNull
    private final String name;

    @NonNull
    private final List<AssetFile> assetFiles;

    public AssetFolder(@NonNull String name) {
        this.name = name;
        assetFiles = new ArrayList<>();
    }

    public void addFile(@NonNull String folder,
                        @NonNull String fileName) {
        Uri uri = getUriFromAssets(folder, fileName);
        this.assetFiles.add(new AssetFile(uri, fileName));
    }

    @Nullable
    public AssetFile find(@NonNull String fileName) {
        for (AssetFile file: assetFiles) {
            if (file.getFileName().contains(fileName)) return file;
        }

        return null;
    }

    private Uri getUriFromAssets(@NonNull String folder,
                                 @NonNull String fileName) {
        String file = String.format(Locale.US, "%1$s%2$s%3$s", folder, File.separator, fileName);
        String path = String.format(Locale.US, "file:///android_asset/%1$s", file);
        return Uri.parse(path);
    }

    @NonNull
    public String getName() {
        return name;
    }

    public List<AssetFile> getAssetFiles() {
        return assetFiles;
    }

    public AssetFile getFolderPreview() {
        return assetFiles.get(0);
    }

    public int size() {
        return assetFiles.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AssetFolder{");
        sb.append("assetFiles=").append(assetFiles);
        sb.append('}');
        return sb.toString();
    }
}
