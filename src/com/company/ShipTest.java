package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShipTest {
    private Ship cruiser;
    private Ship frigate;
    private Ship submarine;
    private Ship sailboat;
    private Playground playground;
    private MissedShot missedShot;

    public ShipTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.cruiser = new Cruiser();
        this.frigate = new Frigate();
        this.submarine = new Submarine();
        this.sailboat = new Sailboat();
        this.playground = new Playground(10);
        this.missedShot = new MissedShot();
    }

    @Test
    public void testShip() {
        this.frigate.setAimRow(4);
        int aimRow = this.frigate.getAimRow();
        Assert.assertEquals(4, aimRow);
        this.frigate.setAimColumn(3);
        int aimColumn = this.frigate.getAimColumn();
        Assert.assertEquals(3, aimColumn);
        this.submarine.setHorizontal(true);
        Assert.assertTrue(this.submarine.isHorizontal());
        this.sailboat.setHorizontal(false);
        Assert.assertFalse(this.sailboat.isHorizontal());
    }


    @Test
    public void testGetShipType() {
        Assert.assertEquals("Cruiser", this.cruiser.getShipType());
        Assert.assertEquals("Frigate", this.frigate.getShipType());
        Assert.assertEquals("Submarine", this.submarine.getShipType());
        Assert.assertEquals("Sailboat", this.sailboat.getShipType());
    }


    @Test
    public void testGetLength() {
        Assert.assertEquals(4, this.cruiser.getLength());
        Assert.assertEquals(3, this.frigate.getLength());
        Assert.assertEquals(2, this.submarine.getLength());
        Assert.assertEquals(1, this.sailboat.getLength());
        Assert.assertEquals(1, this.missedShot.getLength());
    }



    @Test
    public void testOkToPlaceShipAt() {
        boolean place0 = this.cruiser.okToPlaceShipAt(3, 4, true, this.playground, 10);
        boolean place1 = this.cruiser.okToPlaceShipAt(2, 7, true, this.playground, 10);
        Assert.assertTrue(place0);
        Assert.assertFalse(place1);
        this.cruiser.placeShipAt(3, 4, true, this.playground, 10);
        boolean place2 = this.frigate.okToPlaceShipAt(2, 1, true, this.playground, 10);
        boolean place3 = this.frigate.okToPlaceShipAt(5, 6, false, this.playground, 10);
        Assert.assertFalse(place2);
        Assert.assertTrue(place3);
        this.frigate.placeShipAt(5, 6, false, this.playground, 10);
        boolean place4 = this.submarine.okToPlaceShipAt(8, 7, false, this.playground, 10);
        boolean place5 = this.submarine.okToPlaceShipAt(0, 1, true, this.playground, 10);
        Assert.assertFalse(place4);
        Assert.assertTrue(place5);
        this.submarine.placeShipAt(0, 1, true, this.playground, 10);
        boolean place6 = this.sailboat.okToPlaceShipAt(1, 0, false, this.playground, 10);
        boolean place7 = this.sailboat.okToPlaceShipAt(0, 9, true, this.playground, 10);
        Assert.assertFalse(place6);
        Assert.assertTrue(place7);
    }


    @Test
    public void testShootHitSunk() {
        Frigate frigate1 = new Frigate();
        frigate1.setAimRow(2);
        frigate1.setAimColumn(3);
        frigate1.setHorizontal(true);
        Assert.assertFalse(frigate1.shootAt(1, 7));
        Assert.assertTrue(frigate1.shootAt(2, 4));
        Assert.assertFalse(frigate1.getHitArray()[0]);
        Assert.assertTrue(frigate1.getHitArray()[1]);
        Assert.assertFalse(frigate1.getHitArray()[2]);
        Assert.assertFalse(frigate1.isSunk());
        Assert.assertTrue(frigate1.shootAt(2, 3));
        Assert.assertTrue(frigate1.shootAt(2, 5));
        Assert.assertTrue(frigate1.getHitArray()[0]);
        Assert.assertTrue(frigate1.getHitArray()[1]);
        Assert.assertTrue(frigate1.getHitArray()[2]);
        Assert.assertTrue(frigate1.isSunk());

    }


    @Test
    public void testToString() {
        Sailboat sailboat1 = new Sailboat();
        sailboat1.setAimRow(2);
        sailboat1.setAimColumn(3);
        sailboat1.setHorizontal(true);
        Assert.assertEquals("c", sailboat1.toString());
        sailboat1.shootAt(2, 3);
        Assert.assertEquals("x", sailboat1.toString());
        Assert.assertEquals("-", this.missedShot.toString());
    }

}
