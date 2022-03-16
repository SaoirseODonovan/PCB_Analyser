package sample;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {
@Test
    public void testUnion(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        int width = 5;
        int height = 5;
        int i = 0;
        int[] exampleImage = {1,0,1,1,1,1,0,0,1,0,1,0,1,0,1,0,0,0,1,1,1,1,0,0,0}; //25 = size
        for (int j = 0; j < exampleImage.length; j++) {
            if (exampleImage[j] != 0){
                exampleImage[i] = i;
            }
            else {
                exampleImage[i] = 0;
            }
            i++;
        }
        for (int c = 0; c < height; c++) {
            for (int d = 0; d < width; d++) {
                if (exampleImage[c * width + d] != 0 && exampleImage[c * width + d + 1] != 0) {
                    Controller.union(exampleImage, c * width + d, c * width + d + 1);
                }
                if (c < height - 1 && exampleImage[c * width + d] != 0 && exampleImage[c * width + d + width] != 0) {
                    Controller.union(exampleImage, c * width + d, c * width + d + width);
                }
            }

        }

        for (int j = 0; j < exampleImage.length; j++) {
            if (exampleImage[j] != 0 && !arrayList.contains(Controller.find(exampleImage, j))) {
                arrayList.add(Controller.find(exampleImage, j));
            }
        }
        System.out.println(arrayList.size());
        assertEquals(3,arrayList.size());
        assertNotEquals(1,arrayList.size());
    }

}