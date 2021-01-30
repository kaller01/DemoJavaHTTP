import java.util.HashMap;

//Simple CRUD database
public class Database {
    private static HashMap<Integer, String> list = new HashMap();
    private static int lastIdUsed = 0;

    public static int create(String text){
        int id = getNewId();
        list.put(id,text);
        return id;
    }

    public static HashMap<Integer, String> read(){
        return list;
    }

    public static String read(int id){
        return list.get(id);
    }

    public static void update(int id, String text){
        list.replace(id, text);   
    }

    public static void delete(int id){
        list.remove(id);
    }

    private static int getNewId(){
        lastIdUsed++;
        return lastIdUsed-1;
    }
}
