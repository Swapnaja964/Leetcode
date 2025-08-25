package HashSet;
import java.util.HashSet;
import java.util.Iterator;

public class Hashing 
{
    public static void main(String args[])
    {
        //Creating
        HashSet<Integer> set = new HashSet<>();

        //Insert
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);

        //size
        System.out.println("Set of size: "+ set.size());

        //Print all elements
        System.out.println(set);

        //Search - contains
        if(set.contains(1))
        {
            System.out.println("set contains 1");
        }
        if(!set.contains(6))
        {
            System.out.println("set does not contain");
        }

        //Delete
        //set.remove(1);
        //if(!set.contains(1))
        //{
        //    System.out.println("does not contain 1 - we deleted 1");
        //}

        //Iterator
        //for(int i=0; i<n; i++)

        Iterator it = set.iterator();
        //hasNext

        while(it.hasNext())
        {
            System.out.println(it.next());
        }

    }
}
