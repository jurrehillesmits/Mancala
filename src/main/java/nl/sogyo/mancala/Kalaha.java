package nl.sogyo.mancala;

class Kalaha extends BeadContainer{
	
	
	Kalaha(Player myPlayer,Bowl Start,int i){
		this.myPlayer = myPlayer;
		this.Content =0;
		this.Neighbour=Neighbour;
		if(i==8) {
			i++;
			this.SetNeighbour(new Bowl(myPlayer.GetOtherPlayer(),Start, i));
		}
		else{
			Start.SetNeighbour(this);
		}

	}
	
	protected void AddOneBeadToSelfAndPassAmountToNextInLine(int Amount){
		//We add one bead to this kalaha and lower the amount of beads by one
		if(myPlayer.GetActive()) {
			this.AddOneBead();
			Amount -= 1;
		}
		//We tell the neighbour to continue the chain if there are one or more beads left in the amount
		if(Amount>0){
			Neighbour.AddOneBeadToSelfAndPassAmountToNextInLine(Amount);
			return;
		}
		//We do not swap the turn if the AddOneBead chain ends here so in effect we repeat the turn since the active player did not change
	}
	//A way to tell the Kalaha to add an amount to itself


	protected void MoveBeadAmountToActivePlayerKalaha(int Amount){
		if(myPlayer.GetActive()){
			this.Add(Amount);
		}
		else{
			Neighbour.MoveBeadAmountToActivePlayerKalaha(Amount);
		}
	}
	protected void PassAlongCommandToOpposingBowlToEmptyToKalaha(int Amount){

	}


    void Add(int Amount){
		this.Content+=Amount;
	}
	
	
}