package nl.sogyo.mancala.domain;

import nl.sogyo.mancala.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class MancalaTest {

    List<Integer> SetupGameState = new ArrayList<>();
    Bowl bowl;
    Bowl bowlEmptySide;
    Bowl bowlStealAndEmptyTest;


    @Before
    public void BeforeTest(){
        SetupGameState.add(0);//Bowl 0  |Steps 0
        SetupGameState.add(1);//Bowl 1  |Steps 1
        SetupGameState.add(2);//Bowl 2  |Steps 2
        SetupGameState.add(3);//Bowl 3  |Steps 3
        SetupGameState.add(4);//Bowl 4  |Steps 4
        SetupGameState.add(5);//Bowl 5  |Steps 5
        SetupGameState.add(0);//Kalaha 1|Steps 6
        SetupGameState.add(7);//Bowl 6  |Steps 7
        SetupGameState.add(8);//Bowl 7  |Steps 8
        SetupGameState.add(9);//Bowl 8  |Steps 9
        SetupGameState.add(10);//Bowl 9 |Steps 10
        SetupGameState.add(11);//Bowl 10|Steps 11
        SetupGameState.add(13);//Bowl 11|Steps 12
        SetupGameState.add(14);//Kalaha2|Steps 13
        bowl = new Bowl(SetupGameState);
        SetupGameState.clear();
        SetupGameState.add(4);//Bowl 0  |Steps 0
        SetupGameState.add(4);//Bowl 1  |Steps 1
        SetupGameState.add(4);//Bowl 2  |Steps 2
        SetupGameState.add(4);//Bowl 3  |Steps 3
        SetupGameState.add(4);//Bowl 4  |Steps 4
        SetupGameState.add(4);//Bowl 5  |Steps 5
        SetupGameState.add(0);//Kalaha 1|Steps 6
        SetupGameState.add(0);//Bowl 6  |Steps 7
        SetupGameState.add(0);//Bowl 7  |Steps 8
        SetupGameState.add(0);//Bowl 8  |Steps 9
        SetupGameState.add(0);//Bowl 9  |Steps 10
        SetupGameState.add(0);//Bowl 10 |Steps 11
        SetupGameState.add(0);//Bowl 11 |Steps 12
        SetupGameState.add(0);//Kalaha2 |Steps 13
        bowlEmptySide = new Bowl(SetupGameState);
        SetupGameState.set(8,1);//Bowl 7|Steps 8
        bowlStealAndEmptyTest = new Bowl(SetupGameState);
    }
    @After
    public void AfterTest(){
        SetupGameState.clear();
    }
    @Test
    public void standardBoardSetup(){
        Bowl StandardBowl = new Bowl();
        Assert.assertEquals(4,StandardBowl.getContentAt(0));
        Assert.assertEquals(4,StandardBowl.getContentAt(1));
        Assert.assertEquals(4,StandardBowl.getContentAt(2));
        Assert.assertEquals(4,StandardBowl.getContentAt(3));
        Assert.assertEquals(4,StandardBowl.getContentAt(4));
        Assert.assertEquals(4,StandardBowl.getContentAt(5));
        Assert.assertEquals(0,StandardBowl.getContentAt(6));
        Assert.assertEquals(4,StandardBowl.getContentAt(7));
        Assert.assertEquals(4,StandardBowl.getContentAt(8));
        Assert.assertEquals(4,StandardBowl.getContentAt(9));
        Assert.assertEquals(4,StandardBowl.getContentAt(10));
        Assert.assertEquals(4,StandardBowl.getContentAt(11));
        Assert.assertEquals(4,StandardBowl.getContentAt(12));
        Assert.assertEquals(0,StandardBowl.getContentAt(13));
        Assert.assertEquals(4,StandardBowl.getContentAt(14));
    }
    @Test
    public void specificSetupTest()
    {
        Assert.assertEquals(0,bowl.getContentAt(0));
        Assert.assertEquals(1,bowl.getContentAt(1));
        Assert.assertEquals(2,bowl.getContentAt(2));
        Assert.assertEquals(3,bowl.getContentAt(3));
        Assert.assertEquals(4,bowl.getContentAt(4));
        Assert.assertEquals(5,bowl.getContentAt(5));
        Assert.assertEquals(0,bowl.getContentAt(6));
        Assert.assertEquals(7,bowl.getContentAt(7));
        Assert.assertEquals(8,bowl.getContentAt(8));
        Assert.assertEquals(9,bowl.getContentAt(9));
        Assert.assertEquals(10,bowl.getContentAt(10));
        Assert.assertEquals(11,bowl.getContentAt(11));
        Assert.assertEquals(13,bowl.getContentAt(12));
        Assert.assertEquals(14,bowl.getContentAt(13));
        Assert.assertEquals(0,bowl.getContentAt(14));
    }
    @Test
    public void moveBeadActivePlayer(){
        bowl.moveBeadsAt(3);
        Assert.assertEquals(0,bowl.getContentAt(3));
        Assert.assertEquals(5,bowl.getContentAt(4));
        Assert.assertEquals(6,bowl.getContentAt(5));
        Assert.assertEquals(1,bowl.getContentAt(6));
    }
    @Test
    public void moveBeadPastActivePlayerKalaha(){
        bowl.moveBeadsAt(4);
        Assert.assertEquals(0,bowl.getContentAt(4));
        Assert.assertEquals(6,bowl.getContentAt(5));
        Assert.assertEquals(1,bowl.getContentAt(6));
        Assert.assertEquals(8,bowl.getContentAt(7));
        Assert.assertEquals(9,bowl.getContentAt(8));
    }
    @Test
    public void moveBeadsAgainByLandingInKalaha(){
        bowl.moveBeadsAt(3);
        Assert.assertTrue(bowl.getActiveStateOfPlayerAt(3));
    }
    @Test
    public void noMoveDueToNotActivePlayer() {
        Assert.assertFalse(bowl.getActiveStateOfPlayerAt(7));
        bowl.moveBeadsAt(7);
        Assert.assertEquals(0,bowl.getContentAt(6));
        Assert.assertEquals(7,bowl.getContentAt(7));
        Assert.assertFalse(bowl.getActiveStateOfPlayerAt(7));
    }
    @Test
    public void noMoveDueToEmptyBowl(){
        Assert.assertTrue(bowl.getActiveStateOfPlayerAt(0));
        bowl.moveBeadsAt(0);
        Assert.assertEquals(1,bowl.getContentAt(1));
        Assert.assertTrue(bowl.getActiveStateOfPlayerAt(0));
    }
    @Test
    public void noMoveDueToSelectingAKalaha(){
        bowl.moveBeadsAt(13);
        Assert.assertEquals(14,bowl.getContentAt(13));
        Assert.assertEquals(0,bowl.getContentAt(0));
        Assert.assertEquals(0,bowl.getContentAt(6));
        Assert.assertEquals(13,bowl.getContentAt(12));
    }
    @Test
    public void swapPlayersAfterNotLandingInAKalaha(){
        Assert.assertTrue(bowl.getActiveStateOfPlayerAt(1));
        bowl.moveBeadsAt(1);
        Assert.assertFalse(bowl.getActiveStateOfPlayerAt(1));
    }
    @Test
    public void skippingInactivePlayerKalahaAndStealingAfterDoingAFullCircle(){
        Assert.assertTrue(bowl.getActiveStateOfPlayerAt(1));
        bowl.moveBeadsAt(1);
        Assert.assertFalse(bowl.getActiveStateOfPlayerAt(1));
        Assert.assertTrue(bowl.getActiveStateOfPlayerAt(12));
        bowl.moveBeadsAt(12);
        Assert.assertFalse(bowl.getActiveStateOfPlayerAt(12));
        Assert.assertEquals(0,bowl.getContentAt(0));
        Assert.assertEquals(0,bowl.getContentAt(12));
        Assert.assertEquals(0,bowl.getContentAt(12));
        Assert.assertEquals(17,bowl.getContentAt(13));
    }
    @Test
    public void emptyIntoKalahaWhenBowlsOfActivePlayerAreEmpty(){
        bowlEmptySide.moveBeadsAt(1);
        Assert.assertEquals(0,bowlEmptySide.getContentAt(2));
        Assert.assertEquals(24,bowlEmptySide.getContentAt(6));
        Assert.assertEquals(0,bowlEmptySide.getContentAt(13));
    }
    @Test
    public void doNotEmptyIntoKalahaWhenBowlsOfActivePlayerWereEmptyButGotFilled(){
        bowlEmptySide.moveBeadsAt(3);
        Assert.assertEquals(4,bowlEmptySide.getContentAt(2));
        Assert.assertEquals(1,bowlEmptySide.getContentAt(6));
        Assert.assertEquals(1,bowlEmptySide.getContentAt(7));
    }
    @Test
    public void doNotEmptyIntoKalahaWhenSamePlayerRemainsActiveWhileOtherPlayerHasOnlyEmptyBowls(){
        bowlEmptySide.moveBeadsAt(2);
        Assert.assertEquals(0,bowlEmptySide.getContentAt(2));
        Assert.assertEquals(1,bowlEmptySide.getContentAt(6));
        Assert.assertEquals(0,bowlEmptySide.getContentAt(7));
    }
    @Test
    public void stealingResultingInAnEmptyBoardForInactivePlayerThatShouldNotGetSwapped(){
        bowlStealAndEmptyTest.moveBeadsAt(0);
        bowlStealAndEmptyTest.moveBeadsAt(8);
        Assert.assertEquals(0,bowlStealAndEmptyTest.getContentAt(9));
        Assert.assertEquals(0,bowlStealAndEmptyTest.getContentAt(3));
        Assert.assertEquals(5,bowlStealAndEmptyTest.getContentAt(4));
        Assert.assertEquals(4,bowlStealAndEmptyTest.getContentAt(5));
        Assert.assertEquals(0,bowlStealAndEmptyTest.getContentAt(6));
        Assert.assertEquals(6,bowlStealAndEmptyTest.getContentAt(13));
    }





}
