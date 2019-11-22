package nl.sogyo.mancala;

abstract class BeadContainer{
	Player myPlayer;

	int getContent() {
		return Content;
	}

	int Content;
	BeadContainer Neighbour;
	
	
	
	//Adding one to content
	void AddOneBead(){
		this.Content+=1;
	}
	//Here we obtain a reference to the neighbouring Bead Container
	void SetNeighbour(BeadContainer Neighbour){
		this.Neighbour = Neighbour;
	}
	protected abstract void AddOneBeadToSelfAndPassAmountToNextInLine(int beadAmount);
	protected abstract void MoveBeadAmountToActivePlayerKalaha(int beadAmount);
	protected abstract void passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(int bowlCount);
	protected abstract void checkIfAllBowlsOfTheActivePlayerAreEmpty(int bowlCount);
	protected abstract void moveBeadAmountToNextKalahaNeighbour(int bowlCount);
	protected abstract void passBeadsAlongToNextKalahaNeighbour(int beadAmount);
	public abstract void passMoveBeadsCommandAlongThisManyStepsExcludingKalahas(int steps);
	public int passGetContentCommandAlongThisManySteps(int steps){
		if((steps%14)>0){
			steps-=1;
			return Neighbour.passGetContentCommandAlongThisManySteps(steps);
		}
		else{
			return getContent();
		}
	}
	public boolean passGetPlayerActiveCommandAlongThisManySteps(int steps){
		if((steps%14)>0){
			steps-=1;
			return Neighbour.passGetPlayerActiveCommandAlongThisManySteps(steps);
		}
		else{
			return myPlayer.GetActive();
		}
	}
}