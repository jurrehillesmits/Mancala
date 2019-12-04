package nl.sogyo.mancala;

class Player{
	private boolean active;
	private Player opponent;
	Player(){
		this.active = true;
		opponent = new Player(false,this);
	}
    Player(boolean active,Player player){
        this.active = active;
        this.opponent = player;
    }
	void swapTurn(){
		this.changeActiveState();
        this.opponent.changeActiveState();
	}
	private void changeActiveState(){
		this.active = !this.active;
	}
	boolean getActive(){
		return this.active;
	}
    Player getOpponent(){return this.opponent;}
}