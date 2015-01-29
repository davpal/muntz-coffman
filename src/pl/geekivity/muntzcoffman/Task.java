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
    private int time, elapsedTime;
    private int level;
    
    public Task(int n, int t){
        super(n);
        level = elapsedTime = time = t;
    }
    
    public boolean isDone(){
        return elapsedTime == 0;
    }
    
    public boolean isBlocked(){
        return !parents.isEmpty();
    }
    
    public void execute(int cpuTime) {
        elapsedTime -= cpuTime;
        level -= cpuTime;
        if(isDone()){
            Iterator it = children.iterator();
            while(it.hasNext()){
                Task task = (Task)it.next();
                task.removeParent(this);
            }
        }
    }
    
    public int getLevel() {
        return level;
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
       return level - ((Task)o).getLevel();
    }
}
