class Kalaha extends BeadContainer{
	
	
	Kalaha(Player myPlayer, String Identification){
		this.myPlayer = myPlayer;
		this.Identification = Identification;
		this.Content =0;
	}
	
	protected void AddOneBead(int Amount){
		//We add one bead to this kalaha and lower the amount of beads by one
		this.AddOneBead();
		Amount -=1;
		//We tell the neighbour to continue the chain if there are one or more beads left in the amount
		if(Amount>0){
			Neighbour.AddOneBead(Amount);
			return;
		}
		Neighbour.StartAreBowlsEmptyCheck();
		//We do not swap the turn if the AddOneBead chain ends here so in effect we repeat the turn since the active player did not change
	}
	//A way to tell the Kalaha to add an amount to itself
	protected void Add(int Amount){
		this.Content+=Amount;
	}
	
	
}