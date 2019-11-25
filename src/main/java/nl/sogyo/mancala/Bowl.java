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
				beadContainerList.add(new Bowl(myPlayer.GetOtherPlayer(),setup.get(i-1)));
			}
			else if(i==7){
				beadContainerList.add(new Kalaha(myPlayer,setup.get(i-1)));
			}
			else{
				beadContainerList.add(new Kalaha(myPlayer.GetOtherPlayer(),setup.get(i-1)));
			}
		}
	}
	private void setSequentialBeadContainersAsNeighbours(List<BeadContainer> beadContainerList) {
		for(int i =0;i<beadContainerList.size();i++){
			beadContainerList.get(i).setNeighbour(beadContainerList.get((i+1)%beadContainerList.size()));
		}
	}

	protected void MoveBeads(){
		if(this.myPlayer.GetActive()&&this.content >0){
			int Amount =GetBeadsFromBowl();
				neighbour.AddOneBeadToSelfAndPassAmountToNeighbour(Amount);
			}
		else{
			System.out.println("Invalid move try again");
		}
	}
	private int GetBeadsFromBowl(){
		int Beads = this.content;
		this.content =0;
		return Beads;
	}
	protected void AddOneBeadToSelfAndPassAmountToNeighbour(int beadAmount){
		this.addOneBead();
		beadAmount -=1;
		if(beadAmount >0){
				neighbour.AddOneBeadToSelfAndPassAmountToNeighbour(beadAmount);
				return;
		}
		this.TryToSteal();
		this.myPlayer.SwapPlayers();
		checkIfAllBowlsOfTheActivePlayerAreEmpty(0);
	}
    private void TryToSteal() {
        if(this.content ==1&&this.myPlayer.GetActive()){
            MoveContentToActivePlayerKalaha();
			passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(0);
        }
    }
	private void MoveContentToActivePlayerKalaha(){
		int Amount = this.content;
		this.content = 0;
		neighbour.MoveBeadAmountToActivePlayerKalaha(Amount);
	}
	protected void MoveBeadAmountToActivePlayerKalaha(int beadAmount){
		neighbour.MoveBeadAmountToActivePlayerKalaha(beadAmount);
	}
	protected void passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(int bowlCount){
		bowlCount +=1;
		if(bowlCount !=0){
			neighbour.passAlongCommandToOpposingBowlToEmptyIntoActivePlayerKalaha(bowlCount);
		}
		else{
			MoveContentToActivePlayerKalaha();
		}
	}
	protected void checkIfAllBowlsOfTheActivePlayerAreEmpty(int bowlCount){
		if(myPlayer.GetActive()){
			if(this.content !=0){
				return;
			}
			bowlCount +=1;
		}
		if(bowlCount ==6){
			moveBeadAmountToNextKalahaNeighbour(0);
			return;
		}
		neighbour.checkIfAllBowlsOfTheActivePlayerAreEmpty(bowlCount);
	}
	protected void moveBeadAmountToNextKalahaNeighbour(int bowlCount){
		passBeadsAlongToNextKalahaNeighbour(GetBeadsFromBowl());
		if(bowlCount <12){
			bowlCount +=1;
			neighbour.moveBeadAmountToNextKalahaNeighbour(bowlCount);
		}
	}
	protected void passBeadsAlongToNextKalahaNeighbour(int beadAmount){
		neighbour.passBeadsAlongToNextKalahaNeighbour(beadAmount);
	}
}