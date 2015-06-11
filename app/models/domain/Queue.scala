package models.domain

import javax.persistence._
import scala.beans.BeanProperty

object Queue
{
    final val FIND_ALL = "Queue.FindAll"
}

@Entity
@Table(name = "ag_queue", uniqueConstraints = Array(new UniqueConstraint(columnNames = Array("name"))))
@NamedQueries(Array(
    new NamedQuery(name = Queue.FIND_ALL, query = "SELECT q FROM Queue q")))
class Queue
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @BeanProperty
    var id: java.lang.Long = null

    @Version
    @Column(nullable = false)
    @BeanProperty
    var version: java.lang.Integer = null

    @Column(length = 64, nullable = false)
    @BeanProperty
    var name: String = null

    @OneToMany(mappedBy = "queue", cascade = Array(CascadeType.ALL), fetch = FetchType.LAZY)
    @BeanProperty
    var queueItems: java.util.List[QueueItem] = null
}
