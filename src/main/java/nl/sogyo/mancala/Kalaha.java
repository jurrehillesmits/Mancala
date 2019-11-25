package nl.sogyo.mancala;

class Kalaha extends BeadContainer{
	Kalaha(Player myPlayer,int content){
		this.myPlayer = myPlayer;
		this.content = content;
	}
	protected void AddOneBeadToSelfAndPassAmountToNeighbour(int beadAmount){
		if(myPlayer.GetActive()) {
			this.addOneBead();
			beadAmount -= 1;
		}
		if(beadAmount >0){
			neighbour.AddOneBeadToSelfAndPassAmountToNeighbour(beadAmount);
			return;
		}
		checkIfAllBowlsOfTheActivePlayerAreEmpty(0);
	}
	protected void MoveBeadAmountToActivePlayerKalaha(int beadAmount){
		if(myPlayer.GetActive()){
			add(beadAmount);
		}
		else{
			neighbour.MoveBeadAmountToActivePlayerKalaha(beadAmount);
		}
	}
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