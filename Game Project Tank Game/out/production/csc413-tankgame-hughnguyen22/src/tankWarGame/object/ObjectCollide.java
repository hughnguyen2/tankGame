package tankWarGame.object;

import java.awt.*;

public interface ObjectCollide {

    public void checkIfCollided(ObjectCollide c);

    public Rectangle getRectangle();
}
