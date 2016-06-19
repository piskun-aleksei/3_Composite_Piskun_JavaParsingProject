package by.bsuir.boatsandharbor.entity; 
import java.util.*; 
import by.bsuir.boatsandharbor.threads.Boat; 
import org.apache.log4j.Logger; 
import java.util.ArrayList; 
import java.util.Random; 
import java.util.concurrent.locks.ReentrantLock; 
public class Harbor { 
private static Harbor instance; 
public static ReentrantLock[] locks; 
private static ArrayList<Dock> docks; 
private static Boat[] boats; 
private static Integer boatNumber; 
public static final int NUMBER_OF_DOCKS = 10; 
private static int harborCargoWeight; 
static{ 
logger.log(); 
}
private Harbor() { 
docks = new ArrayList<Dock>(); 
boats = new Boat[NUMBER_OF_DOCKS]; 
locks = new ReentrantLock[NUMBER_OF_DOCKS]; 
boatNumber = 1; 
harborCargoWeight = 0; 
}
public static Harbor getInstance() { 
return instance; 
}
public static int findDock() { 
return -1; 
}
public static int findBoat() { 
return -1; 
}
public static Boat getBoat(int boatNumber){ 
return boats[boatNumber]; 
}
public static void freeDock(int dockNumber) { 
boats[dockNumber] = null; 
}
public static int getDockWeight(int index) { 
return docks.get(index).getCargoWeight(); 
}
public static void addWeight(int weight) { 
harborCargoWeight += weight; 
}
}
public class Second{ 
private int check; 
}
