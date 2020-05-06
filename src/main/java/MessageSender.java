import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageSender {

    private static final TraceOrgLogger _logger = new TraceOrgLogger(MessageSender.class, "Producer");
   MessageProducer producer;
   Session session;
   Connection con;
   AuthConf authConf;

    public MessageSender(AuthConf authConf) throws Exception {

        this.authConf = authConf;
        //ConnectionFactory factory =  new ActiveMQConnectionFactory("vm://localhost");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(authConf.getBrokerURL().trim());
        connectionFactory.setUserName(authConf.getUserName().trim());
        connectionFactory.setPassword(authConf.getPassword().trim());
        this.con = connectionFactory.createConnection();
        con.start();

        this.session = con.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        Topic prodtopic = session.createTopic(authConf.getTopicName().trim());
        this.producer = session.createProducer(prodtopic);
    }

    public void sendMessage (String message) throws JMSException {
        System.out.println("Sending message: "+ message);
        _logger.info("JMS Message"+message);
        TextMessage textMessage = session.createTextMessage(message);
       this.producer.send(textMessage);
    }

    public void destroy () throws JMSException {
        this.con.close();
    }

}