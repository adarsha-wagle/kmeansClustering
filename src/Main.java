import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int k,num;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter no. of cluster: ");
        k = scan.nextInt();
        System.out.println("Data Range: ");
        num = scan.nextInt();
       Kmeans kMeans = new Kmeans(k,num);//Initialization of data and centroid
     kMeans.doClustering();
    }
}