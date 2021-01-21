package pl.kuziow.vetclinic.exceptions;

public class AppointmentServiceException extends RuntimeException{
    private static final long serialVersionUID = -476628233573337365L;

    public AppointmentServiceException(String message) {
        super(message);
    }
}
