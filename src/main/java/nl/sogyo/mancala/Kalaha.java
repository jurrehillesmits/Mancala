package nl.sogyo.mancala;

import java.util.List;

class Kalaha extends BeadContainer{






	Kalaha(Player myPlayer,int i,List<Integer> Setup){
		this.myPlayer = myPlayer;
		this.content =Setup.get(i-2);
		if(i==8) {
			i++;
			this.setNeighbour(new Bowl(myPlayer.GetOtherPlayer(), i,Setup));
		}
	}





	protected void AddOneBeadToSelfAndPassAmountToNeighbour(int beadAmount){
		//We add one bead to this kalaha and lower the amount of beads by one
		if(myPlayer.GetActive()) {
			this.addOneBead();
			beadAmount -= 1;
		}
		//We tell the neighbour to continue the chain if there are one or more beads left in the amount
		if(beadAmount >0){
			neighbour.AddOneBeadToSelfAndPassAmountToNeighbour(beadAmount);
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
			neighbour.MoveBeadAmountToActivePlayerKalaha(beadAmount);
		}
	}
	//Invert the amount so we start counting up until we reach 0 at the opossing bowl
	protected void passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(int bowlCount){
		bowlCount *=-1;
		neighbour.passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(bowlCount);
	}
	protected void checkIfAllBowlsOfTheActivePlayerAreEmpty(int bowlCount){
		neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(bowlCount);
	}
	protected void passBeadsAlongToNextKalahaNeighbour(int beadAmount){
		add(beadAmount);
	}
	protected void moveBeadAmountToNextKalahaNeighbour(int bowlCount){
		neighbour.moveBeadAmountToNextKalahaNeighbour(bowlCount);
	}
    void add(int amount){
		this.content +=amount;
	}
	protected void MoveBeads(){
		System.out.println("Invalid move try again");
	}
	
}