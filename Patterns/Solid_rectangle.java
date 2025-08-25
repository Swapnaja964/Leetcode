import java.util.*;

public class Solid_rectangle
{
    public static void main(String args[])
    {
        int m,n,i,j;
        Scanner in= new Scanner(System.in);
        System.out.println("Enter the dimensions of the rectangle");
        n=in.nextInt();
        m=in.nextInt();

        for(i=1;i<=n;i++)
        {
            System.out.println("*");
            for(j=1;j<=m;j++)
            {
                System.out.print("*");
            }
        }
        System.out.println(" ");

    }
}