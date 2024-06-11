package io.mohkeita.quizz_app.service.mailjet;

public interface IMailJetService {
    void sendSuccessfulEmail(MailDataDto mailDataDto, String confirmationLink, String activationCode);

}
