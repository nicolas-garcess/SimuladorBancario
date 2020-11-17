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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel con las opciones de la aplicaci�n.
 */
@SuppressWarnings("serial")
public class PanelOpciones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la extensi�n 1.
     */
    private final static String OPCION_1 = "OPCION_1";

    /**
     * Constante para la extensi�n 2.
     */
    private final static String OPCION_2 = "OPCION_2";
    
    private final static String OPCION_3 = "OPCION_3";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal del simulador.
     */
    private InterfazSimulador principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para hacer la extensi�n 1.
     */
    private JButton opcion1;

    /**
     * Bot�n para hacer la extensi�n 2.
     */
    private JButton opcion2;
    
    private JButton opcion3;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo panel e inicializa sus elementos. <br>
     * <b>post: </b> Se inicializ� el panel.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public PanelOpciones( InterfazSimulador pPrincipal )
    {

        // Inicializa los elementos del panel
        principal = pPrincipal;
        opcion1 = new JButton( );
        opcion1.setText( "Acelear" );
        opcion1.setActionCommand( OPCION_1 );
        opcion1.addActionListener( this );

        opcion2 = new JButton( );
        opcion2.setText( "Reiniciar" );
        opcion2.setActionCommand( OPCION_2 );
        opcion2.addActionListener( this );

        opcion3 = new JButton( );
        opcion3.setText( "Reto Final" );
        opcion3.setActionCommand( OPCION_3 );
        opcion3.addActionListener( this );
        
        // Ubica los elementos en el panel
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder("Reto Final" ) ) );
        setLayout( new GridLayout( 1, 3 ) );
        add( opcion1 );
        add( opcion2 );
        add( opcion3 );
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Evento de click sobre un bot�n. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        try
        {
            String command = pEvento.getActionCommand( );
            if( command.equals( OPCION_1 ) )
            {
            	String strValor = JOptionPane.showInputDialog( principal, "Introduzca la cantidad de meses a avanzar", "Avanzar", JOptionPane.QUESTION_MESSAGE );
                principal.reqFuncOpcion1(strValor );
            }
            else if( command.equals( OPCION_2 ) )
            {
                principal.reqFuncOpcion2( );
            }
            else if( command.equals( OPCION_3 ) )
            {
            	principal.reqFuncOpcion3( );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( principal, "Informaci�n inv�lida: intente de nuevo..." );
        }
    

    }

}
