package com.company;

import com.company.Playground;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class PlaygroundTest {

    Playground playground;
    Playground playground1;
    Playground playground2;
    ArrayList<Ship> shipArrayList;


    public PlaygroundTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.playground = new Playground(10);
        this.shipArrayList = new ArrayList();
        this.shipArrayList = this.playground.getAllShips();
        ((Ship)this.shipArrayList.get(0)).placeShipAt(1, 3, true, this.playground, 10);
        ((Ship)this.shipArrayList.get(1)).placeShipAt(3, 4, true, this.playground, 10);
        ((Ship)this.shipArrayList.get(2)).placeShipAt(3, 2, false, this.playground, 10);
        ((Ship)this.shipArrayList.get(3)).placeShipAt(7, 0, false, this.playground, 10);
        ((Ship)this.shipArrayList.get(4)).placeShipAt(7, 6, true, this.playground, 10);
        ((Ship)this.shipArrayList.get(5)).placeShipAt(6, 9, false, this.playground, 10);
        ((Ship)this.shipArrayList.get(6)).placeShipAt(1, 0, false, this.playground, 10);
        ((Ship)this.shipArrayList.get(7)).placeShipAt(4, 0, true, this.playground, 10);
        ((Ship)this.shipArrayList.get(8)).placeShipAt(7, 2, false, this.playground, 10);
        ((Ship)this.shipArrayList.get(9)).placeShipAt(5, 7, true, this.playground, 10);
        this.playground1 = new Playground(10);
    }

    @Test
    public void testPlaceAllShipsRandomly() {
    }

    @Test
    public void testPlayground() {
        this.playground2 = new Playground(10);
        Assert.assertTrue(this.playground2.getShipArray()[5][5] instanceof MissedShot);
        Assert.assertFalse(this.playground2.getShadow()[5][5]);
        Assert.assertEquals(this.playground2.getAllShips().size(), 10);
        Assert.assertEquals(this.playground2.getShotsFired(), 0);
        Assert.assertEquals(this.playground2.getHitCount(), 0);
    }


    @Test
    public void testIsOccupied() {
        Assert.assertTrue(this.playground.isOccupied(1, 0));
        Assert.assertTrue(this.playground.isOccupied(1, 6));
        Assert.assertFalse(this.playground.isOccupied(1, 7));
        Assert.assertFalse(this.playground.isOccupied(9, 9));

    }


    @Test
    public void testIsGameOver() {
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                this.playground.shootAt(i, j);
            }
        }

        Assert.assertFalse(this.playground.isGameOver(10));
        this.playground.shootAt(6, 9);
        this.playground.shootAt(7, 9);
        Assert.assertFalse(this.playground.isGameOver(10));
        this.playground.shootAt(8, 0);
        Assert.assertTrue(this.playground.isGameOver(10));
        this.playground.shootAt(8, 1);
        Assert.assertTrue(this.playground.isGameOver(10));
    }
}
