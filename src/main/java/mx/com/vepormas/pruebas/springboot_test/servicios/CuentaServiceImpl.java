package mx.com.vepormas.pruebas.springboot_test.servicios;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.vepormas.pruebas.springboot_test.modelos.Banco;
import mx.com.vepormas.pruebas.springboot_test.modelos.Cuenta;
import mx.com.vepormas.pruebas.springboot_test.repositorios.BancoRepositorio;
import mx.com.vepormas.pruebas.springboot_test.repositorios.CuentaRepositorio;

@Service
public class CuentaServiceImpl implements CuentaService{
    private CuentaRepositorio crep;
    private BancoRepositorio brep;

    public CuentaServiceImpl(CuentaRepositorio crep, BancoRepositorio brep) {
    this.crep = crep;
    this.brep = brep;
}

    @Override
    public Cuenta findById(Long id) {
        return crep.findById(id);
    }

    @Override
    public int revisarTotalTransferencia(Long bancoId) {
        Banco banco = brep.findById(bancoId);
        return banco.getTotalTransferencia();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = crep.findById(cuentaId);
        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto, Long bancoId) {
        Cuenta cor = crep.findById(numCuentaOrigen);
        cor.debito(monto);
        crep.update(cor);
        
        Cuenta cdes = crep.findById(numCuentaDestino);
        cdes.credito(monto);
        crep.update(cdes);

        Banco banco = brep.findById(bancoId);
        int totalTransferencia = banco.getTotalTransferencia();
        banco.setTotalTransferencia(++totalTransferencia);
        brep.update(banco);

    }
    
}
