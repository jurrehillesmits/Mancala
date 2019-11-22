package nl.sogyo.mancala;

import java.util.ArrayList;

import java.util.List;

public class Bowl extends BeadContainer{
	private static final List<Integer> StandardStart = new ArrayList<>(List.of(4,4,4,4,4,4,0,4,4,4,4,4,4,0));
	public Bowl(){
		this.myPlayer = new Player();
		this.content = StandardStart.get(0);
		this.setNeighbour(new Bowl(myPlayer,3,StandardStart));
		neighbour.passNeighbourReference(this);
	}
	//Varying Test Setup
	public Bowl(List<Integer> Setup){
		this.myPlayer = new Player();
		this.content = Setup.get(0);
		this.setNeighbour(new Bowl(myPlayer,3,Setup));
		neighbour.passNeighbourReference(this);
	}
	Bowl(Player myPlayer,int i,List<Integer> Setup) {
		this.myPlayer = myPlayer;
		this.content = Setup.get(i-2);
		if(i<7) {
			i++;
			this.setNeighbour(new Bowl(myPlayer,i, Setup));
		}
		else if(i==7){
			i++;
			this.setNeighbour(new Kalaha(myPlayer,i,Setup));
		}
		else if(i<14){
			i++;
			this.setNeighbour(new Bowl(myPlayer,i,Setup));
		}
		else if(i==14){
			i++;
			this.setNeighbour(new Kalaha(myPlayer,i,Setup));
		}
	}
	//Here we get the beads from this bowl and tell our neighbour to start an AddOneBead chain using that amount
	protected void MoveBeads(){
		//If the player that is referenced by the bowl is active this move is valid
		if(this.myPlayer.GetActive()&&this.content >0){
			int Amount =GetBeadsFromBowl();
				neighbour.AddOneBeadToSelfAndPassAmountToNeighbour(Amount);
			}
		else{
			System.out.println("Invalid move try again");
		}
	}
	//This returns the contents of the bowl and sets the contents to zero
	private int GetBeadsFromBowl(){
		int Beads = this.content;
		this.content =0;
		return Beads;
	}
	//This adds one bead to this bowl and then either tells the next bowl to continue this cycle or if it was the last bead it checks if this bowl was empty to do a Steal move. Afterwards it tells the players to swap
	protected void AddOneBeadToSelfAndPassAmountToNeighbour(int beadAmount){
		//We add one bead to this bowl and lower the amount of beads by one
		this.addOneBead();
		beadAmount -=1;
		//We tell the neighbour to continue the chain if there are one or more beads left in the amount and we stop the action in this bowl by returning
		if(beadAmount >0){
				neighbour.AddOneBeadToSelfAndPassAmountToNeighbour(beadAmount);
				return;
		}
		this.TryToSteal();
		//We can call on myplayer no matter whose turn it is since they set the other as inactive when they go active and vice versa
		this.myPlayer.SwapPlayers();
		//Here we check if the game is ended and if so we add all of the beads to their respective kalahas
		checkIfAllBowlsOfTheActivePlayerAreEmpty(0);
	}
	//If the bowl content was empty(so after adding the last bead it only contains 1) and the player that is assigned to this bowl is active we perform the stealing action
    private void TryToSteal() {
        if(this.content ==1&&this.myPlayer.GetActive()){
            MoveContentToActivePlayerKalaha();
			passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(0);
        }
    }
    //We tell the bowl to move its content along to the neighbours until it hits the Kalaha of the active player, where it gets deposited.
	private void MoveContentToActivePlayerKalaha(){
		int Amount = this.content;
		this.content = 0;
		neighbour.MoveBeadAmountToActivePlayerKalaha(Amount);
	}
	protected void MoveBeadAmountToActivePlayerKalaha(int beadAmount){
		neighbour.MoveBeadAmountToActivePlayerKalaha(beadAmount);
	}
	protected void passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(int bowlCount){
		bowlCount +=1;
		if(bowlCount !=0){
			neighbour.passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(bowlCount);
		}
		else{
			MoveContentToActivePlayerKalaha();
		}
	}
	protected void checkIfAllBowlsOfTheActivePlayerAreEmpty(int bowlCount){
		if(myPlayer.GetActive()){
			if(this.content !=0){
				return;
			}
			bowlCount +=1;
		}
		if(bowlCount ==6){
			moveBeadAmountToNextKalahaNeighbour(0);
			return;
		}
		neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(bowlCount);
	}
	protected void moveBeadAmountToNextKalahaNeighbour(int bowlCount){
		passBeadsAlongToNextKalahaNeighbour(GetBeadsFromBowl());
		if(bowlCount <12){
			bowlCount +=1;
			neighbour.moveBeadAmountToNextKalahaNeighbour(bowlCount);
		}
	}
	protected void passBeadsAlongToNextKalahaNeighbour(int beadAmount){
		neighbour.passBeadsAlongToNextKalahaNeighbour(beadAmount);
	}

}