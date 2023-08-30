package mx.com.vepormas.pruebas.springboot_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import mx.com.vepormas.pruebas.springboot_test.excepciones.DineroInsuficienteException;
import mx.com.vepormas.pruebas.springboot_test.modelos.Banco;
import mx.com.vepormas.pruebas.springboot_test.modelos.Cuenta;
import mx.com.vepormas.pruebas.springboot_test.repositorios.BancoRepositorio;
import mx.com.vepormas.pruebas.springboot_test.repositorios.CuentaRepositorio;
import mx.com.vepormas.pruebas.springboot_test.servicios.CuentaService;

@SpringBootTest
class SpringbootTestApplicationTests {

	@MockBean
	CuentaRepositorio crep;
	@MockBean
	BancoRepositorio brep;
    @Autowired
	CuentaService cser;

	// @Mock
	// CuentaRepositorio crep;
	// @Mock
	// BancoRepositorio brep;
	// @InjectMocks
	// CuentaServiceImpl cser;

	@BeforeEach
    void initTests(){
        //MockitoAnnotations.openMocks(this);
		Datos.c1.setSaldo(new BigDecimal("1000"));
		Datos.c2.setSaldo(new BigDecimal("2000"));
		Datos.banco.setTotalTransferencia(0);
    }

	@Test
	void contextLoads() {
		when(crep.findById(1L)).thenReturn(Datos.c1);
		when(crep.findById(2L)).thenReturn(Datos.c2);
		when(brep.findById(1L)).thenReturn(Datos.banco);

		BigDecimal saldoOrigen = cser.revisarSaldo(1L);
		BigDecimal saldoDestino = cser.revisarSaldo(2L);
		assertEquals("1000",saldoOrigen.toPlainString());
		assertEquals("2000",saldoDestino.toPlainString());

		cser.transferir(1L, 2L, new BigDecimal("100"), 1L);

		saldoOrigen = cser.revisarSaldo(1L);
		saldoDestino = cser.revisarSaldo(2L);
		assertEquals("900",saldoOrigen.toPlainString());
		assertEquals("2100",saldoDestino.toPlainString());

		int total = cser.revisarTotalTransferencia(1L);
		assertEquals(1, total);
		verify(crep, times(3)).findById(1L);
		verify(crep, times(3)).findById(2L);
		verify(crep, times(2)).update(any(Cuenta.class));

		verify(brep, times(2)).findById(1L);
		verify(brep).update(any(Banco.class));

		verify(crep, times(6)).findById(anyLong());
		verify(crep, never()).findAll();
	}

	@Test
	void contextLoads2() {
		when(crep.findById(1L)).thenReturn(Datos.c1);
		when(crep.findById(2L)).thenReturn(Datos.c2);
		when(brep.findById(1L)).thenReturn(Datos.banco);

		BigDecimal saldoOrigen = cser.revisarSaldo(1L);
		BigDecimal saldoDestino = cser.revisarSaldo(2L);
		assertEquals("1000",saldoOrigen.toPlainString());
		assertEquals("2000",saldoDestino.toPlainString());

		assertThrows(DineroInsuficienteException.class, ()->{
		cser.transferir(1L, 2L, new BigDecimal("1200"), 1L);});

		saldoOrigen = cser.revisarSaldo(1L);
		saldoDestino = cser.revisarSaldo(2L);
		assertEquals("1000",saldoOrigen.toPlainString());
		assertEquals("2000",saldoDestino.toPlainString());

		int total = cser.revisarTotalTransferencia(1L);
		assertEquals(0, total);
		verify(crep, times(3)).findById(1L);
		verify(crep, times(2)).findById(2L);
		verify(crep, never()).update(any(Cuenta.class));

		verify(brep).findById(1L);
		verify(brep, never()).update(any(Banco.class));
	}

	@Test
	void contextLoads3(){
		when(crep.findById(1L)).thenReturn(Datos.crearC1());

		Cuenta c1 = cser.findById(1L);
		Cuenta c2 = cser.findById(1L);

		assertSame(c1,c2);
		assertTrue(c1==c2);
		assertEquals("Rafael",c1.getPersona());
		assertEquals("Rafael",c2.getPersona());

		verify(crep, times(2)).findById(1L);
	}
	

}
