package testing;

import javafx.util.Pair;
import math.DiscreteCoordinate;
import pathFinders.Group;

import java.util.PriorityQueue;

public class PriorityQueueTest
{
    public static void main(String[] args)
    {
        PriorityQueue<Group<DiscreteCoordinate, Double>> queue = new PriorityQueue<>();

        Group<DiscreteCoordinate, Double> a = new Group(new DiscreteCoordinate(0,0), new Double(10));
        Group<DiscreteCoordinate, Double> b = new Group(new DiscreteCoordinate(0,0), new Double(1));
        Group<DiscreteCoordinate, Double> c = new Group(new DiscreteCoordinate(5,5), new Double(10));
        DiscreteCoordinate d = new DiscreteCoordinate(0,0);


        queue.add(a);
        System.out.println("!!!!" + queue.contains(b));
        System.out.println("!!!!" + a.equals(b));
        System.out.println("!!!!" + a.equals(d));
        System.out.println("!!!!" + queue.contains(DiscreteCoordinateConverter(d)));

    }

    public static Group<DiscreteCoordinate, Double> DiscreteCoordinateConverter(DiscreteCoordinate c)
    {
        return new Group<DiscreteCoordinate,Double>(c,null);
    }
}
