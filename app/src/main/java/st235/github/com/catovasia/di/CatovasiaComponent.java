package st235.github.com.catovasia.di;

import javax.inject.Singleton;

import dagger.Component;
import st235.github.com.catovasia.ui.MainActivity;

@Singleton
@Component(modules = {DataModule.class, UtilsModule.class})
public interface CatovasiaComponent {
    void inject(MainActivity activity);
}
