package common.utils;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import common.ExceptionID;
import common.LogicException;
import model.business.user.UserBusinessImpl;

public class SendMailUtils {

	private static final Logger logger = Logger.getLogger(UserBusinessImpl.class);
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void SendMail(String strMailTo, String strMailSubject, String strMailMessage) throws Exception {

		// Create message
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(strMailTo);
		mailMessage.setSubject(strMailSubject);
		mailMessage.setText(strMailMessage);
		// sending mail
		try {
			mailSender.send(mailMessage);
		} catch (MailException ex) {
			Utils utils = new Utils();
			Exception e = utils.getLogicException(ex, ExceptionID.MU0004);
			// thực hiện ghi log.
			logger.error("Gửi mail thất bại", e);

			throw new LogicException(ExceptionID.MU0004);
		}

	}

}
