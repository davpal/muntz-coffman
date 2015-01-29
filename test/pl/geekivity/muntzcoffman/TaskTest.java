package pl.geekivity.muntzcoffman;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class TaskTest {
    
    public TaskTest() {
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
    public void testTaskExecution(){
        Task t = new Task(0, 5);
        
        t.execute(2);
        t.execute(3);
        
        assertTrue(t.isDone());
    }
    
    @Test
    public void testTaskLevelCalculation(){
        Task task = new Task(0, 3);
        Task task1 = new Task(1, 4);
        Task task2 = new Task(2, 5);
        Task task3 = new Task(3, 6);
        
        task.addChild(task1);
        task1.addChild(task2);
        task1.addChild(task3);
        
        task.calculateLevel();
        
        assertTrue((13 - task.getLevel()) < 0.000001);
        assertTrue((10 - task.getLevel()) < 0.000001);
    }
    
    @Test
    public void testTaskLevelChange(){
        Task task = new Task(0, 3);
        Task task1 = new Task(1, 4);
        Task task2 = new Task(2, 5);
        Task task3 = new Task(3, 6);
        
        task.addChild(task1);
        task1.addChild(task2);
        task1.addChild(task3);
        
        task.calculateLevel();
        
        task.execute(4);
        
        assertTrue((9 - task.getLevel()) < 0.000001);
    }
    
    @Test
    public void testTaskBlocking(){
        Task task = new Task(0, 1);
        Task task1 = new Task(0, 2);
        
        task.addChild(task1);
        
        assertTrue(task1.isBlocked());
        assertFalse(task.isBlocked());
        
        task.execute(1);
        
        assertFalse(task1.isBlocked());
    }
    
    @Test
    public void testBetaRatio(){
        Task t = new Task(0, 5);
        double beta = 1.5;
        t.execute(beta);
        
        assertTrue(3.5 - t.getLevel() < 0.000001);
    }
}
