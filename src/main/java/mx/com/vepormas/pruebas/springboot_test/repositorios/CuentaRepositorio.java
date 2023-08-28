package mx.com.vepormas.pruebas.springboot_test.repositorios;

import java.util.List;

import mx.com.vepormas.pruebas.springboot_test.modelos.Cuenta;

public interface CuentaRepositorio {
    List<Cuenta> findAll();
    Cuenta findById(Long id);
    void update(Cuenta cuenta);
}
