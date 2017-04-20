package AI;

import robot.*;
/**
 * Interface for A* search.
 * Created by charles on 21/03/17.
 */
public interface ISearcher {

    public Node start(Robot r);

    public void finalise();

    public void playSolution();

    public int calculateCost(Node grid);
}
