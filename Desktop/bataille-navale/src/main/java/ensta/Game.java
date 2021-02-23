package ensta;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import ensta.navires.*;

public class Game implements  Serializable{

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sc;

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        if (!loadSave()) {
            // init attributes
            System.out.println("entre ton nom:");

            // TODO use a scanner to read player name
            Scanner sc = new Scanner(System.in);
            String PlayerName = sc.nextLine();
            // TODO init boards
            Board b1, b2;
            b1 = new Board(PlayerName);
            b2 = new Board("IA");
            // TODO init this.player1 & this.player2
             List<AbstractShip> shipsPlayer1 = createDefaultShips();
             player1 = new Player(b1, b2, shipsPlayer1);
             List<AbstractShip> shipsPlayer2 = createDefaultShips();
             player2 = new AIPlayer(b2, b1, shipsPlayer2);
             b1.print();
             // place player ships
            player1.putShips();
            player2.putShips();
        }else {
            try {
                ObjectInputStream objectInputStream =  new ObjectInputStream(new FileInputStream(SAVE_FILE));
                return (Game) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this;

    }
    public Game initMultiJoueur() {
        if (!loadSave()) {
            // init attributes
            System.out.println("entre le nom du 1er joueur");
            // TODO use a scanner to read player name
            Scanner sc = new Scanner(System.in);
            String PlayerName1 = sc.nextLine();
            System.out.println("entre le nom du 2eme joueur");
            String PlayerName2 = sc.nextLine();
            // TODO init boards
            Board b1, b2;
            b1 = new Board(PlayerName1);
            b2 = new Board(PlayerName2);
            // TODO init this.player1 & this.player2
            List<AbstractShip> shipsPlayer1 = createDefaultShips();
            player1 = new Player(b1, b2, shipsPlayer1);
            List<AbstractShip> shipsPlayer2 = createDefaultShips();
            player2 = new Player(b2, b1, shipsPlayer2);
            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }else {
            try {
                ObjectInputStream objectInputStream =  new ObjectInputStream(new FileInputStream(SAVE_FILE));
                return (Game) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this;
    }


    /* ***
     * Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
             // TODO player1 send a hit
            System.out.println(b1.getNom());
            b1.print();
            hit = player1.sendHit(coords);
            boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)
            try{
                b1.setHit(strike,coords[0],coords[1]);
            }
            catch(Exception e){}
            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = player2.sendHit(coords); // TODO player2 send a hit.

                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sc.close();
    }


    private void save() {

        try {
            // TODO bonus 2 : uncomment
              if (!SAVE_FILE.exists()) {
               SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
              }

            // TODO bonus 2 : serialize players
            ObjectOutputStream objectoutputstream =  new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
            objectoutputstream.writeObject(this) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadSave() {

        if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players
                ObjectInputStream objectInputStream =  new ObjectInputStream(new FileInputStream(SAVE_FILE));
                objectInputStream.readObject() ;
                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[1])),
                (coords[0] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}


