package models.converter

import models.domain.{QueueItem => QueueItemEntity}
import models.schema.QueueItem
import models.dao.{QueueDao, QueueItemDao}

class QueueItemEntityConverter(val queueDao: QueueDao, val queueItemDao: QueueItemDao)
{
    def convert(queueItemEntity: QueueItemEntity): QueueItem =
    {
        val queueItem = new QueueItem

        queueItem.id = queueItemEntity.id
        queueItem.version = queueItemEntity.version
        queueItem.queueId = queueItemEntity.queue.id
        queueItem.reference = queueItemEntity.reference
        queueItem.title = queueItemEntity.title
        queueItem.author = queueItemEntity.author
        queueItem.mergedInd = queueItemEntity.mergedInd

        queueItem
    }

    def convertBack(queueItem: QueueItem): QueueItemEntity =
    {
        val queueEntity = if (queueItem.id != null) queueItemDao.findById(queueItem.id) else new QueueItemEntity

        queueEntity.reference = queueItem.reference
        queueEntity.title = queueItem.title
        queueEntity.author = queueItem.author
        queueEntity.mergedInd = queueItem.mergedInd

        if (queueEntity.queue == null && queueItem.queueId != null)
        {
            queueEntity.queue = queueDao.findById(queueItem.queueId)
        }

        queueEntity
    }    
}
