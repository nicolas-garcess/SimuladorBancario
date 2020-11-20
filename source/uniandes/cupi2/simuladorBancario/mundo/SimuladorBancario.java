/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_simuladorBancario
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.simuladorBancario.mundo;

import java.util.ArrayList;

/**
 * Clase que representa el simulador bancario para las tres cuentas de un cliente.
 */
public class SimuladorBancario
{
	
	public static final double INVERSION_MAXIMO = 100000000;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	//TODO: 1 Crear atributo
    private double interesGenerado;
	
    /**
     * Cédula del cliente.
     */
    private String cedula;

    /**
     * Nombre del cliente.
     */
    private String nombre;

    /**
     * Mes actual.
     */
    private int mesActual;

    /**
     * Cuenta corriente del cliente.
     */
    private CuentaCorriente corriente;

    /**
     * Cuenta de ahorros del cliente.
     */
    private CuentaAhorros ahorros;

    /**
     * CDT del cliente.
     */
    private CDT inversion;
    
    /** Arraylist de tipo transacción, para alamcenar las transacciones realizadas*/
    private ArrayList<Transaccion> transaccionesHistoricas;
    
    /** Consecutivo de las transacciones*/
    private int consecutivoTransacciones;
    

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el simulador con la información del cliente. <br>
     * <b>post: </b> El mes fue inicializado en 1, y las tres cuentas (CDT, corriente y de ahorros) fueron inicializadas como vacías. <br>
     * @param pCedula Cédula del nuevo cliente. pCedula != null && pCedula != "".
     * @param pNombre Nombre del nuevo cliente. pNombre != null && pNombre != "".
     */
    public SimuladorBancario( String pCedula, String pNombre )
    {
        // Inicializa los atributos personales del cliente
        nombre = pNombre;
        cedula = pCedula;
        // Inicializa el mes en el 1
        mesActual = 1;
        // Inicializa las tres cuentas en vacío
        corriente = new CuentaCorriente( );
        ahorros = new CuentaAhorros( );
        inversion = new CDT( );
        transaccionesHistoricas = new ArrayList<Transaccion>();
        consecutivoTransacciones = 0;
        
        verificarInvariante();
    }

    /**
     * Retorna el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String darNombre( )
    {
        return nombre;
    }
    
    public double darInteresGenerado() {
    	return interesGenerado + ahorros.darInteresGenerado();
    }

    /**
     * Retorna la cédula del cliente.
     * @return Cédula del cliente.
     */
    public String darCedula( )
    {
        return cedula;
    }

    /**
     * Retorna la cuenta corriente del cliente.
     * @return Cuenta corriente del cliente.
     */
    public CuentaCorriente darCuentaCorriente( )
    {
        return corriente;
    }

    /**
     * Retorna el CDT del cliente.
     * @return CDT del cliente.
     */
    public CDT darCDT( )
    {
        return inversion;
    }

    /**
	 * Retorna la cuenta de ahorros del cliente.
	 * @return Cuenta de ahorros del cliente.
	 */
	public CuentaAhorros darCuentaAhorros( )
	{
	    return ahorros;
	}

	/**
     * Retorna el mes en el que se encuentra la simulación.
     * @return Mes actual.
     */
    public int darMesActual( )
    {
        return mesActual;
    }
    
    /**
     * Retorna la lista de transacciones realizadas.
     * @return lista de transacciones realizadas.
     */
    public ArrayList<Transaccion> getTransaccionesHistoricas() {
		return transaccionesHistoricas;
	}

	/**
     * Calcula el saldo total de las cuentas del cliente.
     * @return Saldo total de las cuentas del cliente.
     */
    public double calcularSaldoTotal( )
    {
    	verificarInvariante();
        return corriente.darSaldo( ) + ahorros.darSaldo( ) + inversion.calcularValorPresente( mesActual );
    }

    /**
     * Invierte un monto de dinero en un CDT. <br>
     * <b>post: </b> Invirtió un monto de dinero en un CDT.
     * @param pMonto Monto de dinero a invertir en un CDT. pMonto > 0.
     * @param pInteresMensual Interés del CDT elegido por el cliente.
     */
    public void invertirCDT( double pMonto, String pInteresMensual ) throws Exception
    {
		double pInteres = Double.parseDouble(pInteresMensual) / 100.0;
		inversion.invertir( pMonto, pInteres, mesActual );
		
		consecutivoTransacciones = consecutivoTransacciones +1;
		Transaccion transaccion = new Transaccion(consecutivoTransacciones,pMonto,Transaccion.ENTRADA,Transaccion.CDT);
		transaccionesHistoricas.add(transaccion);
		
		verificarInvariante();
    }

