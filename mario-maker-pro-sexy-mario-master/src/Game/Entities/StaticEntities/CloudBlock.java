package Game.Entities.StaticEntities;

import Main.Handler;
import Resources.Images;

public class CloudBlock extends BaseStaticEntity {

    public CloudBlock(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height,handler, Images.cloudBlock);
    }

}
