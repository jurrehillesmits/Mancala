package nl.sogyo.mancala;

class Kalaha extends BeadContainer{
	Kalaha(Player myPlayer,int content){
		this.myPlayer = myPlayer;
		this.content = content;
	}
	protected void addOneBeadAndPassRemainingToNeighbour(int beadAmount){
		if(myPlayer.getActive()) {
			this.addOneBead();
			beadAmount -= 1;
		}
		if(beadAmount >0){
			neighbour.addOneBeadAndPassRemainingToNeighbour(beadAmount);
			return;
		}
		neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(this);
	}
	protected void moveBeadsToActivePlayerKalaha(int beadAmount){
		if(myPlayer.getActive()){
			add(beadAmount);
		}
		else{
			neighbour.moveBeadsToActivePlayerKalaha(beadAmount);
		}
	}
	protected void stealFromOpposingBowl(int bowlCount){
		bowlCount *=-1;
		neighbour.stealFromOpposingBowl(bowlCount);
	}
	protected void checkIfAllBowlsOfTheActivePlayerAreEmpty(BeadContainer start){
        if(this==start){
            neighbour.emptyIntoOwnKalaha(this);
            return;
        }
		neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(start);
	}
	protected void passBeadsAlongToNextKalaha(int beadAmount){
		add(beadAmount);
	}
	protected void emptyIntoOwnKalaha(BeadContainer start){
        if(this==start){
            return;
        }
		neighbour.emptyIntoOwnKalaha(start);
	}
    void add(int amount){
		this.content +=amount;
	}
	protected void moveBeads() throws IllegalArgumentException{
	throw new IllegalArgumentException("Illegal move");
	}
	
}