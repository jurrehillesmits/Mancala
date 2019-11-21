package nl.sogyo.mancala;

class Player{
	private boolean Active;
	private Player OtherPlayer;
	
	//We initialize by giving a String ID and an ActiveOrNot nr that is either positive or negative
	Player(){
		this.Active = true;
		OtherPlayer = new Player(false,this);
	}
    Player(boolean Active,Player player){
        this.Active = Active;
        this.OtherPlayer = player;
    }
	//Here we set a reference to our otherplayer
	void SetOtherPlayer(Player OtherPlayer){
		this.OtherPlayer = OtherPlayer;
	}
	//We invert our own state and then tell the Otherplayer to do the same
	void SwapPlayers(){
		this.ChangeActiveState();
        this.OtherPlayer.ChangeActiveState();
	}
	//We invert our Active state here
	private void ChangeActiveState(){
		this.Active = !this.Active;
	}

	//We return our active state
	boolean GetActive(){
		return this.Active;
	}
	//We return our playername for printing
    Player GetOtherPlayer(){return this.OtherPlayer;}






	//This is only relevant if an external source wants to play the game until it ends

	//Currently housed under the players
	private boolean EndState =false;
	void GameEnded(){
		this.ChangeEndState();
		OtherPlayer.ChangeEndState();
	}
	void ChangeEndState(){
		this.EndState=true;
	}
	
	boolean IsGameStillGoing(){
		return !EndState;
	}
}