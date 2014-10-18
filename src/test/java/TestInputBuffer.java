import middle.InputBuffer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ksh on 2014-10-19.
 */
public class TestInputBuffer {
    @Test
    public void testSelectAndGet(){
        InputBuffer inputBuffer = InputBuffer.getInstance();
        List<Integer> selectList = new ArrayList<Integer>();
        selectList.add(1);
        selectList.add(3);
        selectList.add(2);
        for(Integer selectionFloor : selectList){
            inputBuffer.selectFloorInElevator(selectionFloor);
        }
        assertThat(selectList,is(inputBuffer.getAllSelectionFloorInElevator()));

    }
}
