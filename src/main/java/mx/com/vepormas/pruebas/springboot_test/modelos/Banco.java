package mx.com.vepormas.pruebas.springboot_test.modelos;

import java.math.BigDecimal;

public class Banco {
    private Long id;
    private String nombre;
    private int totalTransferencia;
    public Banco(Long id, String nombre, int totalTransferencia) {
        this.id = id;
        this.nombre = nombre;
        this.totalTransferencia = totalTransferencia;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getTotalTransferencia() {
        return totalTransferencia;
    }
    public void setTotalTransferencia(int totalTransferencia) {
        this.totalTransferencia = totalTransferencia;
    }
    
}
