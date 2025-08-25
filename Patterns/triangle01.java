import java.util.*;
public class triangle01 
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
            for(j=1;j<=i;j++)
            {
                int sum=i+j;
                if(sum %2==0)
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                }
            }
            System.out.println(" ");
        }
    }
    
}
