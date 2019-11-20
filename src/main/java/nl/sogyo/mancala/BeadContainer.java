package nl.sogyo.mancala;

abstract class BeadContainer{
	Player myPlayer;
	String Identification;
	int Content;
	Bowl Neighbour;
	
	
	
	//Adding one to content
	void AddOneBead(){
		this.Content+=1;
	}
	
	//Here we obtain a reference to the neighbouring bowl(even for the kalaha)
	void SetNeighbour(Bowl Neighbour){
		this.Neighbour = Neighbour;
		
	}
	
	protected abstract void AddOneBead(int Amount);
	
	String GetIdentification(){
		return this.Identification;
		
	}
	
	int GetContent(){
		return this.Content;
	}
}