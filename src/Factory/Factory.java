package Factory;

import java.util.Objects;

/*
 * Class to load Objects.
 * Implements Interface Factory
 * Created by charles on 24/03/17.
 */
public class Factory implements IFactory {


    @Override
    public void register(String type) {

    }

    @Override
    public void registerSingleton(Object type) {

    }

    @Override
    public Object getInstance(String type) {
        return null;
    }
}
