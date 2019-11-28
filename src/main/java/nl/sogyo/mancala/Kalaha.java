package nl.sogyo.mancala;
class Kalaha extends BeadContainer{
	Kalaha(Player myPlayer, int content){
		this.myPlayer = myPlayer;
		this.content = content;
	}
	protected void addOneBeadToSelfAndPassAmountToNeighbour(int beadAmount){
		if(myPlayer.getActive()) {
			this.addOneBead();
			beadAmount -= 1;
		}
		if(beadAmount >0){
			neighbour.addOneBeadToSelfAndPassAmountToNeighbour(beadAmount);
			return;
		}
		checkIfAllBowlsOfTheActivePlayerAreEmpty(0);
	}
	protected void moveBeadAmountToActivePlayerKalaha(int beadAmount){
		if(myPlayer.getActive()){
			add(beadAmount);
		}
		else{
			neighbour.moveBeadAmountToActivePlayerKalaha(beadAmount);
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
	protected void moveBeads(){
		System.out.println("Invalid move try again");
	}
	
}