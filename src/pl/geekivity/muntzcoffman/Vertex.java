/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.geekivity.muntzcoffman;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dave
 */
public class Vertex {
    List<Vertex> children = new ArrayList<Vertex>();
    int number;
    
    public Vertex(int n){
        super();
        number = n;
    }
    
    List<Vertex> parents = new ArrayList<Vertex>();
    
    public void addParent(Vertex t){
        parents.add(t);
    }
    
    public void addChild(Vertex v){
        v.addParent(this);
        children.add(v);
    }
    
    public List<Vertex> getChildren(){
        return children;
    }
    
    public String toString(){
        return Integer.toString(number);
    }
}
