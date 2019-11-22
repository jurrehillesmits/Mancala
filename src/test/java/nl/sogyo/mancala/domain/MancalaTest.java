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
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(0));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(1));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(2));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(3));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(4));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(5));
        Assert.assertEquals(0,StandardBowl.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(7));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(8));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(9));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(10));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(11));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(12));
        Assert.assertEquals(0,StandardBowl.passGetContentCommandAlongThisManySteps(13));
        Assert.assertEquals(4,StandardBowl.passGetContentCommandAlongThisManySteps(14));
    }
    @Test
    public void specificSetupTest()
    {
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(0));
        Assert.assertEquals(1,bowl.passGetContentCommandAlongThisManySteps(1));
        Assert.assertEquals(2,bowl.passGetContentCommandAlongThisManySteps(2));
        Assert.assertEquals(3,bowl.passGetContentCommandAlongThisManySteps(3));
        Assert.assertEquals(4,bowl.passGetContentCommandAlongThisManySteps(4));
        Assert.assertEquals(5,bowl.passGetContentCommandAlongThisManySteps(5));
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(7,bowl.passGetContentCommandAlongThisManySteps(7));
        Assert.assertEquals(8,bowl.passGetContentCommandAlongThisManySteps(8));
        Assert.assertEquals(9,bowl.passGetContentCommandAlongThisManySteps(9));
        Assert.assertEquals(10,bowl.passGetContentCommandAlongThisManySteps(10));
        Assert.assertEquals(11,bowl.passGetContentCommandAlongThisManySteps(11));
        Assert.assertEquals(13,bowl.passGetContentCommandAlongThisManySteps(12));
        Assert.assertEquals(14,bowl.passGetContentCommandAlongThisManySteps(13));
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(14));
    }
    @Test
    public void moveBeadActivePlayer(){
        bowl.passMoveBeadsCommandAlongThisManySteps(3);
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(3));
        Assert.assertEquals(5,bowl.passGetContentCommandAlongThisManySteps(4));
        Assert.assertEquals(6,bowl.passGetContentCommandAlongThisManySteps(5));
        Assert.assertEquals(1,bowl.passGetContentCommandAlongThisManySteps(6));
    }
    @Test
    public void moveBeadPastActivePlayerKalaha(){
        bowl.passMoveBeadsCommandAlongThisManySteps(4);
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(4));
        Assert.assertEquals(6,bowl.passGetContentCommandAlongThisManySteps(5));
        Assert.assertEquals(1,bowl.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(8,bowl.passGetContentCommandAlongThisManySteps(7));
        Assert.assertEquals(9,bowl.passGetContentCommandAlongThisManySteps(8));
    }
    @Test
    public void moveBeadsAgainByLandingInKalaha(){
        bowl.passMoveBeadsCommandAlongThisManySteps(3);
        Assert.assertTrue(bowl.passGetPlayerActiveCommandAlongThisManySteps(3));
    }
    @Test
    public void noMoveDueToNotActivePlayer() {
        Assert.assertFalse(bowl.passGetPlayerActiveCommandAlongThisManySteps(7));
        bowl.passMoveBeadsCommandAlongThisManySteps(7);
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(7,bowl.passGetContentCommandAlongThisManySteps(7));
        Assert.assertFalse(bowl.passGetPlayerActiveCommandAlongThisManySteps(7));
    }
    @Test
    public void noMoveDueToEmptyBowl(){
        Assert.assertTrue(bowl.passGetPlayerActiveCommandAlongThisManySteps(0));
        bowl.passMoveBeadsCommandAlongThisManySteps(0);
        Assert.assertEquals(1,bowl.passGetContentCommandAlongThisManySteps(1));
        Assert.assertTrue(bowl.passGetPlayerActiveCommandAlongThisManySteps(0));
    }
    @Test
    public void noMoveDueToSelectingAKalaha(){
        bowl.passMoveBeadsCommandAlongThisManySteps(13);
        Assert.assertEquals(14,bowl.passGetContentCommandAlongThisManySteps(13));
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(0));
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(13,bowl.passGetContentCommandAlongThisManySteps(12));
    }
    @Test
    public void swapPlayersAfterNotLandingInAKalaha(){
        Assert.assertTrue(bowl.passGetPlayerActiveCommandAlongThisManySteps(1));
        bowl.passMoveBeadsCommandAlongThisManySteps(1);
        Assert.assertFalse(bowl.passGetPlayerActiveCommandAlongThisManySteps(1));
    }
    @Test
    public void skippingInactivePlayerKalahaAndStealingAfterDoingAFullCircle(){
        Assert.assertTrue(bowl.passGetPlayerActiveCommandAlongThisManySteps(1));
        bowl.passMoveBeadsCommandAlongThisManySteps(1);
        Assert.assertFalse(bowl.passGetPlayerActiveCommandAlongThisManySteps(1));
        Assert.assertTrue(bowl.passGetPlayerActiveCommandAlongThisManySteps(12));
        bowl.passMoveBeadsCommandAlongThisManySteps(12);
        Assert.assertFalse(bowl.passGetPlayerActiveCommandAlongThisManySteps(12));
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(0));
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(12));
        Assert.assertEquals(0,bowl.passGetContentCommandAlongThisManySteps(12));
        Assert.assertEquals(17,bowl.passGetContentCommandAlongThisManySteps(13));
    }
    @Test
    public void emptyIntoKalahaWhenBowlsOfActivePlayerAreEmpty(){
        bowlEmptySide.passMoveBeadsCommandAlongThisManySteps(1);
        Assert.assertEquals(0,bowlEmptySide.passGetContentCommandAlongThisManySteps(2));
        Assert.assertEquals(24,bowlEmptySide.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(0,bowlEmptySide.passGetContentCommandAlongThisManySteps(13));
    }
    @Test
    public void doNotEmptyIntoKalahaWhenBowlsOfActivePlayerWereEmptyButGotFilled(){
        bowlEmptySide.passMoveBeadsCommandAlongThisManySteps(3);
        Assert.assertEquals(4,bowlEmptySide.passGetContentCommandAlongThisManySteps(2));
        Assert.assertEquals(1,bowlEmptySide.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(1,bowlEmptySide.passGetContentCommandAlongThisManySteps(7));
    }
    @Test
    public void doNotEmptyIntoKalahaWhenSamePlayerRemainsActiveWhileOtherPlayerHasOnlyEmptyBowls(){
        bowlEmptySide.passMoveBeadsCommandAlongThisManySteps(2);
        Assert.assertEquals(0,bowlEmptySide.passGetContentCommandAlongThisManySteps(2));
        Assert.assertEquals(1,bowlEmptySide.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(0,bowlEmptySide.passGetContentCommandAlongThisManySteps(7));
    }
    @Test
    public void stealingResultingInAnEmptyBoardForInactivePlayerThatShouldNotGetSwapped(){
        bowlStealAndEmptyTest.passMoveBeadsCommandAlongThisManySteps(0);
        bowlStealAndEmptyTest.passMoveBeadsCommandAlongThisManySteps(8);
        Assert.assertEquals(0,bowlStealAndEmptyTest.passGetContentCommandAlongThisManySteps(9));
        Assert.assertEquals(0,bowlStealAndEmptyTest.passGetContentCommandAlongThisManySteps(3));
        Assert.assertEquals(5,bowlStealAndEmptyTest.passGetContentCommandAlongThisManySteps(4));
        Assert.assertEquals(4,bowlStealAndEmptyTest.passGetContentCommandAlongThisManySteps(5));
        Assert.assertEquals(0,bowlStealAndEmptyTest.passGetContentCommandAlongThisManySteps(6));
        Assert.assertEquals(6,bowlStealAndEmptyTest.passGetContentCommandAlongThisManySteps(13));
    }





}