    /**
     * Consigna un monto de dinero en la cuenta corriente. <br>
     * <b>post: </b> Consignó un monto de dinero en la cuenta corriente.
     * @param pMonto Monto de dinero a consignar en la cuenta. pMonto > 0.
     */
    public void consignarCuentaCorriente( double pMonto )
    {
        corriente.consignarMonto( pMonto );
        
        consecutivoTransacciones = consecutivoTransacciones +1;
		Transaccion transaccion = new Transaccion(consecutivoTransacciones,pMonto,Transaccion.ENTRADA,Transaccion.CORRIENTE);
		transaccionesHistoricas.add(transaccion);
        
        verificarInvariante();
    }

    /**
     * Consigna un monto de dinero en la cuenta de ahorros. <br>
     * * <b>post: </b> Consignó un monto de dinero en la cuenta de ahorros.
     * @param pMonto Monto de dinero a consignar en la cuenta. pMonto > 0.
     */
    public void consignarCuentaAhorros( double pMonto )
    {
        ahorros.consignarMonto( pMonto );
        
        consecutivoTransacciones = consecutivoTransacciones +1;
		Transaccion transaccion = new Transaccion(consecutivoTransacciones,pMonto,Transaccion.ENTRADA,Transaccion.AHORROS);
		transaccionesHistoricas.add(transaccion);
        
        verificarInvariante();
    }

    /**
     * Retira un monto de dinero de la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente ha sido inicializada
     * <b>post: </b> Si hay saldo suficiente, entonces este se redujo en el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta. pMonto > 0.
     */
    public void retirarCuentaCorriente( double pMonto )
    {
        corriente.retirarMonto( pMonto );
        
        consecutivoTransacciones = consecutivoTransacciones +1;
		Transaccion transaccion = new Transaccion(consecutivoTransacciones,pMonto,Transaccion.SALIDA,Transaccion.CORRIENTE);
		transaccionesHistoricas.add(transaccion);
        
        verificarInvariante();
    }

    /**
     * Retira un monto de dinero de la cuenta de ahorros. <br>
     * <b>post: </b> Se redujo el saldo de la cuenta en el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta. pMonto > 0.
     */
    public void retirarCuentaAhorros( double pMonto )
    {
        ahorros.retirarMonto( pMonto );
        
        consecutivoTransacciones = consecutivoTransacciones +1;
		Transaccion transaccion = new Transaccion(consecutivoTransacciones,pMonto,Transaccion.SALIDA,Transaccion.AHORROS);
		transaccionesHistoricas.add(transaccion);
        
        verificarInvariante();
    }
    

    /**
     * Avanza en un mes la simulación. <br>
     * <b>post: </b> Se avanzó el mes de la simulación en 1. Se actualizó el saldo de la cuenta de ahorros.
     */
    public void avanzarMesSimulacion( )
    {
    	verificarInvariante();
        mesActual += 1;
        ahorros.actualizarSaldoPorPasoMes( );
        
        if(ahorros.darSaldo() > 0)
        {
	        consecutivoTransacciones = consecutivoTransacciones +1;
			Transaccion transaccion1 = new Transaccion(consecutivoTransacciones,(ahorros.darSaldo()*ahorros.darInteresMensual()),Transaccion.ENTRADA,Transaccion.AHORROS);
			transaccionesHistoricas.add(transaccion1);
        }
        else if(inversion.calcularValorPresente(mesActual) > 0)
        {
        	consecutivoTransacciones = consecutivoTransacciones +1;
    		Transaccion transaccion2 = new Transaccion(consecutivoTransacciones,inversion.darInteresGenerado(mesActual),Transaccion.ENTRADA,Transaccion.CDT);
    		transaccionesHistoricas.add(transaccion2);
        }
    }

    /**
     * Cierra el CDT, pasando el saldo a la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente y el CDT han sido inicializados. <br>
     * <b>post: </b> El CDT quedó cerrado y con valores en 0, y la cuenta corriente aumentó su saldo en el valor del cierre del CDT.
     */
    public void cerrarCDT( )
    {
    	//TODO: 8 agregar el interes generado por el cdt al total de la simulacion
    	verificarInvariante();
        interesGenerado += inversion.darInteresGenerado(mesActual);
        double valorCierreCDT = inversion.cerrar( mesActual );
        corriente.consignarMonto( valorCierreCDT );
        
        consecutivoTransacciones = consecutivoTransacciones + 1;
		Transaccion transaccion1 = new Transaccion(consecutivoTransacciones,valorCierreCDT,Transaccion.SALIDA,Transaccion.CDT);
		transaccionesHistoricas.add(transaccion1);
		
		consecutivoTransacciones = consecutivoTransacciones + 1;
		Transaccion transaccion2 = new Transaccion(consecutivoTransacciones,valorCierreCDT,Transaccion.ENTRADA,Transaccion.CORRIENTE);
		transaccionesHistoricas.add(transaccion2);
    }
    
