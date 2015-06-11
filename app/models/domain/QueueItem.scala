package models.domain

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "ag_queue_item")
class QueueItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @BeanProperty
    var id: java.lang.Long = null

    @Version
    @Column(nullable = false)
    @BeanProperty
    var version: java.lang.Integer = null

    @ManyToOne(optional = false)
    @BeanProperty
    var queue : Queue = null

    @Column(length = 64, nullable = false)
    @BeanProperty
    var reference: String = null

    @Column(length = 250, nullable = false)
    @BeanProperty
    var title: String = null

    @Column(length = 250, nullable = false)
    @BeanProperty
    var author: String = null

    @Column(nullable = false)
    @BeanProperty
    var mergedInd: java.lang.Boolean = null
}
