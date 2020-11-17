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
package uniandes.cupi2.simuladorBancario.interfaz;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.simuladorBancario.mundo.*;

/**
 * Ventana principal de la aplicaci�n.
 */
@SuppressWarnings("serial")
public class InterfazSimulador extends JFrame
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private SimuladorBancario cuenta;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la imagen.
     */
    private PanelImagen panelImagen;

    /**
     * Panel de botones para transacciones de cuentas de ahorro.
     */
    private PanelAhorros panelAhorros;

    /**
     * Panel de botones para transacciones de cuentas corriente.
     */
    private PanelCorriente panelCorriente;

    /**
     * Panel de botones para transacciones de CDT.
     */
    private PanelCDT panelCDT;

    /**
     * Panel de botones con las opciones de las transacciones.
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel de visualizacion de datos de las cuentas.
     */
    private PanelSaldos panelSaldos;

    /**
     * Panel de visualizaci�n de datos personales.
     */
    private PanelDatosCliente panelDatos;

    
    private PanelTransacciones panelTransacciones;
        
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva ventana principal del simulador de una cuenta bancaria. <br>
     * <b>post: </b> Se inicializ� la interfaz principal y sus paneles.
     */
    public InterfazSimulador( )
    {
        setTitle( "Simulador bancario" );
        setSize( 600, 750 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        cuenta = new SimuladorBancario( "50.152.468", "Sergio L�pez" );

        panelCDT = new PanelCDT( this );
        panelAhorros = new PanelAhorros( this );
        panelCorriente = new PanelCorriente( this );
        panelOpciones = new PanelOpciones( this );

        panelSaldos = new PanelSaldos( this );
        panelDatos = new PanelDatosCliente( );
        panelImagen = new PanelImagen( );
        panelTransacciones = new PanelTransacciones( this );
        
        // Organizar el panel principal
        getContentPane( ).setLayout( new BorderLayout( ) );

        JPanel panelCentral = new JPanel( );
        panelCentral.setLayout( new BorderLayout( ) );
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout( new BorderLayout( ));
        JPanel panelCuentas = new JPanel( );
        panelCuentas.setLayout( new GridLayout( 4, 1 ) );
        panelCuentas.setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Informaci�n Bancaria" ) ) );

        getContentPane( ).add( panelImagen, BorderLayout.NORTH );
        getContentPane( ).add( panelCentral, BorderLayout.CENTER );
        getContentPane( ).add( panelInferior, BorderLayout.SOUTH );

        panelCentral.add( panelDatos, BorderLayout.NORTH );
        panelCentral.add( panelCuentas, BorderLayout.CENTER );

        panelCuentas.add( panelAhorros );
        panelCuentas.add( panelCorriente );
        panelCuentas.add( panelCDT );
        panelCuentas.add( panelSaldos );

        panelInferior.add( panelTransacciones, BorderLayout.CENTER);
        panelInferior.add( panelOpciones, BorderLayout.SOUTH);
        
        setLocationRelativeTo( null );
        setResizable( false );

        actualizar( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la interfaz con los datos de la cuenta. <br>
     * <b>post: </b> Se actualiz� los cambios en los paneles correspondientes.
     */
    public void actualizar( )
    {
        String nombre = cuenta.darNombre( );
        String cedula = cuenta.darCedula( );
        panelDatos.actualizarNombre( nombre );
        panelDatos.actualizarCedula( "" + cedula );
        panelSaldos.actualizarMes( cuenta.darMesActual( ) );
        panelSaldos.actualizarSaldoTotal( formatearValor( cuenta.calcularSaldoTotal( ) ) );

        panelCorriente.actualizarSaldoCorriente( formatearValor( cuenta.darCuentaCorriente( ).darSaldo( ) ) );
        panelAhorros.actualizarSaldoAhorros( formatearValor( cuenta.darCuentaAhorros( ).darSaldo( ) ) + "   [" + ( cuenta.darCuentaAhorros( ).darInteresMensual( ) * 100 ) + "%]" );
        panelCDT.actualizarSaldoCDT( formatearValor( cuenta.darCDT( ).calcularValorPresente( cuenta.darMesActual( ) ) ) + "   [" + ( cuenta.darCDT( ).darInteresMensual( ) * 100 ) + "%]" );

    }

    /**
     * Invierte un monto de dinero en un CDT. <br>
     * <b>post: </b> Se realiz� la inversi�n en un CDT.
     * @param pMonto Monto de dinero a invertir en el CDT.
     * @param pInteres Valor de los intereses en porcentaje.
     */
    public void invertirCDT( String pMonto, String pInteres )
    {
        if( pMonto != null )
        {
            try
            {
                int monto = Integer.parseInt( pMonto );
                if( monto > 0 )
                {
                    cuenta.invertirCDT( monto, pInteres );
                    actualizar();
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "Debe  ingresar valores positivos.", "Invertir en CDT", JOptionPane.ERROR_MESSAGE );
                }
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, "Se ingres� un monto de dinero o inter�s inv�lido.", "Invertir en CDT", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Cierra el CDT del cliente. <br>
     * <b>post: </b> Se cerr� el CDT del cliente.
     */
    public void cerrarCDT( )
    {
        cuenta.cerrarCDT( );
        actualizar();
    }

    /**
     * Retira un monto de dinero de la cuenta de ahorros del cliente. <br>
     * <b>post: </b> Se retir� un monto de dinero de la cuenta de ahorros del cliente.
     * @param pMonto Monto de dinero a retirar de la cuenta de ahorros del cliente.
     */
    public void retirarAhorros( String pMonto )
    {
        try
        {
            double monto = Double.parseDouble( pMonto );
            if( monto < 0 )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un valor  positivo.", "Retirar", JOptionPane.ERROR_MESSAGE );
            }
            else if( monto > cuenta.darCuentaAhorros( ).darSaldo( ) )
            {
                JOptionPane.showMessageDialog( this, "No tiene saldo suficente.", "Retirar", JOptionPane.ERROR_MESSAGE );

            }
            else
            {
                cuenta.retirarCuentaAhorros( monto );
                actualizar( );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Se ingres� un monto de dinero inv�lido.", "Retirar", JOptionPane.ERROR_MESSAGE );

        }
    }

    /**
     * Consigna un monto de dinero en la cuenta de ahorros del cliente. <br>
     * <b>post: </b> Se consign� el monto de dinero a la cuenta de ahorros del cliente.
     * @param pMonto Monto de dinero a consignar en la cuenta de ahorros del cliente.
     */
    public void consignarAhorros( String pMonto )
    {
        try
        {
            double monto = Double.parseDouble( pMonto );

            if( monto >= 0 )
            {
                cuenta.consignarCuentaAhorros( Double.parseDouble( pMonto ) );
                actualizar();
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un valor positivo.", "Consignar", JOptionPane.ERROR_MESSAGE );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Se ingres� un monto de dinero inv�lido.", "Consignar", JOptionPane.ERROR_MESSAGE );

        }
    }

    /**
     * Retira un monto de dinero de la cuenta corriente. <br>
     * <b>post: </b> Se retir� de la cuenta corriente del cliente el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta corriente.
     */
    public void retirarCorriente( String pMonto )
    {
        try
        {
            double monto = Double.parseDouble( pMonto );
            if( monto < 0 )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un valor positivo.", "Retirar", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
            	try {
                cuenta.retirarCuentaCorriente( monto );
            	}
            	catch(Exception e)
            	{
            		JOptionPane.showMessageDialog( this, e.getMessage(), "Retirar", JOptionPane.ERROR_MESSAGE );

            	}
                actualizar( );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Se ingres� un monto de dinero inv�lido.", "Retirar", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Consigna un monto de dinero en la cuenta corriente. <br>
     * <b>post: </b> Se consign� el monto especificado en la cuenta corriente del cliente.
     * @param pMonto Monto de dinero a consignar de la cuenta corriente.
     */
    public void consignarCorriente( String pMonto )
    {
        try
        {
            double monto = Double.parseDouble( pMonto );
            if( monto >= 0 )
            {
                cuenta.consignarCuentaCorriente( monto );
                actualizar( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un valor positivo.", "Consignar", JOptionPane.ERROR_MESSAGE );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Se ingres� un monto de dinero inv�lido.", "Consignar", JOptionPane.ERROR_MESSAGE );

        }
    }

    /**
     * Formatea un valor num�rico para presentar en la interfaz.
     * @param pValor Valor num�rico a ser formateado. pValor > 0.
     * @return Retorna un string con el valor formateado con puntos y signos.
     */
    private String formatearValor( double pValor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        df.setMinimumFractionDigits( 2 );
        return df.format( pValor );
    }

    /**
     * Avanza un mes en la simulaci�n del banco.
     */
    public void avanzarMesSimulacion( )
    {
        cuenta.avanzarMesSimulacion( );
        actualizar( );

    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     */
    public void reqFuncOpcion1( String pMeses )
    {
    	try
        {
            int meses = Integer.parseInt( pMeses );
            if( meses >= 1 )
            {
                cuenta.metodo1( meses );
                actualizar( );
                //JOptionPane.showMessageDialog( this, "Requerimiento 1." , "Avanzar simulacion", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un valor positivo mayor o igual a 0.", "Avanzar simulacion", JOptionPane.ERROR_MESSAGE );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Se ingres� una cantidad de meses inv�lida.", "Avanzar simulacion", JOptionPane.ERROR_MESSAGE );

        }
    }

    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncOpcion2( )
    {
    	double ans = cuenta.metodo2( );
        String respuesta = Double.toString(ans);
        actualizar( );
        JOptionPane.showMessageDialog( this, "El total de intereses generados en la simulaci�n fue: " + respuesta, "Reiniciar simulacion.", JOptionPane.INFORMATION_MESSAGE );
    }
    
    public void reqFuncOpcion3() {
    	
    	Object[] possibilities1 = { "Ingreso", "Egreso"};
        String tipoMovimiento = ( String )JOptionPane.showInputDialog( this, "Tipo de movimiento:", "Movimiento", JOptionPane.QUESTION_MESSAGE, null, possibilities1, "Ingreso" );
        
        Object[] possibilities2 = { "Ahorros", "Corriente", "CDT"};
        String tipoCuenta = ( String )JOptionPane.showInputDialog( this, "Tipo de cuenta:", "Cuenta", JOptionPane.QUESTION_MESSAGE, null, possibilities2, "Ahorros" );
        
        int tipoM = 0;
        int tipoC = 0;
        
        if (tipoMovimiento.equals("Egreso"))
        {	
        	tipoM = 1;
        }
        
        if (tipoCuenta.equals("Corriente"))
        {
        	tipoC = 1;
        }
        else if (tipoCuenta.equals("CDT"))
        {
        	tipoC = 2;
        }
        
		int ans = cuenta.metodo3( tipoM, tipoC);
        String respuesta = Integer.toString(ans);
        actualizar( );
        JOptionPane.showMessageDialog( this, "La transacci�n mas costosa es: " + respuesta, "Mayor transacci�n.", JOptionPane.INFORMATION_MESSAGE );
	}
    
    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicaci�n.
     * @param pArgs Par�metros de la ejecuci�n. No son necesarios.
     */
    public static void main( String[] pArgs )
    {
        try
        {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            InterfazSimulador ventana = new InterfazSimulador( );
            ventana.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

}