    /**
     * Retrira el saldo total la cuenta de ahorros, pasandolo a la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente y el la cuenta de ahorros han sido inicializados. <br>
     * <b>post: </b> La cuenta de ahorros queda vacia ( con valores en 0 ), y la cuenta corriente aumentó su saldo en el valor del saldo total que tenia la cuenta de ahorros.
     */
    public void pasarAhorrosToCorriente()
    {
    	verificarInvariante();
    	double cantidad = ahorros.darSaldo();
    	//ahorros = null;
    	corriente.consignarMonto(cantidad);
    	ahorros.cerrarCuenta();
    	
    	consecutivoTransacciones = consecutivoTransacciones + 1;
		Transaccion transaccion1 = new Transaccion(consecutivoTransacciones,cantidad,Transaccion.SALIDA,Transaccion.AHORROS);
		transaccionesHistoricas.add(transaccion1);
		
		consecutivoTransacciones = consecutivoTransacciones + 1;
		Transaccion transaccion2 = new Transaccion(consecutivoTransacciones,cantidad,Transaccion.ENTRADA,Transaccion.CORRIENTE);
		transaccionesHistoricas.add(transaccion2);
    }

    /**
     * Avanza la simulción un numero de meses dado por parámetro.
     * @param pMeses numero de meses a avanzar
     * <b>post: </b> Se avanzaron los meses de la simulación. Se actualizaron los saldos.
     */
    public void metodo1( int pMeses )
    {
    	verificarInvariante();
    	mesActual += pMeses;
    	ahorros.actualizarSaldoMeses(pMeses);
    	
    	for(int i = (mesActual-pMeses)+1; i <= mesActual; i++)
    	{
    		if(ahorros.darSaldo() > 0)
            {
    	        consecutivoTransacciones = consecutivoTransacciones +1;
    			Transaccion transaccion1 = new Transaccion(consecutivoTransacciones,(ahorros.darSaldo()*ahorros.darInteresMensual()),Transaccion.ENTRADA,Transaccion.AHORROS);
    			transaccionesHistoricas.add(transaccion1);
            }
            else if(inversion.calcularValorPresente(mesActual) > 0)
            {
            	consecutivoTransacciones = consecutivoTransacciones +1;
        		Transaccion transaccion2 = new Transaccion(consecutivoTransacciones,inversion.darInteresGenerado(i),Transaccion.ENTRADA,Transaccion.CDT);
        		transaccionesHistoricas.add(transaccion2);
            }
    	}
    	
    }

    /**
     * Reinicia la simulación.
     * @return interes total generado por la simulación.
     */
    public double metodo2( )
    {	
    	verificarInvariante();
    	cerrarCDT();
    	corriente.cerrarCuenta();
    	double respuesta = interesGenerado + ahorros.darInteresGenerado();
    	ahorros.cerrarCuenta();
    	interesGenerado = 0;
    	mesActual = 1;
    	
    	transaccionesHistoricas.clear();
    	consecutivoTransacciones = 0;
        return respuesta;
    }

	public int metodo3(int pTipo, int pCuenta) {
		
		double mayorTransaccion = 0;
		int consecutivoTransaccion = 0;
		
		for(Transaccion transaccion: transaccionesHistoricas)
		{
			if (transaccion.getTipoTransaccion(pTipo) == pTipo && transaccion.getTipoCuenta(pCuenta) == pCuenta)
			{
				if(transaccion.getValor() > mayorTransaccion)
				{
					mayorTransaccion = transaccion.getValor();
					consecutivoTransaccion = transaccion.getConsecutivo();
				}
			}
		}
		return consecutivoTransaccion;
	}
	
	private void verificarInvariante() {
    	
    	assert mesActual > 0 : "El mes de actual debe ser mayor a cero";
        assert interesGenerado >= 0 : "El interés generado debe ser positivo";
        assert (ahorros.darSaldo() + inversion.calcularValorPresente(mesActual)) < INVERSION_MAXIMO: "ERROR: SE SUPERÓ EL MONTO MÁXIMO DE INVERSIÓN";
    	assert corriente != null : "Cuenta corriente sin inicializar";
        assert ahorros != null : "Cuenta de ahorros sin inicializar";
        assert inversion != null : "CDT sin inicializar";
    }
}