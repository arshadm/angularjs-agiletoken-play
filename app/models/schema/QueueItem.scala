package models.schema

import javax.persistence._
import scala.beans.BeanProperty
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "QueueItem")
class QueueItem
{
    @BeanProperty
    var id: java.lang.Long = null

    @BeanProperty
    var version: java.lang.Integer = null

    @BeanProperty
    var queueId: java.lang.Long = null

    @BeanProperty
    var reference: String = null

    @BeanProperty
    var title: String = null

    @BeanProperty
    var author: String = null

    @BeanProperty
    var mergedInd: java.lang.Boolean = null
}
