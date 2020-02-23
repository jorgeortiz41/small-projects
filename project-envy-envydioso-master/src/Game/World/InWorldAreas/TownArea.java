package Game.World.InWorldAreas;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Statics.LightStatue;
import Game.World.Walls;


public class TownArea extends BaseArea  {
	Rectangle exit;
    Rectangle playerRect;
    public static boolean isInTown = false;

    private int imageWidth = 3680, imageHeight = 4000;
    public final static int playerXSpawn = -1000, playerYSpawn = -3180
    		;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> TownWalls;

    public TownArea(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name = "Town";
        handler.setXInWorldDisplacement(playerXSpawn);
        handler.setYInWorldDisplacement(playerYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;

        

        this.entityManager.AddEntity(handler.newEntity(handler, 1900, 3000, "InWorldState", "Town", "Merchant", null));
 //       this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle, handler, 1600, 1900, "InWorldState", "Mewtwo", "Town", "Mewtwo",100,25,40,1,8,12,20,10,20,10,1,5, "none", "Fire", null, null));

        
        TownWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls t : TownWalls) {
            t.tick();
        }
        if(!GameSetUp.LOADING) {
            entityManager.tick();
        }

    }

    @Override
    public void render(Graphics g) {
        super.render(g);


        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.fill(background);

        g.drawImage(Images.ScaledTown, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if (GameSetUp.DEBUGMODE) {
            for (Walls t : TownWalls) {

                if (t.getType().equals("Wall"))
                    g2.setColor(Color.black);
                else
                    g2.setColor(Color.PINK);

                t.render(g2);
            }
        }


        entityManager.render(g);

    }

    private void AddWalls() {


        					// Entrance
    	
        TownWalls.add(new InWorldWalls(handler, 1870, 1500, 220, 100, "Start Exit"));							// Exit at Start
        TownWalls.add(new InWorldWalls(handler, 1845, 3950, 280, 100, "End Exit"));							// Exit at End
        					//BUILD A WALL
        TownWalls.add(new InWorldWalls(handler,0,0,imageWidth,imageHeight-2500, "Wall"));                   //upper boundary
        TownWalls.add(new InWorldWalls(handler, 2120, 1500, 100, 220, "Wall"));//tree
        TownWalls.add(new InWorldWalls(handler, 2200, 1500, 100, 280, "Wall")); // tree
        TownWalls.add(new InWorldWalls(handler, 1180, 3000, 150, 300/2, "Wall")); //tree
        
//        TownWalls.add(new InWorldWalls(handler, 1900, 3000, 100, 100, "Wall")); //zard

        
        TownWalls.add(new InWorldWalls(handler, 1180, 2800, 150, 300, "Wall")); //tree
        TownWalls.add(new InWorldWalls(handler, 400, 2600, 800, 700, "Wall")); //Inn
        TownWalls.add(new InWorldWalls(handler, 400, 3300, 300, 350, "Wall")); //Lake
        TownWalls.add(new InWorldWalls(handler, 600, 3550, 1000, 100, "Wall")); //Lake
        TownWalls.add(new InWorldWalls(handler, 1450, 3100, 100, 300, "Wall")); //Inn
        TownWalls.add(new InWorldWalls(handler, 1150, 3200, 300, 100, "Wall")); //Inn
        TownWalls.add(new InWorldWalls(handler, 2220, 1700, 220, 100, "Wall")); //Fence
        TownWalls.add(new InWorldWalls(handler, 2420, 1700, 520, 550, "Wall")); //Armor store
        TownWalls.add(new InWorldWalls(handler, 2820, 1800, 720, 500, "Wall")); //tree
        TownWalls.add(new InWorldWalls(handler, 3525, 1800, 100, 1800, "Wall")); //tree
        TownWalls.add(new InWorldWalls(handler, 3325, 2500, 200, 100, "Wall")); //tree
        TownWalls.add(new InWorldWalls(handler, 3325, 2500, 100, 300, "Wall")); //tree
        TownWalls.add(new InWorldWalls(handler, 3000, 2650, 300, 600, "Wall")); //Big house
        TownWalls.add(new InWorldWalls(handler, 2500, 2650, 350, 800, "Wall")); //Big house
        TownWalls.add(new InWorldWalls(handler, 2350, 3300, 150, 300, "Wall")); //Big house
        TownWalls.add(new InWorldWalls(handler, 2250, 3450, 200, 600, "Wall")); //Big house
        TownWalls.add(new InWorldWalls(handler, 0, 4000, 4000, 20, "Wall")); //Back
        TownWalls.add(new InWorldWalls(handler, 1550, 3700, 150, 300, "Wall")); //Big house
        
        
        TownWalls.add(new InWorldWalls(handler, 400, 2500, 600, 100, "Wall")); //Inn
        TownWalls.add(new InWorldWalls(handler, 1180, 1500, 100, 620, "Wall"));	//Red Building
        TownWalls.add(new InWorldWalls(handler, 500, 2000, 720, 200, "Wall"));	//Red Building
        TownWalls.add(new InWorldWalls(handler, 550, 2000, 50, 700, "Wall"));	//Red Building
        TownWalls.add(new InWorldWalls(handler, 1550, 2050, 700, 900, "Wall")); // Fountain
        TownWalls.add(new InWorldWalls(handler, 1500, 1600, 220, 100, "Wall")); //
        TownWalls.add(new InWorldWalls(handler, 1720, 1500, 100, 220, "Wall")); // tree
        TownWalls.add(new InWorldWalls(handler, 1500, 1700, 180, 100, "Wall")); // tree
        TownWalls.add(new InWorldWalls(handler, 1250, 1600, 100, 100, "Wall")); // tree
        TownWalls.add(new InWorldWalls(handler, 2850, 2750, 180, 100, "Wall")); // tree
        TownWalls.add(new InWorldWalls(handler, 1250, 3100, 180, 100, "Wall")); // tree
        TownWalls.add(new InWorldWalls(handler, 1500, 3550, 180, 100, "Wall")); 
        TownWalls.add(new InWorldWalls(handler, 1440, 3300, 180, 100, "Wall")); 
    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return TownWalls;
    }
}

