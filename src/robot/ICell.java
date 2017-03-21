package robot;

/**
 * Interface for the robot.Cell class
 * Created by charles on 07/03/17.
 */
public interface ICell {
    public void setLeft(ICell left);

    public ICell getLeft();

    public void setRight(ICell right);

    public ICell getRight();
}
