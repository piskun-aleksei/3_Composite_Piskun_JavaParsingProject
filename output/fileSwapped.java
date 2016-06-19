by.bsuir.boatsandharbor.entity; package 
java.util.*; import 
by.bsuir.boatsandharbor.threads.Boat; import 
org.apache.log4j.Logger; import 
java.util.ArrayList; import 
java.util.Random; import 
java.util.concurrent.locks.ReentrantLock; import 
{ class Harbor public 
instance; static Harbor private 
locks; static ReentrantLock[] public 
docks; static ArrayList<Dock> private 
boats; static Boat[] private 
boatNumber; static Integer private 
10; static final int NUMBER_OF_DOCKS = public 
harborCargoWeight; static int private 
static{ 
logger.log(); 
}
private Harbor() { 
ArrayList<Dock>(); = new docks 
Boat[NUMBER_OF_DOCKS]; = new boats 
ReentrantLock[NUMBER_OF_DOCKS]; = new locks 
1; = boatNumber 
0; = harborCargoWeight 
}
public static Harbor getInstance() { 
instance; return 
}
public static int findDock() { 
-1; return 
}
public static int findBoat() { 
-1; return 
}
public static Boat getBoat(int boatNumber){ 
boats[boatNumber]; return 
}
public static void freeDock(int dockNumber) { 
null; = boats[dockNumber] 
}
public static int getDockWeight(int index) { 
docks.get(index).getCargoWeight(); return 
}
public static void addWeight(int weight) { 
weight; += harborCargoWeight 
}
}
Second{ class public 
check; int private 
}
