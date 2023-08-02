import java.util.Arrays;
import java.util.Random;

public class Kmeans {
    int num;//number of data points
    int k;//number of clusters
    int x[], y[];//data points
    double meanX[], meanY[];//current cluster centers

    private final int MAXITER = 20;
    private int iteration = 0;
    int cAssign[];
    int prevCAssign[];
    double distance[] ;

    Random random;

    public Kmeans(int k, int num)
    {
        this.k = k;
        this.num = num;
        random = new Random();
        x = new int[num];
        y = new int [num];
        meanX = new double[k];
        meanY = new double[k];
        distance = new double[k];
        cAssign = new int[num];
        prevCAssign = new int[num];
//
//      x[0] = 2;
//      y[0] = 10;
//
//      x[1] = 2;
//      y[1] = 5;
//
//       x[2] = 8;
//      y[2] = 4;
//
//      x[3] = 5;
//      y[3] = 8;
//
//      x[4] = 7;
//      y[4] = 5;
//
//      x[5] = 6;
//      y[5] = 4;
//
//       x[6] = 1;
//      y[6] = 2;
//
//      x[7] = 4;
//      y[7] = 9;

// INITIALIZE X && Y
        for (int i = 0;i<num;i++)
         {

             x[i]= randomData();
             y[i] = randomData();
          }
// INITIALIZE INITIAL CENTROID
        for (int i = 0;i<k;i++)
        {
            int data = randomMean();
           meanX[i] = x[data];
           meanY[i] = y[data];

        }

//        meanX[0] = x[0];
//        meanY[0] = y[0];
//
//        meanX[1] = x[3];
//        meanY[1] = y[3];
//
//        meanX[2] = x[6];
//        meanY[2] = y[6];
        System.out.println("Initial Centriod: ");
printCentroid();
    }

    public void doClustering()
    {

        do {
            assignCluster();
            updateMeans();
            iteration++;
        }while(isDifferent() || iteration > MAXITER);

        System.out.println("Data: ");
        printData();
        System.out.println("\nFinal Centroid: ");
        printCentroid();
        System.out.println("\nFinal Cluster: ");
        printCluster();
        System.out.println("\nNo of iteration is "+iteration);
    }

    public void assignCluster()
    {
        //COPYING PREVIOUS CASSIGN VALUE
        prevCAssign = cAssign.clone();

        //CALCULATING DISTANCE BETWEEN CENTROID AND DATA AND STORING AS ARRAY
        for (int j = 0;j<num;j++)
        {
            for (int i = 0;i<k;i++)
            {
                    distance[i] = Math.sqrt(Math.pow(meanX[i] - x[j],2) + Math.pow(meanY[i] - y[j],2));
            }
            //ASSIGNING MINIMUM DISTANCE INDEX TO CASSIGN
           double min = distance[0];
            for(int k = 0;k<distance.length;k++)
            {
               if(min>=distance[k])
               {
                   min = distance[k];
                   cAssign[j] = k;
               }
            }

        }
    }

    public void updateMeans()
    {
        int[] count= new int[k];//stores no. of data that are in same cluster (cAssign)
//        int count0 = 0;
//        int count1 = 0;
//        int count2 = 0;

        // ASSIGNING ZERO TO CENTROID TO CALCULATE SUM
        for (int i = 0; i<k;i++)
        {
            meanX[i] = 0;
            meanY[i] = 0;
        }
        //ADDING DATA TO CENTROID THAT HAS SAME CASSIGN VALUE
        for (int i = 0;i<num;i++)
        {
            for (int j = 0;j<k;j++) {


                if (cAssign[i] == j) {
                    meanX[j] += x[i];
                    meanY[j] += y[i];
                    count[j]++;
                }
            }
        }
        //DIVIDING CENTROID BY COUNT
        for (int i = 0; i<k;i++)
        {
            meanX[i]  = meanX[i]/count[i];
            meanY[i]  = meanY[i]/count[i];
        }
//        meanX[0] = meanX[0]/count0;
//        meanY[0] = meanY[0]/count0;
//
//        meanX[1] = meanX[1]/count1;
//        meanY[1] = meanY[1]/count1;
//
//        meanX[2] = meanX[2]/count2;
//        meanY[2] = meanY[2]/count2;
    }


    boolean isDifferent()
    {
//RETURNS FALSE IF PREVCASSIGN AND CURRENT CASSIGN MATCHES ELSE RETURNS TRUE
        if(Arrays.equals(cAssign,prevCAssign))
        {
            return false;
        }
        return true;
    }
    public int randomData()
    {

        return random.nextInt(num);
    }
    public int randomMean()
    {
        return random.nextInt(num);
    }
    public void printCentroid()
    {
        for (int i = 0;i<k;i++)
        {
            System.out.print("meanX["+i+"] -> "+ meanX[i]);
            System.out.println("\tmeanY["+i+"] -> "+ meanY[i]);
        }
    }
    public void printData()
    {
        for (int i = 0;i<num;i++)
        {
            System.out.print("x["+i+"] -> "+ x[i]);
            System.out.println("\ty["+i+"] ->"+ y[i]);
        }
    }
    public void printCluster()
    {
        for (int i = 0;i<num;i++)
        {
            System.out.println(cAssign[i]);
        }
    }

}
