package models.dao

import models.domain.Queue
import javax.persistence.{EntityManager, PersistenceContext}

import scala.collection.JavaConverters._
import play.db.jpa.JPA

class QueueDao
{
    def save(queue: Queue) {
        JPA.em().persist(queue)
    }

    def findById(id: java.lang.Long) = {
        JPA.em().find(classOf[Queue], id)
    }

    def removeById(id: java.lang.Long) {
        val queue = findById(id)

        JPA.em().remove(queue)
    }

    def findAll() : Seq[Queue] = {
        val query = JPA.em().createNamedQuery(Queue.FIND_ALL, classOf[Queue])
        query.getResultList.asScala
    }
}
