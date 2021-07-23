import com.icthh.xm.ms.entity.domain.XmEntity
import com.icthh.xm.ms.entity.domain.ext.IdOrKey
import com.icthh.xm.ms.entity.service.XmEntityService
import com.icthh.xm.ms.entity.domain.XmEntity

//import org.slf4j.LoggerFactory

//def xmEntity = lepContext.inArgs.xmEntity

//def log = LoggerFactory.getLogger(getClass())

XmEntityService entityService = lepContext.services.xmEntity

IdOrKey idOrKey = lepContext.inArgs?.idOrKey

newPrice = lepContext.inArgs?.functionInput?.newPrice

XmEntity myEntity = entityService.findOne(idOrKey)

if (myEntity.typeKey.startsWith("TARIFF")) {




    tariff.data.tariffPrice = newPrice

    entityService.save(tariff)

}

return [:]