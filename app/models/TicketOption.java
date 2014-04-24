package models;

import java.util.*;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

/**
 * Created with IntelliJ IDEA.
 * User: danfaulkner
 * Date: 24/04/2014
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class TicketOption {

    @Id
    @ObjectId
    public String id;

    public String label;

    private static JacksonDBCollection<TicketOption, String> coll = MongoDB.getCollection("ticketoptions", TicketOption.class, String.class);

    public TicketOption(){

    }

    public TicketOption(String label) {
        this.label = label;
    }

    public static List<TicketOption> all() {
        return TicketOption.coll.find().toArray();
    }

    public static void create(TicketOption ticketOption) {
        TicketOption.coll.save(ticketOption);
    }

    public static void create(String label){
        create(new TicketOption(label));
    }

    public static void delete(String id) {
        TicketOption ticketOption = TicketOption.coll.findOneById(id);
        if (ticketOption != null)
            TicketOption.coll.remove(ticketOption);
    }

    public static void removeAll(){
        TicketOption.coll.drop();
    }
}
