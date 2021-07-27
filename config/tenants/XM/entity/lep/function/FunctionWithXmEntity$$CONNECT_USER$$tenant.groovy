import com.icthh.xm.ms.entity.service.LinkService
import com.icthh.xm.ms.entity.domain.Link
import com.icthh.xm.ms.entity.domain.XmEntity
import com.icthh.xm.ms.entity.domain.ext.IdOrKey
import com.icthh.xm.ms.entity.service.XmEntityService


//def tariffConnection = new XmEntity()
//tariffConnection.setKey(UUID.randomUUID().toString())
//tariffConnection.setName("${orderData.salesChannel} ${orderData.sapOrderId}")
//tariffConnection.setData(orderData)
//tariffConnection.typeKey('ORDER')

//entityService.save(order)

IdOrKey idOrKey = lepContext.inArgs.idOrKey
XmEntityService xmEntityService = lepContext.services.xmEntity
XmEntity xmEntity = xmEntityService.findOne(idOrKey)
XmEntity xmUserEntity = xmEntityService.findOne(lepContext.inArgs?.functionInput?.id)


LinkService linkService = lepContext.services.linkService
Link link = new Link()
link.setName('Link to user')
link.setTypeKey('TARIFF-TO-USER')
link.setStartDate(new Date().toInstant())
link.setSource(xmUserEntity)
link.setTarget(xmEntity)
linkService.save(link)

return [:]



