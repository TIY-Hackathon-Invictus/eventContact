package tiy.invictus;

/**
 * Created by Brice on 10/2/16.
 */
public class ResponseError implements Response{

    public String message;



    public ResponseError(String message) {
        this.message = message;
    }

    public ResponseError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
