/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.geekivity.muntzcoffman;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dave
 */
public class Task extends Vertex implements Comparable {
    private double timeLeft;
    private double level;
    
    public Task(int n, double t){
        super(n);
        level = timeLeft = t;
    }
    
    public boolean isDone(){
        return timeLeft < 0.000001;
    }
    
    public boolean isBlocked(){
        return !parents.isEmpty();
    }
    
    public void execute(double cpuTime) {
        timeLeft -= cpuTime;
        level -= cpuTime;
        if(isDone()){
            level = 0;
            Iterator it = children.iterator();
            while(it.hasNext()){
                Task task = (Task)it.next();
                task.removeParent(this);
            }
        }
    }
    
    public double getLevel() {
        return level;
    }
    
    public double getTime(){
        return timeLeft;
    }

    public void calculateLevel() {
        Iterator it = children.iterator();
        while(it.hasNext()){
            Task task = (Task)it.next();
            task.calculateLevel();
        }
        
        if(!children.isEmpty()){
            Task max = (Task)Collections.max(children,
                    new Comparator<Vertex>() {
                        @Override
                        public int compare(Vertex a, Vertex b){
                            return ((Task)a).compareTo((Task)b);
                        }
                    }
                );
            
            level += max.getLevel();
        }
    }
    
    @Override
    public int compareTo(Object o) {
       return (int)(level - ((Task)o).getLevel());
    }
}
