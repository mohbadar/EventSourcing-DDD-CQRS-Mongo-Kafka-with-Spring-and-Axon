package af.asr.springaxon.exceptions;

public class InsufficientBalanceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -7905944771946485097L;


    public InsufficientBalanceException(String message) {
        super(message);
    }

}