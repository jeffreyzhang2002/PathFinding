package pathFinders;

import javafx.util.Pair;
import math.DiscreteCoordinate;

import java.util.Comparator;

public class Group<K,V extends Comparable<V>> extends Pair<K,V> implements Comparable<Pair<K,V>>
{
    public Group(K key, V value)
    {
        super(key, value);
    }

    public int compareTo(Pair<K,V> other)
    {
        return super.getValue().compareTo(other.getValue());
    }

    public boolean equals(Object o)
    {
        if(o instanceof Group)
            return super.getKey().equals(((Group)o).getKey());
        return false;
    }

    public int hashCode()
    {
        return 1;
    }

    public String toString()
    {
        return "<" + super.getKey().toString() + "," + super.getValue().toString() + ">";
    }
}
