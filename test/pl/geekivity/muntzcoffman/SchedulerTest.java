/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.geekivity.muntzcoffman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dave
 */
public class SchedulerTest {
    private int[][] matrix;
    private int[] times;
    
    public SchedulerTest() {
        matrix = new int[12][12];
        times = new int[12];
        
        Scanner in = null;
        try {
            in = new Scanner(new FileReader("tasks.txt"));
        } catch (FileNotFoundException ex) {
           
        }
        
        for(int i = 0; i < 12; ++i)
            for(int j = 0; j < 12; ++j)
                matrix[i][j] = in.nextInt();

        for(int i = 0; i < 12; ++i)
            times[i] = in.nextInt();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testTaskLoading(){
        Scheduler sched = new Scheduler(matrix, times, 2);
        assertEquals(25, sched.getTask(0).getLevel());
        assertEquals(14, sched.getTask(4).getLevel());
        assertEquals(21, sched.getTask(2).getLevel());
    }
    
    @Test
    public void testSchedulerRunning(){
        Scheduler sched = new Scheduler(matrix, times, 2);
        
        sched.next();
        
        assertEquals(24, sched.getTask(0).getLevel());
        assertEquals(6, sched.getTask(1).getLevel());
        
        while(sched.hasNextTask())
            sched.next();
    }
}
