public class Moves {
    public static boolean whitePawn(int[] from , int[] to){
        int yPos = from[0] - to[0];
        int xPos = from[1] - to[1];
        if(yPos == 1 && Math.abs(xPos) == 1){
            if(! toFriend(from, to)) return true;
            else return false ;
        }
        if(yPos > 0 && yPos ==2 && xPos==0 && (toEmpty(to)) ){
            if(from[0] == 6 && toEmpty(to)){
                return true ;
            }
            else return false;
        }
        if(yPos > 0 && yPos ==1 && xPos==0 && toEmpty(to)) return true;
        
        return false ;
    }
    public static  boolean blackPawn(int[] from , int[] to){
        int yPos = from[0] - to[0];
        int xPos = from[1] - to[1];
        if(yPos == -1 && Math.abs(xPos) == 1){
            if(! toFriend(from, to)) return true;
            else return false ;
        }
        if(yPos < 0 && yPos == -2 && xPos==0 && (toEmpty(to)) ){
            if(from[0] == 1 && toEmpty(to)){
                return true ;
            }
            else return false;
        }
        if(yPos < 0 && yPos == -1 && xPos==0 && (toEmpty(to) )) return true;
        
        return false ;
    }
    public static boolean Rook(int[] from , int[] to){
       if( from[1] == to[1]) {
            int minCol = Math.min(from[0], to[0]); 
            int maxCol = Math.max(from[0], to[0]);
            for(int col = minCol+1 ; col <maxCol ; col++){
                int[] pos = new int[]{col , to[1]};
                if(! toEmpty(pos)) return false;
            }
            if(! toFriend(from, to)) return true ;
       }
       if( from[0] == to[0]) {
        int minRow = Math.min(from[1], to[1]); 
        int maxRow = Math.max(from[1], to[1]);
        for(int row = minRow+1 ; row <maxRow ; row++){
            int[] pos = new int[]{to[0] , row};
            if(! toEmpty(pos)) return false;
        }
        if(! toFriend(from, to)) return true ;
   }
       return false ;
    }
    public static boolean Knight(int[] from , int[] to){
        int yPos = from[0] - to[0];
        int xPos = from[1] - to[1];
        if(Math.abs(yPos) == 2 && Math.abs(xPos) == 1 && (! toFriend(from, to)) ) return true;
        if(Math.abs(yPos) == 1 && Math.abs(xPos) == 2 && (! toFriend(from, to) )) return true;
        return false;
    }
    public static boolean Bishub(int[] from , int[] to){
        int absYPos = Math.abs(from[0] - to[0]);
        int absXPos = Math.abs(from[1] - to[1]);
        if(absXPos == absYPos){
            int fromX = from[0] , fromY = from[1]  ;
            int toX = to[0] , toY = to[1] ;
            if(fromX > toX){
                if(fromY > toY){
                    int j = toY +1 ; // to y +
                    for(int i = toX + 1 ; i<fromX ; i++){
                        int[] pos = new int[]{i,j};
                        if(!toEmpty(pos)) return false;
                        j++; // ++
                    }
                    if(!toFriend(from, to)) return true;
                    return false;
                }
                if(fromY < toY){
                    int j = toY - 1 ; // to y -
                    for(int i = toX + 1 ; i<fromX ; i++){
                        int[] pos = new int[]{i,j};
                        if(!toEmpty(pos)) return false;
                        j--; // --
                    }
                    if(!toFriend(from, to)) return true;
                    return false;
                }
                
            }
            if(fromX < toX){
                if(fromY > toY){
                    int j = fromY - 1 ; // from y -
                    for(int i = fromX + 1 ; i<toX ; i++){
                        int[] pos = new int[]{i,j};
                        if(!toEmpty(pos)) return false;
                        j--; // --
                    }
                    if(!toFriend(from, to)) return true;
                    return false;
                }

                if(fromY < toY){
                    int j = fromY + 1 ; // from y +
                    for(int i = fromX + 1 ; i<toX ; i++){
                        int[] pos = new int[]{i,j};
                        if(!toEmpty(pos)) return false;
                        j++; // ++
                    }
                    if(!toFriend(from, to)) return true;
                    return false;
                }
            }
        }
        else return false;

        return false; // this move is to the same square "thanks chat gpt" this bug made me cry
        
    }
    public static boolean Quen(int[] from , int[] to){
        if(Bishub(from , to) || Rook(from,to)) return true;
        return false;
    }
    public static boolean King(int[] from , int[] to){
        int absYPos = Math.abs(from[0] - to[0]);
        int absXPos = Math.abs(from[1] - to[1]);
        if(absXPos == 1  && (!toFriend(from, to))){
            if(absYPos ==1 || absYPos ==0) return true;
        }
        if(absYPos == 1  && (!toFriend(from, to))){
            if(absXPos ==1 || absXPos ==0) return true;
        }
        return false;
    }



    public static boolean toBlack(int[] to){
        return Character.isLowerCase((Game.board[to[0]][to[1]]).charAt(0)) ;
    }

    public static boolean toWhite(int[] to){
        return Character.isUpperCase((Game.board[to[0]][to[1]]).charAt(0)) ;
    }

    public static boolean toEmpty(int[] to){
        return  ((Game.board[to[0]][to[1]]).charAt(0) == '.') ;
    }

    public static boolean toFriend(int[] from ,int[] to){
        String current = Game.board[from[0]][from[1]];
        String selected = Game.board[to[0]][to[1]];
        if(Character.isUpperCase(current.charAt(0)) && Character.isUpperCase(selected.charAt(0)) ) return true ;
        if(Character.isLowerCase(current.charAt(0)) && Character.isLowerCase(selected.charAt(0)) ) return true ;
        return false ;
    }
}
