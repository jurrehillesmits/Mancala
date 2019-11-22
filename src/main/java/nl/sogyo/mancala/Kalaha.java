package nl.sogyo.mancala;

import java.util.List;

class Kalaha extends BeadContainer{
	
	
	Kalaha(Player myPlayer,Bowl Start,int i){
		this.myPlayer = myPlayer;
		this.Content =0;
		if(i==8) {
			i++;
			this.SetNeighbour(new Bowl(myPlayer.GetOtherPlayer(),Start, i));
		}
		else{
			this.SetNeighbour(Start);
		}
	}
	Kalaha(Player myPlayer,Bowl Start,int i,List<Integer> Setup){
		this.myPlayer = myPlayer;
		this.Content =Setup.get(i-2);
		if(i==8) {
			i++;
			this.SetNeighbour(new Bowl(myPlayer.GetOtherPlayer(),Start, i,Setup));
		}
		else{
			this.SetNeighbour(Start);
		}
	}
	protected void AddOneBeadToSelfAndPassAmountToNextInLine(int beadAmount){
		//We add one bead to this kalaha and lower the amount of beads by one
		if(myPlayer.GetActive()) {
			this.AddOneBead();
			beadAmount -= 1;
		}
		//We tell the neighbour to continue the chain if there are one or more beads left in the amount
		if(beadAmount >0){
			Neighbour.AddOneBeadToSelfAndPassAmountToNextInLine(beadAmount);
			return;
		}
		checkIfAllBowlsOfTheActivePlayerAreEmpty(0);
		//We do not swap the turn if the AddOneBead chain ends here so in effect we repeat the turn since the active player did not change
	}
	protected void MoveBeadAmountToActivePlayerKalaha(int beadAmount){
		if(myPlayer.GetActive()){
			add(beadAmount);
		}
		else{
			Neighbour.MoveBeadAmountToActivePlayerKalaha(beadAmount);
		}
	}
	//Invert the amount so we start counting up until we reach 0 at the opossing bowl
	protected void passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(int bowlCount){
		bowlCount *=-1;
		Neighbour.passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(bowlCount);
	}
	protected void checkIfAllBowlsOfTheActivePlayerAreEmpty(int bowlCount){
		Neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(bowlCount);
	}
	protected void passBeadsAlongToNextKalahaNeighbour(int beadAmount){
		add(beadAmount);
	}
	protected void moveBeadAmountToNextKalahaNeighbour(int bowlCount){
		Neighbour.moveBeadAmountToNextKalahaNeighbour(bowlCount);
	}
	public void passMoveBeadsCommandAlongThisManyStepsExcludingKalahas(int steps){
		Neighbour.passMoveBeadsCommandAlongThisManyStepsExcludingKalahas(steps);
	}
    void add(int amount){
		this.Content+=amount;
	}
	
	
}