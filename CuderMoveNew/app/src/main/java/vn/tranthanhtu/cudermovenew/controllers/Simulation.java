package vn.tranthanhtu.cudermovenew.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import vn.tranthanhtu.cudermovenew.models.Constants;
import vn.tranthanhtu.cudermovenew.models.MapModel;
import vn.tranthanhtu.cudermovenew.views.MainActivity;

public class Simulation {

    // so dinh cua do thi
    private int N;

    // ma tran ke cua do thi
    private int[][] G;
    // mang luu ket qua duong di
    private int[] tracePath;

    public Simulation() {
        // building a Graph
        // setupGraph();
    }

    // perform a BFS starting at node start
    public void performBFS(int start, int finish) {
        // a visited array to mark which vertices
        // have been visited while doing the BFS
        boolean[] V = new boolean[N];

        // A queue to process nodes
        Queue<Integer> Q = new LinkedList<>();

        // start with only the start node in the queue and mark it as visited
        Q.offer(start);
        V[start] = true;

        // continue searching the graph while still nodes in the queue
        while (!Q.isEmpty()) {
            int at = Q.poll(); // get the head of the queue
//            System.out.printf("At node %d in the BFS%n", at);

            // add any unseen nodes to the queue to process, then mark them as
            // visited so they don't get re-added
            for (int i = 0; i < N; ++i) {
                if (G[at][i] == 1 && !V[i]) {
                    Q.offer(i);
                    V[i] = true;
                    tracePath[i] = at;
//                    System.out.printf("Adding node %d to the queue in the BFS%n", i);
                }
            }

//            System.out.printf("Done processing node %d%n", at);
        }

//        System.out.printf("Finished with the BFS from start node %d%n", start);

        // print path from node start to node finish
        printPath(start, finish, tracePath);
    }

    private void printPath(int u, int v, int back[]) {
        if (u == v) {
//            System.out.printf("%d << ", v);
            MainActivity.listStepMovePosition.add(v);
        } else {
            if (back[v] == -1) {
//                System.out.printf("No way from node %d to node %d%n", u, v);
            } else {
                printPath(u, back[v], back);
//                System.out.printf("%d << ", v);
                MainActivity.listStepMovePosition.add(v);
            }
        }
    }

    public void cuderGraph() {
        int size = Constants.SPAN_COUNT;
        N = size * size;
        G = new int[N][N];
        // init normal node
        for (int i = 0; i < N; i++) {
            if (i + 1 < N && (i + 1) % size != 0) {
                G[i][i + 1] = G[i + 1][i] = 1;
            }
            if (i + size < N) {
                G[i][i + size] = G[i + size][i] = 1;
            }
        }

        ArrayList<Integer> impediment = new ArrayList<>();

        for (int j = 0; j < MapModel.list.size(); j++ ){
            if (MapModel.list.get(j).getSquare().equals(Constants.IMPEDIMENT)){
                impediment.add(j);
            }
        }
//        Log.d(TAG, String.format("onCreate: %s", impediment));
        for (int i = 0; i < N; i++) {
            for (int j : impediment){
                G[i][j] = G[j][i] = 0;
            }
        }

        // initialize data for tracking route when searching process is finished
        tracePath = new int[N];
        for (int i = 0; i < N; ++i) {
            tracePath[i] = -1;
        }
    }

    public void printGraph() {
//        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
//                System.out.printf("%d ", G[i][j]);
            }
//            System.out.println();
        }
    }

}