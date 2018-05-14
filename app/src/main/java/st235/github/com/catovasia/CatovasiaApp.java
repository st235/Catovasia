package st235.github.com.catovasia;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import st235.github.com.catovasia.di.CatovasiaComponent;
import st235.github.com.catovasia.di.DiProvider;

public class CatovasiaApp extends Application {
    private CatovasiaComponent catovasiaComponent;

    @NonNull
    public static CatovasiaApp get(@NonNull Context context) {
        return (CatovasiaApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        catovasiaComponent = DiProvider.obtainApplicationComponent(this);
    }

    @NonNull
    public CatovasiaComponent getCatovasiaComponent() {
        return catovasiaComponent;
    }
}
