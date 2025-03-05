/**
 * Autor: Alexandru Gavril
 */

public class GraphGenerator {
    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Dati exact 2 valori.");
        }
        else{
            int n = Integer.parseInt(args[0]);
            int k = Integer.parseInt(args[1]);
            if(n>=2*k) {
                long startTime,endTime,eLapsedTime;
                startTime = System.currentTimeMillis();
                int[] cliqueNodes = new int[n];
                int[] stableSetNodes = new int[n];
                int[][] adj = new int[n][n];
                int m=0;
                int randomNum;
                int minDegree=0,maxDegree=0,degreeSum=0;
                int aux=0;
                for (int i = 0; i < n; i++) {
                    cliqueNodes[i] = 0;
                    stableSetNodes[i] = 0;
                }
                int ok1 = 0, ok2 = 0;
                while (ok1 != k) {
                    randomNum = (int) (Math.random() * n);
                    if (cliqueNodes[randomNum] == 0) {
                        ok1++;
                        cliqueNodes[randomNum] = 1;
                    }
                }
                while (ok2 != k) {
                    randomNum = (int) (Math.random() * n);
                    if (stableSetNodes[randomNum] == 0 && cliqueNodes[randomNum] == 0) {
                        ok2++;
                        stableSetNodes[randomNum] = 1;
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        adj[i][j] = 0;
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (cliqueNodes[i] == 1 && cliqueNodes[j] == 1 && i != j) {
                            if(adj[i][j]==0 && adj[j][i]==0) m++;
                            adj[i][j] = 1;
                        }
                        else if (stableSetNodes[i] == 1 && stableSetNodes[j] == 1 && i != j) adj[i][j] = 0;
                        else if (adj[i][j] == 0 && i != j) {
                            randomNum = (Math.random() < 0.5) ? 0 : 1;
                            if(randomNum==1) m++;
                            adj[i][j] = adj[j][i] = randomNum;
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    aux=0;
                    for (int j = 0; j < n; j++) {
                        if (adj[i][j] == 1) {
                            degreeSum++;
                            aux++;
                        }
                    }
                    if(i==0){
                        minDegree=aux;
                        maxDegree=aux;
                    }
                    else{
                        if(aux<minDegree) minDegree=aux;
                        else if(aux>maxDegree) maxDegree=aux;
                    }
                }
                if(n<=1000) printGraph(adj,n);
                System.out.println();
                System.out.print("Muchii: " + m);
                System.out.println();
                System.out.print("Δ(G): " + maxDegree);
                System.out.println();
                System.out.print("δ(G): " + minDegree);
                System.out.println();
                if(degreeSum == 2*m) System.out.print("Suma gradelor este egala cu 2*m");
                else System.out.print("Suma gradelor nu este egala cu 2*m");
                System.out.println();
                endTime = System.currentTimeMillis();
                eLapsedTime = endTime - startTime;
                System.out.println("Timp de rulare: " + eLapsedTime + " ms");
            }
            else {
                System.out.print("Nu e posibil sa realizam graful cu cerintele date.");
                 }
        }
    }
    public static void printGraph(int[][] graph, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
