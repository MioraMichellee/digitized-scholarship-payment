

package service;

import util.EmailSender;

public class NotifierRetardataires {
    private final EmailSender emailSender = new EmailSender();

    public void notifierRetardataires(String mail) {
        String subject = "Notification de retard de paiement";
        String message = "Cher étudiant,\n\nVous avez un retard de paiement de plus de trois semaines. Veuillez régulariser votre situation dans les plus brefs délais.\n\nCordialement,\nAdministration";
        emailSender.sendEmail(mail, subject, message);
    }
}


