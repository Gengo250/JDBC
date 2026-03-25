package db;

import java.io.Serial;

public class DBIntegridyException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public DBIntegridyException(String msg){
        super(msg);
    }
}
