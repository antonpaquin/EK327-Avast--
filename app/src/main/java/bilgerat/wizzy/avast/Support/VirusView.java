package bilgerat.wizzy.avast.Support;

import android.content.Context;
import android.view.View;

/**
 * Created by Anton on 12/6/2015.
 *
 */
public class VirusView extends View{
    /*
     * Extends view
     * Custom class for showing virus images
     */

    private int[][] colors = new int[13][3];

    public VirusView(Context context) {
        super(context);
    }

    private void greyColors() {
        for (int i=0; i<colors.length; i++) {
            for (int j=0; j<colors[0].length; j++) {
                colors[i][j] = 128;
            }
        }
    }

    private void setColor(int index, int r, int g, int b) {
        colors[index] = new int[] {r,g,b};
        
    }


}
