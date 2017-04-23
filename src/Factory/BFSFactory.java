package Factory;

import AI.AStar;
import AI.BFS;
import AI.ISearcher;

/**
 * Factory Loader for bfs algorithms
 * Created by charles on 21/04/17.
 */
public class BFSFactory extends Factory {

    @Override
    public ISearcher createSearcher() {
        return new BFS();
    }
}
