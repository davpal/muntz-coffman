/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.geekivity.muntzcoffman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author dave
 */
public class Main {
    public static void main(String[] args){
        if(args.length == 0) return;
        
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(args[0]));
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        
        int n = in.nextInt();
        int m = in.nextInt();
        
        int[][] matrix = new int[n][n];
        double[] times = new double[n];
        
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j)
                matrix[i][j] = in.nextInt();

        for(int i = 0; i < n; ++i)
            times[i] = in.nextInt();
        
        Scheduler sched = new Scheduler(matrix, times, m);
        
        while(sched.hasNextTask())
            sched.next();
    }
}
