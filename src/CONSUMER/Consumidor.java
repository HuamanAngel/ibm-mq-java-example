package CONSUMER;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.swing.JLabel;
import java.awt.Font;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class Consumidor extends JFrame {

	private JPanel contentPane;
	
	private static final String HOST = "manejando-cdb1.qm.us-south.mq.appdomain.cloud";
	private static final int PORT = 30377; 
	private static final String CHANNEL = "CLOUD.APP.SVRCONN"; 
	private static final String QMGR = "manejando";
	private static final String APP_USER = "appnuevo";
	private static final String APP_PASSWORD = "PxINhgea8sTEdMEn7aiTSO5cYQS8hvCm-dFLXLQE4O7E";
	private static final String QUEUE_NAME = "DEV.QUEUE.1"; 
	public static final String TOPIC_NAME = "dev/";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consumidor frame = new Consumidor();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Consumidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 298);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel Titulo = new JLabel("\u00A1\u00A1 Consigue tus cupones ya mismo !!");
		Titulo.setForeground(new Color(65, 105, 225));
		Titulo.setBackground(new Color(100, 149, 237));
		Titulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		Titulo.setBounds(57, 11, 413, 31);
		contentPane.add(Titulo);
		
		JButton BotonRecibirCupon = new JButton("Recibir cupon");
		
		BotonRecibirCupon.setBounds(285, 150, 128, 23);
		contentPane.add(BotonRecibirCupon);
		
		JLabel Indicador = new JLabel("Da clic aqui para obtener tu cupon");
		Indicador.setForeground(new Color(65, 105, 225));
		Indicador.setFont(new Font("Tahoma", Font.BOLD, 15));
		Indicador.setBounds(219, 94, 273, 31);
		contentPane.add(Indicador);
		
		JTextArea Paneltxt = new JTextArea();
		Paneltxt.setForeground(new Color(70, 130, 180));
		Paneltxt.setFont(new Font("Monospaced", Font.BOLD, 18));
		Paneltxt.setBackground(new Color(224, 255, 255));
		Paneltxt.setEditable(false);
		Paneltxt.setLineWrap(true);
		Paneltxt.setWrapStyleWord(true);
		Paneltxt.setBounds(10, 53, 189, 172);
		contentPane.add(Paneltxt);
		
		JLabel Indicador2 = new JLabel("Puede tardar unos segundos, por favor espere");
		Indicador2.setBounds(214, 125, 278, 14);
		contentPane.add(Indicador2);
		
		BotonRecibirCupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				Paneltxt.setText("Espere unos segundos porfavor");
				
				// Codigo necesario para recibir mensaje
				JMSContext context = null;
				Destination destination = null;
				JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
				JmsConnectionFactory cf = ff.createConnectionFactory();
				

				// Set the properties
				cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, HOST);
				cf.setIntProperty(WMQConstants.WMQ_PORT, PORT);
				cf.setStringProperty(WMQConstants.WMQ_CHANNEL, CHANNEL);

				cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
				cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, QMGR);
				cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "usuario1");
				cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
				cf.setStringProperty(WMQConstants.USERID, APP_USER);
				cf.setStringProperty(WMQConstants.PASSWORD, APP_PASSWORD);
				
				context = cf.createContext();
				destination = context.createQueue("queue:///" + QUEUE_NAME);					

				JMSConsumer consumer;
				consumer = context.createConsumer(destination);
				String  receivedMessage = consumer.receiveBody(String.class,10000);
				
				//System.out.println("\nMensaje recibido:\n" + receivedMessage );
				if(receivedMessage==null){
					Paneltxt.setText("Lo siento, los cupones se encuentran agotados, intente mas tarde");
				}
				else {
					Paneltxt.setText(receivedMessage);
				}
				
				}catch(Exception ex) {
					System.out.println(ex);
				}		
			}
		});
		
	}
}
