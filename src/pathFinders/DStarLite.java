package pathFinders;

import grid.Field;
import math.DiscreteCoordinate;
import org.omg.PortableInterceptor.DISCARDING;

import java.util.*;
import java.util.stream.Collectors;

public class DStarLite extends PathFinder
{
    HashMap<DiscreteCoordinate,double[]> score;  // Score (RHS,G)
    PriorityQueue<Group<DiscreteCoordinate, Keys>> openGroup;

    public DStarLite(Field field)
    {
        super(field);
    }

    public DStarLite(Field field, DiscreteCoordinate start, DiscreteCoordinate end)
    {
        super(field,start,end);
    }

    public ArrayList<DiscreteCoordinate> genPath (boolean containCorners)
    {
        score = new HashMap<>();
        openGroup = new PriorityQueue<>();
        init();
        while(openGroup.peek().getValue().primaryKey < calculateKeys(super.getStart())[0] || score.get(super.getStart())[0] != score.get(super.getStart())[1] )
        {
            Group<DiscreteCoordinate,Keys> top = openGroup.poll();
            double[] currentScore = score.get(top.getKey());

            HashSet<DiscreteCoordinate> neighbors = super.getField().getEmptyNeighboringCoordinates(top.getKey(), containCorners);

            if(currentScore[1] > currentScore[0])
            {
                currentScore[1] = currentScore[0];
                for(DiscreteCoordinate current: neighbors)
                    updateVertex(current, containCorners, currentScore);
            } else {
                currentScore[1] = Double.MAX_VALUE;
                for(DiscreteCoordinate current: neighbors)
                    if (openGroup.contains(discreteCoordinateConverter(current)))
                        updateVertex(current, containCorners, currentScore);

            }
        }
        return rebuildPath(containCorners);
    }

    public void updateVertex(DiscreteCoordinate current, boolean containCorners, double[] currentScore)
    {
        if (current != super.getEnd())
        {
            // get all neighbors of the current value
            HashSet<DiscreteCoordinate> neighbors = super.getField().getEmptyNeighboringCoordinates(current, containCorners);


            // find the value with least connection cost + g cost
            double min = Double.MAX_VALUE;
            for (DiscreteCoordinate neighbor : neighbors)
            {
                double sum = heuristic(current, neighbor) + score.get(neighbor)[1];
                if (sum < min)
                    min = sum;
            }

            // set the RHS value to the min
            currentScore[0] = min;
            System.out.println(currentScore[0]);
        }

        openGroup.remove(discreteCoordinateConverter(current));

        if (currentScore[1] != currentScore[0])
            openGroup.add(new Group(current, new Keys(calculateKeys(current))));
    }

    private Group<DiscreteCoordinate, Keys> discreteCoordinateConverter(DiscreteCoordinate c)
    {
        return new Group<DiscreteCoordinate, Keys>(c,null);
    }

    private void init()
    {
        //set all position in the field with a max value
        for(int i = 0; i < super.getField().getRows(); i++)
            for(int j = 0; j < super.getField().getCols(); j++)
                score.put(new DiscreteCoordinate(i, j), new double[] {Double.MAX_VALUE, Double.MAX_VALUE });

        // set the RHS value of the End to 0
        score.get(super.getEnd())[0] = 0;

        // Add End to the open Group and calculate is keys
        openGroup.add(new Group(super.getEnd(), new Keys(calculateKeys(super.getEnd()))));
    }

    private double[] calculateKeys(DiscreteCoordinate current)
    {
        //calculate the Keys
        double[] currentScore = score.get(current);
        return new double[]{Math.min(currentScore[0], currentScore[1]) + heuristic(super.getStart(), current), Math.min(currentScore[0],currentScore[1])};
    }

    private ArrayList<DiscreteCoordinate> rebuildPath(boolean containCorners)
    {

        ArrayList<DiscreteCoordinate> path = new ArrayList<>();
        DiscreteCoordinate current = super.getStart();

        if(current == null)
            System.out.println("start == null");

        while(current != super.getEnd()) {
            path.add(current);
            HashSet<DiscreteCoordinate> neighbors = super.getField().getNeighborCoordinates(current, containCorners);
            double min = Double.MAX_VALUE;
            DiscreteCoordinate minCoordinate = null;
            for (DiscreteCoordinate n : neighbors)
            {
                double t = score.get(n)[0];
                System.out.println(t);
                if (t < min) {
                    min = t;
                    minCoordinate = n;
                }
            }
            current = minCoordinate;
        }
        System.out.println("made it again");
        return path;
    }

    private double heuristic(DiscreteCoordinate a, DiscreteCoordinate b)
    { return a.distance(b); }


    private class Keys implements Comparable<Keys>
    {
        private double primaryKey, secondaryKey;

        public Keys(double primaryKey, double secondaryKey)
        {
            this.primaryKey = primaryKey;
            this.secondaryKey = secondaryKey;
        }

        public Keys(double[]keys)
        {
            this.primaryKey = keys[0];
            this.secondaryKey = keys[1];
        }

        public double getPrimaryKey()
        {
            return primaryKey;
        }

        public double getSecondaryKey()
        {
            return secondaryKey;
        }

        public double[] getKeys()
        {
            return new double[]{primaryKey,secondaryKey};
        }

        public int compareTo(Keys other)
        {
            Double otherKey = new Double(other.getPrimaryKey());
            return otherKey.compareTo(primaryKey);
        }

        public String toString()
        {
            return "Primary Key: " + primaryKey + " Secondary Key:" + secondaryKey + "]";
        }
    }
}
