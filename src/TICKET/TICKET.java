package TICKET;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;
import javax.swing.ImageIcon;

public class TICKET extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	
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
					TICKET frame = new TICKET();
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
	public TICKET() {
		
		setBackground(Color.BLUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 239);
		panel.setBackground(new Color(173, 216, 230));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Cantidad");
		lblNewLabel_6.setForeground(SystemColor.windowBorder);
		lblNewLabel_6.setBackground(new Color(240, 240, 240));
		lblNewLabel_6.setBounds(108, 74, 140, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel = new JLabel("Ticket");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(174, 11, 45, 24);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Llena los campos");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(143, 35, 117, 22);
		lblNewLabel_1.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Departamento");
		lblNewLabel_3.setForeground(SystemColor.windowBorder);
		lblNewLabel_3.setBounds(108, 154, 140, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Porcentaje de descuento");
		lblNewLabel_4.setForeground(SystemColor.windowBorder);
		lblNewLabel_4.setBackground(new Color(205, 133, 63));
		lblNewLabel_4.setBounds(108, 118, 142, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha de vencimiento");
		lblNewLabel_5.setForeground(SystemColor.windowBorder);
		lblNewLabel_5.setBounds(107, 191, 139, 14);
		panel.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(276, 106, 111, 24);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(276, 146, 111, 24);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(276, 183, 111, 24);
		panel.add(textField_3);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(TICKET.class.getResource("/img/number-blocks.png")));
		lblNewLabel_8.setBounds(54, 68, 29, 27);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(TICKET.class.getResource("/img/discount.png")));
		lblNewLabel_9.setBounds(55, 113, 34, 26);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(TICKET.class.getResource("/img/sitemap.png")));
		lblNewLabel_10.setBounds(54, 148, 31, 28);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(TICKET.class.getResource("/img/date.png")));
		lblNewLabel_11.setBounds(51, 192, 36, 24);
		panel.add(lblNewLabel_11);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(275, 71, 111, 24);
		panel.add(textField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 311, 373, -53);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 155, 434, 154);
		panel_2.setBackground(new Color(100, 149, 237));
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("REALIZADO");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setBounds(162, 105, 112, 23);
		panel_2.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String datosCupon = "con un descuento del "+textField_1.getText()+"% "+"en todo "+textField_2.getText()+" con vencimiento el "+textField_3.getText();
					int nroCupon = Integer.parseInt(textField.getText());
					
					while(nroCupon>0) {
							
					int numero = (int) (Math.random() * 500 + 1);
					
					JMSContext context = null;		
					Destination destination = null;
					JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
					JmsConnectionFactory cf = ff.createConnectionFactory();

					cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, HOST);
					cf.setIntProperty(WMQConstants.WMQ_PORT, PORT);
					cf.setStringProperty(WMQConstants.WMQ_CHANNEL, CHANNEL);
					cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
					cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, QMGR);
					cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "nuevo1");
					cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
					cf.setStringProperty(WMQConstants.USERID, APP_USER);
					cf.setStringProperty(WMQConstants.PASSWORD, APP_PASSWORD);
					context = cf.createContext();
							
					destination = context.createQueue("queue:///" + QUEUE_NAME);					
					TextMessage message = context.createTextMessage("Cupon nro: "+numero+" "+datosCupon);		
					JMSProducer producer = null;
					producer = context.createProducer();
					producer.send(destination, message);
					nroCupon=nroCupon-1;
					
					}
				
				}catch(Exception ex) {
					System.out.println(ex);
				}		
			}
		});
		
	}
}
