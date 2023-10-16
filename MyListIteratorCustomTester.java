import static org.junit.Assert.*;
import org.junit.*;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class MyListIteratorCustomTester {
    private MyLinkedList listLen1, listLen2, listLen3;
    private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter, listLen3Iter;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen1 = new MyLinkedList();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();

        listLen3 = new MyLinkedList();
        listLen3Iter = listLen3.new MyListIterator();

    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() { // check
        listLen2Iter.next();
        listLen2Iter.next();
        assertEquals(2, listLen2Iter.idx);

        assertThrows(NoSuchElementException.class, () ->{
        listLen2Iter.next();
    });

    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() { // check 
        
        assertThrows(NoSuchElementException.class, () ->{
            listLen2Iter.previous();
        });
        
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {

        assertThrows(NullPointerException.class, () -> {
            listLen2Iter.add(null);
        });

    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {

        listLen2Iter.canRemoveOrSet=false;

        assertThrows(IllegalStateException.class, () -> {
            listLen2Iter.set("Me");
        });

    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test
    public void testSetInvalid() { // check
        assertThrows(NullPointerException.class, () -> {
            listLen1Iter.set(null);
        });

    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() { // check
        listLen2Iter.canRemoveOrSet= false;
        assertThrows(IllegalStateException.class, () -> {
            listLen2Iter.remove();
        });

    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() { // check
        listLen2Iter.next();
        listLen2Iter.next();

        assertFalse(listLen2Iter.hasNext());


    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() { // check
        assertFalse(listLen2Iter.hasPrevious());

    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() { //check 

        assertEquals(-1,listLen2Iter.previousIndex());

    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() { //check
        listLen2Iter.next();
        listLen2Iter.next();

        assertEquals(2,listLen2Iter.nextIndex());

    }

    @Test
    public void testAddOnEmptyList(){
        listLen3Iter.add(9);
        assertEquals(1,listLen3.size);
        assertEquals(9,listLen3.get(0) );
    }
}
