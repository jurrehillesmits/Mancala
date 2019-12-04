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
	void passNeighbourReference(BeadContainer neighbour){
		if(this.neighbour!=null){
            this.neighbour.passNeighbourReference(neighbour);
		}
		else{
            this.setNeighbour(neighbour);
		}
	}

    public void passMoveBeadsCommandAlongThisManySteps(int steps){
	    try {
            if (steps > 0) {
                steps -= 1;
                neighbour.passMoveBeadsCommandAlongThisManySteps(steps);
            } else {
                moveBeads();
            }
        }
	    catch(IllegalArgumentException e ){
	        System.out.println(e.getMessage());
        }
    }
    public int passGetContentCommandAlongThisManySteps(int steps){
        if(steps>0){
            steps-=1;
            return neighbour.passGetContentCommandAlongThisManySteps(steps);
        }
        else{
            return getContent();
        }
    }
    public boolean passGetPlayerActiveCommandAlongThisManySteps(int steps){
        if(steps>0){
            steps-=1;
            return neighbour.passGetPlayerActiveCommandAlongThisManySteps(steps);
        }
        else{
            return myPlayer.getActive();
        }
    }
//Bowl 0  |Steps 0
//Bowl 1  |Steps 1
//Bowl 2  |Steps 2
//Bowl 3  |Steps 3
// Bowl 4  |Steps 4
// Bowl 5  |Steps 5
// Kalaha 1|Steps 6
// Bowl 6  |Steps 7
// Bowl 7  |Steps 8
// Bowl 8  |Steps 9
// Bowl 9 |Steps 10
// Bowl 10|Steps 11
// Bowl 11|Steps 12
// Kalaha2|Steps 13


	protected abstract void addOneBeadAndPassRemainingToNeighbour(int beadAmount);
	protected abstract void moveBeadsToActivePlayerKalaha(int beadAmount);
	protected abstract void stealFromOpposingBowl(int bowlCount);
	protected abstract void checkIfAllBowlsOfTheActivePlayerAreEmpty(BeadContainer start);
	protected abstract void emptyIntoOwnKalaha(BeadContainer start);
	protected abstract void passBeadsAlongToNextKalaha(int beadAmount);
	protected abstract void moveBeads();

}