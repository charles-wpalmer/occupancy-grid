package Factory;

import java.util.Objects;

/**
 * Interface for the factory
 * Created by charles on 23/03/17.
 */
public interface IFactory {

    public void register(String type);

    public void registerSingleton(Object type);

    public Object getInstance(String type);
}
