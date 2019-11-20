class Player{
	private String PlayerName;
	private boolean Active;
	private Player OtherPlayer;
	
	//We initialize by giving a String ID and an ActiveOrNot nr that is either positive or negative
	Player(String PlayerName,boolean Active){
		this.PlayerName = PlayerName;
		this.Active = Active;
	}
	//Here we set a reference to our otherplayer
	protected void SetOtherPlayer(Player OtherPlayer){
		this.OtherPlayer = OtherPlayer;
	}
	//We invert our own state and then tell the Otherplayer to do the same
	protected void SwapPlayers(){
		this.ChangeState();
		this.ChangeOtherPlayer();
	}
	//We invert our Active state here
	protected void ChangeState(){
		this.Active = !this.Active;
	}
	//We tell our OtherPlayer to invert their Active state
	private void ChangeOtherPlayer(){
		this.OtherPlayer.ChangeState();
	}
	//We return our active state
	protected boolean GetActive(){
		return this.Active;
	}
	//We return our playername
	protected String GetPlayerName(){
		return this.PlayerName;
	}


	//This is only relevant if an external source wants to play the game until it ends

	//Currently housed under the players
	private boolean EndState =false;
	protected void GameEnded(){
		this.ChangeEndState();
		OtherPlayer.ChangeEndState();
	}
	protected void ChangeEndState(){
		this.EndState=true;
	}
	
	protected boolean IsGameStillGoing(){
		return !EndState;
	}
}