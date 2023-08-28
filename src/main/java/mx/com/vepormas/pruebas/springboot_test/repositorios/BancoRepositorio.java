package mx.com.vepormas.pruebas.springboot_test.repositorios;

import java.util.List;

import mx.com.vepormas.pruebas.springboot_test.modelos.Banco;

public interface BancoRepositorio {
    List<Banco> findAll();
    Banco findById(Long id);
    void update(Banco banco);
}
