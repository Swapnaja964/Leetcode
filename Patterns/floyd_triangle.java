import java.util.*;
public class floyd_triangle 
{
    public static void main(String args[])
    {
        int num=1,n,i,j;
        Scanner in= new Scanner(System.in);
        System.out.println("Enter the no. of rows required for the triangle");
        n=in.nextInt();
        //m=in.nextInt();

        for(i=1;i<=n;i++)
        {
            for(j=1;j<=i;j++)
            {
                System.out.print(num+" ");
                num++;
            }
            System.out.println(" ");
        }
    }
}   

