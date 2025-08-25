import java.util.*;
public class inverted_nos 
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
            for(j=1;j<=n-i+1;j++)
            {
                System.out.print(j+" ");
            }
            System.out.println(" ");
        }
    }
}   

