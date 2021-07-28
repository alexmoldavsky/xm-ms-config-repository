import com.icthh.xm.ms.entity.service.LinkService
import com.icthh.xm.ms.entity.domain.Link
import com.icthh.xm.ms.entity.domain.XmEntity
import com.icthh.xm.ms.entity.domain.ext.IdOrKey
import com.icthh.xm.ms.entity.service.XmEntityService

import java.time.LocalDateTime




IdOrKey idOrKey = lepContext.inArgs.idOrKey
XmEntityService xmEntityService = lepContext.services.xmEntity
XmEntity xmEntity = xmEntityService.findOne(idOrKey)

if (lepContext.inArgs?.functionInput?.userValue) {

    XmEntity xmUserEntity = xmEntityService.findOne(IdOrKey.of(lepContext.inArgs?.functionInput?.userValue))

    if (xmUserEntity) {

        XmEntity connectionEntity = new XmEntity()
        connectionEntity.setKey(UUID.randomUUID().toString())
        connectionEntity.setName("${xmEntity.data.tariffName} ${xmUserEntity.getName()} ${LocalDateTime.now()}")
        connectionEntity.typeKey('CONNECTION')
        xmEntityService.save(connectionEntity)

        CreateLink("Link to user", "CONNECTION-TO-USER", xmUserEntity, connectionEntity)
        CreateLink("Link to tariff", "CONNECTION-TO-TARIFF", xmEntity, connectionEntity)


    }
}

return [:]

def CreateLink(String linkName, String linkTypeKey, XmEntity sourceEntity, XmEntity targetEntity)
{

    LinkService linkService = lepContext.services.linkService

    Link link = new Link()
    link.setName(linkName)
    link.setTypeKey(linkTypeKey)
    link.setStartDate(new Date().toInstant())
    link.setSource(sourceEntity)
    link.setTarget(targetEntity)
    linkService.save(link)

}



