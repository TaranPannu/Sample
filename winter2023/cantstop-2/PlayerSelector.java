public class PlayerSelector {
    
    // player selector: get the text from the dropdown
    // menu in setup screen and procedurally assign
    // colours to enum types
    public PlayerSelector(){

    }
    public Player nextPlayer(Player curPlayer, int playerCount){

        switch (playerCount){

            case 4:
                switch (curPlayer){
                    case player1:
                        return Player.player2;
                    
                    case player2:
                        return Player.player3;
                    
                    case player3:
                        return Player.player4;
            
                    case player4:
                        return Player.player1;
                    
                    default: return null;
                }
            case 3:
                switch (curPlayer){
                    case player1:
                        return Player.player2;
                
                    case player2:
                        return Player.player3;
                
                    case player3:
                        return Player.player1;

                    default: return null;
                }
            case 2:
                switch (curPlayer){
                    case player1:
                        return Player.player2;
                
                    case player2:
                        return Player.player1;
                
                    default: return null;
                }

            default: return null;

        }
    }
}
