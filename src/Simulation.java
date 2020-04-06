import actor.Barrier;
import actor.Robot;
import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;
import pathFinders.DStarLite;

public class Simulation
{
    public static void main(String[] args) {
        for (int z = 0; z < 3; z++) {
            int n = 1;
            double percent = n / 10.0;
            Field field = new Field(500, 500);
            DStarLite robot = new DStarLite(field, new DiscreteCoordinate(0,0), new DiscreteCoordinate(field.getRows() - 1, field.getCols() - 1));

            for (int count = 0; count < field.getRows() * field.getCols() * percent; count++) {
                Barrier wall = new Barrier(field, new DiscreteCoordinate
                        (random(0, field.getRows() - 1), random(0, field.getRows() - 1)));
                wall.placeSelfInGrid();
            }

            System.out.println(field.getRows() * field.getCols() * percent);

            long time = System.currentTimeMillis();
            robot.genPath(true);
            long newTime = System.currentTimeMillis();
            long deltaT = newTime - time;
            System.out.println(n + "-->" + deltaT + "       " + newTime + " - " + time);
            field = null;
            robot = null;
        }
    }


    public static int random(int min, int max)
    {
        return (int)(Math.random() * (max - min) + min);
    }
}
