import java.util.*;
import java.util.regex.*;

public class ConstructMancala{

	List<Bowl> BowlList = new ArrayList<Bowl>();
	Player playerOne;
	Player playerTwo;
	Kalaha KalahaOne;
	Kalaha KalahaTwo;
	//We construct 12 bowls, and give each reference to a neighbour bowl, a specific player, a kalaha and an opposing bowl
	public ConstructMancala(){
		playerOne = new Player("One",true);
		playerTwo = new Player("Two",false);
		playerOne.SetOtherPlayer(playerTwo);
		playerTwo.SetOtherPlayer(playerOne);
		
		KalahaOne = new Kalaha(playerOne,"One");
		KalahaTwo = new Kalaha(playerTwo,"Two");
		//Here we create a set of bowls that all reference eachother, consisting of 6 bowls with playerOne and 6 bowls with playerTwo
		for(int i =0;i<12;i++){
			if(i<6){
				BowlList.add(new Bowl(playerOne,Integer.toString(i),4));
			}
			else if(i>5){
				BowlList.add(new Bowl(playerTwo,Integer.toString(i),4));
			}
			if(BowlList.size()>1){
				BowlList.get(i-1).SetNeighbour(BowlList.get(i));
			}
		}
		BowlList.get(BowlList.size()-1).SetNeighbour(BowlList.get(0));
		//Adding the kalahas to the bowls
		for(int i=0;i<6;i++){
			BowlList.get(i).SetmyKalaha(KalahaOne);
		}
		for(int i =6;i<12;i++){
			BowlList.get(i).SetmyKalaha(KalahaTwo);
		}
		//Adding Neighbours to the Kalahas
		KalahaOne.SetNeighbour(BowlList.get(6));
		KalahaTwo.SetNeighbour(BowlList.get(0));
		
		//Setting Opposing
		for(int i = 0;i<6;i++){
			BowlList.get(i).SetOpposing(BowlList.get(BowlList.size()-1-i));
			BowlList.get(BowlList.size()-1-i).SetOpposing(BowlList.get(i));
		}
	}
	//Printing all of the bowls and the kalahas in order
	private void PrintState(){
		
		for (int i=0;i<12;i++){
			if(i<10){
			System.out.println("Bowl"+BowlList.get(i).GetIdentification()+":  "+BowlList.get(i).GetContent());
			}
			else{
				System.out.println("Bowl"+BowlList.get(i).GetIdentification()+": "+BowlList.get(i).GetContent());
			}
			if(i==5){
				System.out.println("Kalaha1:"+KalahaOne.GetContent());
			}
			if(i==11){
				System.out.println("Kalaha2:"+KalahaTwo.GetContent());
			}
		}
		System.out.println("______________________");
	}
	//By getting an int we get a specific bowl and tell the specified bowl to start the moveBeads method
	private void SelectBowlAndDoMove(int i){
		BowlList.get(i).MoveBeads();
		PrintState();
	}
	
	private int PlayerInput(Scanner input){
		int Number;
		String s;
		while(true){
			s = input.nextLine();
			if(s.matches("[0-9]+")){
				Number = Integer.parseInt(s);
				if(Number>-1&&Number<12){
					break;
				}
			}
			System.out.println("Invalid input");
		}
		return Number;
	}
	
	public void RunGame(){
		Scanner input = new Scanner(System.in);
		this.PrintState();
		while(this.playerOne.IsGameStillGoing()){
			this.SelectBowlAndDoMove(PlayerInput(input));
		}
		input.close();
	}
	
	
	
	public static void main(String[] args) {
		ConstructMancala x = new ConstructMancala();
		x.RunGame();
		
		/*
		//This sequence ends in a capture through empty bowl event
		x.SelectBowlAndDoMove(5);
		x.SelectBowlAndDoMove(8);
		x.SelectBowlAndDoMove(0);
		*/
		
		/*
		//This sequence shows that if a move ends in a kalaha the turn does not switch
		x.SelectBowlAndDoMove(2);
		x.SelectBowlAndDoMove(0);
		//Now the turn has switched and this move gives back invalid
		x.SelectBowlAndDoMove(1);
		*/
		
		/*
		//This Sequence triggers the bowls to empty themselves into the kalahas
		x.SelectBowlAndDoMove(5);
		x.SelectBowlAndDoMove(8);
		x.SelectBowlAndDoMove(0);
		x.SelectBowlAndDoMove(7);
		x.SelectBowlAndDoMove(9);
		x.SelectBowlAndDoMove(1);
		x.SelectBowlAndDoMove(8);
		x.SelectBowlAndDoMove(5);
		x.SelectBowlAndDoMove(0);
		x.SelectBowlAndDoMove(11);
		x.SelectBowlAndDoMove(5);
		x.SelectBowlAndDoMove(4);
		x.SelectBowlAndDoMove(8);
		x.SelectBowlAndDoMove(5);
		x.SelectBowlAndDoMove(2);
		x.SelectBowlAndDoMove(7);
		x.SelectBowlAndDoMove(0);
		x.SelectBowlAndDoMove(6);
		x.SelectBowlAndDoMove(1);
		x.SelectBowlAndDoMove(7);
		x.SelectBowlAndDoMove(2);
		*/
	}
	
}