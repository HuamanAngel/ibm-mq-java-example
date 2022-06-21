package ibmmq1;

import javax.jms.*;

import com.ibm.msg.client.jms.*;
import com.ibm.msg.client.wmq.*;

public class main {
	
	/*IBM CLOUD*/
	private static final String HOST = "manejando-cdb1.qm.us-south.mq.appdomain.cloud";
	private static final int PORT = 30377; 
	private static final String CHANNEL = "CLOUD.APP.SVRCONN"; 
	private static final String QMGR = "manejando";
	private static final String APP_USER = "appnuevo";
	private static final String APP_PASSWORD = "PxINhgea8sTEdMEn7aiTSO5cYQS8hvCm-dFLXLQE4O7E";
	private static final String QUEUE_NAME = "DEV.QUEUE.1"; 
	public static final String TOPIC_NAME = "dev/";
	
	
	/*Docker*/
	/*
	private static final String HOST = "localhost"; 
	private static final int PORT = 49155; 
	private static final String CHANNEL = "DEV.APP.SVRCONN"; 
	private static final String QMGR = "93ab967dd0d1";
	private static final String APP_USER = "app"; 
	private static final String APP_PASSWORD = "passw0rd"; 
	private static final String QUEUE_NAME = "DEV.QUEUE.1";
	*/									

	public static void main(String args[]) {
		try {
			//producer();
			//consumer();
			//publisher();
			suscriptor();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void producer() {
		try {
			JMSContext context = null;
			Destination destination = null;
			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, HOST);
			cf.setIntProperty(WMQConstants.WMQ_PORT, PORT);
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, CHANNEL);
			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, QMGR);
			cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "app");
			cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
			cf.setStringProperty(WMQConstants.USERID, APP_USER);
			cf.setStringProperty(WMQConstants.PASSWORD, APP_PASSWORD);
			context = cf.createContext();

			destination = context.createQueue("queue:///" + QUEUE_NAME);
			TextMessage message = context.createTextMessage("Otro mensaje");
			JMSProducer producer = null;
			producer = context.createProducer();
			producer.send(destination, message);
			System.out.println("Se produjo el mensaje");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void consumer() {

		try {
			JMSContext context = null;
			Destination destination = null;
			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();

			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, HOST);
			cf.setIntProperty(WMQConstants.WMQ_PORT, PORT);
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, CHANNEL);

			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, QMGR);
			cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "app");
			cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
			cf.setStringProperty(WMQConstants.USERID, APP_USER);
			cf.setStringProperty(WMQConstants.PASSWORD, APP_PASSWORD);

			context = cf.createContext();
			destination = context.createQueue("queue:///" + QUEUE_NAME);

			JMSConsumer consumer;
			consumer = context.createConsumer(destination);
			String receivedMessage = consumer.receiveBody(String.class);
			System.out.println("\nMensaje recibido:\n" + receivedMessage);

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	public static void publisher() {
		try {
			
			JMSContext context = null;
			Destination destination = null;
			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();
		    
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, HOST);
			cf.setIntProperty(WMQConstants.WMQ_PORT, PORT);
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, CHANNEL);

			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, QMGR);
			cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "app");
			cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
			cf.setStringProperty(WMQConstants.USERID, APP_USER);
			cf.setStringProperty(WMQConstants.PASSWORD, APP_PASSWORD);		    
			cf.setStringProperty(WMQConstants.USERID, APP_USER);
			 cf.setStringProperty(WMQConstants.CLIENT_ID, "mi pub name");	
			 context = cf.createContext();
			 destination = context.createTopic("topic://" + TOPIC_NAME);
			 JMSProducer publisher = null;
			 publisher = context.createProducer();
			 publisher.send(destination, "Enviado");
			 System.out.println("enviado");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	public static void suscriptor() {
		try {
			JMSContext context = null;
			Destination destination = null;
			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();
			JMSConsumer subscriber2 = null;
		    
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, HOST);
			cf.setIntProperty(WMQConstants.WMQ_PORT, PORT);
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, CHANNEL);

			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, QMGR);
			cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "app");
			cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
			cf.setStringProperty(WMQConstants.USERID, APP_USER);
			cf.setStringProperty(WMQConstants.PASSWORD, APP_PASSWORD);		    
			cf.setStringProperty(WMQConstants.USERID, APP_USER);
			cf.setStringProperty(WMQConstants.CLIENT_ID, "cliente1");
			context = cf.createContext();
			destination = context.createTopic("topic://" + TOPIC_NAME);
			subscriber2 = context.createConsumer(destination);
			System.out.println("Esperando");
			Message receivedMessage = subscriber2.receive();
			TextMessage textMessage = (TextMessage) receivedMessage;
			System.out.println("Recibido : " + textMessage.getText());
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}	
}
