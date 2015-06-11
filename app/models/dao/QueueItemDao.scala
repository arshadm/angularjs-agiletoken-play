package models.dao

import models.domain.QueueItem
import javax.persistence.{EntityManager, PersistenceContext}
import play.db.jpa.JPA

class QueueItemDao
{
    def save(queueItem: QueueItem) {
        JPA.em().persist(queueItem)
    }

    def findById(id: java.lang.Long) = {
        JPA.em().find(classOf[QueueItem], id)
    }

    def removeById(id: java.lang.Long) {
        val queueItem = findById(id)

        JPA.em().remove(queueItem)
    }
}
