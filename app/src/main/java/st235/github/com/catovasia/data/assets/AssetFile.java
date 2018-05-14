// Copyright © 2018 by Alexander Dadukin (st235@yandex.ru)
// All rights reserved.

package st235.github.com.catovasia.data.assets;

import android.net.Uri;
import android.support.annotation.NonNull;

public class AssetFile {

    @NonNull
    private final Uri uri;

    @NonNull
    private final String fileName;

    public AssetFile(@NonNull Uri uri,
                     @NonNull String fileName) {
        this.uri = uri;
        this.fileName = fileName;
    }

    @NonNull
    public Uri getUri() {
        return uri;
    }

    @NonNull
    public String getFileName() {
        return fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetFile assetFile = (AssetFile) o;

        return uri != null ? uri.equals(assetFile.uri) : assetFile.uri == null;
    }

    @Override
    public int hashCode() {
        return uri != null ? uri.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AssetFile{");
        sb.append("uri=").append(uri);
        sb.append('}');
        return sb.toString();
    }
}
