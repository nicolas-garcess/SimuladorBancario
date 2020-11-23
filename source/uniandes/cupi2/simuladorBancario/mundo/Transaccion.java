package uniandes.cupi2.simuladorBancario.mundo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Clase que representa las transacciones realizadas en el simulador bancario
 */
public class Transaccion {
	
	/**Constantes tipo entero para identificar el tipo de transacci�n y cuenta 
	 * */
	protected final static int ENTRADA = 0;
	
	protected final static int SALIDA = 1;
	
	protected final static int AHORROS = 0;
	
	protected final static int CORRIENTE = 1;
	
	protected final static int CDT = 2;
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**Consecutivo de la transacci�n.*/
	private int consecutivo;
	
	/**Valor de la transacci�n.*/
	private double valor;
	
	/**Tipo de la transacci�n.*/
	private int tipoTransaccion;
	
	/**Tipo de cuenta.*/
	private int tipoCuenta;
	
	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Da el molde al objeto transacci�n. <br>
     * <b>post: </b> El consecutivo, valor, tipo de transacci�n y tipo de cuenta, toman el valor pasado por par�metro. <br>
     * @param consecutivo Consecutivo de la transacci�n.
     * @param valor Valor de la transacci�n.
     * @param tipoTransaccion Tipo de la transacci�n.
     * @param tipoCuenta Tipo de la cuenta.
     */
	public Transaccion(int consecutivo, double valor, int tipoTransaccion, int tipoCuenta) {
		super();
		this.consecutivo = consecutivo;
		this.valor = valor;
		this.tipoTransaccion = tipoTransaccion;
		this.tipoCuenta = tipoCuenta;
	}

	/**
     * Retorna el consecutivo de la transacci�n.
     * @return Consecuitvo transacci�n.
     */
	public int getConsecutivo() {
		return consecutivo;
	}

	/**
     * Retorna el valor de la transacci�n.
     * @return Valor transacci�n.
     */
	public double getValor() {
		return valor;
	}

	/**
     * Retorna el tipo de transacci�n.
     * @return Tipo de transacci�n.
     */
	public int getTipoTransaccion() {	
		return tipoTransaccion;
	}

	/**
     * Retorna la cuenta de la transacci�n.
     * @return Tipo de la cuenta de transacci�n.
     */
	public int getTipoCuenta() {
		return tipoCuenta;
	}
	
	/**
     * Retorna la representaci�n en cadena de caracteres un objeto Transaccion.
     * @return La representaci�n en cadena de caracteres del objeto Transaccion.
     */
	public String toString( )
    {
		/*DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "##0.00" );*/
        
        String representacion = "";
        
		if(tipoTransaccion == 0 && tipoCuenta == 0)
		{
			representacion = consecutivo + " - " + "ENTRADA" + " - " + "AHORROS";
		}
		else if(tipoTransaccion == 0 && tipoCuenta == 1)
		{
			representacion = consecutivo + " - " + "ENTRADA" + " - " + "CORRIENTE";
		}
		else if(tipoTransaccion == 0 && tipoCuenta == 2)
		{
			representacion = consecutivo + " - " + "ENTRADA" + " - " + "CDT";
		}
		else if(tipoTransaccion == 1 && tipoCuenta == 0)
		{
			representacion = consecutivo + " - " + "SALIDA" + " - " + "AHORROS";
		}
		else if(tipoTransaccion == 1 && tipoCuenta == 1)
		{
			representacion = consecutivo + " - " + "SALIDA" + " - " + "CORRIENTE";
		}
		else
		{
			representacion = consecutivo + " - " + "SALIDA" + " - " + "CDT";
		}
            		
        //String representacion = consecutivo + " - " + tipoTransaccion + " - " + tipoCuenta + " - " + df.format(valor);
        return representacion;
    }
}
