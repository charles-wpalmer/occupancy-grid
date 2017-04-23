package Factory;

import AI.AStar;
import AI.ISearcher;

/**
 * Factory to create AStar classes
 * Created by charles on 13/04/17.
 */
public class AStarFactory extends Factory {

    @Override
    public ISearcher createSearcher() {
        return new AStar();
    }
}
