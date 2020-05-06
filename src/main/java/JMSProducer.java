import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.*;

public class JMSProducer
{
    private static final TraceOrgLogger _logger = new TraceOrgLogger(JMSProducer.class, "Producer");

    static ObjectMapper mapper;

    public static void main(String[] args)
    {
        try {
// Here connection yaml file is placed within the same folder as the script
            doinit();
            AuthConf authConf = loadConnectionDetails(args[0]);
            MessageSender sender = validateSender(authConf);
            _logger.info("Sender is authenticated");

          /*
            System.out.println(" File Based sender: 1");
            System.out.println(" Console Based sender: 2");
            System.out.println(" Insert option of sender:  ");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 1:*/
                    fileMessageSender(sender, args[1]);
/*                    break;
                case 2:
                    console_based(sender);
                    break;
                default:
                    System.out.println("Wrong option");
            }*/
            System.out.println("Exit system");

        } catch (Exception e)
        {
            _logger.error(e);
            System.out.println("Exception has occurred and can be read from logs");
        }
    }

    private static void doinit() {
         mapper = new ObjectMapper(new YAMLFactory());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
    }

    private static AuthConf loadConnectionDetails(String yamlPath) throws Exception {
        File file = new File(yamlPath);
        return mapper.readValue(file, AuthConf.class);
    }

    private static MessageSender validateSender(AuthConf authConf) throws Exception {
        try {
            return new MessageSender(authConf);
        } catch (Exception e) {
            _logger.info("Credentials are not Matching");
            _logger.error(e);
            throw new Exception(e);
        }
    }

    private static void console_based(MessageSender sender) {
        try {
            System.out.println("Enter stop to quit !! ");
            String end = "";

            Scanner scanner = new Scanner(System.in);

            while (!end.equals("stop")) {
                System.out.println("Please enter message: ");
                end = scanner.next();
                sender.sendMessage(end.trim());
            }
            sender.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fileMessageSender(MessageSender sender, String filepath) {
        try {
            // List<String> content = Files.readAllLines(Paths.get(filepath), StandardCharsets.UTF_8);

            File file = new File(filepath);
            Alarm[] content = mapper.readValue(file, Alarm[].class);

            for (Alarm jmsMessage : content) {
                sender.sendMessage(jmsMessage.toString());
                Thread.sleep(300 * 2);
            }
            sender.destroy();
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }
}