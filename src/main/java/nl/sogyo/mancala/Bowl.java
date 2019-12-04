package nl.sogyo.mancala;

import java.util.ArrayList;

import java.util.List;

public class Bowl extends BeadContainer{
	private static final List<Integer> standardStart = new ArrayList<>(List.of(4,4,4,4,4,4,0,4,4,4,4,4,4,0));
	public Bowl(){
		this(standardStart);
	}
	public Bowl(List<Integer> setup){
		this.myPlayer = new Player();
		this.content = setup.get(0);
		List<BeadContainer> beadContainerList = new ArrayList<>();
		beadContainerList.add(this);
		createBeadContainerList(setup, beadContainerList);
		setSequentialBeadContainersAsNeighbours(beadContainerList);
	}
	private Bowl(Player myPlayer, int content){
		this.myPlayer = myPlayer;
		this.content = content;
	}
	private void createBeadContainerList(List<Integer> setup, List<BeadContainer> beadContainerList) {
		for(int i =2;i<=14;i++){
			if(i<7){
				beadContainerList.add(new Bowl(myPlayer,setup.get(i-1)));
			}
			else if(i>7&&i<14){
				beadContainerList.add(new Bowl(myPlayer.getOpponent(),setup.get(i-1)));
			}
			else if(i==7){
				beadContainerList.add(new Kalaha(myPlayer,setup.get(i-1)));
			}
			else{
				beadContainerList.add(new Kalaha(myPlayer.getOpponent(),setup.get(i-1)));
			}
		}
	}
	private void setSequentialBeadContainersAsNeighbours(List<BeadContainer> beadContainerList) {
		for(int i =0;i<beadContainerList.size();i++){
			beadContainerList.get(i).setNeighbour(beadContainerList.get((i+1)%beadContainerList.size()));
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
		this.TryToSteal();
		this.myPlayer.swapTurn();
		neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(this);
	}
    private void TryToSteal() {
        if(this.content ==1&&this.myPlayer.getActive()){
            MoveContentToActivePlayerKalaha();
			stealFromOpposingBowl(0);
        }
    }
	private void MoveContentToActivePlayerKalaha(){
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
			MoveContentToActivePlayerKalaha();
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