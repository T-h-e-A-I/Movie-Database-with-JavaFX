package server;

import java.io.Serializable;

public class Datawrapper implements Serializable {
    public String command;
    public Object data;
    public Datawrapper(String command, Object data){
        this.command =command;
        this.data = data;
    }
}
