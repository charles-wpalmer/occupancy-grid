package Factory;

import AI.AStar;

/**
 * Factory to create AStar classes
 * Created by charles on 13/04/17.
 */
public class AStarFactory extends Factory {

    @Override
    public AStar createSearcher() {
        return new AStar();
    }
}
