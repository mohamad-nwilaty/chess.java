import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    public static String board[][] = new String[8][8] ;
    public static boolean on = true; 
    public static ArrayList<Character> deadWhite = new ArrayList<>();
    public static ArrayList<Character> deadBlack = new ArrayList<>();
    public static boolean trunCount = true ;
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner((System.in));
        initializeBoard();
        printBoard();
        System.out.println("enter the first move the space then the secound one \n (move in chess notation ex. e2 e4)");
        while(on){
            String from = input.next();
            String to = input.next();
            movePeice(from, to);
            printBoard();
            System.out.println();
            
        }

    }

    public static void initializeBoard(){

        board[0] = new String[]{"r","n","b","q","k","b","n","r"};
        board[1] = new String[]{"p","p","p","p","p","p","p","p"};
        for(int i=2 ; i<6 ; i++){
            board[i] = new String[]{".",".",".",".",".",".",".","."};
        }
        board[6] = new String[]{"P","P","P","P","P","P","P","P"};
        board[7] = new String[]{"R","N","B","Q","K","B","N","R"};
    }

    public static void printBoard(){
        System.out.println("    a b c d e f g h\n");
        for(int i=0 ; i<8 ; i++){
            System.out.print(8 - i+ "   ");
            for(int j=0 ; j<8 ; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.print( "  " + (8 - i));
            System.out.println();
        }
        System.out.println("\n    a b c d e f g h");
    }

    public static int[] chessNotation(String pos){
        int xPos = 8 - Character.getNumericValue(pos.charAt(1));  
        int yPos = pos.charAt(0) - 'a' ;     
        int[] arrPos = new int[]{xPos , yPos};
        return arrPos;
    } 

    public static void movePeice(String from, String to) {
        try {
            int[] From = chessNotation(from);
            int[] To = chessNotation(to);
    
            if (checkValid(From) && checkValid(To) && !board[From[0]][From[1]].equals(".")) {
                if (legalMove(from, to) && turns(From)) {
                    killWhite(To); // here since that we know that the move is legal and that we are moving a peice we check if the 
                    killBlack(To);// move is to a peice if so we add it to a list and if its a king we break out of the loop
                    board[To[0]][To[1]] = board[From[0]][From[1]];
                    board[From[0]][From[1]] = ".";
                    

                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter valid chess positions.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Please enter valid chess positions.");
        }
    }

    public static boolean checkValid(int[] pos){    
        if((pos[0] >= 0 && pos[0] < 8) && (pos[1] >=0 && pos[1] < 8 )){
            return true ;
        }
        else{
            return false ;
        }

    }

    public static boolean legalMove(String From , String To){
        int[] from = chessNotation(From);
        int[] to = chessNotation(To);
        String peice = board[from[0]][from[1]]; 
        boolean isWhiter = Character.isUpperCase(peice.charAt(0)) ; // this boolean is to call a different methode for white and black pawns
        peice = peice.toUpperCase() ; // convert to uppercase to call same methode for the rest of the peices
        
        switch (peice) {
            case "P":
                return isWhiter ? Moves.whitePawn(from, to) : Moves.blackPawn(from, to) ;
            case "R":
                return Moves.Rook(from , to);
            case "N":
                return Moves.Knight(from , to);
            case "B":
                return Moves.Bishub(from , to);
            case "Q":
                return Moves.Quen(from , to);
            case "K":
                return Moves.King(from , to);
            default:
            return true;
        }
        
    }

    public static void killWhite(int[] to){
        char peice = board[to[0]][to[1]].charAt(0);

        if(Character.isUpperCase(peice)){
            if(peice == 'K'){
                on = false ;
                System.out.println("Black wins !! ");
                printDead();
            } 
            deadWhite.add(peice);
        }
     }

    public static void killBlack(int[] to){
        char peice = board[to[0]][to[1]].charAt(0);
        if(Character.isLowerCase(peice)){
            if(peice == 'k'){
                on = false ;
                System.out.println("white wins !! ");
                printDead();

            } 
            deadBlack.add(peice);
        }
    }
    public static void printDead(){
        System.out.println("dead white peices: ");
        for(int i=0 ; i<deadWhite.size() ; i++){
            System.out.print(deadWhite.get(i) + " ");
        }
        System.out.println("\ndead black peices: ");
        for(int i=0 ; i<deadBlack.size() ; i++){
            System.out.print(deadBlack.get(i) + " ");
        }
        System.out.println("\n the last board:\n");
        
    }
    public static boolean turns(int[] from) {
        char peice = board[from[0]][from[1]].charAt(0);
        if(Character.isUpperCase(peice) && trunCount) {
            trunCount = !trunCount ;
            return true ;
        }
        if(Character.isLowerCase(peice) && !trunCount) {
            trunCount = !trunCount ;
            return true ;
        }
        System.out.println("not your turn !");
        return false ;
    }

}
