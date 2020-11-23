/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_simuladorBancario
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.simuladorBancario.test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uniandes.cupi2.simuladorBancario.mundo.SimuladorBancario;


/**
 * Clase usada para verificar que los m�todos de la clase CuentaBancaria est�n correctamente implementados.
 */
public class SimuladorBancarioTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cuenta bancaria usada para las pruebas.
     */
    private SimuladorBancario cuenta;

    // -----------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------

    /**
     * Escenario 1: Crea una cuenta bancaria sin CDT, sin cuenta de ahorros y sin cuenta corriente.
     */
    private void setupEscenario1( )
    {
        cuenta = new SimuladorBancario( "50.152.468", "Sergio L�pez" );
    }

    /**
     * Escenario 2: Crea una cuenta bancaria con valores en todas las cuentas.
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        try {
			cuenta.invertirCDT( 1000000, "0.10" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cuenta.consignarCuentaAhorros( 500000 );
        cuenta.consignarCuentaCorriente( 600000 );
    }

    /**
     * Escenario 3:Cre una cuenta bancaria con valores en todas las cuentas y retiros.
     */
    private void setupEscenario3( )
    {
        setupEscenario1( );
        try {
			cuenta.invertirCDT( 1000000, "10" );
		} catch (Exception e) {
			e.printStackTrace();
		}
        cuenta.consignarCuentaAhorros( 500000 );
        cuenta.retirarCuentaAhorros( 200000 );
        cuenta.consignarCuentaCorriente( 600000 );
        cuenta.retirarCuentaCorriente( 100000 );
    }
    
    private void setupEscenario4()
    {
    	setupEscenario1();
    	cuenta.consignarCuentaAhorros(1000000);
    	try {
			cuenta.invertirCDT(50000, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void setupEscenario9()
    {
    	setupEscenario1();
    	//cuenta.retirarCuentaCorriente(2000);
    	cuenta.consignarCuentaAhorros(83000000);
    	try {
			cuenta.invertirCDT(14000000, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void setupEscenario10( )
    {
    	setupEscenario2( );
        try {
        cuenta.invertirCDT( 500000, "2" );
        }catch(Exception e2) {
        	e2.printStackTrace();
        }
        cuenta.retirarCuentaAhorros( 200000 );
    }
    
    @Test
    public void testPunto1( )
    {
        setupEscenario10( );
        cuenta.avanzarMesSimulacion();
        cuenta.cerrarCDT();
        assertEquals( "ERROR EN EL PUNTO 1", 1902800, cuenta.calcularSaldoTotal(), 0.0001);
    }
    
    

    /**
     * Prueba 1: M�todo que se encarga de verificar el m�todo invertirCDT.<br>
     * <b> M�todos a probar: </b> <br>
     * invertirCDT<br>
     * darCDT<br>
     * <b> Casos de prueba: </b> <br>
     * 1. El CDT no tiene saldo..
     */
    @Test
    public void testInvertirCDT( )
    {
        setupEscenario2( );
        assertEquals( "El valor presente del CDT no es el esperado", 1000000, cuenta.darCDT( ).calcularValorPresente( 1 ), 2 );

    }

    /**
     * Prueba 2: M�todo que se encarga de verificar el m�todo consignarCuentaCorriente.<br>
     * <b> M�todos a probar: </b> <br>
     * consignarCuentaCorriente<br>
     * darCuentaCorriente<br>
     * <b> Casos de prueba: </b> <br>
     * 1. La cuenta corriente no tiene saldo.
     */
    @Test
    public void testConsignarCuentaCorriente( )
    {
        setupEscenario2( );
        assertEquals( "El saldo de la cuenta corriente no es el esperado", 600000, cuenta.darCuentaCorriente( ).darSaldo( ), 2 );

    }

    /**
     * Prueba 3: M�todo que se encarga de verificar el m�todo consignarCuentaAhorro.<br>
     * <b> M�todos a probar: </b> <br>
     * consignarCuentaAhorro<br>
     * darCuentaAhorro<br>
     * <b> Casos de prueba: </b> <br>
     * 1. La cuenta de ahorros no tiene saldo.
     */
    @Test
    public void testConsignarCuentaAhorros( )
    {
        setupEscenario2( );
        assertEquals( "El saldo de la cuenta de ahorros no es el esperado", 500000, cuenta.darCuentaAhorros( ).darSaldo( ), 2 );
    }

    /**
     * Prueba 4: M�todo que se encarga de verificar el m�todo cerrarCDT.<br>
     * <b> M�todos a probar: </b> <br>
     * cerrarCDT<br>
     * darCDT<br>
     * calcularSaldoTotal<br>
     * <b> Casos de prueba: </b> <br>
     * 1. El CDT tiene saldo.
     */
    @Test
    public void testCerrarCDT( )
    {
        setupEscenario2( );
        double cerrado = cuenta.darCDT( ).calcularValorPresente( 1 );
        double saldo = 1100000 + cerrado;
        cuenta.cerrarCDT( );
        assertEquals( "El saldo de la cuenta bancaria no es el esperado", ( int )saldo, ( int )cuenta.calcularSaldoTotal( ), 0.01 );
    }

    /**
     * Prueba 5: M�todo que se encarga de verificar el m�todo retirarCuentaCorriente.<br>
     * <b> M�todos a probar: </b> <br>
     * retirarCuentaCorriente<br>
     * darCuentaCorriente<br>
     * <b> Casos de prueba: </b> <br>
     * 1. La cuenta corriente tiene suficiente saldo para retirar.
     */
    @Test
    public void testRetirarCuentaCorriente( )
    {
        setupEscenario3( );
        assertEquals( "El saldo de la cuenta corriente no es el esperado", 500000, cuenta.darCuentaCorriente( ).darSaldo( ), 2 );
    }

    /**
     * Prueba 6: M�todo que se encarga de verificar el m�todo retirarCuentaAhorro.<br>
     * <b> M�todos a probar: </b> <br>
     * retirarCuentaAhorro<br>
     * darCuentaAhorro<br>
     * <b> Casos de prueba: </b> <br>
     * 1. La cuenta ahorros tiene suficiente saldo para retirar.
     */
    @Test
    public void testRetirarCuentaAhorros( )
    {
        setupEscenario3( );
        assertEquals( "El saldo de la cuenta de ahorro no es el esperado", 300000, cuenta.darCuentaAhorros( ).darSaldo( ), 2 );
    }

    /**
     * Prueba 7: M�todo que se encarga de verificar el m�todo calcularSaldoTotal.<br>
     * <b> M�todos a probar: </b> <br>
     * calcularSaldoTotal<br>
     * <b> Casos de prueba: </b> <br>
     * 1. El CDT, la cuenta ahorros y la cuenta corriente tienen saldo.
     */
    @Test
    public void testSaldoTotal( )
    {
        setupEscenario3( );
        assertEquals( 1800000, ( int )cuenta.calcularSaldoTotal( ) );
    }
    

	@Test
    public void testMetodo1() {
    	setupEscenario4();
    	cuenta.metodo1(4);
    	assertEquals( (int) 52000 + 1000000*Math.pow(1+0.006,4.0), (int) cuenta.calcularSaldoTotal(), 1 );
    }
    
    @Test
    public void testMetodo2Parte1() {
    	setupEscenario4();
    	for(int i = 0; i < 4; i++) {
    		cuenta.avanzarMesSimulacion();
    	}
    	double interes = cuenta.metodo2();
    	assertEquals(interes, 2000 + 1000000*Math.pow(1+0.006,4.0) - 1000000, 1);
    }
    
    @Test
    public void testMetodo2Parte2() {
    	setupEscenario4();
    	for(int i = 0; i < 4; i++) {
    		cuenta.avanzarMesSimulacion();
    	}
    	double interes = cuenta.metodo2();
    	assertTrue(cuenta.calcularSaldoTotal() == 0 & cuenta.darMesActual() == 1);
    }
    
    
    @Test
    public void testRetoConjunto() {
    	
    	try
    	{
    	setupEscenario3();
    	cuenta.avanzarMesSimulacion();
    	cuenta.metodo1(10);
    	double sim1 = cuenta.metodo2();
    	cuenta.consignarCuentaAhorros(1000000);
    	cuenta.invertirCDT(50000, "1");
    	cuenta.metodo1(4);
    	cuenta.cerrarCDT();
    	cuenta.avanzarMesSimulacion();
    	cuenta.retirarCuentaAhorros(500000);
    	cuenta.avanzarMesSimulacion();
    	cuenta.invertirCDT(400000, "5");
    	cuenta.metodo1(5);
    	double respuesta = cuenta.metodo2();
        double total = 151743.90;
    	assertEquals(total, respuesta, 1);
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }
    
    @Test
    public void testPunto2a()
    {
    	setupEscenario9();
    	cuenta.metodo1(2);
    	//cuenta.consignarCuentaAhorros(5000000);
    	cuenta.pasarAhorrosToCorriente();
    	cuenta.avanzarMesSimulacion();
    	cuenta.consignarCuentaCorriente(150000);
    	int respuesta = 98568988;
    	assertEquals( "El saldo de la cuenta corriente no es el esperado", respuesta, ( int )cuenta.calcularSaldoTotal(), 5 );    	
    }

}
