import com.icthh.xm.ms.entity.domain.XmEntity
import com.icthh.xm.ms.entity.service.mail.MailService
import static java.util.Locale.forLanguageTag

import org.slf4j.LoggerFactory


def log = LoggerFactory.getLogger(getClass())

XmEntity xmEntity = lepContext.inArgs.xmEntity
if (xmEntity.typeKey.startsWith("USER")) {

    log.info("###### BEGIN: xmEntity {}", xmEntity)

    MailService mailService = lepContext.services.mailService

    def targetEmail = xmEntity.data.userEmail
    def userFirstName = xmEntity.data.userFirstname
    def userLastName = xmEntity.data.userLastName

//sendEmailFromTemplate(Locale locale,
    //      String templateName,
    //    String subject,
    //   String email,
    //   String from,
    //   Map<String, Object> objectModel)

    if (targetEmail.contains("@")) {

        mailService.sendEmailFromTemplate(forLanguageTag('en'), "userNotif", "XM-User notification test", targetEmail, 'xmtest@xmtest.com',
                   ["firstName": userFirstName, "lastName": userLastName])
    }

    log.info("###### END: xmEntity {}", xmEntity)
}
return lepContext.lep.proceed(xmEntity)
