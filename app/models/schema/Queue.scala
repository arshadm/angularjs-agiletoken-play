package models.schema

import javax.persistence._
import scala.beans.BeanProperty
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "Queue")
class Queue
{
    @BeanProperty
    var id: java.lang.Long = null

    @BeanProperty
    var version: java.lang.Integer = null

    @BeanProperty
    var name: String = null

    @BeanProperty
    var queueItems: java.util.List[QueueItem] = null
}
