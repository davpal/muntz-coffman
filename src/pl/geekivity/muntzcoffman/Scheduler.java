/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.geekivity.muntzcoffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author dave
 */
class Scheduler {
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Task> running = new ArrayList<>();
    int time, cpuNumber, idleCpus;

    Scheduler(int[][] matrix, int[] times, int cpus) {
        idleCpus = cpuNumber = cpus;
        
        for(int i = 0; i < times.length; ++i)
            tasks.add(new Task(i, times[i]));
        
        for(int i = 0; i < matrix.length; ++i){
            for(int j = 0; j < matrix[i].length; ++j){
                if(matrix[i][j] > 0) tasks.get(i).addChild(tasks.get(j));
            }     
        }
        
        tasks.get(0).calculateLevel();
    }
    
    boolean hasNextTask(){
        return !tasks.isEmpty();
    }

    void next(){
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task a, Task b) {
                if(b.isBlocked() || b.isDone()) return -1;
                if(a.isBlocked() || a.isDone()) return 1;
                return (int)(b.getLevel() - a.getLevel());
            }
        });
        
        int i = 0;
        System.out.print(time + ": Executing tasks: ");
        while(idleCpus > 0){
            System.out.print(tasks.get(i).toString());
            tasks.get(i).execute(1);
            --idleCpus;
            if(tasks.get(i).isDone()){
                System.out.print("* ");
                tasks.remove(i);
                continue;
            } else System.out.print(" ");
            ++i;
        }
        
        idleCpus = cpuNumber;
        ++time;
        System.out.println();
    }

    Task getTask(int i){
        return tasks.get(i);
    }
}
