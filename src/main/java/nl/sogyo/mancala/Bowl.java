class Bowl extends BeadContainer{
private Kalaha myKalaha;
private Bowl Opposing;

	Bowl(Player myPlayer, String Identification,int Content){
		this.myPlayer = myPlayer;
		this.Identification = Identification;
		this.Content = Content;
	}
	
	//Here we add a refernce to a Kalaha, this kalaha should have the same myPlayer object as this bowl
	protected void SetmyKalaha(Kalaha kalaha){
		this.myKalaha = kalaha;
	}
	//Here we add a reference to the opposing bowl. If we werent allowed to directly set an opposing bowl we could move through the neighbours and count the distance until a switch. Then by moving as far as that distance we reach the opposing bowl
	protected void SetOpposing(Bowl Opposing){
		this.Opposing = Opposing;
	}
	//If the neighbour bowl player is a different player and myPlayer is currently active we should go the kalaha
	private boolean GoToKalaha(){
		if(Neighbour.DifferentPlayer(myPlayer)&&myPlayer.GetActive()){
			return true;
		}
		return false;
		
	}
	//Here we check if the player reference is the same for this bowl and its neighbour
	protected boolean DifferentPlayer(Player Other){
		if(Other==myPlayer){
			return false;
		}
		return true;
	}
	//Here we get the beads from this bowl and tell our neighbour to start an AddOneBead chain using that amount
	public void MoveBeads(){
		//If the player that is referenced by the bowl is active this move is valid
		if(this.myPlayer.GetActive()&&this.Content>0){
			int Amount =GetBeadsFromBowl();
			if(GoToKalaha()){
				myKalaha.AddOneBead(Amount);
				return;
			}
			else{
				Neighbour.AddOneBead(Amount);
				return;
			}
		}
		else{
			System.out.println("Invalid move try again");
		}
	}
	//This returns the contents of the bowl and sets the contents to zero
	private int GetBeadsFromBowl(){
		int Beads = this.Content;
		this.Content =0;
		return Beads;
	}
	//This adds one bead to this bowl and then either tells the next bowl to continue this cycle or if it was the last bead it checks if this bowl was empty to do a Steal move. Afterwards it tells the players to swap
	protected void AddOneBead(int Amount){
		//We add one bead to this bowl and lower the amount of beads by one
		this.AddOneBead();
		Amount -=1;
		//We tell the neighbour to continue the chain if there are one or more beads left in the amount and we stop the action in this bowl by returning
		if(Amount>0){
			if(GoToKalaha()){
				myKalaha.AddOneBead(Amount);
				return;
			}
			else{
				Neighbour.AddOneBead(Amount);
				return;
			}
		}
		//If the bowl content was empty(so now only contains 1) and the player that is assigned to this bowl is active we perform the stealing action
		if(this.Content==1&&this.myPlayer.GetActive()){
			MoveContentToKalaha(myKalaha);
			Opposing.MoveContentToKalaha(myKalaha);
		}
		//Here we check if the game is ended and if so we add all of the beads to their respective kalahas
		this.StartAreBowlsEmptyCheck();
		//We can call on myplayer no matter whose turn it is since they set the other as inactive when they go active and vice versa
		this.myPlayer.SwapPlayers();
	}
	//We give the kalaha reference in the method so we can give our opposing bowl the same kalaha reference
	protected void MoveContentToKalaha(Kalaha ThisKalaha){
		int Amount = this.Content;
		this.Content = 0;
		ThisKalaha.Add(Amount);
	}
	//If no kalaha is specified we default to the linked kalaha
	protected void MoveContentToKalaha(){
		this.MoveContentToKalaha(this.myKalaha);
	}


	private boolean AllBowlsOfAnyOnePlayerAreEmpty =false;
	//Here we start the sequence of questions to determine if any bowl is empty by having the bowls pass around a boolean and have them change it if they are not empty
	protected void StartAreBowlsEmptyCheck(){
		Neighbour.AreBowlsEmpty(true,true,this.Identification,this.myPlayer);
		//This is true only if one of the players bowls are all empty, so this is the game end state
		if(AllBowlsOfAnyOnePlayerAreEmpty){
			Neighbour.ObtainEndScoreByAddingToKalaha(this.Identification);
			myPlayer.GameEnded();
		}
	}
	//Checks if this bowl is empty
	protected boolean IsEmpty(){
		if(this.Content==0){
			return true;
		}
		return false;
	}
	//Here we check if this bowl is empty,if so we modify the boolean that this bowl belongs to, and subsequently we pass that information to the next bowl until we reach the start where we change a value is any of the checks are still true
	protected void AreBowlsEmpty(boolean CheckBowlsOfMyPlayer,boolean CheckBowlsOfOtherPlayer, String Identification,Player ComparisonPlayer){
		//This checks if any bowls belonging to the same player as the bowl that started the sequence are not empty
		if(ComparisonPlayer==this.myPlayer){
			if(!this.IsEmpty()){
				CheckBowlsOfMyPlayer=false;
			}
		}
		//This checks if any bowls belonging to a different player from the bowl that started the sequence are not empty
		else{
			if(!this.IsEmpty()){
			CheckBowlsOfOtherPlayer = false;
			}
		}
		//We only continue the sequence if the bowl that is asked if it is empty is not the starting bowl
		if(!this.Identification.equals(Identification)){
			Neighbour.AreBowlsEmpty(CheckBowlsOfMyPlayer,CheckBowlsOfOtherPlayer,Identification,ComparisonPlayer);
			return;
		}
		//If any of the checks remained true we set AllBowlsOfAnyOnePlayerAreEmpty to true in the start bowl
		if(CheckBowlsOfMyPlayer||CheckBowlsOfOtherPlayer){
			this.AllBowlsOfAnyOnePlayerAreEmpty=true;
		}
	}
	//The bowl tells the kalaha to add the content of the bowl to itself, then if it is not the bowl who started the sequence it it tells its neighbour to repeat this method.
	protected void ObtainEndScoreByAddingToKalaha(String ID){
		this.MoveContentToKalaha();
		if(!this.Identification.equals(ID)){
			Neighbour.ObtainEndScoreByAddingToKalaha(ID);
		}
	}
	
	
	
	
}