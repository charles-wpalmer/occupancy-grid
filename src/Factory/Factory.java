package Factory;

import AI.AStar;

import java.util.Objects;

/*
 * Class to load Objects.
 * Implements Interface Factory
 * Created by charles on 24/03/17.
 */
public abstract class Factory{

    public static Factory getInstance(String type) {

        Factory factory = null;

        switch(type){
            case "astar":
                factory = new AStarFactory();
                break;
            case "bfs":
                factory = new AStarFactory();
                break;
        }

        return factory;
    }

    public abstract AStar createSearcher();
}
