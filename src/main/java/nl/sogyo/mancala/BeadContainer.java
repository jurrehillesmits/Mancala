abstract class BeadContainer{
	protected Player myPlayer;
	protected String Identification;
	protected int Content;
	protected Bowl Neighbour;
	
	
	
	//Adding one to content
	protected void AddOneBead(){
		this.Content+=1;
	}
	
	//Here we obtain a reference to the neighbouring bowl(even for the kalaha)
	protected void SetNeighbour(Bowl Neighbour){
		this.Neighbour = Neighbour;
		
	}
	
	protected abstract void AddOneBead(int Amount);
	
	protected String GetIdentification(){
		return this.Identification;
		
	}
	
	protected int GetContent(){
		return this.Content;
	}
}