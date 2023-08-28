package mx.com.vepormas.pruebas.springboot_test.servicios;

import java.math.BigDecimal;

import mx.com.vepormas.pruebas.springboot_test.modelos.Cuenta;

public interface CuentaService {
    Cuenta findById(Long id);
    int revisarTotalTransferencia(Long bancoId);
    BigDecimal revisarSaldo( Long cuentaId);
    void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto, Long bancoId);
}
