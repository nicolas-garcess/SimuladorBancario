package uniandes.cupi2.simuladorBancario.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.simuladorBancario.mundo.SimuladorBancario;
import uniandes.cupi2.simuladorBancario.mundo.Transaccion;


public class PanelTransacciones extends JPanel implements ListSelectionListener {

	private InterfazSimulador principal;
	
	private JList transacciones;
	
	private JScrollPane scroll;
	
	private JPanel panelDetalles;
	
	private JLabel etiquetaConsecutivo;
	
	private JLabel etiquetaTransaccion;
	
	private JLabel etiquetaCuenta;
	
	private JLabel etiquetaValor;
	
	private JLabel txtConsecutivo;
	
	private JLabel txtTipoTransaccion;
	
	private JLabel txtTipoCuenta;
	
	private JLabel txtValor;
	
		
	public PanelTransacciones(InterfazSimulador pPrincipal)
	{
		principal = pPrincipal;
			
		panelDetalles = new JPanel();
		panelDetalles.setBorder(new TitledBorder("Detalles"));
		panelDetalles.setLayout( new GridLayout( 4, 1 ) );
		
		etiquetaConsecutivo = new JLabel( "Consecutivo: " );
		panelDetalles.add(etiquetaConsecutivo);
		
		txtConsecutivo = new JLabel();
		panelDetalles.add(txtConsecutivo);
		
		etiquetaTransaccion = new JLabel( "Transaccion: " );
		panelDetalles.add(etiquetaTransaccion);
		
		txtTipoTransaccion = new JLabel();
		panelDetalles.add(txtTipoTransaccion);
		
		etiquetaCuenta = new JLabel( "Cuenta: " );
		panelDetalles.add(etiquetaCuenta);
		
		txtTipoCuenta = new JLabel();
		panelDetalles.add(txtTipoCuenta);
		
		etiquetaValor = new JLabel( "Valor: " );
		panelDetalles.add(etiquetaValor);
				
		txtValor = new JLabel();
		panelDetalles.add(txtValor);
		
		setLayout( new BorderLayout( ) );
		
		transacciones = new JList( );
        transacciones.addListSelectionListener( this );
        scroll = new JScrollPane( );
        setBorder( new TitledBorder( "Historial de transacciones" ) );
        scroll.setViewportView( transacciones );
        scroll.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

		add(scroll, BorderLayout.WEST);
		add(panelDetalles, BorderLayout.CENTER);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
				
		List<Transaccion> valores = transacciones.getSelectedValuesList();
				
		for(Transaccion transaccion: valores)
		{
			String sConsecutivo = String.valueOf(transaccion.getConsecutivo());			
			txtConsecutivo.setText(sConsecutivo);
			
			switch (transaccion.getTipoTransaccion())
			{
				case 0:
					txtTipoTransaccion.setText("ENTRADA");
					break;
				case 1:
					txtTipoTransaccion.setText("SALIDA");
					break;
			}
			
			switch (transaccion.getTipoCuenta())
			{
				case 0:
					txtTipoCuenta.setText("AHORROS");
					break;
				case 1:
					txtTipoCuenta.setText("CORRIENTE");
					break;
				case 2:
					txtTipoCuenta.setText("CDT");
					break;
			}
						
			DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
	        df.applyPattern( "$ ###,###.##" );
	        df.setMinimumFractionDigits( 2 );
	        txtValor.setText( df.format( transaccion.getValor()) );
		}	
	}
	
	public void refrescar( ArrayList<Transaccion> pTransacciones )
    {
		transacciones.setListData( pTransacciones.toArray() );
		
    }
	
	public void eliminar() {
		
		transacciones.setListData(new String[0]);
		txtConsecutivo.setText("");
		txtTipoTransaccion.setText("");
		txtTipoCuenta.setText("");
		txtValor.setText("");
	
	}
	
}
