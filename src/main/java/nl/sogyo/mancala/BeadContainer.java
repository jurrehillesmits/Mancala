package nl.sogyo.mancala;

abstract class BeadContainer{
	Player myPlayer;
	int Content;
	BeadContainer Neighbour;
	
	
	
	//Adding one to content
	void AddOneBead(){
		this.Content+=1;
	}
	
	//Here we obtain a reference to the neighbouring bowl(even for the kalaha)
	void SetNeighbour(BeadContainer Neighbour){
		this.Neighbour = Neighbour;
	}
	
	protected abstract void AddOneBeadToSelfAndPassAmountToNextInLine(int Amount);



	protected abstract void MoveBeadAmountToActivePlayerKalaha(int Amount);


	protected abstract void PassAlongCommandToOpposingBowlToEmptyToKalaha(int Amount);


	int GetContent(){
		return this.Content;
	}
}