import robot.*;
/**
 * Interface for A* search.
 * Created by charles on 21/03/17.
 */
public interface IAStar {

    public void expandAll(Grid grid, int depth);

    public void finalise();

    public void playSolution();

    public int calculateCost();
}
