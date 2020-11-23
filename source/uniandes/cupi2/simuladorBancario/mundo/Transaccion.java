package uniandes.cupi2.simuladorBancario.mundo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Clase que representa las transacciones realizadas en el simulador bancario
 */
public class Transaccion {
	
	/**Constantes tipo entero para identificar el tipo de transacción y cuenta 
	 * */
	protected final static int ENTRADA = 0;
	
	protected final static int SALIDA = 1;
	
	protected final static int AHORROS = 0;
	
	protected final static int CORRIENTE = 1;
	
	protected final static int CDT = 2;
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**Consecutivo de la transacción.*/
	private int consecutivo;
	
	/**Valor de la transacción.*/
	private double valor;
	
	/**Tipo de la transacción.*/
	private int tipoTransaccion;
	
	/**Tipo de cuenta.*/
	private int tipoCuenta;
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Da el molde al objeto transacción. <br>
     * <b>post: </b> El consecutivo, valor, tipo de transacción y tipo de cuenta, toman el valor pasado por parámetro. <br>
     * @param consecutivo Consecutivo de la transacción.
     * @param valor Valor de la transacción.
     * @param tipoTransaccion Tipo de la transacción.
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
     * Retorna el consecutivo de la transacción.
     * @return Consecuitvo transacción.
     */
	public int getConsecutivo() {
		return consecutivo;
	}

	/**
     * Retorna el valor de la transacción.
     * @return Valor transacción.
     */
	public double getValor() {
		return valor;
	}

	/**
     * Retorna el tipo de transacción.
     * @return Tipo de transacción.
     */
	public int getTipoTransaccion() {	
		return tipoTransaccion;
	}

	/**
     * Retorna la cuenta de la transacción.
     * @return Tipo de la cuenta de transacción.
     */
	public int getTipoCuenta() {
		return tipoCuenta;
	}
	
	/**
     * Retorna la representación en cadena de caracteres un objeto Transaccion.
     * @return La representación en cadena de caracteres del objeto Transaccion.
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
