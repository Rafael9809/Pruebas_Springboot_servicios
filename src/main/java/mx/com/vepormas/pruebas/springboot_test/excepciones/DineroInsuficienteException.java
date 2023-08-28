package mx.com.vepormas.pruebas.springboot_test.excepciones;

public class DineroInsuficienteException extends RuntimeException{
    public DineroInsuficienteException(String mensaje){
        super(mensaje);
    }
}
