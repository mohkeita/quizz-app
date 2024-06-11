package io.mohkeita.quizz_app.service.mailjet;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailJetServiceImp implements IMailJetService {

    @Value("${mailjet.apiKey}")
    private String apiKey;
    @Value("${mailjet.apiSecret}")
    private String apiSecret;
    @Value("${mailjet.senderEmail}")
    private String senderEmail;

    public void sendSuccessfulEmail(MailDataDto mailDataDto, String confirmationLink, String activationCode) {
        try {

            ClientOptions options = ClientOptions.builder()
                    .apiKey(apiKey)
                    .apiSecretKey(apiSecret)
                    .build();

            MailjetClient client;
            MailjetRequest request;
            MailjetResponse response;
            client = new MailjetClient(options);
            request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", "mohkeita13@gmail.com")
                                            .put("Name", "Quizz App Team"))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", mailDataDto.getEmail())
                                                    .put("Name", mailDataDto.getName())))
                                    .put(Emailv31.Message.TEMPLATEID, 6037396)
                                    .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                    .put(Emailv31.Message.SUBJECT, "Confirmation mail")
                                    .put(Emailv31.Message.VARIABLES, new JSONObject()
                                            .put("name", mailDataDto.getName())
                                            .put("code_activation", activationCode)
                                            .put("confirmation_link", confirmationLink))));
            response = client.post(request);
            System.out.println(response.getStatus());
            System.out.println(response.getData());
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
