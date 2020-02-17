package com.company;

import org.junit.Assert;
import org.junit.Test;


public class GameTest {
    public GameTest() {
    }

    @Test
    public void testCheckValidInput() {

        String input1 = "a b";
        String input2 = "2 9";
        Game game = new Game(10);
        Assert.assertFalse(game.checkValidInput(input1));
        Assert.assertTrue(game.checkValidInput(input2));
    }


    @Test
    public void testGetCoordinates() {
        String input = "0 0";
        Game game = new Game(5);
        int[] coordinates = game.getCoordinates(input);
        Assert.assertEquals(0, coordinates[0]);
        Assert.assertEquals(0, coordinates[1]);
    }

}
