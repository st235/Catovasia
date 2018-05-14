package st235.github.com.catovasia.di;

import android.content.Context;
import android.support.annotation.NonNull;

public class DiProvider {

    public static CatovasiaComponent obtainApplicationComponent(@NonNull Context context) {
        return DaggerCatovasiaComponent.builder()
                .dataModule(new DataModule(context))
                .build();
    }
}
