package nl.sogyo.mancala;
abstract class BeadContainer{
	Player myPlayer;
	int content;
	BeadContainer neighbour;
	int getContent() {
		return content;
	}
	void addOneBead(){
		this.content +=1;
	}
	void setNeighbour(BeadContainer neighbour){
		this.neighbour = neighbour;
	}
    public void passMoveBeadsCommandAlongThisManySteps(int steps){
        if((steps %14)>0){
            steps -=1;
            neighbour.passMoveBeadsCommandAlongThisManySteps(steps);
        }
        else{
            moveBeads();
        }
    }
    public int passGetContentCommandAlongThisManySteps(int steps){
        if((steps%14)>0){
            steps-=1;
            return neighbour.passGetContentCommandAlongThisManySteps(steps);
        }
        else{
            return getContent();
        }
    }
    public boolean passGetPlayerActiveCommandAlongThisManySteps(int steps){
        if((steps%14)>0){
            steps-=1;
            return neighbour.passGetPlayerActiveCommandAlongThisManySteps(steps);
        }
        else{
            return myPlayer.getActive();
        }
    }
	protected abstract void addOneBeadToSelfAndPassAmountToNeighbour(int beadAmount);
	protected abstract void moveBeadAmountToActivePlayerKalaha(int beadAmount);
	protected abstract void passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(int bowlCount);
	protected abstract void checkIfAllBowlsOfTheActivePlayerAreEmpty(int bowlCount);
	protected abstract void moveBeadAmountToNextKalahaNeighbour(int bowlCount);
	protected abstract void passBeadsAlongToNextKalahaNeighbour(int beadAmount);
	protected abstract void moveBeads();

}