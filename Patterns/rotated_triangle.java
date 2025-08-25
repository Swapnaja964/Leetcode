import java.util.*;

public class rotated_triangle
{
    public static void main(String args[])
    {
        int n,i,j;
        Scanner in= new Scanner(System.in);
        System.out.println("Enter the dimensions of the rectangle");
        n=in.nextInt();
        //m=in.nextInt();

        for(i=1;i<=n;i++)
        {
            for(j=1;j<=n-i;j++)
            {
                System.out.print(" ");
            }
            for(j=1;j<=i;j++)
            {
                System.out.print("*");
            }
            System.out.println(" ");
        }

    }
}