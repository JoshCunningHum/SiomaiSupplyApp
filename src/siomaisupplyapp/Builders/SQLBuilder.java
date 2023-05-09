package siomaisupplyapp.Builders;

/**
 *
 * @author Laptop ni CC
 */
abstract public class SQLBuilder {
    String command;

    public SQLBuilder(String command){
        this.command = command;
    }

    protected String parseObject(Object o){
        StringBuilder sb = new StringBuilder();
        sb.append(o instanceof String ? "'" + o + "'" : o.toString());
        return sb.toString();
    }

    public String toString(){
        return command + " ";
    }
}
