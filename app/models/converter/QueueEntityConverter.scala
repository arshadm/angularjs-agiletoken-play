package models.converter

import models.dao.QueueDao
import models.domain.{Queue => QueueEntity}
import models.schema.{QueueItem, Queue}

import scala.collection.JavaConverters._
import java.util

class QueueEntityConverter(val queueDao : QueueDao, val queueItemConverter: QueueItemEntityConverter)
{
    def convert(queueEntity: QueueEntity, deepCopy: Boolean = false): Queue =
    {
        val queue = new Queue
        val queueItems = new util.ArrayList[QueueItem]

        queue.id = queueEntity.id
        queue.version = queueEntity.version
        queue.name = queueEntity.name

        if (deepCopy) {
            for (queueItem <- queueEntity.queueItems.asScala) {
                queueItems.add(queueItemConverter.convert(queueItem))
            }
        }

        queue.queueItems = queueItems

        queue
    }

    def convertBack(queue: Queue): QueueEntity =
    {
        val queueEntity = if (queue.id != null) queueDao.findById(queue.id) else new QueueEntity

        queueEntity.name = queue.name

        queueEntity
    }
}
