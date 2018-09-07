package temp.home.hometemp.di.modules;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class EntryModule {

    @Provides
    public Entry provideEntry() {
        return new Entry();
    }

    @Provides
    public List<Entry> providesListEntry(){
        return new ArrayList<Entry>();
    }

}
