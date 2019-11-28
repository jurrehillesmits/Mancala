package nl.sogyo.mancala;
class Player {
	private boolean Active;
	private Player OtherPlayer;
	Player(){
		this.Active = true;
		OtherPlayer = new Player(false,this);
	}
    Player(boolean Active, Player player){
        this.Active = Active;
        this.OtherPlayer = player;
    }
	void swapPlayers(){
		this.changeActiveState();
        this.OtherPlayer.changeActiveState();
	}
	private void changeActiveState(){
		this.Active = !this.Active;
	}
	boolean getActive(){
		return this.Active;
	}
    Player getOtherPlayer(){return this.OtherPlayer;}
}