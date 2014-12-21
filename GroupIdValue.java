package pack;

/**
 *
 * @author abelash
 */
public class GroupIdValue
{
    private int id;
    private String value;

    public GroupIdValue(int id, String value) {
        this.id = id;//идентификатор 
        this.value = value;//значение 
    }

    public GroupIdValue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    
}
