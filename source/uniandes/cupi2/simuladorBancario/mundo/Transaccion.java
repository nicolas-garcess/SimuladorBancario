package uniandes.cupi2.simuladorBancario.mundo;

public class Transaccion {
	
	protected final static int ENTRADA = 0;
	
	protected final static int SALIDA = 1;
	
	protected final static int AHORROS = 0;
	
	protected final static int CORRIENTE = 1;
	
	protected final static int CDT = 2;
	
	private int consecutivo;
	
	private double valor;
	
	private int tipoTransaccion;
	
	private int tipoCuenta;
	

	public Transaccion(int consecutivo, double valor, int tipoTransaccion, int tipoCuenta) {
		super();
		this.consecutivo = consecutivo;
		this.valor = valor;
		this.tipoTransaccion = tipoTransaccion;
		this.tipoCuenta = tipoCuenta;
	}

	public int getConsecutivo() {
		return consecutivo;
	}

	public double getValor() {
		return valor;
	}

	public int getTipoTransaccion(int pTipoTransaccion) {	
		return tipoTransaccion;
	}

	public int getTipoCuenta(int pTipoCuenta) {
		return tipoCuenta;
	}

}
