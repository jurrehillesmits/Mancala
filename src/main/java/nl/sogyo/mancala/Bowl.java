package nl.sogyo.mancala;

import java.util.ArrayList;

import java.util.List;

public class Bowl extends BeadContainer{
	private static final List<Integer> standardStart = new ArrayList<>(List.of(4,4,4,4,4,4,0,4,4,4,4,4,4,0));
	public Bowl(){
		this(standardStart);
	}
	public Bowl(List<Integer> setup) throws IllegalArgumentException{
		if(setup.size()%2!=0){
			throw new IllegalArgumentException("Setup list needs to contain an even amount of values");
		}
		int number = 0;
		this.myPlayer = new Player();
		this.content = setup.get(number);
		number++;
		setNeighbour(new Bowl(myPlayer,setup,number,this));
	}
	 Bowl(Player myPlayer,List<Integer> setup,int number, BeadContainer start){
		this.myPlayer = myPlayer;
		this.content = setup.get(number);
		number++;
		if(number!=(setup.size()/2)-1&&number!=(setup.size()-1)) {
			setNeighbour(new Bowl(myPlayer,setup,number,start));
		}
		else {
			setNeighbour(new Kalaha(myPlayer, setup, number, start));
		}
}
	protected void moveBeads(){
		if(this.myPlayer.getActive()&&this.content >0){
			int Amount = getBeadsFromBowl();
				neighbour.addOneBeadAndPassRemainingToNeighbour(Amount);
			}
		else{
			throw new IllegalArgumentException("Illegal move");
		}
	}
	private int getBeadsFromBowl(){
		int beads = this.content;
		this.content =0;
		return beads;
	}
	protected void addOneBeadAndPassRemainingToNeighbour(int beadAmount){
		this.addOneBead();
		beadAmount -=1;
		if(beadAmount >0){
				neighbour.addOneBeadAndPassRemainingToNeighbour(beadAmount);
				return;
		}
		this.tryToSteal();
		this.myPlayer.swapTurn();
		neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(this);
	}
    private void tryToSteal() {
        if(this.content ==1&&this.myPlayer.getActive()){
            moveContentToActivePlayerKalaha();
			stealFromOpposingBowl(0);
        }
    }
	private void moveContentToActivePlayerKalaha(){
		int Amount = this.content;
		this.content = 0;
		neighbour.moveBeadsToActivePlayerKalaha(Amount);
	}
	protected void moveBeadsToActivePlayerKalaha(int beadAmount){
		neighbour.moveBeadsToActivePlayerKalaha(beadAmount);
	}
	protected void stealFromOpposingBowl(int bowlCount){
		bowlCount +=1;
		if(bowlCount !=0){
			neighbour.stealFromOpposingBowl(bowlCount);
		}
		else{
			moveContentToActivePlayerKalaha();
		}
	}
	protected void checkIfAllBowlsOfTheActivePlayerAreEmpty(BeadContainer start){
		if(myPlayer.getActive()){
			if(this.content !=0){
				return;
			}
		}
		if(this==start){
			neighbour.emptyIntoOwnKalaha(this);
			return;
		}
		neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(start);
	}
	protected void emptyIntoOwnKalaha(BeadContainer start){
		passBeadsAlongToNextKalaha(getBeadsFromBowl());
		if(this!=start){
			neighbour.emptyIntoOwnKalaha(start);
		}
	}
	protected void passBeadsAlongToNextKalaha(int beadAmount){
		neighbour.passBeadsAlongToNextKalaha(beadAmount);
	}
}