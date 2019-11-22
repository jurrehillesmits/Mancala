package nl.sogyo.mancala;

import java.util.List;

public class Bowl extends BeadContainer{
	public Bowl(){
		this.myPlayer = new Player();
		this.Content = 4;
		this.SetNeighbour(new Bowl(myPlayer,this,3));
	}
	Bowl(Player myPlayer,Bowl Start,int i) {
		this.myPlayer = myPlayer;
		this.Content = 4;
		if(i<7) {
			i++;
			this.SetNeighbour(new Bowl(myPlayer, Start, i));
		}
		else if(i==7){
			i++;
			this.SetNeighbour(new Kalaha(myPlayer,Start,i));
		}
		else if(i<14){
			i++;
			this.SetNeighbour(new Bowl(myPlayer, Start, i));
		}
		else if(i==14){
			i++;
			this.SetNeighbour(new Kalaha(myPlayer,Start,i));
		}
	}
	//Varying Test Setup
	public Bowl(List<Integer> Setup){
		this.myPlayer = new Player();
		this.Content = Setup.get(0);
		this.SetNeighbour(new Bowl(myPlayer,this,3,Setup));
	}
	Bowl(Player myPlayer,Bowl Start,int i,List<Integer> Setup) {
		this.myPlayer = myPlayer;
		this.Content = Setup.get(i-2);
		if(i<7) {
			i++;
			this.SetNeighbour(new Bowl(myPlayer, Start, i, Setup));
		}
		else if(i==7){
			i++;
			this.SetNeighbour(new Kalaha(myPlayer,Start,i,Setup));
		}
		else if(i>7&&i<14){
			i++;
			this.SetNeighbour(new Bowl(myPlayer, Start, i,Setup));
		}
		else if(i==14){
			i++;
			this.SetNeighbour(new Kalaha(myPlayer,Start,i,Setup));
		}
	}




	//Here we get the beads from this bowl and tell our neighbour to start an AddOneBead chain using that amount
	private void MoveBeads(){
		//If the player that is referenced by the bowl is active this move is valid
		if(this.myPlayer.GetActive()&&this.Content>0){
			int Amount =GetBeadsFromBowl();
				Neighbour.AddOneBeadToSelfAndPassAmountToNextInLine(Amount);
			}
		else{
			System.out.println("Invalid move try again");
		}
	}
	public void passMoveBeadsCommandAlongThisManyStepsExcludingKalahas(int steps) {
		if((steps %12)>0){
			steps -=1;
			Neighbour.passMoveBeadsCommandAlongThisManyStepsExcludingKalahas(steps);
		}
		else{
			MoveBeads();
		}
	}
	//This returns the contents of the bowl and sets the contents to zero
	private int GetBeadsFromBowl(){
		int Beads = this.Content;
		this.Content =0;
		return Beads;
	}
	//This adds one bead to this bowl and then either tells the next bowl to continue this cycle or if it was the last bead it checks if this bowl was empty to do a Steal move. Afterwards it tells the players to swap
	protected void AddOneBeadToSelfAndPassAmountToNextInLine(int beadAmount){
		//We add one bead to this bowl and lower the amount of beads by one
		this.AddOneBead();
		beadAmount -=1;
		//We tell the neighbour to continue the chain if there are one or more beads left in the amount and we stop the action in this bowl by returning
		if(beadAmount >0){
				Neighbour.AddOneBeadToSelfAndPassAmountToNextInLine(beadAmount);
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
        if(this.Content==1&&this.myPlayer.GetActive()){
            MoveContentToActivePlayerKalaha();
			passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(0);
        }
    }
    //We tell the bowl to move its content along to the neighbours until it hits the Kalaha of the active player, where it gets deposited.
	private void MoveContentToActivePlayerKalaha(){
		int Amount = this.Content;
		this.Content = 0;
		Neighbour.MoveBeadAmountToActivePlayerKalaha(Amount);
	}
	protected void MoveBeadAmountToActivePlayerKalaha(int beadAmount){
		Neighbour.MoveBeadAmountToActivePlayerKalaha(beadAmount);
	}
	protected void passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(int bowlCount){
		bowlCount +=1;
		if(bowlCount !=0){
			Neighbour.passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(bowlCount);
		}
		else{
			MoveContentToActivePlayerKalaha();
		}
	}
	protected void checkIfAllBowlsOfTheActivePlayerAreEmpty(int bowlCount){
		if(myPlayer.GetActive()){
			if(this.Content!=0){
				return;
			}
			bowlCount +=1;
		}
		if(bowlCount ==6){
			moveBeadAmountToNextKalahaNeighbour(0);
			return;
		}
		Neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(bowlCount);
	}
	protected void moveBeadAmountToNextKalahaNeighbour(int bowlCount){
		passBeadsAlongToNextKalahaNeighbour(GetBeadsFromBowl());
		if(bowlCount <12){
			bowlCount +=1;
			Neighbour.moveBeadAmountToNextKalahaNeighbour(bowlCount);
		}
	}
	protected void passBeadsAlongToNextKalahaNeighbour(int beadAmount){
		Neighbour.passBeadsAlongToNextKalahaNeighbour(beadAmount);
	}
	public static void main(String[] args) {
		Bowl bowl = new Bowl();
		bowl.passMoveBeadsCommandAlongThisManyStepsExcludingKalahas(5);
		bowl.passMoveBeadsCommandAlongThisManyStepsExcludingKalahas(8);
		bowl.passMoveBeadsCommandAlongThisManyStepsExcludingKalahas(0);
	}

}