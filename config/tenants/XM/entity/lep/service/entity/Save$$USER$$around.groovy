import com.icthh.xm.ms.entity.domain.XmEntity
import com.icthh.xm.ms.entity.service.mail.MailService
import static java.util.Locale.forLanguageTag

import org.slf4j.LoggerFactory


def log = LoggerFactory.getLogger(getClass())

XmEntity xmEntity = lepContext.inArgs.xmEntity
if (xmEntity.typeKey.startsWith("USER")) {


    MailService mailService = lepContext.services.mailService

    log.info("xmEntity {}", xmEntity)

    def targetEmail = xmEntity.data?.userEmail

//sendEmailFromTemplate(Locale locale,
    //      String templateName,
    //    String subject,
    //   String email,
    //   String from,
    //   Map<String, Object> objectModel)

    if (targetEmail.contains("@")) {

        mailService.sendEmailFromTemplate(forLanguageTag('en'), "userNotif", "XM-User notification test", targetEmail, 'xmtest@xmtest.com',
                   ["user.firstName": xmEntity.data?.userFirstname, "user.lastName": xmEntity.data?.userLastName])
    }
}
return lepContext.lep.proceed(xmEntity)
