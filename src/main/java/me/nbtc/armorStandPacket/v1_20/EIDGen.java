package me.nbtc.armorStandPacket.v1_20;

/**
 * The function of this class is a bit shaky. It is mostly to avoid use of NMS code to create a proper EID.
 * This class creates Entity IDs that are used by minecraft to identify entities in the protocol.
 * There are two ways to break the function of this class and risk server errors or client crashing:
 *  - A world with 2 billion entites at once might cause client crashes.
 *  - A user of this library creating 147,483,647 entities with this class would beak something. I think the server would
 *      crash, but I'm not 100% sure. Java would wrap the entity ids back to negative max value, which is likely to cause trouble.
 * @author Jaxon A Brown
 */
public class EIDGen {
    private static int lastIssuedEID = 15000000;//2 billion
    public static int generateEID() {
        return lastIssuedEID++;
    }
}