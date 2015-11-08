package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Shannor on 11/7/2015.
 */
public class PubTest {

    //Timebonus = 50
    //Test cases
    Pub pub = new Pub();

    private int test1 = pub.getTimeBonus(38); //x > 37 ,200
    private int test2 = pub.getTimeBonus(26); //25 < x =< 37 ,150
    private int test3 = pub.getTimeBonus(13); //12 < x =< 25 ,100
    private int test4 = pub.getTimeBonus(9); //x =< 12 , 50

    @Test
    public void testOver37() throws Exception {
        System.out.println("Check for above 38 seconds left.");
        assertEquals(200,test1);
    }

    @Test
    public void testOver24Less36() throws Exception {
        System.out.println("Check for above 25 seconds left.");
        assertEquals(150, test2);
    }

    @Test
    public void testOver12Less25() throws Exception {
        System.out.println("Check for above 12 seconds left.");
        assertEquals(100,test3);
    }
    @Test
    public void testLess12() throws Exception {
        System.out.println("Check for less than 12 seconds left.");
        assertEquals(50,test4);
    }
}