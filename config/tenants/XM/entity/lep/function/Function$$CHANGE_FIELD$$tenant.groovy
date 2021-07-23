import com.icthh.xm.ms.entity.domain.ext.IdOrKey
import com.icthh.xm.ms.entity.service.XmEntityService
import com.icthh.xm.ms.entity.domain.XmEntity

//import org.slf4j.LoggerFactory

//def xmEntity = lepContext.inArgs.xmEntity

//def log = LoggerFactory.getLogger(getClass())

XmEntityService entityService = lepContext.services.xmEntity

log.info("entityService ${entityService}")

IdOrKey idOrKey = lepContext.inArgs.idOrKey

nprice = lepContext.inArgs?.functionInput?.newPrice

XmEntity myEntity = entityService.findOne(idOrKey)

if (myEntity.typeKey.startsWith("TARIFF")) {

    log.info("ENTITY OK!!!")

    myEntity.data.tariffPrice = nprice

    entityService.save(myEntity)

}

return [:]